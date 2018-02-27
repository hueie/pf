package com.polarisfinder.fileupload.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.security.Principal;

import javax.servlet.ServletContext;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.websocket.Session;

import org.apache.commons.lang3.SystemUtils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.polarisfinder.fileupload.entity.Fileupload;
import com.polarisfinder.fileupload.service.FileuploadService;
import com.polarisfinder.user.entity.User;
import com.polarisfinder.user.service.UserService;

public class MyWebSocketHandler extends AbstractWebSocketHandler {
	
	@Autowired
	ServletContext context;

	@Autowired
	private UserService userService;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	//application.properties
	@Value("${polarisfinder.file.upload.dir}")
	private String polarisfinder_FILE_UPLOAD_DIR;
	@Value("${polarisfinder.libs.dir}")
	private String polarisfinder_LIBS_DIR;
	@Value("${polarisfinder.video.server.ip}")
	private String polarisfinder_VIDEO_SERVER_IP;
	@Value("${polarisfinder.video.server.port}")
	private String polarisfinder_VIDEO_SERVER_PORT;
	
	Integer progressPercent, currentChunkLength, totalChunkLength;
    String fullFilePath;
    int userId;

	String fileName;
	BufferedOutputStream bos;
    
	@Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
		System.out.println("Upload File Path : "+polarisfinder_FILE_UPLOAD_DIR);
		
		//Get Message Content
		String msg = message.getPayload();
		System.out.println("Text Message : "+msg);
        if(!msg.equals("end")){ //File Upload Start
            // msg shape is "filename:aaa.jpg"
        	String msginfo = msg.substring(0, msg.indexOf(":"));
        	System.out.println(msginfo);
        	
        	if (msginfo.equals("userId")) {
        		String userEmail = msg.substring(msg.indexOf(":")+1);
        		
        		
         		User user = userService.findUserByEmail(userEmail);
         		userId = user.getUser_id();
                System.out.println(userId+ "userId" + userId);
        		File file = new File(polarisfinder_FILE_UPLOAD_DIR+"/"+userId);
                file.mkdir();
         	} else if(msginfo.equals("fileName")) {
        		fileName = msg.substring(msg.indexOf(":")+1);
        		fullFilePath = polarisfinder_FILE_UPLOAD_DIR+"/"+userId+"/"+fileName;
        		File file = new File(fullFilePath);
                try {
                    bos = new BufferedOutputStream(new FileOutputStream(file));
                } catch (FileNotFoundException e) {
                	System.out.println(e.toString());
                	e.printStackTrace();
                }
        	} else if(msginfo.equals("totalChunkLength")) {
        		totalChunkLength = new Integer(msg.substring(msg.indexOf(":")+1));
        		progressPercent = 0;
        		currentChunkLength = 0;
        		System.out.println(totalChunkLength);
        	}
        }else if(msg.equals("end")){ //File Upload End
        	System.out.println("End!!!");
        	// If msg is "end", Close OutputStream
            try {
            	Fileupload fileupload = new Fileupload();
                fileupload.setFile_nm(fileName);
                fileupload.setFile_path(fullFilePath);
                fileupload.setFileupload_reg_id(userId);
            	boolean flag = fileuploadService.createFileupload(fileupload);
            	
                bos.flush();
                bos.close();
                
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
            
            //TCP Message To Video Process Server
            Socket clientSocket;
            DataOutputStream outToServer;
            //BufferedReader inFromServer;
			try {
				clientSocket = new Socket(polarisfinder_VIDEO_SERVER_IP, new Integer(polarisfinder_VIDEO_SERVER_PORT));

				outToServer = new DataOutputStream(clientSocket.getOutputStream());
	            //outToServer.writeBytes(String.valueOf(userId) + '\n');
	            outToServer.writeBytes(fullFilePath + '\n');
	            
	            //inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	            //String resultmsg = inFromServer.readLine();
	            //System.out.println("FROM SERVER: " + modifiedSentence);
	            clientSocket.close();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            
        }else {
        	System.out.println("Else!!!");
        }
    }
	
	@Override
	public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws IOException  {
		//System.out.println("Start Binary Upload");
        while(message.getPayload().hasRemaining()){
            bos.write(message.getPayload().get());
        }
        currentChunkLength++;
        //reply
        //progressPercent = (currentChunkLength/totalChunkLength);
        
        //Open 443 Port For Transfer data to Browser
        
        session.sendMessage(new TextMessage(currentChunkLength.toString()));
        //System.out.println(progressPercent);
    }
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("WebSocket File Server Open......");
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		System.out.println("error.......");
		exception.printStackTrace();
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("close.......");
	}
}

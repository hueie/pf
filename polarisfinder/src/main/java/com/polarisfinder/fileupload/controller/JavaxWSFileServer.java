package com.polarisfinder.fileupload.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/wsupload")
public class JavaxWSFileServer {

    BufferedOutputStream bos;
    String path = "C:\\Users\\alpha\\git\\polarisfinder\\polarisfinder\\upload";
    
    // 메세지를 받으면 호출된다.
    @OnMessage
    public void onMessage(Session session, String msg) {
        
        // 클라이언트에서 파일이 끝났다는 신호로 "end" 문자열을 보낸다.
        // msg가 end가 아니라면 파일로 연결된 스트림을 연다.
        if(!msg.equals("end")){
            
            // 클라이언트에서 파일을 전송하기전 파일이름을 "file name:aaa.aaa" 형식으로 보낸다.
            String fileName = msg.substring(msg.indexOf(":")+1);
            System.out.println(fileName);
            File file = new File(path + fileName);
            try {
                bos = new BufferedOutputStream(new FileOutputStream(file));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        // msg 가 end가 왔다면 전송이 완료되었음을 알리는 신호이므로 outputstream 을 닫아준다.
        }else{
            try {
                bos.flush();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    // 바이너리 데이터가 오게되면 호출된다.
    @OnMessage
    public void processUpload(ByteBuffer msg, boolean last, Session session) {
        
        while(msg.hasRemaining()){
            try {
                bos.write(msg.get());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @OnOpen
    public void open(Session session) {
        System.out.println("WebSocket File Server Open......");
    }

    @OnError
    public void error(Session session, Throwable t) {
        System.out.println("error.......");
    }

    @OnClose
    public void closedConnection(Session session) {
        System.out.println("close........");
    }
    
} 
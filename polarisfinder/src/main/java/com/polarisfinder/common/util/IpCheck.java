package com.polarisfinder.common.util;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


/**
 * ========================================================
 * 주서비스 : KTDA
 * 프로그램명 : IpCheck.java
 * 설명 : IP  체크 
 * 작성자 : @author Jung mi kyoung
 * 작성일 : 2015. 10. 27.
 * ========================================================
 * 수정자/수정일 :
 * 수정사유/내역 :
 * ========================================================
 */
public class IpCheck {

	
	
	/** 
	 * @Method Name : getIp
	 * @Method Desc : 로컬 IP 가져오기
	 * 
	 * @return
	 * @throws SocketException
	 */
	public static String getIp() throws SocketException{
		String clientIp = null;
		try {
			boolean isLoopBack = true;
			Enumeration<NetworkInterface> en;		
			en = NetworkInterface.getNetworkInterfaces();

 			while(en.hasMoreElements()) {
				NetworkInterface ni = en.nextElement();
				if (ni.isLoopback())
					continue;
				
				Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
				while(inetAddresses.hasMoreElements()) { 
					InetAddress ia = inetAddresses.nextElement();
					if (ia.getHostAddress() != null && ia.getHostAddress().indexOf(".") != -1) {
						clientIp = ia.getHostAddress();
						isLoopBack = false;
						break;
					}
				}
				if (!isLoopBack)
					break;
			}
			System.out.println(" IP  =   " +clientIp);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return clientIp;
	}
	
}

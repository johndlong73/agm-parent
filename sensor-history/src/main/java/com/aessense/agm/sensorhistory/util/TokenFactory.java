package com.aessense.agm.sensorhistory.util;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class TokenFactory {

    public String generateUrlSafeToken(String customerId, long timestamp) throws Exception {
		byte[] digest = this.generateRawToken(customerId, timestamp);		
		byte[] b64Bytes = Base64.getEncoder().encode(digest);
		return URLEncoder.encode(new String(b64Bytes), "UTF-8");
    }

    public byte[] generateRawToken(String customerId, long timestamp) throws Exception {
    	String hashSource = "AGM"+ customerId + timestamp;
    	MessageDigest digester = MessageDigest.getInstance("MD5");
		return digester.digest(hashSource.getBytes());		
    } 
}

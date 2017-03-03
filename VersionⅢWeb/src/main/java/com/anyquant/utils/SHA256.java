package com.anyquant.utils;

import java.security.MessageDigest;

/**
 * Created by Jiayiwu on 16/6/7.
 */
public class SHA256 {
    public static String  encrypt (String word){
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return Byte2String(sha256.digest(word.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String Byte2String(byte[] bytes){
        String result="";
        for(int i = 0;i<bytes.length;i++){
            int temp = bytes[i]&0xff;
            String tempHex = Integer.toHexString(temp);
            if(tempHex.length()<2){
                result+="0"+tempHex;

            }else {
                result+=tempHex;
            }
        }
        return result;
    }
}

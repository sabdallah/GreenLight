/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.models;
import java.lang.*;

/**
 *
 * @author 1670676
 */
public class Encryption {
    
    public static String encryptEmail(String s){
        char[] chars = s.toCharArray();
        String result = "";
        for(char c: chars)
        {
            Character character = new Character(c);
            
            result = result + (int)character.charValue() + "-";
        }
        return result.substring(0, result.length()-1);
    }
    
    public static String decryptEmail(String s){
        String[] codes = s.split("-");
        String result = "";
        for(String code: codes){
            
        result = result + (char)(Integer.parseInt(code));
        }
        return result;
    }
    
}

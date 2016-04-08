/*
 * Converts between confirmation codes and emails. 
 * Confirmation codes are email addresses obfuscated into a string consisting of the numeric char value of each character in the email.
 */
package org.mypackage.models;
import java.lang.*;

/**
 *
 * @author Sam Abdallah
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

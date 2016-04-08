/*
 * A kluge class created so that we can easily pass strings (Like errors and other information) into the JSP pages in Bean form.
 */
package org.mypackage.models;

/**
 *
 * @author Sam Abdallah
 */
public class StringHolder {
    String s1;
    /**
     * Constructs the StringHolder from a given string.
     */
    public StringHolder(String s){
        s1 = s;
    
    }
    
    /**
     * Returns the string.
     */
    public String getString(){
        return s1;
    }
}

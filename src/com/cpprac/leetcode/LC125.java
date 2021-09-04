package com.cpprac.leetcode;

/**
 * @author priyesh.mishra
 */
public class LC125 {

    public static void main(String[] args) {
        isPalindrome("A man, a plan, a canal: Panama");
    }
    public static boolean isPalindrome(String s) {

        for(int i=0, j=s.length()-1;i<j;i++,j--){

            while(i<j && !Character.isLetterOrDigit(s.charAt(i)))
                i++;
            while(i<j && !Character.isLetterOrDigit(s.charAt(j)))
                j--;

            if(i>=j)
                return true;

            if(!String.valueOf(s.charAt(i)).equalsIgnoreCase(String.valueOf(s.charAt(j))))
                return false;

        }
        return true;
    }
}

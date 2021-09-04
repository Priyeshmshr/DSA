package com.cpprac.Hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RansomNote {
    static void checkMagazine(String[] magazine, String[] note) {

        Map<String,Integer> map = new HashMap<String,Integer>();
        for(String s : magazine){
            if(map.containsKey(s))
                map.put(s,map.get(s)+1);
            else
                map.put(s,1);
        }
        for(String s : note){
            if(!(map.containsKey(s) && map.get(s)>0)){
                System.out.println("No");
                return;
            }
            map.put(s,map.get(s)-1);
        }
        System.out.println("Yes");

    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}

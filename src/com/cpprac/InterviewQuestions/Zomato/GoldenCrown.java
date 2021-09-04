package com.cpprac.InterviewQuestions.Zomato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoldenCrown {

    public void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        printQandA("None", "None");
        System.out.println("Number of input messages? ");
        int n = Integer.parseInt(br.readLine());
        System.out.println("Enter your messages to the kingdom..");
        Map<String,String> kingdomAnimalMap = new HashMap<>();
        kingdomAnimalMap.put("LAND", "panda");
        kingdomAnimalMap.put("WATER", "octopus");
        kingdomAnimalMap.put("ICE", "mammoth");
        kingdomAnimalMap.put("AIR", "owl");
        kingdomAnimalMap.put("FIRE", "dragon");

        int res = 0;
        List<String> alliesOfKingShan = new ArrayList<>();
        for(int i=0;i<n;i++){
            String s = br.readLine();
            String kingdomMessage[] = s.split(",");

            String kingdom = kingdomMessage[0];
            String message = kingdomMessage[1];
            message = message.trim();
            int messageCharacterCount[] = new int[26];

            for(int j=0;j<message.length();j++){
                if(Character.isAlphabetic(message.charAt(j))) {
                    char ch = Character.toLowerCase(message.charAt(j));
                    messageCharacterCount[ch - 'a']++;
                }
            }

            String animalName = kingdomAnimalMap.get(kingdom.toUpperCase());
            int flag =1;
            for(char ch : animalName.toCharArray()){
                if(messageCharacterCount[ch-'a']>0)
                    messageCharacterCount[ch-'a']--;
                else{
                    flag = 0;
                    break;
                }
            }
            if(flag==1) {
                res++;
                alliesOfKingShan.add(kingdom);
            }
        }
        if(res>=3){
            printQandA("King Shan", String.join(", ", alliesOfKingShan));
        }else
            printQandA("None","None");
    }

    private void printQandA(String ans1, String ans2) {
        System.out.println("Who is the ruler of Southeros?");
        System.out.println(ans1+"\n");
        System.out.println("Allies of Ruler?");
        System.out.println(ans2+"\n");
    }

    public static void main(String[] args) throws IOException {
        GoldenCrown solver = new GoldenCrown();
        solver.solve();
    }
}
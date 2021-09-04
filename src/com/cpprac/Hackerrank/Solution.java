package com.cpprac.Hackerrank;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class Solution {
    static String twoStrings(String s1, String s2) {
        int a[] = new int[26];

        for (char c : s1.toCharArray()) {
            a[(int) c - '0'] = 1;
        }
        for (char c : s2.toCharArray()) {
            if (a[(int) c - '0'] > 0)
                return "YES";
        }
        return "NO";
    }

    static int sherlockAndAnagrams(String s) {

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(s, i, j + 1);
                char temp[] = sb.toString().toCharArray();
                Arrays.sort(temp);
                String curr = String.valueOf(temp);
                if (!map.containsKey(curr))
                    map.put(curr, 1);
                else
                    map.put(curr, map.get(curr) + 1);
            }
        }
        int res = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            res += (count * (count - 1)) / 2;
        }
        //System.out.println(res);
        return res;
    }

    static long countTriplets(List<Long> arr, long r) {

        Map<Long, Integer> rmap = new HashMap<>();
        Map<Long, Integer> lmap = new LinkedHashMap<>();

        long max = 0;
        for (Long l : arr) {
            if (rmap.containsKey(l)) {
                rmap.put(l, rmap.get(l) + 1);
            } else
                rmap.put(l, 1);
            max = Math.max(max, l);
        }

        long res = 0;
        for (long iter : arr) {
            long c1 = 0, c3 = 0;

            rmap.put(iter, rmap.get(iter) - 1);

            if (iter % r == 0) {
                if (rmap.containsKey(iter * r)) {
                    c3 = rmap.get(iter * r);
                }
                if (lmap.containsKey(iter / r)) {
                    c1 = lmap.get(iter / r);
                }
            }

            if (lmap.containsKey(iter)) {
                lmap.put(iter, lmap.get(iter) + 1);
            } else
                lmap.put(iter, 1);

            res += c1 * c3;
        }
        //System.out.println(res);
        return res;
    }


    static List<Integer> freqQuery(List<List<Integer>> queries) {


        Map<Integer, Integer> elementCount = new TreeMap<>(), countElement = new TreeMap<>();

        List<Integer> res = new LinkedList<>();
        for (List<Integer> query : queries) {

            int type = query.get(0);
            int value = query.get(1);
            if (type == 1) {
                int count = 1;
                if (elementCount.containsKey(value)) {
                    count = elementCount.get(value) + 1;
                    elementCount.put(value, count);
                } else
                    elementCount.put(value, 1);

                int count1 = count - 1;

                //Removing from previous count.
                if (countElement.containsKey(count1)) {
                    int temp = countElement.get(count1);
                    if (temp == 1)
                        countElement.remove(count1);
                    else
                        countElement.put(count1, temp - 1);
                }

                //Adding to current count.
                if (countElement.containsKey(count))
                    countElement.put(count, countElement.get(count) + 1);
                else
                    countElement.put(count, 1);
            } else if (type == 2) {
                if (elementCount.containsKey(value)) {
                    int count1 = elementCount.get(value);

                    if (count1 == 1)
                        elementCount.remove(value);
                    else
                        elementCount.put(value, count1 - 1);


                    //Removing from previous count.
                    int temp = countElement.get(count1);
                    if (temp == 1)
                        countElement.remove(count1);
                    else
                        countElement.put(count1, temp - 1);

                    //Adding to current count.
                    int count = count1 - 1;
                    if (count > 0) {
                        if (countElement.containsKey(count))
                            countElement.put(count, countElement.get(count) + 1);
                        else
                            countElement.put(count, 1);
                    }
                }
            } else {
                if (countElement.containsKey(value)) {
                    res.add(1);
                } else
                    res.add(0);
            }
        }
        return res;
    }


    static int activityNotifications(int[] exp, int d) {

        int ans =  solveActivityNotifications(exp,d);

        //Code for finding median in a running data starts here
        PriorityQueue<Integer> right = new PriorityQueue<>();
        PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
        double median = 0.00d;
        for (int i = 0; i < exp.length; i++) {
            int segment = Integer.compare(left.size(), right.size());
            switch (segment) {
                case -1: //left has less elements than right
                    if (exp[i] < median) {
                        left.add(exp[i]);
                    } else {
                        int tempEle = right.poll();
                        left.add(tempEle);
                        right.add(exp[i]);
                    }
                    median = (left.peek() + right.peek()) / 2.00d;
                    break;
                case 0: //both have same elements
                    if (exp[i] < median) {
                        left.add(exp[i]);
                        median = left.peek();
                    } else {
                        right.add(exp[i]);
                        median = right.peek();
                    }
                    break;
                case 1://right has less elements than left
                    if (exp[i] < median) {
                        int tempEle = left.poll();
                        right.add(tempEle);
                        left.add(exp[i]);
                    } else {
                        right.add(exp[i]);
                    }
                    median = (left.peek() + right.peek()) / 2.00d;
                    break;
            }
//            System.out.println(median);
        }
        //Code for finding median in a running data ends here

        return ans;
    }

    private static int solveActivityNotifications(int[] exp, int d) {

        int count[]= new int[205];
        int ans= 0;
        for(int i=0;i<d;i++){
            count[exp[i]]++;
        }
        for(int i=d;i<exp.length;i++){
            int index = (d+1)/2;
            //1 2 3 4 5 6
            int eleCount = 0;
            int first =-1,second = -1;
            for(int j=0;j<200;j++){
                eleCount+=count[j];
                if(first==-1 && eleCount >= Math.floor((d+1)/2.0d))
                    first=j;
                if(second==-1 && eleCount >= Math.ceil((d+1)/2.0d))
                    second=j;
            }
            double median = (first+second)/2.0d;
            if(median*2.0d<=exp[i])
                ans++;
            count[exp[i-d]]--;
            count[exp[i]]++;
        }
        return ans;
    }


    static String isValid(String s) {

        int vis[] = new int[26];
        for(char ch : s.toCharArray()){
            vis[ch-'a']++;
        }
        boolean flag=false;
        Map<Integer, Integer> mp = new HashMap<>();
        for(int i=0;i<26;i++){
            if(vis[i] == 0)
                continue;
            else{
                if(!mp.containsKey(vis[i])){
                    mp.put(vis[i],1);
                }else{
                    mp.put(vis[i],mp.get(vis[i])+1);
                }
            }
        }
        if(mp.size()>2){
            return "NO";
        }
        if(mp.size()==1)
            return "YES";
        int val=0,valCount=0;
        for(Map.Entry<Integer,Integer> entry : mp.entrySet()){
            if(val==0) {
                val = entry.getKey();
                valCount = entry.getValue();
            }
            else {
                /*if(valCount==1 && val ==1)
                    return "YES";
                else if(entry.getKey()==1 && entry.getValue()==1)
                    return "YES";*/
                if(entry.getValue()==1 && (entry.getKey()-val==1 || entry.getKey()==1)){
                    return "YES";
                }
                else if(valCount==1 && (val-entry.getKey()==1 || val==1)){
                    return "YES";
                }
                // }else if(entry.getKey()<val && valCount>1){
                //     return "NO";
                // }
            }
        }
        return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

package com.cpprac.Hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class HourRank30A {

    public static List<String> solve(List<String> names) {
        // Write your code here

        Map<String,String> map = new HashMap<>();
        Map<String,String> res = new LinkedHashMap<>();

        for(String s : names){

            if(map.containsValue(s)){
                insertNew(res,map,s,s);
                continue;
            }
            for(int i=0;i<s.length();i++){
                StringBuilder sb = new StringBuilder();
                sb.append(s.charAt(i));
                String temp = sb.toString();
                if(!map.containsKey(temp)){
                    map.put(temp,s);
                    res.put(temp,"");
                    break;
                }else{
                    boolean inserted=false;
                    String match = map.get(temp);
                    for(int j=sb.length();j<match.length();j++){
                        sb.append(s.charAt(j));
                        String st = match.substring(0,j+1);
                        if(st.equals(sb.toString())){
                            continue;
                        }else{
                            if(!map.containsKey(sb.toString())){
                                map.put(sb.toString(),s);
                                res.put(sb.toString(),"");
                                inserted = true;
                                break;
                            }else{
                                match = map.get(sb.toString());
                            }
                        }
                    }
                    if(!inserted){
                        insertNew(res, map,match,s);
                        break;
                    }else{
                        break;
                    }
                }
            }
        }

        List<String> prefixes = new ArrayList<>();
        for(Map.Entry<String,String> entry: res.entrySet()){
            prefixes.add(entry.getKey().concat(entry.getValue()));
        }
        return prefixes;
    }

    public static void insertNew(Map<String, String> res,Map<String, String> map,String match,String original){

        if(!original.equals(match) && original.length()>match.length()){
            int l = match.length();
            match = original.substring(0,l+1);
        }
        if(!map.containsValue(match)){
            map.put(match,match);
            res.put(match,"");
        }
        else if(!map.containsKey(match)){
            map.put(match,match+" 2");
            res.put(match," 2");
        }
        else{
            String value[] = map.get(match).split(" ");
            if(value[1] == null)
                value[1] = "2";
            StringBuilder val = new StringBuilder();
            val.append(" ");
            val.append(Integer.parseInt(value[1])+1);
            map.put(match,match.concat(val.toString()));
            res.put(match, val.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        HourRank30A solver = new HourRank30A();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> names = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .collect(toList());

        List<String> res = HourRank30A.solve(names);

        bufferedWriter.write(
                res.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }

    class Scan {
        BufferedReader br;
        StringTokenizer st;

        public Scan() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
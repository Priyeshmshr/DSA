package com.cpprac.codechef.Snackdown19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SNCK1A19A {

    public void solve() {
        Scan sn = new Scan();

        int t= sn.nextInt();
        Map<Character,Integer> map = new HashMap<>();
        map.put('d',1);
        map.put('f',1);
        map.put('j',2);
        map.put('k',2);
        while(t-->0){
            int n = sn.nextInt();
            Map<String, Integer> words = new HashMap<>();
            int res=0;
            while(n-->0){
                String s = sn.nextLine();
                if (words.containsKey(s)) {
                    res+=(words.get(s)/2);
                    continue;
                }

                int wordTime = 2;
                int same = 4,diff = 2;
                for(int i=1;i<s.length();i++) {
                    if(map.get(s.charAt(i)).equals(map.get(s.charAt(i-1)))){
                        wordTime = wordTime +same;
                    }
                    else
                        wordTime = wordTime + diff;
                }
                words.put(s,wordTime);
                    res+=wordTime;
            }
            System.out.println(res);
        }
    }

    public static void main(String[] args) {
        SNCK1A19A solver = new SNCK1A19A();
        solver.solve();
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
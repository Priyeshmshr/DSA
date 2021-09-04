package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class A459 {

    public void solve() {
        Scan sn = new Scan();
        int n = sn.nextInt();
        int m = sn.nextInt();
        Map<String,String> map = new HashMap<>();
        for(int i=0;i<n;i++)
        {
            String name, ip;
            String input[] = sn.nextLine().split(" ");
            name = input[0];
            ip = input[1];
            map.put(ip ,name);
        }
        for(int i=0;i<m;i++)
        {
            String name, ip;
            String input = sn.nextLine();
            System.out.println(input+ " #"+ map.get(input.split(" ")[1].split(";")[0]) );
        }
    }

    public static void main(String[] args) {
        A459 solver = new A459();
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

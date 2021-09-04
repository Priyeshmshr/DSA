package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class A989 {

    public void solve() {
        Scan sn = new Scan();
        String s = sn.nextLine();
        Set<Character> set = new HashSet<>();
        for(int i=1;i<s.length()-1;i++)
        {
            if(s.charAt(i-1)!= '.' && s.charAt(i)!= '.' && s.charAt(i+1)!='.') {
                set.add(s.charAt(i));
                set.add(s.charAt(i-1));
                set.add(s.charAt(i+1));
                if(set.size()==3) {
                    System.out.println("Yes");
                    System.exit(0);
                }
                set.clear();
            }
        }
        System.out.println("No");
    }

    public static void main(String[] args) {
        A989 solver = new A989();
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

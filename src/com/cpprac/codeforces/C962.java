package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C962 {

    public void solve() {
        Scan sn = new Scan();
        long n;
        n = sn.nextLong();
        long temp = (long)Math.sqrt(n);
        for(long i=temp ;i>=1;i--)
        {
            long te = i*i;
            long num = n;
            int count =0;
            String s = String.valueOf(num);
            for(int j=s.length()-1;j>=0;j--){
                long t = te%10;
                if(s.charAt(j)-'0'==t){
                    count++;
                    te = te/10;
                }
                if(te==0)
                    break;
            }
            if(count==String.valueOf(i*i).length()){
                System.out.println(String.valueOf(n).length()-count);
//                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) {
        C962 solver = new C962();
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

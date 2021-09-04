package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1017 {

    public void solve() {
        Scan sn = new Scan();
        int n = sn.nextInt();
        String a = sn.nextLine();
        String b = sn.nextLine();

        int one = 0,zero = 0;
        for(int i=0;i<n;i++){
            if(a.charAt(i)=='0'){
                zero++;
            }
            else
                one++;
        }
        long res= 0;
        for(int i=0;i<n;i++) {
            if(b.charAt(i)=='0'){
                if(a.charAt(i)=='1'){
                    res+=zero;
                    one--;
                }
                else{
                    res+=one;
                    zero--;
                }
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        B1017 solver = new B1017();
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

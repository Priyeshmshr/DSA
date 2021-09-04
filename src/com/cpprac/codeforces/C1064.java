package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class C1064 {

    public void solve() {
        Scan sn = new Scan();

        int n= sn.nextInt();
        String s = sn.nextLine();
        char a[] = s.toCharArray();
        Arrays.sort(a);
        System.out.println(a);
        /*int temp=1;
        long res=0;
        for(int i=1;i<a.length;i++)
        {
            if(a[i]==a[i-1]){
                temp++;
            }else {
                res += (temp * (temp + 1)) / 2;
                temp = 1;
            }
        }
        res += (temp * (temp + 1)) / 2;
        System.out.println(res);*/
    }

    public static void main(String[] args) {
        C1064 solver = new C1064();
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
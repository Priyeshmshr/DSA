package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class A1041 {

    public void solve() {

        Scan sn = new Scan();

        int n = sn.nextInt();
        int arr[]= new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sn.nextInt();
        }
        Arrays.sort(arr);

        int min = arr[0];
        int max = arr[n-1];
        System.out.println((max-min+1)-n);
    }

    public static void main(String[] args) {
        A1041 solver = new A1041();
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
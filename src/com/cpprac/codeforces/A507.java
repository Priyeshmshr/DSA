package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A507 {

    public void solve(Scan sn) {

        int n, a, b;
        n = sn.nextInt();
        a = sn.nextInt();
        b = sn.nextInt();

        int arr[] = new int[n];
        for(int i=0;i<n;i++)
        {
            arr[i] = sn.nextInt();
        }

        long res=0;
        for(int i=0,j=n-1;i<=j;i++,j--){

            if(arr[i]!=2 && arr[j] !=2 && arr[i]!=arr[j]){
                System.out.println(-1);
                return;
            }
            else if(arr[i]==2 && arr[j]==1)
                res+=b;
            else if(arr[i]==2 && arr[j]==0)
                res+=a;
            else if(arr[i]==0 && arr[j]==2)
                res+=a;
            else if(arr[i]==1 && arr[j]==2)
                res+=b;
            else if(arr[i]==2 && arr[j]==2)
                if(i!=j)
                    res+=Math.min(a,b)*2;
                else
                    res+=Math.min(a,b);
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        A507 solver = new A507();
        Scan sn = new Scan();
        solver.solve(sn);
    }

    static class Scan {
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
package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D1058 {

    public long gcd(long a, long b){
        if(b==0)
            return a;
        return gcd(b, a%b);
    }

    public void solve() {
        Scan sn = new Scan();
        long n = sn.nextLong(),m= sn.nextLong(),k = sn.nextLong();
        long a,b;

        if((n*m*2)%k!=0){
            System.out.println("NO");
            return;
        }
        System.out.println("YES");

        long k1 = k;
        if(k1%2==0)
            k=k/2;
        long g1 = gcd (n,k);
        k = k/g1;
        a = n/g1;
        b = m/k;

        if(k1%2!=0){
            if(a<n)
                a = 2*a;
            else
                b= 2*b;
        }
        // a*B = area;
//        System.out.println(a +" , "+ B);
        System.out.println(0 + " " + 0);
        System.out.println(a + " " + 0);
        System.out.println(0 + " " + b);

    }

    public static void main(String[] args) {
        D1058 solver = new D1058();
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
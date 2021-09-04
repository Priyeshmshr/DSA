package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class A1033 {

    public void solve() {
        Scan sn = new Scan();
        int t= sn.nextInt();
        while(t-->0){
            long a = sn.nextLong(), b = sn.nextLong();

            BigInteger side = BigInteger.valueOf(a-b);

            BigInteger A = BigInteger.valueOf(a);
            BigInteger B = BigInteger.valueOf(b);
            BigInteger area1 = A.multiply(side);
            BigInteger area2 = side.multiply(A.subtract(side));

            BigInteger area = area1.add(area2);
            BigInteger bigInteger = new BigInteger(String.valueOf(area));
            System.out.println(bigInteger.isProbablePrime(1)?"YES":"NO");
        }
    }

    public static void main(String[] args) {
        A1033 solver = new A1033();
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
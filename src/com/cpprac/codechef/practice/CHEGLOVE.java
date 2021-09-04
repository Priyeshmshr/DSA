package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CHEGLOVE {

    public void solve() {
        Scan sn = new Scan();

        int t = sn.nextInt();
        while(t-->0){
            int n = sn.nextInt();
            long f[] = new long[100005], g[] = new long[100005];
            for(int i=0;i<n;i++)
                f[i] = sn.nextLong();
            for(int i=0;i<n;i++)
                g[i] = sn.nextLong();

            boolean front = true, back = true;
            for(int i=0;i<n;i++) {
                if(f[i]>g[i]){
                    front = false;
                    break;
                }
            }
            int k=0;
            for(int i=n-1;i>=0;i--) {
                if(f[i]>g[k++]){
                    back = false;
                    break;
                }
            }
            if(front && back)
                System.out.println("both");
            else if(front)
                System.out.println("front");
            else if (back)
                System.out.println("back");
            else
                System.out.println("none");
        }

    }

    public static void main(String[] args) {
        CHEGLOVE solver = new CHEGLOVE();
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

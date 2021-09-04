package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A1058 {

    float area(int x1, int y1, int x2,
                      int y2, int x3, int y3)
    {
        return (float)Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);

        //
        // (x2 * y3)   = n*m*2/k

        //(0,0), (x2,0), (0,y3)
    }

    boolean check(int x1, int y1, int x2, int y2,
                         int x3, int y3, int x4, int y4, int x, int y)
    {

        float A = area(x1, y1, x2, y2, x3, y3)+
                area(x1, y1, x4, y4, x3, y3);

        float A1 = area(x, y, x1, y1, x2, y2);

        float A2 = area(x, y, x2, y2, x3, y3);

        float A3 = area(x, y, x3, y3, x4, y4);

        float A4 = area(x, y, x1, y1, x4, y4);

        return (A == A1 + A2 + A3 + A4);
    }
    public void solve() {
        Scan sn = new Scan();

        int n = sn.nextInt(), d= sn.nextInt();

        int m = sn.nextInt();

        int x1 = 0, y1 = d, x2 = d, y2 = 0, x3 = n,y3 = n-d, x4 =  n-d, y4 = n;
        for(int i=0;i<m;i++){

            int xi = sn.nextInt();
            int yi = sn.nextInt();
            if(check(x1,y1,x2,y2,x3,y3,x4,y4,xi,yi)){
                System.out.println("YES");
            }
            else System.out.println("NO");
        }

    }

    public static void main(String[] args) {
        A1058 solver = new A1058();
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
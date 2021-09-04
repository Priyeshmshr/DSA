package com.cpprac.codechef.Snackdown19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SNCK1A19B {

    public void solve() {
        Scan sn = new Scan();
        int t = sn.nextInt();
        while (t-- > 0) {
            int n = sn.nextInt();

            int a[] = new int[n];

//            Set<Integer> left = new HashSet<>();
            int side = 0, small = Integer.MAX_VALUE;
            
            boolean flag = true;
            
            for (int i = 0; i < a.length; i++) {

                a[i] = sn.nextInt();

                if(!flag)
                    continue;

                if (i > 0) {
                    if (a[i] >= a[i - 1]) {
                        if (side == 0) {
//                            left.add(a[i]);
                            small = Math.min(small, a[i]);
                        } else {
                            if (a[i] > small) {
                                flag = false;
                            }
                        }
                    } else if (side == 1)
                        flag = false;
                    else
                        side = 1;
                } else {
//                    left.add(a[i]);
                    small = Math.min(small, a[i]);
                }

            }

            if(flag)
                System.out.println("YES");
            else
                System.out.println("NO");

        }
    }

    public static void main(String[] args) {
        SNCK1A19B solver = new SNCK1A19B();
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
package com.cpprac.topcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    class Deadfish{
        public  int shortestCode(int N){
            return solve1(0,N,0,0);
        }

        private int solve1(int i,int N,int count,int prev) {
            if(i>N)
                return count;
            if(i<0)
                return count;
            if(i==prev)
                return count;
            if(i!=N) {
                String s = String.valueOf(N);
                char tempArray[] = s.toCharArray();
                Arrays.sort(tempArray);
                String sorted = new String(tempArray);
                int val = Integer.valueOf(sorted);
//                res =  Math.min(Math.min(solve1(i + 1, N), solve1(i - 1, N)), Math.min(solve1(i * i, N), solve1(val,N)));
                solve1(i + 1, N,count+1,i);
                solve1(i - 1, N,count+1,i);
                solve1(i * i, N,count+1,i);
                solve1(val,N,count+1,i);
            }
            return count;
        }
    }
    public void solve() {
        Scan sn = new Scan();
        Deadfish d = new Deadfish();
        System.out.println(d.shortestCode(3));
    }

    public static void main(String[] args) {
        Main solver = new Main();
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

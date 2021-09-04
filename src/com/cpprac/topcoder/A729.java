package com.cpprac.topcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A729 {

    public void solve() {
        Scan sn = new Scan();


    }

    public static void main(String[] args) {
        A729 solver = new A729();
        solver.solve();
    }

    public class BrokenChessboard
    {
        public int minimumFixes(String[] board) {
            int n = board.length;
            int m = board[0].length();

            int prev = 0,mcount=0;
            for(int i=0;i<n;i++)
            {
                int b=prev^1;
                prev=prev^1;
                for(int j=0;j<m;j++)
                {
                    if(board[i].charAt(j)=='B'){
                        if(b==0)
                            mcount++;
                    }
                    else
                    {
                        if(b==1)
                            mcount++;
                    }
                    b=b^1;
                }
            }
            prev = 1;
            int micount=0;
            for(int i=0;i<n;i++)
            {
                int b=prev^1;
                prev=prev^1;
                for(int j=0;j<m;j++)
                {
                    if(board[i].charAt(j)=='B'){
                        if(b==0)
                            micount++;
                    }
                    else
                    {
                        if(b==1)
                            micount++;
                    }
                    b=b^1;
                }
            }
            return Math.max(mcount,micount);
        }
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

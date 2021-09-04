package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LTIME56 {

    int arr[][] = new int[1005][1005];
    int ans[][] = new int[1005][1005];
    int n,m;
    int dx[] = {-1,1,0};
    public void bfs(int i,int j,int val)
    {
        if(val>=0 && ans[i][j]<val)
            ans[i][j] = val;
        else
            return;
        val--;
        for(int k=0;k<3;k++) {
            int te = i+dx[k];
            if(te>=0 && te<n && arr[te][j]>=0)
                bfs(te,j,Math.max(arr[te][j],val));
        }
        for(int k=0;k<3;k++) {
            int te = j + dx[k];
            if (te>=0 && te<m && arr[i][te] >=0)
                bfs(i, te, Math.max(arr[i][te],val));
        }
    }
    public void solve() {
        Scan sn = new Scan();
        int t = sn.nextInt();
        while(t-->0) {
            n = sn.nextInt();
            m = sn.nextInt();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = sn.nextInt();
                    ans[i][j]=0;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] > 0 && ans[i][j]==0)
                        bfs(i, j, arr[i][j]);
                    else if (arr[i][j] == -1)
                        ans[i][j] = -1;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (ans[i][j] == -1)
                        System.out.print("B");
                    else if (ans[i][j] == 1)
                        System.out.print("Y");
                    else
                        System.out.print("N");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        LTIME56 solver = new LTIME56();
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

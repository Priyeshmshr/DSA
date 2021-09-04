package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D1064 {

    int n,m,r,c;
    char a[][] = new char[2005][2005];
    boolean vis[][] = new boolean[2005][2005];
    long ans=1;
    int dx[] = {-1,0,1,0};
    int dy[] = {0 ,1,0,-1};

    void dfs(int sr, int sc, int x,int y){

        for(int i=0;i<4;i++){

            if(dx[i]==0){
                if(dy[i]==-1 && x<=0)
                    continue;
                else if(dy[i]==1 && y<=0)
                    continue;
            }
            int tx = sr+dx[i];
            int ty = sc+dy[i];
            if(tx>=0 && ty>=0 && tx<n && ty<m && !vis[tx][ty] && a[tx][ty]=='.'){
                vis[tx][ty]=true;
                ans++;
                if(dx[i]==0){
                    if(dy[i]==-1)
                        dfs(tx,ty,x-1,y);
                    else if(dy[i]==1)
                        dfs(tx,ty,x,y-1);
                }else
                    dfs(tx ,ty, x, y);
            }
        }
    }
    public void solve() {
        Scan sn = new Scan();
        n=sn.nextInt();m=sn.nextInt();
        r=sn.nextInt();c = sn.nextInt();
        int x,y;
        x = sn.nextInt();y = sn.nextInt();

        for(int i=0;i<n;i++) {

            String s = sn.nextLine();
            a[i] = s.toCharArray();
        }
        vis[--r][--c]=true;
        dfs(r,c,x,y);
        System.out.println(ans);
    }

    public static void main(String[] args) {
        D1064 solver = new D1064();
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
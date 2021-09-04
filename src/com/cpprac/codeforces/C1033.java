package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C1033 {

    int n;
    int a[] = new int[100005];
    public boolean satify(int ai, int aj){
        return a[aj]>a[ai] && Math.abs(aj-ai)%a[ai] ==0 ;
    }

    int dp[] = new int[100005];
    //if both dp[i] = 2, else if even dp[i]=1, odd dp[i]=-1;
    public boolean sol(int i){

        if(a[i]==1)
            return true;
        if(dp[i]!=0)
            return (dp[i] == -1 || dp[i] == 2);

        boolean ans = false;
        for(int j=i+a[i];j<=n;j+=a[i]){
            if(satify(i,j)){
                ans = !sol(j);
                if(ans) {
                    dp[i] = -1;
                    return ans;
                }
            }
        }
        for(int j=i-a[i];j>=1; j = j-a[i]){
            if(satify(i,j)){
                ans = !sol(j);
                if(ans) {
                    dp[i] = -1;
                    return ans;
                }
            }
        }
        dp[i] = ans?-1:1;
        return ans;
    }

    public void solve() {
        Scan sn = new Scan();
        n = sn.nextInt();
        for(int i=1;i<=n;i++)
            a[i]=sn.nextInt();

        if(n==1) {
            System.out.print("B");
            return;
        }
        for(int i=1;i<=n;i++){
            if( a[i] == 1) {
                dp[i] = -1;
                System.out.print("A");
            }
            else{
                if(sol(i))
                    System.out.print("A");
                else
                    System.out.print("B");
            }
        }
    }

    public static void main(String[] args) {
        C1033 solver = new C1033();
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
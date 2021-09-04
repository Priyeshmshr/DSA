package com.cpprac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

    public void solve() {
        Scan sn = new Scan();

        int t;
        t = sn.nextInt();
        int cn=1;
        while(t-->0){

            int n,l;
            n= sn.nextInt();
            l= sn.nextInt();
            String s[] = new String [n];
            Set<String> set= new HashSet<String>();
            for(int i=0;i<n;i++){
                s[i]=sn.nextLine();
                set.add(s[i]);
            }
            String res ="-";
            int flag =0;
            for(int i=0;i<n;i++){
                for(int j=0;j<l;j++){
                    for(int k=0;k<n;k++) {
                        StringBuilder te = new StringBuilder(s[i]);
                        String toReplace = Character.toString(s[k].charAt(j));
                        te.replace(j,j+1,toReplace);
                        if(!set.contains(te.toString())){
                            flag=1;
                            res = te.toString();
                            break;
                        }
                    }
                    if(flag==1)
                        break;
                }
                if(flag==1)
                    break;
            }
            System.out.println("Case #"+ cn++ + ": "+ res);
        }
    }
    public void B(){
        Scan sn = new Scan();
        int t;
        t = sn.nextInt();
        while(t-->0){
            int n;
            n= sn.nextInt();
            int a[] = new int[n];
            for(int i=0;i<n;i++){
                int d;
                int res =-1;
                d = sn.nextInt();
                for(int j=0;j<d;j++){
                    int di = sn.nextInt();
                    if(res==-1 && a[di]==0)
                    {
                        a[di]=1;
                        res=1;
                    }
                }
                System.out.print(res);
            }
        }
    }

    public static void main(String[] args) {
//        NewYearChaos solver = new NewYearChaos();
//        solver.solve5th();
//        solver.B();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date()));
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

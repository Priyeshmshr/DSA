package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B989 {

    public void solve() {
        Scan sn = new Scan();
        int n= sn.nextInt();
        int p = sn.nextInt();
        String s =sn.nextLine();
        char ch[] = new char[s.length()];

        for(int i=0;i<s.length();i++)
            ch[i]='p';

        int count=0;
        for(int i=0;i<s.length()-p;i++){
            if(s.charAt(i)=='.'){
                if(s.charAt(i+p)=='.'){
                    ch[i]='0';
                    ch[i+p]='1';
                }
                else
                {
                    if(s.charAt(i+p)=='0')
                        ch[i]='1';
                    else
                        ch[i]='0';
                }
            }
            else{
                if(s.charAt(i+p)=='.'){
                    if(s.charAt(i)=='0')
                        ch[i+p]='1';
                    else
                        ch[i+p]='0';
                }
                else
                {
                   if(s.charAt(i)==s.charAt(i+p))
                       count++;
                }
            }
        }
        for(int i=0;i<s.length();i++)
        {
            if(ch[i]=='p') {
                if(s.charAt(i)=='.')
                    ch[i] = '0';
                else
                    ch[i]= s.charAt(i);
            }
        }
        if(count==s.length()-p){
            System.out.println("No");
        }else
        System.out.println(ch);
    }

    public static void main(String[] args) {
        B989 solver = new B989();
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

package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C1058 {

    public void solve() {
        Scan sn = new Scan();

        int n= sn.nextInt();

        String s = sn.nextLine();

        long sum=0;
        for(int i=0;i<s.length();i++)
            sum += Integer.valueOf(Character.getNumericValue(s.charAt(i)));

        int div = 2;
        boolean ans = false;
        while(div<=n){
            if(sum%div == 0){
                long divSum= sum/div;
                long tempSum=0;
                boolean flag=true;
                for(int i=0;i<s.length();i++){
                    if(Integer.valueOf(Character.getNumericValue(s.charAt(i)))==0) {
                        continue;
                    }
                    if(tempSum<divSum){
                        tempSum+=Integer.valueOf(Character.getNumericValue(s.charAt(i)));
                    }
                    else if(tempSum==divSum){
                        tempSum = 0;
                        tempSum+=Integer.valueOf(Character.getNumericValue(s.charAt(i)));
                    }else{
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    if(tempSum!=divSum)
                        flag=false;
                }
                if(flag){
                    ans = true;
                    break;
                }
            }
            div++;
        }
        if(ans)
            System.out.println("YES");
        else
            System.out.println("NO");

    }

    public static void main(String[] args) {
        C1058 solver = new C1058();
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
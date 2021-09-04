package com.cpprac.codechef.Snackdown19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class QL19E {

    public static final long mod = 1000000007;
    public void solve() {
        Scan sn = new Scan();
        int t=sn.nextInt();
        while(t-->0){
            int n=sn.nextInt();
            TreeMap<Long,Long> map = new TreeMap<>(Collections.reverseOrder());

            for(int i=0;i<n;i++){
                long temp = sn.nextLong();
                if(map.containsKey(temp))
                    map.put(temp,map.get(temp)+1);
                else
                    map.put(temp,1l);
            }

            long res = 1;
            for(Map.Entry<Long,Long> iter: map.entrySet()){
                long r = iter.getValue();

                long temp =1;
                long no2s = r/2;
                for(long i=r;i>r/2;i--){

                    long temp2 = i;
                    if(temp2%2==0)
                    {
                        while(temp2%2==0 && no2s>0){
                            temp2=temp2/2;
                            no2s--;
                        }

                    }
                    temp = (temp * temp2)%mod;
                }

                res = (res * temp)%mod;
                /*if(r>=2) {
                    long currCount = ((r * (r - 1)) / 2)%mod;
                    res = (res * currCount) % mod;
                }*/
            }
            System.out.println(res%mod);
        }
    }
    public static void main(String[] args) {
        QL19E solver = new QL19E();
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
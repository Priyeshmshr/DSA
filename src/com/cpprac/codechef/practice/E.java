package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class E {

    public void solve() {
        Scan sn = new Scan();

        long n = sn.nextInt();
        long a[] = new long[200005];
        Map<Long,Integer> mp = new HashMap<>();
        long maxi = 0;
        for(int i=0;i<n;i++) {
            a[i]=sn.nextInt();
            mp.put(a[i],i);
            maxi = Math.max(a[i],maxi);
        }
        long start =1;
        int res = 0;
        int prev = -1;
        List<Long> list = new ArrayList<Long>();
        List<Long> resL = new ArrayList<>();
        int count=0;
        while(start<maxi){
            if(mp.containsKey(start)) {
                int ind = mp.get(start);
                if(ind>prev){
                    count++;
                    list.add(start);
                    prev = ind;
                }
            }
            else{
                if(res>count) {
                    resL = list;
                    res = count;
                    prev = -1;
                }
                count=0;
            }
            start++;
        }
        System.out.println(res);
        for(int i=0;i<resL.size();i++) {
            System.out.print(resL.get(i)+" ");
        }
    }

    public static void main(String[] args) {
        E solver = new E();
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

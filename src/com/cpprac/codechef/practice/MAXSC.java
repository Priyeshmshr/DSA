package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class MAXSC {

    public void solve() {
        Scan sn = new Scan();
        int t = sn.nextInt();
        while(t-->0)
        {
            int n = sn.nextInt();
            ArrayList<ArrayList<Long>> a = new  ArrayList<ArrayList<Long>> ();
            for(int i=0;i<n;i++)
            {
                ArrayList<Long> te = new ArrayList<>();
                for(int j=0;j<n;j++)
                {
                    long ai = sn.nextLong();
                    te.add(ai);
                }
                te.sort(Comparator.naturalOrder());
                a.add(te);
            }
            long prevMax = a.get(n-1).get(n-1);
            long res = prevMax;
            boolean flag = true;
            for(int i=n-2;i>=0;i--)
            {
                ArrayList<Long> te = a.get(i);
                if(prevMax<te.get(0))
                {
                    flag = false;
                    break;
                }
                if(prevMax>te.get(n-1))
                {
                    res+=te.get(n-1);
                    prevMax=te.get(n-1);
                    continue;
                }
                long curr = 0;
                for(int j=n-1;j>=0;j--)
                {
                    if(te.get(j)<prevMax) {
                        curr = te.get(0);
                        prevMax = curr;
                        break;
                    }
                }
                if(curr>0)
                    res+=curr;
                else
                {
                    flag = false;
                    break;
                }
                //res += lower_bound(te,prevMax);
            }
            if(flag)
                System.out.println(res);
            else
                System.out.println(-1);
        }
    }

    private long lower_bound(ArrayList<Long> te, long prevMax) {

        int low = 0,high = te.size()-1;

        while(low<high)
        {
            int mid = (low+high)/2;
            if(te.get(mid) <= prevMax)
                low = mid;
            else
                high = mid;
        }
        return te.get(low);
    }

    public static void main(String[] args) {
        MAXSC solver = new MAXSC();
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

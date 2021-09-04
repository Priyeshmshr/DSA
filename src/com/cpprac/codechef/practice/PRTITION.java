package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class PRTITION {

    public void solve() {
        Scan sn = new Scan();
        int t= sn.nextInt();
        while(t-->0)
        {
            long x,n;
            x = sn.nextLong();
            n = sn.nextLong();
            long tot = (n*(n+1))/2;
            if((tot-x)%2 != 0){
                System.out.println("impossible");
                continue;
            }
            long half = (tot-x)/2;
            List<Long> first = new ArrayList<>();
            long sum =0l;
            for(long i=n;i>=1;i--)
            {
                if(i==x)
                    continue;
                if(half>=i) {
                    half = half - i;
                    first.add(i);
                    sum+=i;
                    i=Math.min(half+1,i);
                }
            }
            if(sum!=((tot-x)/2))
            {
                System.out.println("impossible");
                continue;
            }
            Collections.sort(first);
            Queue<Long> queue = new LinkedList<>();
            queue.addAll(first);
            for(long i=1;i<=n;i++)
            {
                if(i==x)
                    System.out.print(2);
                else if(queue.size()>0 && queue.peek() == i) {
                    System.out.print(0);
                    queue.poll();
                }
                else
                    System.out.print(1);
            }
            System.out.println();
        }
    }

    public void solveCF(){
        Scan sn = new Scan();
        long n = sn.nextInt();
        long tot=(n*(n+1))/2;
        long half = tot/2;
        List<Long> first = new ArrayList<>();
        long sum =0;
        for(long i=n;i>=1;i--)
        {
            if(half>=i) {
                half = half - i;
                first.add(i);
                //sum+=i;
                i=Math.min(half+1,i);
            }
        }
        if(tot%2==0)
            System.out.println(0);
        else
            System.out.println(1);
        System.out.print(first.size());
        for(long i : first)
            System.out.print(" "+i);
        return;
    }
    public static void main(String[] args) {
        PRTITION solver = new PRTITION();
        solver.solve();
        //solver.solveCF();
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

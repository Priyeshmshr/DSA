package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 *   4 -15 9 4
 */
public class KCON {

    public void solve() {
        Scan sn = new Scan();
        int t = sn.nextInt();
        while(t-->0)
        {
            int n =sn.nextInt(),k=sn.nextInt();
            long a[] = new long[200005];

            if(n*k<=100000) {
                subTask(n, k,sn,a);
                continue;
            }

            for(int i=0;i<n;i++)
                a[i] = sn.nextLong();
            if(k==1)
            {
                System.out.println(maxSubarray(a,n));
                continue;
            }

            long firstMax=Long.MIN_VALUE,arrSum=0;
            for(int i=0;i<n;i++)
                arrSum+=a[i];
            if(arrSum > 0) {
                long b[] = new long[200005];
                for(int i=0;i<n;i++)
                    b[i] = a[0];
                b[n]=arrSum;
                for (int i = 0; i < n; i++)
                    b[i+n+1] = a[i];
                firstMax = maxSubarray(b,2*n+1);
            }

            for (int i = 0; i < n; i++)
                a[i+n] = a[i];
            long secSum = maxSubarray(a,2*n);

            System.out.println(Math.max(firstMax,secSum));
        }
    }

    private long maxSubarray(long a[],int n)
    {
        long sum=a[0],currSum = a[0];
        for(int i=1;i<n;i++)
        {
            currSum = Math.max(a[i],currSum+a[i]);
            sum = Math.max(sum,currSum);
        }
        return sum;
    }

    private void subTask(int n, int k,Scan sn,long a[]) {

        for(int i=0;i<n;i++)
            a[i] = sn.nextLong();

        int te = 1;
        while(te<k) {
            for (int i = 0; i < n; i++) {
                a[i+(te*n)] = a[i];
            }
            te++;
        }

       /* for(int i=0;i<n*k;i++)
            System.out.print(a[i]+" ");
        System.out.println();*/

        /*long sum=a[0],currSum =a[0];
        for(int i=1;i<n*k;i++)
        {
            currSum = Math.max(a[i],currSum+a[i]);
            sum = Math.max(sum,currSum);
        }*/
        System.out.println(maxSubarray(a,n*k));

    }

    public static void main(String[] args) {
        KCON solver = new KCON();
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

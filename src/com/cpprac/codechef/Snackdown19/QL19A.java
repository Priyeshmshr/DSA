package com.cpprac.codechef.Snackdown19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class QL19A {

    public void solve() {
        Scan sn = new Scan();
        int t = sn.nextInt();
        while(t-->0){
            int n = sn.nextInt(),k= sn.nextInt();
            Integer a[] = new Integer[n];
            for(int i=0;i<n;i++){
                a[i] = sn.nextInt();
            }
            Arrays.sort(a, new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o2-o1;
                }
            });

            int i = k-1;
            for(;i<n-1;i++)
            {
                if(a[i].equals(a[i + 1]))
                    continue;
                else
                    break;
            }
            System.out.println(i+1);
        }
    }

    public static void main(String[] args) {
        QL19A solver = new QL19A();
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
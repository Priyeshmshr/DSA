package com.cpprac.topcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A21 {

    class ArithmeticSequenceDiv1 {

        int findMinCost(int[] x) {
            int d= -100;
            int res = Integer.MAX_VALUE;
            while(d<=100){
                int temp = 0;
                for(int i=1;i<x.length;i++){
                    temp += Math.abs(x[i]-(x[0]+i*d));
                }
//                System.out.println(d+" , "+ temp);
                /*res = Math.min(res,temp);
                temp =0;
                int n=x.length-1;
                for(int i=x.length-2;i>=0;i--){
                    temp += Math.abs(x[i]-(x[n]-(n-i)*d));
                }*/
//                System.out.println(temp);
                res = Math.min(res,temp);
                d++;
            }
            return res;
        }
    }

    /*class MakingRegularGraph{
        public int[] addEdges(int n, int[] x, int[] y){
            Map<Integer,Integer> map  = new HashMap<>();
            int [][]a = new int[n][n];
            int ve[] = new int[n];
            for(int i=0;i<x.length;i++){
                a[x[i]][y[i]]=1;
                ve[x[i]]++;
                ve[y[i]]++;
            }
            ArrayList<Integer> arr = new ArrayList<>();
            for(int i=0;i<n;i++){
                if(ve[i]==0)
                {
                    arr.add(i);
                    arr.add(i);
                }
                else if(ve[i]==1)
                    arr.add(i);
            }
            Collections.sort(arr);

            ArrayList<Integer> res = new ArrayList<>();
            res.add(arr.get(arr.size()-1));
            for(int i=arr.size()-2;i>=0;i--){
                int prev = res
                if(a[i])
            }
        }
    }*/

    public void solve() {
        Scan sn = new Scan();
        int []x = {5, 7,7,7,7,7,7};
        ArithmeticSequenceDiv1 div1 = new ArithmeticSequenceDiv1();
        System.out.println(div1.findMinCost(x));
    }

    public static void main(String[] args) {
        A21 solver = new A21();
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

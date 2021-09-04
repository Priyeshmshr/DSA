package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class A1017 {

    /*class Scores {
        int a,B,c,d;
    }*/
    /*class Pair implements Comparable{
        int id;
        int sum;

        @Override
        public int compareTo(Object p) {
            Pair o = (Pair)p;
            return sum==o.sum?id-o.id:sum-o.sum;
        }
    }*/
   /* class SortBySum implements Comparator<Pair>
    {
        @Override
        public int compare(Pair pair, Pair pair1) {
            if(pair.sum==pair1.sum){
                return pair.id>pair1.id?1:0;
            }
            return pair.sum<pair1.sum?1:0;
        }
    }*/
    public void solve() {
        Scan sn = new Scan();

        int n = sn.nextInt();
        Map<Integer,Integer> pairs = new HashMap<>();

/*
        Pair thomas = new Pair();
*/
        for(int i=1;i<=n;i++){
            int id = i;
            int sum = sn.nextInt() + sn.nextInt()+sn.nextInt()+sn.nextInt();
            pairs.put(id,sum);
        }
        ArrayList<Map.Entry<Integer,Integer>> list = new ArrayList<Map.Entry<Integer,Integer>>(pairs.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                if(a.getValue()==b.getValue()){
                    return a.getKey().compareTo(b.getKey());
                }else
                    return b.getValue().compareTo(a.getValue());
            }
        });

        int counter= 1;
        for(Map.Entry<Integer,Integer> iter : list){
            if(iter.getKey()==1) {
                break;
            }
            else
                counter++;
        }
        System.out.println(counter);
    }

    public static void main(String[] args) {
        A1017 solver = new A1017();
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

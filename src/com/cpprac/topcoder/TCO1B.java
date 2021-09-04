package com.cpprac.topcoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TCO1B {

    class LineOff{
        public  int movesToDo(String points){

            char c = points.charAt(0);
            Stack<Character> s = new Stack<>();
            int count=0;
            s.push(points.charAt(0));
            for(int i=1;i<points.length();i++)
            {
                if(s.isEmpty())
                    s.push(points.charAt(i));
                else if(s.peek()==points.charAt(i)){
                    s.pop();
                    count++;
                }else
                    s.push(points.charAt(i));
            }
            return count;
        }
    }
    class StablePairsDiv1{
        public int[] findMaxStablePairs(int n, int c, int k){

            if(k > n/2){
                return new int[0];
            }
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(n);
            list.add(n-1);

            int i=1,curr = n-1,pos=1;
            while(i<k  && curr>0){
                int yi =  curr-1;
                int xi = (list.get(pos)+list.get(pos-1))-yi -c;
                list.add(yi);
                list.add(xi);
                pos+=2;
                i++;
                curr = xi;
            }
            Collections.reverse(list);
            int arr[] = new int[2*k];
            for(int j=0;j<list.size();j++){
                arr[j]=list.get(j);
            }
            return arr;
        }
    }

    class ThreeSameLetters{
        int mod = 1000000000 + 7;

        private int fact(int n){
            int res =1;
            for(int i=1;i<n;i++){
                res=res*i;
                res= res%mod;
            }
            return res;
        }

        public  int countStrings(int L, int S){

            //(S-1)C(L-3) (S-1)C(L-4)
            if(L<3)
                return 0;
            int te=0,te1=0;
            if(S-1>L-3)
            te = (fact(S-1)/ (fact((S-1)-(L-3))%mod*fact(L-3)%mod) )%mod;
            if(S-1>L-4)
            te1 = (fact(S-1)/ (fact((S-1)-(L-4))%mod*fact(L-4)%mod)) %mod;
            return (S* te *fact(L-2)%mod + S* te1 * fact(L-3)%mod)%mod;

        }
    }
    public void solve() {
        StablePairsDiv1 l = new StablePairsDiv1();
        int arr[] = l.findMaxStablePairs(5,4,2);
        for(int i=0;i<arr.length;i++)
            System.out.println(arr[i]+" ");
    }

    public static void main(String[] args) {
        TCO1B solver = new TCO1B();
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

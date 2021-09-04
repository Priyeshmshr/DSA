package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MINEAT {

    public void solve() {
        Scan sn = new Scan();
        int t = sn.nextInt();
        while (t-- > 0) {
            int n = sn.nextInt();
            long h = sn.nextLong();
            PriorityQueue<Long> pq = new PriorityQueue<>();
            Map<Long, Long> mp = new HashMap<>();
            for (int i = 0; i < n; i++) {
                long l = sn.nextLong();
                if(mp.containsKey(l))
                mp.put(l, mp.get(l)+1);
                else {
                    mp.put(l,1l);
                    pq.add(-1 * l);
                }
            }
            long count = n;
            long res = -1*pq.peek();
            while (pq.size() > 1) {
                long curr = -1 * pq.poll();
                long next = -1 * pq.peek();
                long totCount =( (long)Math.ceil(curr / (next*1.00)) - 1) * mp.get(curr);
                if (h>count && totCount <= h - count) {

                    mp.put(next, mp.get(next) + (curr/next)*mp.get(curr));
                    if (curr % next > 0) {
                        long second = curr % next;
                        if (mp.containsKey(second)) {
                            mp.put(second, mp.get(second) + 1);
                        } else {
                            mp.put(second, 1l);
                            pq.add(-1 * second);
                        }
                        //count++;
                    }
                    mp.remove(curr);
                    //pq.remove(-1 * curr);
                    count += totCount;
                    res = next;
                } else if(h>count){
                    long rem = h - count;
                    long parts = rem/mp.get(curr) + 1;
                    res = (long) Math.ceil(curr / (parts*1.00));
                    count = h;
                    break;
                }else
                    break;
            }
            if(h>count){
                long curr = -1*pq.poll();
                long rem = h - count;
                long parts = rem/mp.get(curr) + 1;
                res = (long) Math.ceil(curr / (parts*1.00));
            }
            System.out.println(res);
        }
    }

    public static void main(String[] args) {
        MINEAT solver = new MINEAT();
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

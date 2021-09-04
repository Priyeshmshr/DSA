package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class ABC{

    Integer i;
    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

}
public class B981 {

    public void solve() {
        Scan sn = new Scan();
        long n = sn.nextLong();
        Map<Long,Long> a=new HashMap();
        Map<Long,Long> b=new HashMap();
        ArrayList<Long> common = new ArrayList();
        for(int i=0;i<n;i++){
            long x,y;
            x = sn.nextLong(); y = sn.nextLong();
            a.put(x,y);
        }
        long m= sn.nextLong();
        for(int i=0;i<m;i++) {
            long x, y;
            x = sn.nextLong();
            y = sn.nextLong();
            b.put(x, y);
            if(a.containsKey(x))
                common.add(x);
        }
        long res = 0;
        for(long iter : common){
            res += Math.max(a.get(iter),b.get(iter));
            a.remove(iter);b.remove(iter);
        }
        for(Map.Entry<Long,Long> iter: a.entrySet()){
            res += iter.getValue();
        }
        for(Map.Entry<Long,Long> iter: b.entrySet()){
            res += iter.getValue();
        }
        System.out.println(res);
    }
    private static void modify(Integer i)
    {
        i = i + 1;
    }
    private static void modify(ABC abc)
    {
        abc.setI(abc.getI()+1);
    }
    private static void modify(String abc)
    {
        abc = abc+"hh";
    }
    public static void main(String[] args) {
        B981 solver = new B981();
        ABC abc = new ABC();
        abc.setI(2000);
        modify(abc.getI());
        System.out.println(abc.getI());
        modify(abc);
        System.out.println(abc.getI());
        String s = "hello";
        modify(s);
        System.out.println(s);
        Integer i =128;
        Integer i2 =128;
        if(i==i2)
            System.out.println("True");

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

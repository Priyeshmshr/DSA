package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class COOK96AA {

    public void solve() {
        Scan sn = new Scan();

        int t = sn.nextInt();

        while(t-->0){

            int n,k;
            n= sn.nextInt();
            k=sn.nextInt();
            if(k<4)
            {
                System.out.println(-1);
                continue;
            }
            long a[] = new long[n];
            Map<Long, Integer> map = new HashMap<>();
            for(int i=0;i<n;i++)
            {
                a[i]= sn.nextLong();
                if(map.containsKey(a[i])){
                    map.put(a[i],map.get(a[i])+1);
                }
                else
                    map.put(a[i],1);
            }

            Map<Long,Integer> dis= new HashMap<>();
            Map<Long,Integer> modifiedMap = new HashMap<>();

            for(Map.Entry<Long,Integer> iter: map.entrySet()){
                dis.put(iter.getKey(),1);
                if(iter.getValue()>1) {
                    modifiedMap.put(iter.getKey(),iter.getValue()-1);
                }
            }
            map = sortByValue(modifiedMap);
            int remaining = k-dis.size();
            if(remaining<=0)
            {
                System.out.println(-1);
            }
            else{
                long l=0,b=0;
                int flag=0;
                for(Map.Entry<Long,Integer> iter: map.entrySet()) {
                    if (iter.getValue() >= remaining) {
                        System.out.println(-1);
                        flag=1;
                        break;
                    }
                    else
                        break;
                }
                if(flag==1)
                    continue;
                map = sortByKey(map);
                int count=dis.size();
                for(Map.Entry<Long,Integer> iter: map.entrySet()){
                    for(int i=0;i<iter.getValue();i++) {
                        if(count<k) {
                            dis.put(iter.getKey(),dis.get(iter.getKey())+1);
                            count++;
                            if(dis.get(iter.getKey())>=4) {
                                l=b=iter.getKey();
                            }
                            else if(dis.get(iter.getKey())>=2 && b!=iter.getKey()){
                                l=b;
                                b=iter.getKey();
                            }
                        }
                        else
                            break;
                    }
                    if(count>=k)
                        break;
                }
                if(l*b==0) {
                    System.out.println(-1);
                }else{
                    System.out.println(l*b);
                }
            }
        }

    }

    private Map<Long,Integer> sortByKey(Map<Long, Integer> map) {
        List<Map.Entry<Long, Integer>> list =
                new LinkedList<Map.Entry<Long, Integer>>(map.entrySet());


        Collections.sort(list, new Comparator<Map.Entry<Long, Integer>>() {
            public int compare(Map.Entry<Long, Integer> o1,
                               Map.Entry<Long, Integer> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        });

        Map<Long, Integer> sortedMap = new LinkedHashMap<Long, Integer>();
        for (Map.Entry<Long, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    private Map<Long, Integer> sortByValue(Map<Long, Integer> unsortMap) {

        List<Map.Entry<Long, Integer>> list =
                new LinkedList<Map.Entry<Long, Integer>>(unsortMap.entrySet());


        Collections.sort(list, new Comparator<Map.Entry<Long, Integer>>() {
            public int compare(Map.Entry<Long, Integer> o1,
                               Map.Entry<Long, Integer> o2) {
                if(o1.getValue() == o2.getValue())
                    return o1.getKey().compareTo(o2.getKey());
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        Map<Long, Integer> sortedMap = new LinkedHashMap<Long, Integer>();
        for (Map.Entry<Long, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static void main(String[] args) {
        COOK96AA solver = new COOK96AA();
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

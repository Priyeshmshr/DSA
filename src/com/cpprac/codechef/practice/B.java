/*
package com.cpprac.codechef;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B {
    Stack<Long> lt;
    int in = 0;
    Long a[] = new Long[105];
    int n;
    void solve5th(Map<Long,Integer> mp){

    */
/*if(lt.size()==0 && in <n){
        lt.push(a[in]);
        mp.erase(a[in]);
        in++;
        solve5th(mp);
    }
    else if(lt.size()==0 && in>=n)
        return ;
    else{*//*

        long i = lt.peek();
        if(a[i]%3==0 && mp.find(a[i]/3)!=mp.end()){
            lt.push(a[i]/3);
            mp.erase(a[i]/3);
            cout<<"Pushed "<<a[i]/3<<endl;
            solve5th(mp);
            if(lt.size()==n){
                return;
            }
            mp[a[i]/3]++;
            cout<<"Poped "<<a[i]/3<<endl;
            lt.pop();
        }
        if(mp.find(a[i]*2)!=mp.end()){
            lt.push(a[i]*2);
            mp.erase(a[i]*2);
            cout<<"Pushed "<<a[i]*2<<endl;
            solve5th(mp);
            if(lt.size()==n){
                return;
            }
            mp[a[i]*2]++;
            cout<<"Poped "<<a[i]*2<<endl;
            lt.pop();
        }
        */
/*while(!lt.empty()){
            ll te = lt.top();
            mp[te]++;
            lt.pop();
        }*//*

        // }
    }
    int main()
    {
        cin>>n;
        map<ll,int> mp;
        for(int i=0;i<n;i++){
            cin>>a[i];
            mp[a[i]]++;
        }

        for(int i=0;i<n;i++){
            lt.push(a[i]);
            mp.erase(a[i]);
            cout<<"Initial Push "<<a[i]<<endl;
            solve5th(mp);
            if(lt.size()==n)
            {
                vector<ll> st;
                while(!lt.empty()){
                    st.push_back(lt.top());
                    lt.pop();
                }
                for(int i=n-1;i>=0;i--){
                    cout<<st[i]<<" ";
                }
                return 0;
            }
            else{
                mp[a[i]]++;
                lt.pop();
            }
        }
        cout<<"-1";
        return 0;
    }

    public void solve5th() {
        Scan sn = new Scan();
        int n = sn.nextInt();
        String s = sn.nextLine();

        Map<String,Integer> mp = new HashMap<>();
        int count=0;
        String res=null;
        for(int i=1;i<s.length();i++)
        {
            StringBuilder sb = new StringBuilder();
            sb.append(s.charAt(i-1)).append(s.charAt(i));
            if(mp.containsKey(sb.toString())){
                int value = mp.get(sb.toString());
                mp.put(sb.toString(),value+1);
                if(count<value+1){
                    count=value+1;
                    res = sb.toString();
                }
            }
            else {
                mp.put(sb.toString(), 1);
                if(count<1){
                    count = 1;
                    res = sb.toString();
                }
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        B solver = new B();
        solver.solve5th();
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
                } catch (IOException E) {
                    E.printStackTrace();
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
            } catch (IOException E) {
                E.printStackTrace();
            }
            return str;
        }
    }
}
*/

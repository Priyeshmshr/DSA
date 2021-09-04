package com.cpprac.codechef.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CHEFHAM {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-->0)
        {
            int n = Integer.parseInt(br.readLine());
            String s[] = br.readLine().split(" ");
            ArrayList<Integer> arr = new ArrayList<Integer>();
            int count[] = new int[100005],a[] = new int[n+1];
            for(int i=0;i<s.length;i++)
            {
                a[i] = Integer.parseInt(s[i]);
            }
            for(int i=0,j=n-1;i<=j;i++,j--) {
                if (i == j) {
                    /*if(Integer.parseInt(s[i])!=Integer.parseInt(s[i-1]))
                    {
                        int te = a[i];
                        a[i] = a[i-1];
                        a[i-1] = te;
                    }
                    else {
                        int te = a[i];
                        a[i] = a[i + 1];
                        a[i + 1] = te;
                    }*/
                    int te = a[i];
                    a[i] = a[n-1];
                    a[n-1] = te;
                    break;
                }
                if (i == j - 1) {
                    if(Integer.parseInt(s[i])!=Integer.parseInt(s[j]))
                    {
                        int pi = i-1,pj = j+1;
                        int te = a[pi];
                        a[pi]=a[i];
                        a[i] = te;

                        te = a[pj];
                        a[pj] = a[j];
                        a[j] = te;
                        break;
                    }
                    else
                    {
                        int te = a[i];
                        a[i] = a[j];
                        a[j] = te;
                    }
                    continue;
                }
                if (Integer.parseInt(s[i])!=Integer.parseInt(s[j])) {
                    int te = a[i];
                    a[i] = a[j];
                    a[j] = te;
                }else{
                    if(i==j-2 && Integer.parseInt(s[i])==Integer.parseInt(s[j-2]))
                    {
                        int te = a[i+1];
                        a[i+1]=a[i];
                        a[i] = te;
                        break;
                    }
                    else{
                        int pi = i + 1, pj = j - 1;
                        int te = a[pi];
                        a[pi] = a[i];
                        a[i] = te;

                        te = a[pj];
                        a[pj] = a[j];
                        a[j] = te;
                        i++;
                        j--;
                    }
                }
            }
            int res =0;
            for(int i=0;i<n;i++)
            {
                if((a[i] != Integer.parseInt(s[i])))
                {
                    res++;
                }
            }
            System.out.println(res);
            for(int i=0;i<n;i++)
            {
                System.out.print(a[i]+" ");
            }
        }
    }
}

package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B895 {

    static long countDivisible(long a,long b,long x)
    {
        if(a%x==0)
            return b/x - a/x +1;
        return b/x-a/x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n,x,k;
        String s[] = br.readLine().split(" ");
        n = Long.parseLong(s[0]);
        x = Long.parseLong(s[1]);
        k = Long.parseLong(s[2]);

        String arr[] = br.readLine().split(" ");
        long a[] = new long[100005];
        for(int i=0;i<n;i++)
            a[i] = Long.parseLong(arr[i]);

        Arrays.sort(a);

    }
}

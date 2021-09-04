package com.cpprac.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B894 {

    public static long mod_pow(long n,long pow, long mod)
    {
        long res = 1;
        n=n%mod;
        while(pow>0)
        {
            if(pow%2==1)
                res=(res*n)%mod;
            pow = pow/2;
            n=(n*n)%mod;
        }
        return res%mod;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n,m,k;
        String s[] = br.readLine().split(" ");
        n = Long.parseLong(s[0]);
        m = Long.parseLong(s[1]);
        k = Long.parseLong(s[2]);

        if(k== -1 && ((n&1)^(m&1)) == 1 )
        {
            System.out.println(0);
        }
        else{
            long res = mod_pow(2L,n-1,1000000007L);
            res = mod_pow(res,m-1,1000000007L);
            System.out.println(res);
        }
    }
}

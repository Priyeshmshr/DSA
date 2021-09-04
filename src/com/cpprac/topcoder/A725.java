package com.cpprac.topcoder;

public class A725 {


    public int findMax(String[] board)
    {
        int res =0;
        for(int i=0;i<board.length;i++)
        {
            int curr=0;
            for(int j=0;j<board[i].length();j++)
            {
                if(board[i].charAt(j)=='R')
                {
                    curr++;
                }
            }
            res= Math.max(curr,res);
        }
        for(int i=0;i<board.length;i++)
        {
            int curr=0;
            for(int j=0;j<board[i].length();j++)
            {
                if(board[j].charAt(i)=='R')
                {
                    curr++;
                }
            }
            res= Math.max(curr,res);
        }
        return res;

    }

    public class OvercookedDiv2{
        public int minStale(int[] start, int[] time)
        {
            //int len = start.length;
            //long size = start[len-1];
            int ans =start[0];
            for(int i=1;i<start.length;i++)
            {
                long te = start[i]-(start[i-1]+time[i-1]-1);
                long re = (long)Math.ceil((te-ans)/((i+1)*1.0));
                if(re<0)
                    continue;
                else
                    ans+=re;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println();
    }
}

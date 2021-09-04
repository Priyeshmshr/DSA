package com.cpprac.topcoder;

public class A724 {

    public static String isPossible(long pairOr, long pairSum) {

         //if(pairSum<pairOr)
           //  return "Impossible";

         //if(pairSum> pairOr*2)
           //  return "Impossible";

         long diff = pairSum-pairOr;

         if((pairOr | diff ) == pairOr)
             return "Possible";
         else
             return "Impossible";


    }
    public String[] solve(String[] board) {

        int colSize = board[0].length();
        int row = board.length;
        int col[] = new int[colSize];
        for (int i = 0; i < colSize; i++) {
            for (int j = 0; j < row; j++) {
                if (board[j].charAt(i) == '#')
                    col[i]++;
            }
        }

        StringBuilder[] temp = new StringBuilder[row];
        String [] res = new String[row];
        int ans[][] = new int[row][colSize];
        for(int i=0;i<colSize;i++)
        {
            temp[i] = new StringBuilder();
            for(int j=row-1;j>=0;j--)
            {
                if(col[i]>0) {
                    ans[j][i] = 1;
                    col[i]--;
                }
                else
                    ans[j][i] =0;
            }
        }
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<colSize;j++)
            {
                if(ans[i][j]==1)
                    temp[i].append('#');
                else
                    temp[i].append('.');
            }
            res[i] = temp[i].toString();
        }
        return res;
    }
    public static void main(String[] args) {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(isPossible(11L,  7L));
    }
}

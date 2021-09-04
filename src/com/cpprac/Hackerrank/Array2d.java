package com.cpprac.Hackerrank;

/**
 * @author priyesh.mishra
 */

public class Array2d {

    static int hourglassSum(int[][] arr) {

        int n= arr.length;
        int m= arr[0].length;
        int sum=0;
        for(int i=0;i<n-2;i++){
            int currSum=0;
            for(int j=0;j<m-2;j++){
                for(int k=0;k<3;k++){
                    currSum+= arr[i][j+k];
                }
                currSum+=arr[i+1][j+1];
                for(int k=0;k<3;k++){
                    currSum+= arr[i+2][j+k];
                }
            }
            sum=Math.max(currSum,sum);
        }
        return sum;
    }
}

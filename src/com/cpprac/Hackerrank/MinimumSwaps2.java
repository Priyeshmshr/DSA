package com.cpprac.Hackerrank;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MinimumSwaps2 {


    static int minimumSwaps(int[] arr) {

        int vis[] = new int[arr.length];

        ArrayList<ArrayList<Integer>> cycles = new ArrayList<>();

        int sortedArr[] = arr.clone();
        Arrays.sort(sortedArr);
        Map<Integer,Integer> correctIndex = new HashMap<>();
        for(int i=0;i<sortedArr.length;i++){
            correctIndex.put(sortedArr[i],i);
        }

        for(int i=0;i<arr.length;i++) {

            if(i!=correctIndex.get(arr[i])) {
                if (vis[i] == 0) {
                    ArrayList<Integer> cycle = new ArrayList<>();
                    int j=i;
                    while (j!=correctIndex.get(arr[j]) && vis[j]==0) {
                        cycle.add(j);
                        vis[j] = 1;
                        j = correctIndex.get(arr[j]);
                    }
                    cycles.add(cycle);
                }
            }else
                vis[i]=1;
        }
        int res=0;
        for(ArrayList<Integer> iter: cycles) {
            res+=iter.size()-1;
        }
        return res;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

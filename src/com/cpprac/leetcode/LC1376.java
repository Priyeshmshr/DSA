package com.cpprac.leetcode;

import java.util.ArrayList;

/**
 * @author priyesh.mishra
 */
public class LC1376 {
    ArrayList<Integer>[] graph;
    public int numOfMinutes(int n, int headId, int[] manager, int[] informTime) {
        graph = new ArrayList[n];
        for(int i =0;i<n;i++){
            graph[i]= new ArrayList<Integer>();
        }
        for(int i =0;i<n;i++){
            if(manager[i]>=0){
                graph[manager[i]].add(i);
            }
        }
        return findTimeToInform(headId,informTime)+informTime[headId];
    }
    public int findTimeToInform(int headId, int[] informTime){
        int curr = 0;
        for(int i=0;i<graph[headId].size();i++){
            curr = Math.max(curr, findTimeToInform(graph[headId].get(i),informTime));
        }
        return curr;
    }
}

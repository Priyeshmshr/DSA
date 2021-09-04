package com.cpprac;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author priyesh.mishra
 */
public class AlgorithmUtils{

    int vis[] = new int[100];
    //basic structure
    public void dfs(HashMap<Integer, ArrayList<Integer>> graph, int currNode){

        if(vis[currNode]==1){
            return;
        }

        if(graph.containsKey(currNode)) {
            for (int i : graph.get(currNode)) {
                dfs(graph, i);
            }
        }
    }
}

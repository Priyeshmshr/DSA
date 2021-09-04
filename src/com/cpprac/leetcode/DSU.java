package com.cpprac.leetcode;

/**
 * @author priyesh.mishra
 */
public class DSU {

    public int[] parent;
    int rank[];
    int connectedComponents;

    public DSU(int size){
        parent = new int[size];
        for(int i=0;i<size;i++)
            parent[i]=i;
        rank = new int[size];
        connectedComponents = size;
    }

    public int find(int x){

        if(parent[x]!=x)
            parent[x]=find(parent[x]);

        return parent[x];
    }

    public boolean union(int x, int y){
        int parentOfX = find(x), parentOfY= find(y);
        if(parentOfX==parentOfY)
            return false;
        else if(rank[parentOfX]<rank[parentOfY]){
            parent[parentOfX] = parentOfY;
        }
        else if(rank[parentOfX]>rank[parentOfY]){
            parent[parentOfY] = parentOfX;
        }else {
            parent[parentOfY] = parentOfX;
            rank[parentOfX]++;
        }
        connectedComponents--;
        return true;
    }
}

package com.cpprac.Algorithms;

import java.util.ArrayList;

/**
 * @author priyesh.mishra
 */
public class LcaBySqrt {
    int level[];
    int parent[];
    int sqrtParent[];
    int vis[];
    ArrayList<Integer> graph[];
    int size;
    int dp[][];

    public LcaBySqrt(int size) {
        this.size = size;
        level = new int[size + 1];
        parent = new int[size + 1];
        sqrtParent = new int[size + 1];
        graph = new ArrayList[size + 1];
        for (int i = 0; i <= size; i++) {
            graph[i] = new ArrayList<>();
        }
    }

    public void addEdge(int a, int b){
        graph[a].add(b);
        graph[b].add(a);
    }

    public void findParent() {
        vis = new int[size + 1];
        dfs(1, 1, 0);
    }

    public void decompose() {
        vis = new int[size + 1];
        sqrtDecompose(1, (int) (Math.sqrt(size)));
    }

    public int findLca(int x, int y) {
        while (sqrtParent[x] != sqrtParent[y]) {
            if ( level[x] > level[y] ) {
                x = sqrtParent[x];
            } else
                y = sqrtParent[y];
        }
        while (x != y) {
            if ( level[x] > level[y] )
                x = parent[x];
            else
                y = parent[y];
        }
        return x;
    }

    private void sqrtDecompose(int source, int sq) {
        vis[source] = 1;
        if ( level[source] < sq ) {
            sqrtParent[source] = 1;
        } else if ( level[source] % sq == 0 ) {
            sqrtParent[source] = parent[source];
        } else {
            sqrtParent[source] = sqrtParent[parent[source]];
        }
        for (Integer neighbours : graph[source]) {
            if ( vis[neighbours] == 0 )
                sqrtDecompose(neighbours, sq);
        }
    }

    private void dfs(int source, int par, int lev) {
        vis[source] = 1;
        parent[source] = par;
        level[source] = lev;
        for (Integer neighbours : graph[source]) {
            if ( vis[neighbours] == 0 )
                dfs(neighbours, source, lev + 1);
        }
    }

    private void preProcessDp() {
        int i, j;
        for (i = 0; i < size; i++)
            for (j = 0; 1 << j < size; j++)
                dp[i][j] = -1;

        for (i = 0; i < size; i++)
            dp[i][0] = parent[i];

        for (j = 1; 1 << j < size; j++)
            for (i = 0; i < size; i++)
                if ( dp[i][j - 1] != -1 )
                    dp[i][j] = dp[dp[i][j - 1]][j - 1];
    }

    public int query(int p, int q) {
        int tmp, log, i;
        if ( level[p] < level[q] ) {
            tmp = p;
            p = q;
            q = tmp;
        }
        for (log = 1; 1 << log <= level[p]; log++) ;
        log--;

        for (i = log; i >= 0; i--)
            if ( level[p] - (1 << i) >= level[q] )
                p = dp[p][i];

        if ( p == q )
            return p;

        for (i = log; i >= 0; i--) {
            if ( dp[p][i] != -1 && dp[p][i] != dp[q][i] ) {
                p = dp[p][i];
                q = dp[q][i];
            }
        }

        return parent[p];
    }
}

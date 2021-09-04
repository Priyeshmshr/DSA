package com.cpprac.Algorithms;

import java.util.ArrayList;

/**
 * @author priyesh.mishra
 */
public class LcaBinaryLifting {
    int dp[][];
    int level[];
    int parent[];
    int n ; //template, fill this in main code.
    ArrayList<Integer>[] graph; // template, fill this in main code
    LcaBinaryLifting(int n) {
        dp = new int[n + 1][50];
        level = new int[n + 1];
        parent = new int[n + 1];
    }

    private void dfs(int source, int par, int lev) {
        parent[source] = par;
        level[source] = lev;
        for (Integer neighbours : graph[source]) {
            if ( neighbours != par )
                dfs(neighbours, source, lev + 1);
        }
    }

    private void preProcessDp() {
        int i, j;
        for (i = 0; i < n; i++)
            for (j = 0; 1 << j < n; j++)
                dp[i][j] = -1;

        for (i = 0; i < n; i++)
            dp[i][0] = parent[i];

        for (j = 1; 1 << j < n; j++)
            for (i = 0; i < n; i++)
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

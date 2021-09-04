package com.cpprac.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author priyesh.mishra
 */
public class GooglePrep {

    public static void main(String[] args) {
        GooglePrep googlePrep = new GooglePrep();
//        System.out.println(googlePrep.numDistinct("rabbb","rabbi"));
        System.out.println(googlePrep.rhymePoems(2));
        System.out.println(googlePrep.rhymePoems(3));
    }

    public int numDecodings(String s) {
        if ( s == null || s.charAt(0) == '0' ) return 0;

        int dp[] = new int[s.length() + 5];

        for (int i = s.length() - 1; i >= 0; i--) {
            int oneDigit = s.charAt(i)-'0';
            if ( oneDigit >= 1 && oneDigit <= 9 ) {
                dp[i] = dp[i + 1] + (i == s.length() - 1 ? 1 : 0);
            }
            if ( s.charAt(i) != '0' ) {
                int num = (s.charAt(i)-'0');
                if ( num >= 1 && num <= 9 ) {
                    dp[i] = dp[i + 1] + (i == s.length() - 1 ? 1 : 0);
                }
                if ( i < s.length() - 1 ) {
                    num = num * 10 + (s.charAt(i+1)-'0');
                    if ( num >= 10 && num <= 26 ) {
                        dp[i] += dp[i + 2] + (i == s.length() - 2 ? 1 : 0);
                    }
                }
            } else {
                dp[i] = 0;
            }
        }
        return dp[0];
    }

    public int numDistinct(String s, String t) {

        // s = rabbbit
        // t = rabbit
        // two option on each character
        // 1) include the character.(only if it matches)
        // 2) exclude the character.(even if it matches)
        // ans[i] =  solve(s[i-1],t[i-1]) + solve(s[i-1],t[i]);
        // Complexity = 2^n Why? 2 options on each char. so 2*2*2..(n times)

        return solve(s, s.length()-1,  t, t.length()-1);

    }

    private int solve(String s, int i, String t, int j){

        if (j==-1) return 1;
        if (i==-1) return 0;

        int include = s.charAt(i) == t.charAt(j) ? solve(s,i-1,t,j-1) : 0;
        int exclude = solve(s,i-1,t,j);

        return include + exclude;
    }

    public List<String> rhymePoemsShort(int N){

        ArrayList<String> poems = new ArrayList<>();
        poems.add("A");

        for(int i=2;i<=N;i++){ // 26

            ArrayList<String> newPoems = new ArrayList<>();

            for(String previousPoem : poems){
                for(int j = 0;j<=previousPoem.length();j++){ // A-> Z // 26
                    char newLine = (char)('A'+j);
                    newPoems.add(newLine + previousPoem);
                }
            }
            poems = newPoems;
        }
        return poems;
    }

    public List<String> rhymePoems(int N){

        ArrayList<String> poems = new ArrayList<>();
        poems.add("A");

        for(int i=2;i<=N;i++){ // 26

            ArrayList<String> temporaryPeoms = new ArrayList<>();

            for(String previousPoem : poems){  //

                Set<Character> distinctCharInPoem = new HashSet<>();
                for(char lineOfPoem : previousPoem.toCharArray()){
                    distinctCharInPoem.add(lineOfPoem);
                }
                for(int j = 0;j<26;j++){ // A-> Z
                    char newLine = (char)('A'+j);
                    if(distinctCharInPoem.contains(newLine) ){
                        temporaryPeoms.add(newLine+previousPoem);
                    }
                    else{
                        temporaryPeoms.add(newLine+previousPoem);
                        break;
                    }
                }
            }
            poems = temporaryPeoms;
        }
        return poems;
    }


    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // 1) Create a graph from the edges.
        // 2) Create a leaves to hold the leaf nodes.
        // 3) Pop the leaves and reduce the of the connected nodes.
        // 4) Delete the edges to the connected nodes from the leaf nodes.
        // 5)
        if(n==0) return new ArrayList<>();
        if(n<2){
            ArrayList<Integer> list = new ArrayList<>();
            for(int i =0;i<n;i++){
                list.add(i);
            }
            return list;
        }

        ArrayList<Set<Integer>> tree = new ArrayList<>();

        for(int i=0;i<n;i++){
            tree.add(new HashSet<>());
        }

        for(int edge[]: edges){
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        ArrayList<Integer> leaves = new ArrayList<>();
        for(int i=0;i<n;i++){
            if(tree.get(i).size()==1){
                leaves.add(i);
            }
        }
        int remainingNodes = n;

        while(remainingNodes>2){

            remainingNodes -= leaves.size();
            ArrayList<Integer> newQueue = new ArrayList<>();
            for (int leaf : leaves) {
                int neighbour = tree.get(leaf).iterator().next();
                if(tree.get(neighbour).size()==1){
                    newQueue.add(neighbour);
                }
                tree.get(neighbour).remove(leaf);
            }
            leaves = newQueue;
        }
        return leaves;
    }

}

package com.cpprac.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author priyesh.mishra
 */
public class Sept30DayChallenge {


    public static void main(String[] args) {
        Sept30DayChallenge ch = new Sept30DayChallenge();
        ch.solve();
    }

    public boolean carPooling(int[][] trips, int capacity) {
        ArrayList<int[]> list = new ArrayList<>();
        int n = trips.length;
        for (int i = 0; i < n; i++) {
            list.add(new int[]{trips[i][1], trips[i][0]});
            list.add(new int[]{trips[i][2], -1 * trips[i][0]});
        }
        list.sort((ints, t1) -> {
            if ( ints[0] == t1[0] ) {
                return Integer.compare(ints[1], t1[1]);
            }
            return Integer.compare(ints[0], t1[0]);
        });
        int currPassengers = 0;
        for (int i = 0; i < list.size(); i++) {
            currPassengers += list.get(i)[1];
            if ( currPassengers > capacity ) return false;
        }
        return true;
    }


    private void solve() {
//        System.out.println(uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}}));
//        System.out.println(carPooling(new int[][]{{4, 5, 6}, {6, 4, 7}, {4, 3, 5}, {2, 3, 5}}, 13));
//        System.out.println(carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4));
        System.out.println(removeDuplicateLetters("ccacbaba"));
    }

    public String removeDuplicateLetters(String s) {
        if ( s == null || s.length() == 1 ) return s;

        HashMap<Character, Integer> map = new HashMap();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), i);
            set.add(s.charAt(i));
        }

        List<Map.Entry<Character, Integer>> list = map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).collect(Collectors.toList());

        int st = 0;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> iter : list) {
            if ( !set.contains(iter.getKey()) ) {
                st = iter.getValue() + 1;
                continue;
            }
            int end = iter.getValue();
            char min = iter.getKey();
            for (int i = st; i < end; i++) {
                if ( min > s.charAt(i) ) {
                    min = s.charAt(i);
                }
            }
            st = end + 1;
            if ( set.contains(min) ) {
                sb.append(min);
                set.remove(min);
            }
            if (set.contains(iter.getKey()) && min != iter.getKey() ) {
                sb.append(iter.getKey());
                set.remove(iter.getKey());
            }
        }
        return sb.toString();
    }

    public String largestNumber(int[] nums) {

        if ( nums.length == 0 ) return "0";

        Integer[] ar = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            ar[i] = nums[i];
        }

        Arrays.sort(ar, (t1, t2) -> Integer.compare(Integer.valueOf(String.valueOf(t2).concat(String.valueOf(t1))
        ), Integer.valueOf(String.valueOf(t1).concat(String.valueOf(t2)))));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(ar[i]);
        }
        return sb.toString();
    }

    int vis[][];
    int gridG[][];
    int totalZeros = 0, n, m;

    public int uniquePathsIII(int[][] grid) {
        n = grid.length;
        if ( n == 0 ) return 0;
        m = grid[0].length;
        vis = new int[n + 1][m + 1];
        gridG = grid;
        int sti = 0, stj = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ( gridG[i][j] == 0 ) totalZeros++;
                if ( gridG[i][j] == 1 ) {
                    sti = i;
                    stj = j;
                }
            }
        }
        dfs(sti, stj, 0);
        return ans;
    }

    int ans = 0;

    private void dfs(int i, int j, int zerosCount) {
        if ( i < 0 || i >= n || j < 0 || j >= m ) return;
        if ( gridG[i][j] == 2 ) {
            if ( zerosCount == totalZeros ) {
                ans++;
            }
            return;
        }
        if ( gridG[i][j] == -1 ) return;
        if ( vis[i][j] == 1 ) return;

        vis[i][j] = 1;
        int curZeros = zerosCount + (gridG[i][j] == 0 ? 1 : 0);
        dfs(i + 1, j, curZeros);
        dfs(i, j + 1, curZeros);
        dfs(i - 1, j, curZeros);
        dfs(i, j - 1, curZeros);
        vis[i][j] = 0;
    }

    public int removeCoveredIntervals(int[][] intervals) {
        int n = intervals.length;
        if ( n == 1 ) return 1;

        Arrays.sort(intervals, (t1, t2) -> {
            if ( t1[0] == t2[0] ) return Integer.compare(t2[1], t1[1]);
            return Integer.compare(t1[0], t2[0]);
        });
        int count = 0;
        int maxEnding = intervals[0][1];
        for (int i = 1; i < n; i++) {
//            int j = i + 1;
            if ( intervals[i][1] <= maxEnding ) {
                count++;
//                j++;
            }
//            i = j - 1;
            maxEnding = Math.max(maxEnding, intervals[i][1]);
        }
        return n - count;
    }
}

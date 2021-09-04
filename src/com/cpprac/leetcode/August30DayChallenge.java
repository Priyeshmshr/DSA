package com.cpprac.leetcode;

import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class August30DayChallenge {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
//        System.out.println(orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));
//        System.out.println(orangesRotting(new int[][]{{0, 2}, {0, 1}, {0, 1}, {1, 1}, {1, 1}, {1, 1}}));
//        System.out.println(getRow(33));
//        CombinationIterator ci = new CombinationIterator("a", 1);
//        System.out.println(ci.next());
//        ci.hasNext();
//        System.out.println(ci.next());
//        ci.hasNext();
//        System.out.println(ci.next());
//        ci.hasNext();
//        longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth");

//        System.out.println(Arrays.toString(distributeCandies(7, 4)));
//        System.out.println(Arrays.toString(distributeCandies(1000000000, 10)));
//        System.out.println(numsSameConsecDiff(3, 0));
//        System.out.println(mincostTickets(new int[]{1, 4, 6, 7, 8, 20}, new int[]{7, 2, 15}));
//        System.out.println(mincostTickets(new int[]{2, 3, 5, 6, 7, 8, 9, 10, 11, 17, 18, 19, 23, 26, 27, 29, 31, 32, 33, 34, 35, 36, 38, 39, 40, 41, 42, 43, 44, 45, 47, 51, 54, 55, 57, 58, 64, 65, 67, 68, 72, 73, 74, 75, 77, 78, 81, 86, 87, 88, 89, 91, 93, 94, 95, 96, 98, 99}, new int[]{5, 24, 85}));
//        System.out.println(mincostTickets(new int[]{3, 4, 7, 8, 9, 11, 12, 18, 19, 20, 24, 27}, new int[]{2, 12, 52}));
//        System.out.println(findRightInterval(new int[][]{{4, 5}, {2, 3}, {1, 2}}));
//        System.out.println(containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9},2 ,3 ));
//        System.out.println(findLengthOfShortestSubarray(new int[]{16,10,0,3,22,1,14,7,1,12,15}));
        System.out.println(insert(new int[][]{{1,3},{6,9}},new int[]{2,5}));
    }

//    public String getHint(String secret, String guess) {
//
//    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if(n==0){
            intervals[0] = newInterval;
            return intervals;
        }
        ArrayList<int[]> res = new ArrayList<>();
        boolean found = false;
        for ( int i = 0 ; i < n ; i++ ) {
            if ( !found && intervals[i][1] >= newInterval[0] ) {
                int st = intervals[i][0];
                int end = Math.max( intervals[i][1], newInterval[1]);
                while( i < n && newInterval[1] >= intervals[i][0] ) {
                    end = Math.max(end, intervals[i][1]);
                    i++;
                }
                i--;
                found = true;
                res.add(new int[]{st,end});
            }else
                res.add(intervals[i]);
        }
        int toReturn[][] = new int[res.size()][2];
        for(int i = 0 ; i < res.size();i++){
            toReturn[i] = res.get(i);
        }
        return toReturn;
    }

    public int compareVersion(String version1, String version2) {
        List<String> ver1 = Arrays.asList(version1.split("\\."));
        List<String> ver2 = Arrays.asList(version2.split("\\."));
        ArrayList<Integer> verI1 = new ArrayList<>(), verI2 = new ArrayList<>();
        for(int i = 0 ; i < 4 ; i++){
            if (ver1.size() <= i){
                verI1.add(0);
            }else{
                verI1.add(Integer.valueOf(ver1.get(i)));
            }
            if (ver2.size() <= i) {
                verI2.add(0);
            }else{
                verI2.add(Integer.valueOf(ver2.get(i)));
            }
        }
        for(int i =0;i<4;i++){
            if(verI1.get(i)>verI2.get(i))return 1;
            if(verI1.get(i)<verI2.get(i))return -1;
        }
        return 0;
    }

    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;
        int incr =0 ;
        for (int i = 1; i < n; i++) {
            if(arr[i]>=arr[i-1])incr++;
            else
                break;
        }
        int decr=0;
        for (int i = n-1; i >= 0; i--) {
            if(arr[i]>=arr[i-1])decr++;
            else
                break;
        }
        int max = Math.min(n-1-incr,n-1-decr);
        for (int i = incr, j = n-1; i >=0 && j>incr ; i--) {
            while(j>=0 && j>i && arr[i]<=arr[j] && (j==n-1 || arr[j]<=arr[j+1])){
                j--;
                max = Math.min(max, j-i);
            }
        }
        return max;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        if(n==1||k==0)return false;
        TreeSet<Integer> set = new TreeSet<Integer>();
        for(int i = 0; i <= Math.min(k,n-1); i++){
            set.add(nums[i]);
        }
        for(int i = 0; i < n; i++){
            set.remove(nums[i]);
            if(i-k-1>=0)set.remove(nums[i-k-1]);
            if(i+k<n)set.add(nums[i+k]);
            Integer lower = set.floor(nums[i]), upper = set.ceiling(nums[i]);
            if(lower!=null && Math.abs(nums[i]-lower)<=t)return true;
            if(upper!=null && Math.abs(nums[i]-upper)<=t)return true;
            set.add(nums[i]);
        }
        return false;
    }

    public int[] findRightInterval(int[][] intervals) {
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            list.add(new int[]{intervals[i][0], intervals[i][1], i});
        }
        list.sort(Comparator.comparingInt(t1 -> t1[0]));

        int res[] = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            res[i] = binarySearch(list, intervals[i][1]);
            System.out.println(res[i]);
        }
        return res;
    }

    private int binarySearch(ArrayList<int[]> list, int k) {
        int st = 0;
        int end = list.size() - 1;
        int res = -1;
        while (st <= end) {
            int mid = st + (end - st) / 2;
            if ( list.get(mid)[0] > k ) {
                end = mid - 1;
                res = list.get(mid)[2];
            } else if ( list.get(mid)[0] < k ) {
                st = mid + 1;
            } else return list.get(mid)[2];
        }
        return res;
    }


    int dp[][];

    public int mincostTickets(int[] days, int[] costs) {
        return dp(days, costs);
    }

    private int dp(int[] days, int[] costs) {
        int dpp[] = new int[366];
        Arrays.fill(dpp, Integer.MAX_VALUE);
        dpp[0] = 0;
        int j = 0;
        for (int i = 1; i < 366; i++) {
            dpp[i] = Math.min(dpp[i - 1] + costs[0], dpp[i]);
            if ( i >= 7 )
                dpp[i] = Math.min(dpp[i - 7] + costs[1], dpp[i]);
            if ( i >= 30 ) {
                dpp[i] = Math.min(dpp[i - 30] + costs[2], dpp[i]);
            }
            if ( j < days.length && days[j] == i ) {
                j++;
            } else {
                dpp[i] = Math.min(dpp[i], dpp[i - 1]);
            }
        }
        return dpp[days[days.length - 1]];
    }

    private int recurseDp(int[] days, int[] costs, int passTillDate, int currDay, int passNo) {
        if ( passTillDate >= days[days.length - 1] ) {
            dp[currDay][0] = 0;
            return 0;
        }
//        if ( currDay > days.length - 1 )
//            return 0;
//        if ( currDay > 0 && dp[currDay - 1][passNo] < 1002 * 365 ) return dp[currDay - 1][passNo];
        if ( days[currDay] <= passTillDate ) {
            if ( dp[currDay][0] >= 1002 * 365 )
                dp[currDay][0] = recurseDp(days, costs, passTillDate, currDay + 1, 0);
            return dp[currDay][0];
        } else {
            if ( dp[currDay][1] >= 1002 * 365 )
                dp[currDay][1] = costs[0] + recurseDp(days, costs, days[currDay] + 1 - 1, currDay + 1, 1);
            if ( dp[currDay][2] >= 1002 * 365 )
                dp[currDay][2] = costs[1] + recurseDp(days, costs, days[currDay] + 7 - 1, currDay + 1, 2);
            if ( dp[currDay][3] >= 1002 * 365 )
                dp[currDay][3] = costs[2] + recurseDp(days, costs, days[currDay] + 30 - 1, currDay + 1, 3);
            return min(dp[currDay][1], dp[currDay][2], dp[currDay][3]);
        }
    }

    private int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private int recurse(int[] days, int[] costs, int passTillDate, int currDay, int costSoFar) {
        if ( passTillDate >= days[days.length - 1] ) {
            return costSoFar;
        }
        if ( days[currDay] <= passTillDate ) {
            return recurse(days, costs, passTillDate, currDay + 1, costSoFar);
        } else {
            return Math.min(Math.min(
                    recurse(days, costs, days[currDay] + 1 - 1, currDay + 1, costSoFar + costs[0]),
                    recurse(days, costs, days[currDay] + 7 - 1, currDay + 1, costSoFar + costs[1])),
                    recurse(days, costs, days[currDay] + 30 - 1, currDay + 1, costSoFar + costs[2]));
        }
    }

    private int recurse1(int[] days, int[] costs, int passTillDate, int currDay, int passNo) {
        if ( passTillDate >= days[days.length - 1] ) {
            return 0;
        }
        if ( days[currDay] <= passTillDate ) {
            return recurse1(days, costs, passTillDate, currDay + 1, 0);
        } else {
            return Math.min(Math.min(
                    costs[0] + recurse1(days, costs, days[currDay] + 1 - 1, currDay + 1, 1),
                    costs[1] + recurse1(days, costs, days[currDay] + 7 - 1, currDay + 1, 2)),
                    costs[2] + recurse1(days, costs, days[currDay] + 30 - 1, currDay + 1, 3));
        }
    }


    ArrayList<Integer> list = new ArrayList<>();

    public int[] numsSameConsecDiff(int N, int K) {
        for (int i = 1; i < 10; i++) {
            createNumber(N, K, i, 1, i);
            /*int temp = i, prev = i;
            for (int j = 1; j < N; j++) {
                for (int l = 0; l < 10; l++) {
                    if ( Math.abs(prev - l) == K ) {

                    }
                }
                if ( prev + K < 10 ) {
                    prev += K;
                    temp = temp * 10 + prev;
                } else if ( prev - K >= 0 ) {
                    prev -= K;
                    temp = temp * 10 + prev;
                } else {
                    temp = -1;
                    break;
                }
            }
            if ( temp > 0 ) list.add(temp);*/
        }
        int res[] = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);
        return res;
    }

    private void createNumber(int n, int k, int prev, int pos, int tillNow) {
        if ( pos == n ) {
            list.add(tillNow);
        } else {
            int next = prev + k;
            if ( next < 10 )
                createNumber(n, k, next, pos + 1, tillNow * 10 + next);
            next = prev - k;
            if ( k != 0 && next >= 0 )
                createNumber(n, k, next, pos + 1, tillNow * 10 + next);
        }
    }

    class StreamChecker {

        class Trie {
            Trie ch[];
            boolean end;

            Trie() {
                ch = new Trie[27];
                end = false;
            }
        }

        Trie root;

        public StreamChecker(String[] words) {
            root = new Trie();
            for (String word : words) {
                Trie cur = root;
                for (int i = word.length() - 1; i >= 0; i--) {
                    if ( cur.ch[word.charAt(i) - 'a'] == null ) {
                        cur.ch[word.charAt(i) - 'a'] = new Trie();
                    }
                    cur = cur.ch[word.charAt(i) - 'a'];
                }
                cur.end = true;
            }
            soFar = root;
        }

        Trie soFar;
        StringBuilder sb;

        public boolean query(char letter) {
            sb.append(letter);
            return search(sb.toString());
        }

        private boolean search(String word) {
            Trie cur = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                if ( cur.ch[word.charAt(i) - 'a'] != null ) {
                    cur = cur.ch[word.charAt(i) - 'a'];
                    if ( cur.end ) return true;
                } else return false;
            }
            return cur.end;
        }
    }


    public int[] sortArrayByParity(int[] A) {
        Integer a[] = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            a[i] = A[i];
        }
        Arrays.sort(a, Comparator.comparingInt(t -> t % 2));
        for (int i = 0; i < A.length; i++) {
            A[i] = a[i];
        }
        return A;
    }

    public int longestPalindrome(String s) {
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int res = 0;
        ArrayList<Integer> oddList = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if ( entry.getValue() % 2 == 0 )
                res += entry.getValue();
            else
                oddList.add(entry.getValue());
        }
        if ( oddList.size() >= 1 ) {
            oddList.sort(Comparator.reverseOrder());
            res += oddList.get(0);
            for (int i = 1; i < oddList.size(); i++) {
                res += oddList.get(i) - 1;
            }
        }
        return res;
    }

    public List<Integer> getRow(int k) {

        ArrayList<Integer> res = new ArrayList<>();
        res.add(1);
        if ( k == 0 ) return res;
        res.add(1);
        if ( k == 1 ) return res;
        for (int i = 2; i <= k; i++) {
            int prev = res.get(0);
            for (int j = 1; j < res.size(); j++) {
                int curr = res.get(j);
                res.set(j, prev + res.get(j));
                prev = curr;
            }
            res.add(1);
        }
        return res;
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {

        if ( root == null ) return new ArrayList<>();
        HashMap<Integer, TreeSet<Integer>> map = new HashMap<>();
        LinkedList<Pair<TreeNode, int[]>> queue = new LinkedList<>();
        queue.add(new Pair(root, new int[]{0, 0}));
        int maxHd = 0, minHd = 0;
        int vdd[] = new int[10005];
        while (!queue.isEmpty()) {
            Pair<TreeNode, int[]> curr = queue.pollFirst();
            int hd = curr.getValue()[0];
            int vd = curr.getValue()[1];
            vdd[curr.getKey().val] = vd;
            maxHd = Math.max(maxHd, hd);
            minHd = Math.min(minHd, hd);
            TreeNode currNode = curr.getKey();
            TreeSet<Integer> list = map.getOrDefault(hd, new TreeSet<>((t1, t2) -> {
                if ( vdd[t1] == vdd[t2] ) return Integer.compare(t1, t2);
                return Integer.compare(vdd[t1], vdd[t2]);
            }));
            list.add(currNode.val);
            map.put(hd, list);
            if ( currNode.left != null ) {
                queue.add(new Pair(currNode.left, new int[]{hd - 1, vd + 1}));
            }
            if ( currNode.right != null ) {
                queue.add(new Pair(currNode.right, new int[]{hd + 1, vd + 1}));
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = minHd; i <= maxHd; i++) {
            res.add(new ArrayList<>(map.get(i)));
        }
        return res;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if ( n <= 1 ) return 0;

        Arrays.sort(intervals, (t1, t2) -> Integer.compare(t2[1], t1[1]));
        int count = 0;

        for (int i = 1; i < n; i++) {
            if ( intervals[i][0] < intervals[i - 1][1] ) {
                count++;
            }
        }
        return count;
    }

    public int[] distributeCandies(int candies, int num_people) {
        int n = num_people;
        int elementInLoop = (int) Math.sqrt(2 * candies);
        while ((elementInLoop * (elementInLoop + 1)) / 2 > candies) {
            elementInLoop--;
        }
        int totalTurn = elementInLoop / n;
        elementInLoop = totalTurn * n;
        int countOfn = ((totalTurn - 1) * (totalTurn)) / 2;
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (countOfn * n) + (totalTurn * (i + 1));
        }
        int remaining = candies - ((elementInLoop * (elementInLoop + 1)) / 2);
        long next = (countOfn + 1) * n + 1;
        int i = 0;
        while (remaining > 0) {
            arr[i] += Math.min(next, remaining);
            remaining -= next;
            next++;
            i = (i + 1) % n;
        }
        return arr;
    }

    class CombinationIterator {
        String characters;
        int combinationLength;
        int len;
        int pos[];

        public CombinationIterator(String characters, int combinationLength) {
            this.characters = characters;
            this.combinationLength = combinationLength;
            this.len = characters.length();
            pos = new int[combinationLength + 1];
            for (int i = 1; i < combinationLength; i++) {
                pos[i] = pos[i - 1] + 1;
            }
        }

        public String next() {
            if ( hasNext() ) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < combinationLength; i++) {
                    sb.append(characters.charAt(pos[i]));
                }
                incrementPos();
                return sb.toString();
            } else return "";
        }

        private void incrementPos() {
            int last = combinationLength - 1;  //5 - 1- 1
            int i = last;
            while (i >= 1 && (pos[i] + 1) == len - (last - i)) {
                i--;
            }
            pos[i++]++;
            for (; i <= last; i++) {
                pos[i] = pos[i - 1] + 1;
            }
        }

        public boolean hasNext() {
            return pos[0] <= (len - combinationLength);
        }
    }


    HashMap<Integer, Integer> map = new HashMap<>();

    public int pathSum(TreeNode root, int sum) {
        return pathSumUsingmap(root, sum, 0);
    }

    int res = 0;

    private int pathSumUsingmap(TreeNode root, int sum, int sumSoFar) {
        if ( root == null ) return 0;
        sumSoFar += root.val;
        if ( map.containsKey(sumSoFar - sum) ) {
            res += map.get(sumSoFar - sum);
        }
        map.put(sumSoFar, map.getOrDefault(sumSoFar, 0) + 1);
        res += pathSumUsingmap(root.left, sum, sumSoFar);
        res += pathSumUsingmap(root.right, sum, sumSoFar);
        map.put(sumSoFar, map.getOrDefault(sumSoFar, 0) - 1);
        return res;
    }

    int ans[][];
    int n, m;
//    int vis[][];

    public int orangesRotting(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        ans = new int[n + 2][m + 2];
        for (int i = 0; i < n + 2; i++) {
            Arrays.fill(ans[i], 1000);
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if ( grid[i][j] == 2 ) {
//                    ans[i + 1][j + 1] = 0;
//                }
//            }
//        }
        LinkedList<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ( grid[i][j] == 2 ) {
//                    vis = new int[n + 2][m + 2];
                    queue.addLast(new int[]{i + 1, j + 1});
//                    dfs(i+1,j+1, grid);
                }
            }
        }
        while (!queue.isEmpty()) {
            int cor[] = queue.removeFirst();
            int x = cor[0], y = cor[1];
            int currVal = ans[x][y];
            if ( grid[x - 1][y - 1] == 2 ) {
                ans[x][y] = 0;
            } else {
                ans[x][y] = Math.min(ans[x][y], 1 + Math.min(ans[x - 1][y],
                        Math.min(ans[x][y - 1], Math.min(ans[x + 1][y], ans[x][y + 1]))));
            }
            if ( ans[x][y] < currVal ) {
                int i = x - 1, j = y - 1;
                if ( i < n - 1 && grid[i + 1][j] == 1 ) {
                    queue.add(new int[]{i + 2, j + 1});
                }
                if ( j < m - 1 && grid[i][j + 1] == 1 ) {
                    queue.add(new int[]{i + 1, j + 2});
                }
                if ( i > 0 && grid[i - 1][j] == 1 ) {
                    queue.add(new int[]{i, j + 1});
                }
                if ( j > 0 && grid[i][j - 1] == 1 ) {
                    queue.add(new int[]{i + 1, j});
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if ( grid[i - 1][j - 1] == 1 )
                    max = Math.max(max, ans[i][j]);
            }
        }
        return max;
    }

//    void dfs(int x, int y, int[][] grid) {
//        if ( x <= 0 || y <= 0 || x > n || y > m || grid[x - 1][y - 1] == 0 )
//            return;
//        if ( vis[x][y] == 1 ) return;
//        vis[x][y] = 1;
//        ans[x][y] = Math.min(ans[x][y], 1 + Math.min(ans[x - 1][y], Math.min(ans[x][y - 1], Math.min(ans[x + 1][y], ans[x][y + 1]))));
//        dfs(x, y - 1, grid);
//        dfs(x - 1, y, grid);
//        dfs(x + 1, y, grid);
//        dfs(x, y + 1, grid);
//    }

    public static void main(String[] args) {
        August30DayChallenge solver = new August30DayChallenge();
        solver.solve();
    }

    static class InputReader {

        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if ( numChars == -1 )
                throw new InputMismatchException();
            if ( curChar >= numChars ) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if ( numChars <= 0 )
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if ( c == '-' ) {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if ( c < '0' || c > '9' )
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public long readLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if ( c == '-' ) {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if ( c < '0' || c > '9' )
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if ( filter != null )
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if ( i != 0 )
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

        public void flush() {
            writer.flush();
        }

    }

    static class IOUtils {

        public static int[] readIntegerArray(InputReader in, int size) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++) {
                array[i] = in.readInt();
            }
            return array;
        }

        public static long[] readLongArray(InputReader in, int size) {
            long[] array = new long[size];
            for (int i = 0; i < size; i++) {
                array[i] = in.readLong();
            }
            return array;
        }

        public static List<Integer> readIntegerList(InputReader in, int size) {
            List<Integer> set = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                set.add(in.readInt());
            }
            return set;
        }

        public static Set<Integer> readIntegerSet(InputReader in, int size) {
            Set<Integer> set = new HashSet<Integer>();
            for (int i = 0; i < size; i++) {
                set.add(in.readInt());
            }
            return set;
        }
    }
}
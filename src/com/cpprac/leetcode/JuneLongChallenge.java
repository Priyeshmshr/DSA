package com.cpprac.leetcode;

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
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

public class JuneLongChallenge {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
//        System.out.println(twoCitySchedCost(new int[][]{{10, 20}, {100, 30}, {50, 400}, {30, 20}}));
//        System.out.println(Arrays.deepToString(reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}})));
//        sortColors(new int[]{0, 2, 2, 2, 0, 2, 1, 1});
//        RandomizedSet obj = new RandomizedSet();
//        boolean param_1 = obj.insert(1);
//        obj.remove(2);
//        obj.insert(2);
//        obj.getRandom();
//        obj.remove(1);
//        obj.insert(2);
//        obj.getRandom();
//        largestDivisibleSubset(new int[]{1, 2, 3});
//        System.out.println(minSumOfLengths(new int[]{78, 18, 1, 94, 1, 1, 1, 29, 58, 3, 4, 1, 2, 56, 17, 19, 4, 1, 63, 2, 16, 11, 1, 1, 2, 1, 25, 62, 10, 69, 12, 7, 1, 6, 2, 92, 4, 1, 61, 7, 26, 1, 1, 1, 67, 26, 2, 2, 70, 25, 2, 68, 13, 4, 11, 1, 34, 14, 7, 37, 4, 1, 12, 51, 25, 2, 4, 3, 56, 21, 7, 8, 5, 93, 1, 1, 2, 55, 14, 25, 1, 1, 1, 89, 6, 1, 1, 24, 22, 50, 1, 28, 9, 51, 9, 88, 1, 7, 1, 30, 32, 18, 12, 3, 2, 18, 10, 4, 11, 43, 6, 5, 93, 2, 2, 68, 18, 11, 47, 33, 17, 27, 56, 13, 1, 2, 29, 1, 17, 1, 10, 15, 18, 3, 1, 86, 7, 4, 16, 45, 3, 29, 2, 1, 1, 31, 19, 18, 16, 12, 1, 56, 4, 35, 1, 1, 36, 59, 1, 1, 16, 58, 18, 4, 1, 43, 31, 15, 6, 1, 1, 6, 49, 27, 12, 1, 2, 80, 14, 2, 1, 21, 32, 18, 15, 11, 59, 10, 1, 14, 3, 3, 7, 15, 4, 55, 4, 1, 12, 4, 1, 1, 53, 37, 2, 5, 72, 3, 6, 10, 3, 3, 83, 8, 1, 5}, 97));
//        minSumOfLengths(new int[]{4, 1, 1, 1, 5, 1, 2, 1}, 3);
//        System.out.println(findCheapestPrice(4, new int[][]{{0,1,1}, {0,2,5}, {1,2,1}, {2,3,1}}, 0, 3, 1));
//        System.out.println(validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));
        System.out.println(getPermutation(3, 1));
        System.out.println(getPermutation(3, 2));
        System.out.println(getPermutation(3, 3));
        System.out.println(getPermutation(3, 4));
        System.out.println(getPermutation(3, 5));
        System.out.println(getPermutation(3, 6));
        System.out.println(getPermutation(4, 9));
    }



//    public int calculateMinimumHP(int[][] dungeon) {
////        PriorityQueujkle<Pair<Integer,Integer>>
//    }

    public String getPermutation(int n, int k) {
        int fact[] = new int[n + 1];
        fact[1] = 1;
        fact[0] = 1;
        for (int i = 2; i <= n; i++) {
            fact[i] = fact[i - 1] * i;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        int loc = n;
        while (loc > 0) {
            int curr = (int) Math.ceil(k / (1.0 * fact[loc - 1]));
            k -= fact[loc - 1] * (curr - 1);
            ans.add(next(ans, curr));
            loc--;
        }

        StringBuilder sb = new StringBuilder();
        for (Integer an : ans) {
            sb.append(an);
        }
        return sb.toString();
    }

    private int next(ArrayList<Integer> ans, int curr) {
        int now = 0;
        while (curr > 0) {
            now++;
            if ( now == 10 )
                now = 1;
            if ( !ans.contains(now) ) {
                curr--;
            }
        }
        return now;
    }

    public String validIPAddress(String IP) {
        if ( IP.matches("^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$") ) {
            return "IPv4";
        } else {
            if ( !IP.startsWith(":") && !IP.endsWith(":") ) {
                String s[] = IP.split(":");
                if ( s.length == 8 ) {
                    for (int i = 0; i < 8; i++) {
                        if ( !s[i].matches("^([0-9]|[a-f]|[A-F]){1,4}$") ) {
                            return "Neither";
                        }
                    }
                    return "IPv6";
                }
            }
        }
        return "Neither";
    }

    int vis[];
    ArrayList<Pair> graph[];
    int dp[][];

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        vis = new int[n + 1];
        graph = new ArrayList[n + 2];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < flights.length; i++) {
            int u = flights[i][0];
            int v = flights[i][1];
            int w = flights[i][2];
            graph[u].add(new Pair(v, w));
        }
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dijkstra(src, 0, K);
        int ans = dist[dst];
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    int dist[];

    private void dijkstra(int src, int steps, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t[0]));
        pq.add(new int[]{0, src, k + 1});
        dist[src] = 0;
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int we = node[0];
            int u = node[1];
            int step = node[2];
            if ( step > 0 ) {
                for (Pair v : graph[u]) {
                    if ( dist[v.i] > we + v.j ) {
                        dist[v.i] = we + v.j;
                        pq.add(new int[]{dist[v.i], v.i, step - 1});
                    }
                }
            }
        }
    }

    private void dfs(int u, int stops, int K) {
        for (Pair vi : graph[u]) {
            if ( stops <= K ) {
                dp[vi.i][stops + 1] = Math.min(dp[vi.i][stops + 1], dp[u][stops] + vi.j);
                dfs(vi.i, stops + 1, K);
            }
        }
    }

    public int[][] reconstructQueue(int[][] people) {
//        int res [][] = new int[people.length][people[0].length];
//        for (int i = 0; i < n; i++) {
//            res[i][0]=-1;
//        }
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] x, int[] y) {
                return x[0] == y[0] ? x[1] - y[1] : y[0] - x[0];
            }
        });
        List<int[]> result = new ArrayList<>();
        for (int[] person : people) {
            result.add(person[1], person);
        }
        return result.toArray(new int[result.size()][]);
    }

    class RandomizedSet {

        ArrayList<Integer> arr;
        Map<Integer, Integer> map;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            arr = new ArrayList();
            map = new HashMap();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if ( !map.containsKey(val) ) {
                arr.add(val);
                map.put(val, arr.size() - 1);
                return true;
            }
            return false;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if ( map.containsKey(val) ) {
                int index = map.get(val);
                Integer last = arr.get(arr.size() - 1);
                arr.set(index, last);
                map.put(last, index);
                arr.remove(arr.size() - 1);
                map.remove(val);
                return true;
            }
            return false;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            int size = arr.size();
            Random rand = new Random();
            return arr.get(rand.nextInt(size));
        }
    }

    public static void main(String[] args) {
        JuneLongChallenge solver = new JuneLongChallenge();
        solver.solve();

    }

    class Pair {
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int dp[][] = new int[n + 1][2];
        HashMap<Integer, Integer> loc = new HashMap<>();
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
            loc.put(nums[i], i);
        }

        int max = 0, maxi = 0;
        for (int i = 1; i < n; i++) {
            int temp = nums[i];
            for (int j = 1; j <= Math.sqrt(temp); j++) {
                if ( nums[i] % j == 0 ) {
                    if ( loc.containsKey(j) ) {
                        int index = loc.get(j);
                        if ( dp[i][0] < dp[index][0] + 1 ) {
                            dp[i][0] = dp[index][0] + 1;
                            dp[i][1] = index;
                            if ( max < dp[i][0] ) {
                                max = Math.max(max, dp[i][0]);
                                maxi = i;
                            }
                        }
                    }
                    if ( j != 1 && loc.containsKey(nums[i] / j) ) {
                        int index = loc.get(nums[i] / j);
                        if ( dp[i][0] < dp[index][0] + 1 ) {
                            dp[i][0] = dp[index][0] + 1;
                            dp[i][1] = index;
                            if ( max < dp[i][0] ) {
                                max = Math.max(max, dp[i][0]);
                                maxi = i;
                            }
                        }
                    }
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        while (dp[maxi][1] < maxi) {
            ans.add(nums[maxi]);
            maxi = dp[maxi][1];
        }
        ans.add(nums[maxi]);
        return ans;
    }

    public int minSumOfLengths(int[] arr, int target) {
        ArrayList<Pair> list = new ArrayList<>();
        int n = arr.length;
        long currSum = 0;
        int sti = 0;
        for (int i = 0; i < n; i++) {
            currSum += arr[i];
            while (currSum > target) {
                currSum -= arr[sti];
                sti++;
            }
            if ( currSum == target ) {
                list.add(new Pair(sti, i));
                currSum -= arr[sti];
                sti++;
            }
        }
        list.sort((t1, t2) -> {
            if ( t1.j - t1.i == t2.j - t2.i ) {
                return t1.i - t2.i;
            } else return (t1.j - t1.i) - (t2.j - t2.i);
        });
        if ( list.size() < 2 ) {
            return -1;
        }
        int index1 = 0, index2 = 1;
        while (!(list.get(index2).j < list.get(0).i || list.get(index2).i > list.get(0).j)) {
            index2++;
        }
        return (list.get(index1).j - list.get(index1).i) + (list.get(index2).j - list.get(index2).i) + 2;
    }

    public void sortColors(int[] nums) {
        int n = nums.length;
        int first = 0, last = n - 1;
        while (last >= 0 && nums[last] == 2)
            last--;
        while (first < n && nums[first] == 0)
            first++;
        for (int i = first; i <= last; ) {
            if ( nums[i] == 2 ) {
                int temp = nums[last];
                nums[last] = nums[i];
                nums[i] = temp;
            } else if ( nums[i] == 0 ) {
                int temp = nums[first];
                nums[first] = nums[i];
                nums[i] = temp;
            }
            while (last >= 0 && nums[last] == 2)
                last--;
            while (first < n && nums[first] == 0) {
                first++;
            }
            if ( nums[i] == 1 )
                i++;
            else if ( nums[i] == 0 && i < first ) {
                i = first;
            } else if ( nums[i] == 2 && i > last ) {
                i++;
            }
        }
    }

    public int twoCitySchedCost(int[][] costs) {
        ans = Integer.MAX_VALUE;
        int n = costs.length;
        int dp[][] = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], (int) 1e5);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][0] + costs[i - 1][1];
            for (int j = 1; j <= i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + costs[i - 1][1], dp[i - 1][j - 1] + costs[i - 1][0]);
            }
        }
        return dp[n][n / 2];
    }

    int ans;

    int dp1[][];

    class Solution {

        int[] value;
        Random rand;

        public Solution(int[] w) {
            value = new int[w.length];
            value[0] = w[0];
            for (int i = 1; i < w.length; i++) {
                value[i] = w[i] + value[i - 1];
            }
            rand = new Random();
        }

        public int pickIndex() {
            int ele = rand.nextInt(value[value.length - 1]);
            return value[upperBound(ele)];
        }

        private int upperBound(int ele) {
            int st = 0, end = value.length - 1;
            while (st < end) {
                int mid = (st + end) / 2;
                if ( ele < value[mid] ) {
                    end = mid - 1;
                } else if ( ele > value[mid] ) {
                    st = mid + 1;
                } else {
                    return mid;
                }
            }
            return st;
        }
    }

    private void recur(int[][] costs, int index, int a, int b, int cost) {
        if ( a > costs.length / 2 ) {
            dp1[index][a] = Integer.MAX_VALUE;
            return;
        }
        if ( index == costs.length ) {
            if ( a == b ) {
                dp1[index][a] = cost;
            } else {
                dp1[index][a] = Integer.MAX_VALUE;
            }
            return;
        }
        if ( dp1[index][a] == -1 ) {
            recur(costs, index + 1, a + 1, b, cost + costs[index][0]);
            recur(costs, index + 1, a, b + 1, cost + costs[index][1]);
            dp1[index][a] = Math.min(dp1[index + 1][a + 1], dp1[index + 1][a]);
        }
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
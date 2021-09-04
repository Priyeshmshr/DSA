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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class May30DayChallenge {

    boolean prime[];
    Map<Integer, Integer> primeFactors = new HashMap<>();

    void sieveOfEratosthenes(int n, int x) {

        for (int i = 2; i < n; i++)
            prime[i] = true;

        for (int p = 2; p * p < n; p++) {
            if ( prime[p] ) {
                while (x % p == 0) {
                    primeFactors.put(p, primeFactors.getOrDefault(p, 0) + 1);
                    x = x / p;
                }
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }
    }

    public boolean isPerfectSquare1(int num) {
        for (int i = 2; i * i <= num; i++) {
            if ( i * i == num )
                return true;
        }
        return false;
    }

    public boolean isPerfectSquare(int num) {
        if ( num == 1 )
            return true;
        prime = new boolean[50000 + 1];
        sieveOfEratosthenes(50000, num);
        if ( primeFactors.size() == 0 )
            return false;
        for (Map.Entry<Integer, Integer> entry : primeFactors.entrySet()) {
            if ( entry.getValue() % 2 != 0 )
                return false;
        }
        return true;

    }

    public int findJudge(int N, int[][] trust) {

        int inDeg[] = new int[N + 1], outDeg[] = new int[N + 1];
        for (int[] aTrust : trust) {
            inDeg[aTrust[1]]++;
            outDeg[aTrust[0]]++;
        }
        for (int i = 1; i <= N; i++) {
            if ( inDeg[i] == 0 && outDeg[i] == N - 1 ) {
                return i;
            }
        }
        return -1;
    }

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        findComplement(5);
//        System.out.println(Math.sqrt(100489));
//        System.out.println(isPerfectSquare(100489));
//        System.out.println(singleNonDuplicate(new int[]{1, 1, 2}));
//        System.out.println(removeKdigits("1432299", 3));
//        System.out.println(maxSubarraySumCircular(new int[]{10, -12, 11}));
//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next = new ListNode(3);
//        head.next = new ListNode(4);
//        head.next = new ListNode(5);
//        oddEvenList(head);
//        System.out.println(findAnagrams("cbaebabacd"
//                , "abc"));
//        frequencySort("tree");
//        intervalIntersection(new int[][]{{0,2},{5,10},{13,23},{24,25}}, new int[][]{{1,5},{8,12},{15,24},{25,26}});
//        System.out.println(maxUncrossedLines(new int[]{3, 1, 4, 1, 1, 3, 5, 1, 2, 2},
//                new int[]{4, 1, 5, 2, 1, 1, 1, 5, 3, 1, 1, 1, 2, 3, 1, 4, 3, 5, 5, 3, 1, 2, 3, 2, 4, 1, 1, 1, 5, 3}));
//        System.out.println(maxUncrossedLines(new int[]{2, 5, 1, 2, 5},
//                new int[]{10, 5, 2, 1, 5, 2}));
//        System.out.println(maxUncrossedLines(new int[]{1, 3, 7, 1, 7, 5},
//                new int[]{1, 9, 2, 5, 1}));
//        System.out.println(maxUncrossedLines(new int[]{2, 3, 1},
//                new int[]{3, 1, 3, 3, 3, 3}));
//        System.out.println(Arrays.toString(countBits(32)));
//        System.out.println(Arrays.toString(canFinish(2, new int[][]{{1, 0}})));
        System.out.println(minDistance("intention", "execution"));
    }


    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        int dp[][] = new int[n + 1][m + 1];

        for (int i = 1; i <= m; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
            for (int j = 1; j <= m; j++) {
                if ( word1.charAt(i - 1) == word2.charAt(j - 1) )
                    dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[n][m];
    }

    public int[] canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> adjList = new HashMap<>();
        int inDeg[] = new int[numCourses + 1];
        for (int[] prerequisite : prerequisites) {
            int st = prerequisite[1];
            int end = prerequisite[0];
            ArrayList<Integer> list = adjList.getOrDefault(end, new ArrayList<>());
            list.add(st);
            adjList.put(end, list);
            inDeg[st]++;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        int res[] = new int[numCourses];
        int k = 0;
        for (int i = 0; i < numCourses; i++) {
            if ( inDeg[i] == 0 ) {
                queue.addLast(i);
                res[k++] = i;
            }
        }
        while (!queue.isEmpty()) {
            int curr = queue.pop();
            ArrayList<Integer> list = adjList.getOrDefault(curr, new ArrayList<>());
            for (int i : list) {
                inDeg[i]--;
                if ( inDeg[i] == 0 ) {
                    queue.addLast(i);
                    res[k++] = i;
                }
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if ( inDeg[i] != 0 )
                return new int[]{};
        }
        return res;
    }

    int dp[][];

    public int maxUncrossedLines(int[] A, int[] B) {
        dp = new int[A.length + 1][B.length + 1];
        int n = A.length, m = B.length;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int include = 0, exclude = 0;
                if ( A[i] == B[j] ) {
                    include = dp[i + 1][j + 1] + 1;
                }
                exclude = Math.max(dp[i + 1][j], dp[i][j + 1]);
                dp[i][j] = Math.max(include, exclude);
            }
        }
        return dp[0][0];
    }

    private int dp(int[] a, int[] b, int aIndex, int bIndex) {
        if ( aIndex == a.length || bIndex == b.length ) {
            return 0;
        }
        int nextBIndex = 0;
        for (int i = bIndex; i < b.length; i++) {
            if ( a[aIndex] == b[i] ) {
                nextBIndex = i + 1;
                break;
            }
        }
        int include = 0, exclude = 0;
        if ( nextBIndex > bIndex ) {
            include = dp[aIndex + 1][nextBIndex] == -1 ? dp(a, b, aIndex + 1, nextBIndex) + 1 : dp[aIndex + 1][nextBIndex] + 1;
        }
        exclude = dp[aIndex + 1][bIndex] == -1 ? dp(a, b, aIndex + 1, bIndex) : dp[aIndex + 1][bIndex];
        dp[aIndex][bIndex] = Math.max(include, exclude);
        return dp[aIndex][bIndex];
    }

    private int recur(int[] a, int[] b, int aIndex, int bIndex, int count) {
        if ( aIndex == a.length || bIndex == b.length ) {
            return 0;
        }
        int nextBIndex = 0;
        for (int i = bIndex; i < b.length; i++) {
            if ( a[aIndex] == b[i] ) {
                nextBIndex = i + 1;
                break;
            }
        }
        int include = 0, exclude = 0;
        if ( nextBIndex > bIndex ) {
            include = recur(a, b, aIndex + 1, nextBIndex, count + 1) + 1;
        }
        exclude = recur(a, b, aIndex + 1, bIndex, count);
        return Math.max(include, exclude);
    }

    class Pair {
        int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int n = A.length;
        int m = B.length;
        if ( n == 0 || m == 0 )
            return new int[0][2];

        ArrayList<int[]> arr = new ArrayList<>();
        int i = 0, j = 0;
        while (i < n && j < m) {
            int maxStart = Math.max(A[i][0], B[j][0]);
            int minEnd = Math.min(A[i][1], B[j][1]);
            if ( maxStart <= minEnd )
                arr.add(new int[]{maxStart, minEnd});

            if ( A[i][1] < B[j][1] )
                i++;
            else if ( A[i][1] > B[j][1] )
                j++;
            else {
                i++;
                j++;
            }
        }
        return arr.toArray(new int[arr.size()][]);
    }

    class eleFreq {
        char element;
        int freq;

        public eleFreq(char element, int freq) {
            this.element = element;
            this.freq = freq;
        }
    }

    public String frequencySort(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0) + 1);
        }
        List<Map.Entry<Character, Integer>> arr = freq.entrySet().stream().sorted(Comparator.comparing(c -> -1 * freq.get(c.getKey()))).collect(Collectors.toList());
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : arr) {
            int count = entry.getValue();
            while (count-- > 0) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }

    class StockSpanner {

        Stack<Integer> stack;
        Stack<Integer> days;

        public StockSpanner() {
            stack = new Stack<>();
            stack.ensureCapacity(10004);
            days = new Stack<>();
        }

        public int next(int price) {
            int day = 1;
            while (!stack.isEmpty() && stack.peek() <= price) {
                day += days.peek();
                stack.pop();
                days.pop();
            }
            stack.push(price);
            days.push(day);
            return day;
        }
    }


    public int maxSubarraySumCircular(int[] A) {
        int centerSum = kadane(A);
        int totalSum = 0;
        int positive = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            totalSum += A[i];
            if ( A[i] > 0 ) {
                positive = 1;
            } else {
                min = Math.max(min, A[i]);
            }
            A[i] = -1 * A[i];
        }
        if ( positive == 0 )
            return min;
        int wrappedSum = kadane(A);
        return Math.max(centerSum, totalSum + wrappedSum);
    }

    int kadane(int[] a) {
        int n = a.length;
        int sum = a[0], currSum = a[0];
        for (int i = 1; i < n; i++) {
            currSum = Math.max(currSum + a[i], a[i]);
            sum = Math.max(currSum, sum);
        }
        return sum;
    }

    public static void main(String[] args) {
        May30DayChallenge solver = new May30DayChallenge();
        solver.solve();
    }

    public int[] countBits(int num) {
        int ans[] = new int[num + 1];
        ans[0] = 0;
        ans[1] = 1;
        ans[2] = 1;
        ans[3] = 2;
        long highestIndex = 4;
        int curr = 0;
        for (int i = 4; i <= num; i++) {
            ans[i] = ans[curr] + 1;
            curr++;
            if ( curr == highestIndex ) {
                curr = 0;
                highestIndex = highestIndex * 2;
            }
        }
        return ans;
    }

    public long countAllBits(int num) {
        int temp = 2;
        long ans = 0;
        while (num > temp / 2) {
            ans += num / temp + (num % temp) + 1 - (temp / 2);
            temp = temp * 2;
        }
        return ans;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int k = p.length();
        HashMap<Character, Integer> expected = new HashMap<>();
        HashMap<Character, Integer> actual = new HashMap<>();
        for (int i = 0; i < k; i++) {
            expected.put(p.charAt(i), expected.getOrDefault(p.charAt(i), 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        int st = 0;
        for (int i = 0; i < s.length(); i++) {
            if ( !expected.containsKey(s.charAt(i)) ) {
                actual.clear();
                st = i + 1;
                continue;
            }
            while (st < i && expected.getOrDefault(s.charAt(i), 0) <= actual.getOrDefault(s.charAt(i), 0)) {
                actual.put(s.charAt(st), actual.getOrDefault(s.charAt(st), 0) - 1);
                st++;
            }
            actual.put(s.charAt(i), actual.getOrDefault(s.charAt(i), 0) + 1);
            if ( i - st + 1 == k ) {
                ans.add(st);
                actual.put(s.charAt(st), actual.getOrDefault(s.charAt(st), 0) - 1);
                st++;
            }
        }
        return ans;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode oddEvenList(ListNode head) {

        //1->2->3->4->5->6->7->NULL
        // 1->3->4

        if ( head == null || head.next == null )
            return head;

        ListNode even = head.next;
        ListNode oddEnd = head;
        ListNode evenStart = head.next;
        while (even != null && even.next != null) {
            oddEnd.next = even.next;
            oddEnd = oddEnd.next;
            even.next = oddEnd.next;
            even = even.next;
        }
        oddEnd.next = evenStart;
        return head;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if ( image[sr][sc] == newColor )
            return image;
        floodFillDfs(image, sr, sc, newColor, image[sr][sc]);
        return image;
    }

    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (char c : num.toCharArray()) {
            while (k > 0 && !stack.isEmpty() && Integer.valueOf(stack.peek()) > (int) c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char i : stack) {
            if ( sb.length() == 0 && i == '0' ) continue;
            sb.append(i);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    private void floodFillDfs(int[][] image, int sr, int sc, int newColor, int originalColor) {

        if ( sr < 0 || sc < 0 )
            return;
        if ( sr > image.length || sr > image[0].length )
            return;
        if ( image[sr][sc] != originalColor )
            return;

        image[sr][sc] = newColor;
        floodFillDfs(image, sr + 1, sc, newColor, originalColor);
        floodFillDfs(image, sr - 1, sc, newColor, originalColor);
        floodFillDfs(image, sr, sc - 1, newColor, originalColor);
        floodFillDfs(image, sr, sc + 1, newColor, originalColor);

    }

    public int singleNonDuplicate(int[] nums) {
        if ( nums.length == 0 )
            return 0;
        if ( nums.length == 1 )
            return nums[0];

        int st = 0, end = nums.length - 1;

        int n = nums.length;

        while (st <= end) {
            int mid = (st + end) / 2;
            if ( found(mid, n, nums) )
                return nums[mid];
            if ( moveRight(mid, n, nums) ) {
                st = mid + 1;
            } else
                end = mid - 1;
        }
        return -1;
    }

    private boolean found(int mid, int n, int[] nums) {
        return (mid == n - 1 && nums[mid] != nums[mid - 1]) || (mid == 0 && nums[mid] != nums[mid + 1]) || (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]);
    }

    private boolean moveRight(int mid, int n, int[] nums) {
        return (mid % 2 == 0 && mid < n - 1 && nums[mid] == nums[mid + 1]) || (mid % 2 != 0 && mid > 0 && nums[mid] == nums[mid - 1]);
    }

    int depths[] = new int[2];

    public boolean isCousins(TreeNode root, int x, int y) {
        Arrays.fill(depths, -1);
        dfs(root, x, y, 0);
        return depths[0] == depths[1] && depths[1] != -1;
    }

    public boolean checkStraightLine(int[][] coordinates) {

        double slope = (coordinates[0][1] - coordinates[1][1]) / ((coordinates[0][0] - coordinates[1][0]) * 1.0);
        for (int i = 2; i < coordinates.length; i++) {
            double slope2 = (coordinates[0][1] - coordinates[i][1]) / ((coordinates[0][0] - coordinates[i][0]) * 1.0);
            if ( slope != slope2 )
                return false;
        }
        return true;
    }


    private void dfs(TreeNode root, int x, int y, int depth) {

        if ( root == null )
            return;

        if ( root.val == x ) {
            depths[0] = depth;
            return;
        }

        if ( root.val == y ) {
            depths[1] = depth;
            return;
        }

        if ( (root.right != null && root.left != null) && ((root.left.val == x && root.right.val == y) || (root.left.val == y && root.right.val == x)) ) {
            return;
        }

        dfs(root.right, x, y, depth + 1);
        dfs(root.left, x, y, depth + 1);

    }

    public int numJewelsInStones(String J, String S) {
        int ans = 0;
        HashSet<Character> set = new HashSet<>();
        for (char c : J.toCharArray()) {
            set.add(c);
        }
        for (char c : S.toCharArray()) {
            if ( set.contains(c) ) {
                ans++;
            }
        }
        return ans;
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> magSet = new HashMap<>();
        for (Character ch : magazine.toCharArray()) {
            magSet.put(ch, magSet.getOrDefault(ch, 0) + 1);
        }
        for (Character ch : ransomNote.toCharArray()) {
            if ( magSet.containsKey(ch) ) {
                int count = magSet.get(ch);
                if ( count == 0 )
                    magSet.remove(ch);
                else magSet.put(ch, magSet.get(ch) - 1);
            } else
                return false;
        }
        return true;
    }

    private int findComplement(int num) {
        num = num ^ ((1 << ((int) (Math.log(num) / Math.log(2)))) - 1);
        return num;
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
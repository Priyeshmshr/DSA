package com.cpprac.leetcode;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class April30DayChallenge {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int arr[] = {0, 1, 0, 0, 0, 1, 1};
//        System.out.println(lastStoneWeight(stones));
//        findMaxLength(arr);
//        System.out.println(stringShift("abcdefg",new int[][]{{1,1}}));

//        productExceptSelf(new int[]{12, 1, 3, 2});
//        System.out.println(checkValidString("(())((())(****)()(*)(*(*)(())())())()()((()())((()))(*"));
//        System.out.println(numIslandsDfs(new char[][]{
//                {'1', '0', '1', '1', '1'},
//                {'1', '0', '1', '0', '1'},
//                {'1', '1', '1', '0', '1'}}));

//        {'0','0','0','0','0'}

//        System.out.println(minPathSum(new int[][]{{1,3,1},{1,5,1},{4,2,1}}));
//        System.out.println(search(new int[]{6, 7, 8, 9, 10, 0, 1, 4, 5}, 6));
//        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 3));

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(maxPathSum(root));
    }

    public static void main(String[] args) {
        April30DayChallenge solver = new April30DayChallenge();
        solver.solve();
    }


    int index=1;

    public TreeNode bstFromPreorder(int[] preorder) {
        if ( preorder.length == 0 )
            return null;
        TreeNode root = new TreeNode(preorder[0]);
        bstFromPreOrderUtil(root, preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return root;
    }

    private void bstFromPreOrderUtil(TreeNode root, int[] preorder, int minValue, int maxValue) {

        if ( index < preorder.length && minValue < preorder[index] && preorder[index] < root.val ) {
            root.left = new TreeNode(preorder[index]);
            index++;
            bstFromPreOrderUtil(root.left, preorder, minValue, root.val);
        }
        if ( index < preorder.length && root.val < preorder[index] && preorder[index] < maxValue ) {
            root.right = new TreeNode(preorder[index]);
            index++;
            bstFromPreOrderUtil(root.right, preorder, root.val, maxValue);
        }
    }


    public int search(int[] nums, int target) {
        int st = 0, end = nums.length - 1;
        while (st <= end) {
            int mid = (st + end) / 2;
            if ( target < nums[mid] ) {
                if ( target >= nums[st] || nums[st] > nums[mid] )
                    end = mid - 1;
                else st = mid + 1;
            } else if ( target > nums[mid] ) {
                if ( target <= nums[end] || nums[end] < nums[mid] )
                    st = mid + 1;
                else end = mid - 1;
            } else return mid;
        }
        return -1;
    }

    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if ( root == null )
            return 0;
        return Math.max(solve(root), res);
    }

    private int solve(TreeNode root) {
        if ( root == null )
            return -20000;

        int leftSum = solve(root.left);
        int rightSum = solve(root.right);
        int currPath = Math.max(root.val, Math.max(leftSum, rightSum) + root.val);
        if ( res < (leftSum + rightSum + root.val) ) {
            res = (leftSum + rightSum + root.val);
        }

        System.out.println(res);
        System.out.println("CurrPath" + currPath);
        return currPath;
    }

    class MinStack {

        class Pair {
            int curr, min;

            Pair(int curr, int min) {
                this.curr = curr;
                this.min = min;
            }
        }

        Stack<Pair> stack;

        public MinStack() {
            stack = new Stack<Pair>();
        }

        public void push(int x) {
            int minSoFar = x;
            if ( stack.size() > 0 ) {
                Pair pair = stack.peek();
                minSoFar = Math.min(x, pair.min);
            }
            stack.add(new Pair(x, minSoFar));
        }

        public void pop() {
            if ( stack.size() > 0 )
                stack.pop();
        }

        public int top() {
            if ( stack.size() > 0 )
                return stack.peek().curr;
            return Integer.MIN_VALUE;
        }

        public int getMin() {
            if ( stack.size() > 0 )
                return stack.peek().min;
            return Integer.MIN_VALUE;
        }
    }

    public int lastStoneWeight(int[] stones) {

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Comparator.reverseOrder());
        for (int stone : stones) {
            queue.add(stone);
        }
        while (queue.size() > 1) {
            int first = queue.poll(), sec = queue.poll();
            if ( Math.abs(first - sec) != 0 ) {
                queue.add(Math.abs(first - sec));
            }
        }
        if ( queue.isEmpty() )
            return 0;
        else
            return queue.poll();
    }

    class Pair {
        int st, end;

        public Pair(int st, int end) {
            this.st = st;
            this.end = end;
        }
    }

    public void findMaxLength(int[] nums) {
        HashMap<Integer, Pair> map = new HashMap<>();
        int[] prefSum = new int[nums.length + 1];
        map.put(0, new Pair(-1, 0));
        int res = 0;
        int lastNumber = 0;
        for (int i = 0; i < nums.length; i++) {
            lastNumber = lastNumber + (nums[i] == 0 ? -1 : 1);
            Pair p = map.getOrDefault(prefSum[i + 1], new Pair(i, i));
            p.end = i;
            map.put(prefSum[i + 1], p);
            res = Math.max(res, p.end - p.st);
        }
        System.out.println(res);
    }

    public String stringShift(String s, int[][] shift) {

        int shifts = 0;
        for (int[] aShift : shift) {
            if ( aShift[0] == 0 ) {
                shifts -= aShift[1];
            } else
                shifts += aShift[1];
        }
        int absShift = Math.abs(shifts) % s.length();
        StringBuilder sb = new StringBuilder();
        if ( absShift == 0 )
            return s;
        else {
            if ( shifts < 0 ) {
                sb.append(s.substring(absShift));
                sb.append(s, 0, absShift);
            } else {
                sb.append(s.substring(s.length() - absShift));
                sb.append(s, 0, s.length() - absShift);
            }
        }
        return sb.toString();
    }

    public int[] productExceptSelf(int[] nums) {

        int[] output = new int[nums.length];
        output[nums.length - 1] = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            output[i] = output[i + 1] * nums[i];
        }
        output[0] = output[1];
        int leftProduct = nums[0];
        System.out.print(output[0] + " ");

        for (int i = 1; i < nums.length; i++) {

            if ( i < nums.length - 1 )
                output[i] = leftProduct * output[i + 1];
            else
                output[i] = leftProduct;

            leftProduct = leftProduct * nums[i];
            System.out.print(output[i] + " ");
        }
        return output;
    }

    public boolean checkValidString(String s) {
        ArrayList<Character> stars = new ArrayList<>();
        for (Character c : s.toCharArray()) {
            if ( !stars.isEmpty() && (c == ')') ) {
                int j = stars.size() - 1;
                int indexOfStar = -1;
                while (j >= 0) {
                    if ( stars.get(j) == '(' ) {
                        stars.remove(j);
                        break;
                    } else if ( stars.get(j) == '*' )
                        indexOfStar = j;
                    j--;
                }
                if ( j == -1 ) {
                    if ( indexOfStar != -1 )
                        stars.remove(indexOfStar);
                    else
                        return false;
                }
            } else if ( stars.isEmpty() && (c == ')') )
                return false;
            else
                stars.add(c);
        }
        int openingBrackets = 0;
        for (Character star : stars) {
            if ( star == '(' )
                openingBrackets++;
            else if ( star == '*' && openingBrackets > 0 )
                openingBrackets--;
        }
        return openingBrackets == 0;
    }

    public int numIslandsDsu(char[][] grid) {
        if ( grid.length == 0 )
            return 0;
        DSU dsu = new DSU(grid.length * grid[0].length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int node = i * grid[0].length + j;
                if ( grid[i][j] == '1' ) {
                    if ( i < grid.length - 1 && grid[i + 1][j] == '1' )
                        dsu.union(node, (i + 1) * grid[0].length + j);
                    if ( j < grid[0].length - 1 && grid[i][j + 1] == '1' )
                        dsu.union(node, i * grid[0].length + (j + 1));
                } else {
                    dsu.parent[node] = -1;
                    dsu.connectedComponents--;
                }
            }
        }
        return dsu.connectedComponents;
    }

    public int numIslandsDfs(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if ( grid[i][j] == '1' ) {
                    count++;
                    dfs(i, j, grid);
                }
            }
        }
        return count;
    }

    public int minPathSum(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int up = Integer.MAX_VALUE, left = Integer.MAX_VALUE;
                if ( i > 0 ) up = grid[i][j] + grid[i - 1][j];
                if ( j > 0 ) left = grid[i][j] + grid[i][j - 1];
                if ( i != 0 || j != 0 ) grid[i][j] = Math.min(up, left);
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public boolean isValidSequence(TreeNode root, int[] arr) {
        return dfs(root, arr, 0);
    }

    private boolean dfs(TreeNode root, int[] arr, int index) {

        if ( root == null ) {
            return index >= arr.length;
        }
        if ( index >= arr.length )
            return false;
        if ( root.val != arr[index] )
            return false;

        return dfs(root.left, arr, index + 1) || dfs(root.right, arr, index + 1);
    }

    private void dfs(int i, int j, char[][] grid) {
        grid[i][j] = '*';
        if ( i > 0 && grid[i - 1][j] == '1' )
            dfs(i - 1, j, grid);
        if ( j > 0 && grid[i][j - 1] == '1' )
            dfs(i, j - 1, grid);
        if ( i < grid.length - 1 && grid[i + 1][j] == '1' )
            dfs(i + 1, j, grid);
        if ( j < grid[0].length - 1 && grid[i][j + 1] == '1' )
            dfs(i, j + 1, grid);
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
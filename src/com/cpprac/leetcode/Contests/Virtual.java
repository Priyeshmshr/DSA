package com.cpprac.leetcode.Contests;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Virtual {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
//        System.out.println(findKthBit(4, 11));

//        System.out.println(maxProfit(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
        System.out.println(containsCycle(new char[][]{{'a', 'b', 'b'}, {'b', 'z', 'b'}, {'b', 'b', 'a'}}));
    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if ( n == 0 ) return 0;
        int rightdp[] = new int[n + 2];
        int min = prices[n - 1], max = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if ( prices[i] > max ) {
                min = prices[i];
                max = prices[i];
            } else if ( prices[i] < min ) {
                min = prices[i];
            }
            rightdp[i + 1] = Math.max(rightdp[i + 2], max - min);
        }
        int leftdp[] = new int[n + 2];
        min = prices[0];
        max = prices[0];
        for (int i = 0; i < n; i++) {
            if ( prices[i] > max ) {
                max = prices[i];
            } else if ( prices[i] < min ) {
                min = prices[i];
                max = prices[i];
            }
            leftdp[i + 1] = Math.max(leftdp[i], max - min);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, leftdp[i] + rightdp[i + 1]);
        }
        return res;
    }

    boolean vis[][];
    int n, m;
    char[][] grid1;

    public boolean containsCycle(char[][] grid) {
        grid1 = grid;
        n = grid.length;
        m = grid[0].length;
        vis = new boolean[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean possible = false;
                if ( !vis[i][j] ) {
                    possible = dfs(i, j, grid1[i][j], -1, -1);
                }
                if ( possible ) return true;
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, char c, int previ, int prevj) {
        if ( i < 0 || j < 0 || i >= n || j >= m ) {
            return false;
        }
        if ( grid1[i][j] != c ) return false;
        if ( vis[i][j] ) return true;
        vis[i][j] = true;
        boolean possible = false;
        if ( i + 1 != previ || j != prevj ) {
            possible |= dfs(i + 1, j, c, i, j);
        }
        if ( !possible && (i != previ || j + 1 != prevj) ) {
            possible |= dfs(i, j + 1, c, i, j);
        }
        if ( !possible && (i - 1 != previ || j != prevj) ) {
            possible |= dfs(i - 1, j, c, i, j);
        }
        if ( !possible && (i != previ || j - 1 != prevj) ) {
            possible |= dfs(i, j - 1, c, i, j);
        }
        return possible;
    }


    public String thousandSeparator(int n) {
        if ( n == 0 ) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder st = new StringBuilder(n);
        int k = 0;
        for (int i = st.length() - 1; i >= 0; i--) {
            sb.append(st.charAt(i));
            k++;
            if ( i != 0 && k == 3 ) {
                sb.append('.');
                k = 0;
            }
        }
        return sb.reverse().toString();
    }

    public int minOperations(int[] nums) {
        int ones = 0;
        int twos = 0;
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            int currTwos = 0;
            while (curr > 0) {
                if ( curr % 2 == 0 ) {
                    currTwos++;
                    curr = curr / 2;
                } else {
                    curr--;
                    ones++;
                }
            }
            twos = Math.max(twos, currTwos);
        }
        return ones + twos;

    }

    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int indeg[] = new int[n + 1];
        for (List<Integer> edge : edges) {
            indeg[edge.get(1)]++;
        }
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ( indeg[i] == 0 )
                res.add(i);
        }
        return res;
    }

    public String makeGood(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < n; i++) { //e// E Ee
            if ( isBad(stack.peek(), s.charAt(i)) ) {
//                while (isBad(stack.peek(), s.charAt(i))) {
                stack.pop();
//                }
            } else {
                stack.push(s.charAt(i));
            }

        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public char findKthBit(int n, int k) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append('0');
        if ( n == 1 )
            return sb1.charAt(k - 1);
        for (int i = 2; i <= n; i++) {
            sb1.append('1');
            int len = sb1.length() - 1;
            for (int j = len - 1; j >= 0; j--) {
                if ( sb1.charAt(j) == '0' )
                    sb1.append('1');
                else
                    sb1.append('0');
            }
        }
        System.out.println(sb1.toString());
        return sb1.charAt(k - 1);
    }

    private boolean isBad(Character peek, char charAt) {
        return peek != charAt && (Character.toUpperCase(peek) == charAt ||
                Character.toLowerCase(peek) == charAt);
    }

    public static void main(String[] args) {
        Virtual solver = new Virtual();
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
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
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

public class LC877 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt();
        out.printLine(stoneGame(IOUtils.readIntegerArray(in, n)));
        out.flush();
    }

    public static void main(String[] args) {
        LC877 solver = new LC877();
        solver.solve();
    }

    int dp[][] = new int[500][500];

    public boolean stoneGame(int[] piles) {

        int i = 0, j = piles.length - 1, alex = 0, lee = 0;
        int sum = 0;
        for (int iter : piles) {
            sum += iter;
        }
        for (int it = 0; it < 500; it++) {
            Arrays.fill(dp[it], -1);
        }
//        alex += Math.max(piles[j] + recur(piles, i, j - 1, 1), piles[i] + recur(piles, i + 1, j, 1));
        alex = dpsol(piles);
        if (alex > sum - alex)
            return true;
        else
            return false;
    }

    private int recur(int[] piles, int st, int end, int turn) {

        if (st == end) {
            return 0;
        }
        int right, left;

        if (dp[st + 1][end] == -1)
            right = recur(piles, st + 1, end, turn ^ 1);
        else
            right = dp[st][end];

        if (dp[st][end - 1] == -1)
            left = recur(piles, st, end - 1, turn ^ 1);
        else
            left = dp[st][end - 1];

        if (turn == 0) {
            return Math.max(piles[st] + right, piles[end] + left);
        } else
            return Math.max(right, left);

    }

    private int dpsol(int[] piles) {

        int n = piles.length;
        int dp[][] = new int[n][n];
        for (int it = 0; it < n; it++) {
            Arrays.fill(dp[it], -1);
            dp[it][it] = 0;
        }

        for (int k = 1; k < n; k++) {
            for (int i = 0; i < n - k; i++) {
                if (k % 2 != 0)
                    dp[i][i + k] = Math.max(piles[i] + dp[i + 1][i + k], piles[i + k] + dp[i][i + k - 1]);
                else
                    dp[i][i + k] = Math.max(dp[i + 1][i + k], dp[i][i + k - 1]);
            }
        }
        return dp[0][n - 1];
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
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
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
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
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
            if (filter != null)
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
                if (i != 0)
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
package com.cpprac.codeforces.practice;

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

public class Codeforces1391 {

    ArrayList<Integer>[] two;
    ArrayList<Integer>[] three;

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt(), m = in.readInt();
        String str[] = new String[n + 1];
        for (int i = 0; i < n; i++) {
            str[i] = in.readString();
        }
        if ( n > 3 ) {
            out.printLine("-1");
            out.flush();
            System.exit(0);
        }
        if ( n == 1 ) {
            out.printLine("0");
            out.flush();
            System.exit(0);
        }
        two = new ArrayList[4];
        three = new ArrayList[8];
        fillTwo();
        fillThree();
        int res = 0;
        if ( n == 2 ) {
            res = solveTwo(str, n, m);
        } else {
            res = solveThree(str, n, m);
        }
        out.printLine(res);
        out.flush();
    }

    private int solveThree(String[] str, int n, int m) {
        int dp[][] = new int[m + 1][8];
        int originalMasks[] = new int[m + 1];
        for (int i = 0; i < m; i++) {
            originalMasks[i] = (str[0].charAt(i) - '0') +
                    (2 * (str[1].charAt(i) - '0')) +
                    (4 * (str[2].charAt(i) - '0'));
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < 8; i++) {
            dp[0][i] = changeInBits(originalMasks[0], i, 3);
        }
        for (int i = 1; i < m; i++) {
            for (int pmask = 0; pmask < 8; pmask++) {
                for (Integer possibleMask : three[pmask]) {
                    int change = changeInBits(originalMasks[i], possibleMask, 3);
                    dp[i][possibleMask] = Math.min(dp[i][possibleMask], dp[i - 1][pmask] + change);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 8; i++) {
            res = Math.min(dp[m - 1][i], res);
        }
        return res;
    }

    private int solveTwo(String[] str, int n, int m) {
        int dp[][] = new int[m + 1][5];
        int originalMasks[] = new int[m + 1];
        for (int i = 0; i < m; i++) {
            originalMasks[i] = (str[0].charAt(i) - '0') + (2 * (str[1].charAt(i) - '0'));
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < 4; i++) {
            dp[0][i] = changeInBits(originalMasks[0], i, 2);
        }
        for (int i = 1; i < m; i++) {
            for (int pmask = 0; pmask < 4; pmask++) {
                for (Integer possibleMask : two[pmask]) {
                    int change = changeInBits(originalMasks[i], possibleMask, 2);
                    dp[i][possibleMask] = Math.min(dp[i][possibleMask], dp[i - 1][pmask] + change);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            res = Math.min(dp[m - 1][i], res);
        }
        return res;
    }

    private int changeInBits(int originalMask, int j, int bits) {
        int count = 0;
        int temp = 1;
        for (int i = 0; i <bits; i++) {
            count += (((originalMask & (temp<<i)) == 0 ? 0 : 1) ^ ((j & (temp<<i)) == 0 ? 0 : 1));
        }
        return count;
    }

    private void fillTwo() {
        for (int i = 0; i < 4; i++) {
            two[i] = new ArrayList<>();
        }
        for (int i = 0; i < 4; i++) {
            for (int j = i+1; j < 4; j++) {
                int possible = ((i & 1)>=1?1:0) ^ ((i & 2)>=1?1:0) ^ ((j & 1)==1?1:0) ^ ((j & 2)>=1?1:0);
                if ( possible >= 1 ) {
                    two[i].add(j);
                    two[j].add(i);
                }
            }
        }
    }


    private void fillThree() {
        for (int i = 0; i < 8; i++) {
            three[i] = new ArrayList<>();
        }
        for (int i = 0; i < 8; i++) {
            for (int j = i+1; j < 8; j++) {
                int possible = (((i & 1)>=1?1:0) ^ ((i & 2)>=1?1:0) ^ ((j & 1)>=1?1:0) ^ ((j & 2)>=1?1:0)) &
                        (((i & 2)>=1?1:0) ^ ((i & 4)>=1?1:0) ^ ((j & 2)>=1?1:0) ^ ((j & 4)>=1?1:0));
                if ( possible >= 1 ) {
                    three[i].add(j);
                    three[j].add(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Codeforces1391 solver = new Codeforces1391();
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
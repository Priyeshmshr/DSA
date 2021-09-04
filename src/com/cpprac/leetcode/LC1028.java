package com.cpprac.leetcode;

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

public class LC1028 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        recoverFromPreorder(in.readString());
    }

    public static void main(String[] args) {
        LC1028 solver = new LC1028();
        solver.solve();
    }

    class Pair{
        int index;
        int currCount;

        public Pair(int index, int currCount) {
            this.index = index;
            this.currCount = currCount;
        }
    }

    public TreeNode recoverFromPreorder(String S) {
        TreeNode treeNode = new TreeNode(S.charAt(0) - '0');
        Pair pair = new Pair(1,0);
        int depth = 0;

        for (int i = pair.index; i < S.length() && S.charAt(i) == '-'; i++)
            pair.currCount++;
        pair.index = pair.index + pair.currCount + 1;

        solve(treeNode, S, depth + 1, pair);

        return treeNode;
    }


    private void solve(TreeNode treeNode, String S, int depth, Pair pair) {
        if ( treeNode == null )
            return;


        if ( depth == pair.currCount ) {

            updateNodes(treeNode, S, pair);
        }
        if ( depth < pair.currCount ) {
            solve(treeNode.left, S, depth + 1,pair);
            if ( depth == pair.currCount ) {
                updateNodes(treeNode, S, pair);
            }
            if ( depth < pair.currCount )
                solve(treeNode.right, S, depth + 1, pair);
        }
    }

    private void updateNodes(TreeNode treeNode, String S, Pair pair) {
        int value = getValue(S, pair);
        if ( treeNode.left == null ) {
            treeNode.left = new TreeNode(value);
        } else {
            treeNode.right = new TreeNode(value);
        }

        int prevCount = pair.currCount;
        pair.currCount = 0;
        for (int i = pair.index; i < S.length() && S.charAt(i) == '-'; i++)
            pair.currCount++;
        pair.index = pair.index + pair.currCount + 1;

        if ( prevCount == pair.currCount ) {
            value = getValue(S, pair);
            treeNode.right = new TreeNode(value);
            pair.currCount = 0;
            for (int i = pair.index; i < S.length() && S.charAt(i) == '-'; i++)
                pair.currCount++;
            pair.index = pair.index + pair.currCount + 1;
        }
    }

    private int getValue(String s, Pair pair) {
        int value = 0;
        for (int i = pair.index - 1; i < s.length() && s.charAt(i) != '-'; i++) {
            value = value * 10 + (s.charAt(i) - '0');
            pair.index++;
        }
        pair.index--;
        return value;
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
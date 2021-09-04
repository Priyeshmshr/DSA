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

public class LC654 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int a[] = IOUtils.readIntegerArray(in, in.readInt());
        TreeNode root = constructMaximumBinaryTree(a);
        insertIntoMaxTree(root, in.readInt());
        System.out.println(root);
    }

    public static void main(String[] args) {
        LC654 solver = new LC654();
        solver.solve();
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode tree = createTree(0, nums.length - 1, nums);
        return tree;
    }

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {

        if ( root == null || val > root.val ) {
            TreeNode temp = root;
            root = new TreeNode(val);
            root.left = temp;
            return root;
        }
        if ( root.right != null ) {
            if ( root.right.val > val )
                rightTraverse(root.right, val);
            else
                root.right = rightTraverse(root.right, val);
        } else {
            root.right = new TreeNode(val);
        }
        return root;
    }

    private TreeNode rightTraverse(TreeNode root, int val) {
        if ( root.val > val ) {
            if ( root.right != null ) {
                if ( root.right.val > val )
                    rightTraverse(root.right, val);
                else
                    root.right = rightTraverse(root.right, val);
            } else {
                root.right = new TreeNode(val);
            }
        } else if ( root.val < val ) {
            TreeNode temp = root;
            root = new TreeNode(val);
            root.left = temp;
            return root;
        }
        return null;
    }

    private TreeNode createTree(int st, int end, int[] nums) {

        if ( st > end )
            return null;

        int pivot = maxNode(nums, st, end);
        TreeNode tree = new TreeNode(nums[pivot]);
        tree.left = createTree(st, pivot - 1, nums);
        tree.right = createTree(pivot + 1, end, nums);
        return tree;

    }

    private int maxNode(int[] nums, int i, int i1) {
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int iter = i; iter <= i1; iter++) {
            if ( max < nums[iter] ) {
                max = nums[iter];
                index = iter;
            }
        }
        return index;
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
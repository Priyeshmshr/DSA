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

public class LC1110 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
    }

    public static void main(String[] args) {
        LC1110 solver = new LC1110();
        solver.solve();
    }

    ArrayList<TreeNode> res = new ArrayList<>();
    List<Integer> list;
    Set<Integer> set = new HashSet<>();

    public List<TreeNode> delNodes1(TreeNode root, int[] to_delete) {

        if ( root == null )
            return res;

        for (int de : to_delete) {
            set.add(de);
        }

        if ( set.contains(root.val) ) {
            root.val = -1;
        } else {
            res.add(root);
        }
        deleteNodes(root.left, root.val, root);
        deleteNodes(root.right, root.val, root);

        return res;
    }


    private void deleteNodes(TreeNode root, int parentVal, TreeNode parentNode) {

        if ( root == null ) {
            return;
        }

        if ( set.contains(root.val) ) {
            set.remove((Integer)root.val);
            root.val = -1;
        }

        if ( parentVal != -1 && root.val == -1 ) {
            if ( parentNode.right != null && parentNode.right.val == -1 )
                parentNode.right = null;
            else
                parentNode.left = null;
        }

        if ( parentVal == -1 && root.val != -1 ) {
            res.add(root); //Adding current node as root of the new tree.
        }

        deleteNodes(root.left, root.val, root);
        deleteNodes(root.right, root.val, root);
    }


    /*Queue<TreeNode> queue = new LinkedList<>(Collections.singletonList(root));



        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();
            int index = Collections.binarySearch(list, node.val);

            if ( index >= 0 ) {
                list.remove((Integer) node.val);
//                res.remove(node);
                node.val = -1;
            }

            if ( node.left != null ) {
                queue.offer(node.left);
//                if(index>=0)
//                    res.add(node.left);
            }

            if ( node.right != null ) {
                queue.offer(node.right);
//                if(index>=0)
//                    res.add(node.right);
            }
        }

        if ( root.val != -1 )
            res.add(root);*/
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
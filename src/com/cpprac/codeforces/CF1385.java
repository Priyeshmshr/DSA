package com.cpprac.codeforces;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CF1385 {

    ArrayList<HashSet<Integer>> graph;
    int leaves[];
    int n;
    LinkedList<Integer> queue;

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            n = in.readInt();
            int k = in.readInt();
            graph = new ArrayList<>();
            for (int i = 1; i <= n + 1; i++) {
                graph.add(new HashSet<>());
            }
            leaves = new int[n + 3];
            for (int i = 0; i < n - 1; i++) {
                int x = in.readInt(), y = in.readInt();
                graph.get(x).add(y);
                graph.get(y).add(x);
            }
            if ( k < 2 ) {
                out.printLine(n - 1);
                out.flush();
                continue;
            }
            for (int i = 1; i <= n; i++) {
                if ( graph.get(i).size() == 1 ) {
                    leaves[graph.get(i).iterator().next()]++;
                }
            }
            queue = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                if ( leaves[i] >= k )
                    queue.addLast(i);
            }
            int res = 0;
            while (!queue.isEmpty()) {
                Integer u = queue.pollFirst();
                int toDelete = (leaves[u] / k) * k;
                res += leaves[u] / k;
                leaves[u] -= toDelete;
                ArrayList<Integer> leafs = new ArrayList<>();
                for (Integer v : graph.get(u)) {
                    if ( graph.get(v).size() == 1 ) {
                        graph.get(v).remove(u);
                        leafs.add(v);
                        toDelete--;
                    }
                    if ( toDelete == 0 ) break;
                }
                graph.get(u).removeAll(leafs);
                if ( graph.get(u).size() == 1 ) {
                    Integer node = graph.get(u).iterator().next();
                    leaves[node]++;
                    if ( leaves[node] == k ) {
                        queue.addLast(node);
                    }
                }
            }
            out.printLine(res);
            out.flush();
        }
    }

    public static void main(String[] args) {
        CF1385 solver = new CF1385();
        solver.solve();
//        new Thread(null, new Runnable() {
//            public void run() {
//
//            }
//        }, "1", 1 << 29).start();
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
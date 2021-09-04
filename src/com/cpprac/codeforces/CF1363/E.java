package com.cpprac.codeforces.CF1363;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class E {

    long cost[];
    int initDigit[];
    int goalDigit[];
    Map<Integer, ArrayList<Integer>> graph;
    int zeros[], ones[];
    int vis[];

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt();
        cost = new long[n + 1];
        initDigit = new int[n + 1];
        goalDigit = new int[n + 1];
        zeros = new int[n + 1];
        ones = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cost[i] = in.readLong();
            initDigit[i] = in.readInt();
            goalDigit[i] = in.readInt();
        }
        graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int u = in.readInt(), v = in.readInt();
            ArrayList<Integer> list = graph.getOrDefault(u, new ArrayList<>());
            list.add(v);
            graph.put(u, list);
            list = graph.getOrDefault(v, new ArrayList<>());
            list.add(u);
            graph.put(v, list);
        }
        vis = new int[n + 1];
        dfs1(1, cost[1]);
        if ( zeros[1] != ones[1] ) {
            out.printLine(-1);
        } else {
            out.printLine(result);
        }
        out.flush();
    }

    private long dfs1(int source, long mini) {
        vis[source] = 1;
        cost[source] = Math.min(cost[source], mini);
        ArrayList<Integer> list = graph.get(source);
        if ( initDigit[source] != goalDigit[source] ) {
            if ( initDigit[source] == 0 )
                zeros[source]++;
            else
                ones[source]++;
        }
        int changed = 0;
        for (int i : list) {
            if ( vis[i] == 0 ) {
                changed+=dfs1(i, Math.min(mini, cost[source]));
                zeros[source] += zeros[i];
                ones[source] += ones[i];
            }
        }
        int left = 2 * Math.min(zeros[source], ones[source]) - changed;
        result += left * cost[source];
        return changed + left;
    }

    private long result;

    public static void main(String[] args) {
        E solver = new E();
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
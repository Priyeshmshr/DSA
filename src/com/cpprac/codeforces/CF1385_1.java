package com.cpprac.codeforces;

import javafx.util.Pair;

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

public class CF1385_1 {
    ArrayList<Integer>[] graph;
    Stack<Pair<Integer, Integer>> undirectedEdges;
    int vis[];
    int vis1[];

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt(), m = in.readInt();
            graph = new ArrayList[n + 2];
            undirectedEdges = new Stack<>();
            vis = new int[n + 2];
            for (int i = 0; i < n + 1; i++) {
                graph[i] = new ArrayList<>();
            }
            while (m-- > 0) {
                int ti = in.readInt(), xi = in.readInt(), yi = in.readInt();
                if ( ti == 0 ) {
                    undirectedEdges.add(new Pair(xi, yi));
                } else {
                    graph[xi].add(yi);
                }
            }
            boolean isCycle = false;
            for (int i = 1; i <= n; i++) {
                if ( vis[i] == 0){
                    vis1 = new int[n+2];
                    if( isCycle(i) ) {
                        isCycle = true;
                        break;
                    }
                }
            }
            while (!isCycle && !undirectedEdges.isEmpty()) {
                Pair<Integer, Integer> edge = undirectedEdges.pop();
                vis1 = new int[n + 2];
                vis1[edge.getKey()] = 1;
                int direction = 0;
                isCycle = false;
                for (Integer v : graph[edge.getValue()]) {
                    if ( isCycle(v) ) {
                        isCycle = true;
                        direction = 1;
                        break;
                    }
                }
                if ( isCycle ) {
                    vis1 = new int[n + 2];
                    vis1[edge.getValue()] = 1;
                    isCycle = false;
                    for (Integer v : graph[edge.getKey()]) {
                        if ( isCycle(v) ) {
                            isCycle = true;
                            break;
                        }
                    }
                }
                if ( isCycle ) {
                    break;
                } else {
                    if ( direction == 0 )
                        graph[edge.getKey()].add(edge.getValue());
                    else
                        graph[edge.getValue()].add(edge.getKey());
                }
            }
            if ( isCycle ) {
                out.printLine("NO");
                out.flush();
            } else {
                out.printLine("YES");
                for (int xi = 1; xi < n + 1; xi++) {
                    if ( graph[xi].size() > 0 ) {
                        for (Integer yi : graph[xi]) {
                            out.printLine(xi + " " + yi);
                        }
                    }
                }
            }
            out.flush();
        }
    }

    private boolean isCycle(int u) {
        if ( vis1[u] == 1 )
            return true;
        vis1[u] = 1;
        vis[u] = 1;
        for (Integer v : graph[u]) {
            if ( isCycle(v) )
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        CF1385_1 solver = new CF1385_1();
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
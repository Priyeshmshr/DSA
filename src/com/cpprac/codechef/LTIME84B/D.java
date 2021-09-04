package com.cpprac.codechef.LTIME84B;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class D {
    Map<Integer, ArrayList<Integer>> graph;

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt(), q = in.readInt();
            int ar[] = IOUtils.readIntegerArray(in, n);
            graph = new HashMap<>();
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
            parent = new int[n + 1];
            level = new int[n + 1];
            level[1] = 1;
            dfs(1, 1);
            while (q-- > 0) {
                int a = in.readInt(), b = in.readInt();
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(ar[a - 1]);
                arr.add(ar[b - 1]);
                while (arr.size() <= 100 && level[a] > level[b]) {
                    a = parent[a];
                    if ( a != b ) {
                        arr.add(ar[a - 1]);
                    }
                }
                while (arr.size() <= 100 && level[a] < level[b]) {
                    b = parent[b];
                    if ( a != b )
                        arr.add(ar[b - 1]);
                }
                while (arr.size() <= 100 && a != b) {
                    a = parent[a];
                    if ( a != b )
                        arr.add(ar[a - 1]);
                    b = parent[b];
                    if ( a != b )
                        arr.add(ar[b - 1]);
                }
                if ( arr.size() > 100 )
                    out.printLine(0);
                else {
                    Collections.sort(arr);
                    long ans = Integer.MAX_VALUE;
                    for (int i = 1; i < arr.size(); i++) {
                        ans = Math.min(ans, Math.abs(arr.get(i) - arr.get(i - 1)));
                    }
                    out.printLine(ans);
                }
            }
        }
        out.flush();
    }

    int vis[];
    int parent[];
    int level[];

    private void dfs(int source, int lev) {
        vis[source] = 1;
        ArrayList<Integer> list = graph.get(source);
        for (int i : list) {
            if ( vis[i] == 0 ) {
                parent[i] = source;
                level[i] = lev + 1;
                dfs(i, lev + 1);
            }
        }
    }

    public static void main(String[] args) {
        D solver = new D();
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
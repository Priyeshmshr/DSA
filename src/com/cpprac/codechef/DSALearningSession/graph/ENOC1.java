package com.cpprac.codechef.DSALearningSession.graph;

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

public class ENOC1 {

    ArrayList<Integer> graph[];
    long ai[];
    long xorFromRoot[];
    int level[];
    int parent[];
    int sqrtPar[];

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt(), q = in.readInt();
            ai = IOUtils.readLongArray(in, n);
            graph = new ArrayList[n + 1];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < n - 1; i++) {
                int u = in.readInt() - 1, v = in.readInt() - 1;
                graph[u].add(v);
                graph[v].add(u);
            }
            xorFromRoot = new long[n + 1];
            storeXORFromRoot(0, -1, 0);
            level = new int[n + 1];
            parent = new int[n + 1];
            sqrtPar = new int[n + 1];
            dfs(0, -1, 0, (int) Math.sqrt(n));
            while (q-- > 0) {
                int pi = in.readInt() - 1, qi = in.readInt() - 1;
                out.printLine(xorFromRoot[pi] ^ xorFromRoot[qi] ^ ai[lca(pi, qi)]);
            }
            out.flush();
        }
    }

    void dfs(int source, int par, int lev, int sq) {
        level[source] = lev;
        parent[source] = par;

        if ( level[source] < sq ) {
            sqrtPar[source] = 0;
        } else if ( level[source] % sq == 0 ) {
            sqrtPar[source] = parent[source];
        } else
            sqrtPar[source] = sqrtPar[parent[source]];
        for (Integer v : graph[source]) {
            if ( v != par )
                dfs(v, source, lev + 1, sq);
        }
    }

    int lca(int a, int b) {
        while (sqrtPar[a] != sqrtPar[b]) {
            if ( level[a] < level[b] ) {
                b = sqrtPar[b];
            } else
                a = sqrtPar[a];
        }
        while (a != b) {
            if ( level[a] < level[b] ) {
                b = parent[b];
            } else
                a = parent[a];
        }
        return a;
    }

    private void storeXORFromRoot(int source, int par, long xor) {
        xor = xor ^ ai[source];
        xorFromRoot[source] = xor;
        for (Integer v : graph[source]) {
            if ( v != par )
                storeXORFromRoot(v, source, xor);
        }
    }

    public static void main(String[] args) {
        ENOC1 solver = new ENOC1();
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
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

public class E342_2 {
    long ans[];
    ArrayList<Integer> graph[];
    int mom[];
    int size[];
    int n, m;

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        n = in.readInt();
        m = in.readInt();
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int a = in.readInt(), b = in.readInt();
            a--;
            b--;
            graph[a].add(b);
            graph[b].add(a);
        }
        CentroidDecomposition cd = new CentroidDecomposition(n);
        cd.decompose(0, -1);
        ans = new long[n + 1];
        Arrays.fill(ans, n + 5);
        ans[0]=0;
        cd.propagateToRoot(0);
        while (m-- > 0) {
            int ti = in.readInt(), vi = in.readInt();
            vi--;
            if ( ti == 1 ) {
                ans[vi] = 0;
                cd.propagateToRoot(vi);
            } else {
                out.printLine(cd.query(vi));
                out.flush();
            }
        }
        out.flush();
    }

    class CentroidDecomposition {
        LCA lca;

        CentroidDecomposition(int n) {
            mom = new int[n + 1];
            size = new int[n + 1];
            lca = new LCA(n);
            lca.dfs(0, 0, 0);
            lca.preProcessDp();
        }

        void decompose(int source, int parent) {

            int subTreeSize = sizeSubTree(source, parent);
            Integer centroid = findCentroid(source, parent, subTreeSize);
            if ( parent == -1 ) parent = centroid;
            mom[centroid] = parent;

            while (graph[centroid].size() > 0) {
                Integer v = graph[centroid].get(0);
                graph[centroid].remove(v);
                graph[v].remove(centroid);
                decompose(v, centroid);
            }
        }

        int findCentroid(int source, int parent, int subTreeSize) {
            for (Integer v : graph[source]) {
                if ( v != parent && size[v] > subTreeSize / 2 ) {
                    return findCentroid(v, source, subTreeSize);
                }
            }
            return source;
        }

        int sizeSubTree(int source, int parent) {
            size[source] = 1;
            for (Integer v : graph[source]) {
                if ( v != parent ) {
                    size[source] += sizeSubTree(v, source);
                }
            }
            return size[source];
        }


        //-------Problem specific code starts here-----

        void propagateToRoot(int a) {
            int b = a;
            while (mom[b] != b) {
                b = mom[b];
                int dist = lca.level[a] + lca.level[b] - 2 * lca.level[lca.query(a, b)];
                ans[b] = Math.min(ans[b], dist);
            }
        }

        long query(int a) {
            long res = ans[a];
            int b = a;
            while (mom[b] != b) {
                b = mom[b];
                int dist = lca.level[a] + lca.level[b] - 2 * lca.level[lca.query(a, b)];
                res = Math.min(res, dist + ans[b]);
            }
            return res;
        }
    }

    class LCA {

        int dp[][];
        int level[];
        int parent[];

        LCA(int n) {
            dp = new int[n + 1][50];
            level = new int[n + 1];
            parent = new int[n + 1];
        }

        private void dfs(int source, int par, int lev) {
            parent[source] = par;
            level[source] = lev;
            for (Integer neighbours : graph[source]) {
                if ( neighbours != par )
                    dfs(neighbours, source, lev + 1);
            }
        }

        private void preProcessDp() {
            int i, j;
            for (i = 0; i < n; i++)
                for (j = 0; 1 << j < n; j++)
                    dp[i][j] = -1;

            for (i = 0; i < n; i++)
                dp[i][0] = parent[i];

            for (j = 1; 1 << j < n; j++)
                for (i = 0; i < n; i++)
                    if ( dp[i][j - 1] != -1 )
                        dp[i][j] = dp[dp[i][j - 1]][j - 1];
        }

        public int query(int p, int q) {
            int tmp, log, i;
            if ( level[p] < level[q] ) {
                tmp = p;
                p = q;
                q = tmp;
            }
            for (log = 1; 1 << log <= level[p]; log++) ;
            log--;

            for (i = log; i >= 0; i--)
                if ( level[p] - (1 << i) >= level[q] )
                    p = dp[p][i];

            if ( p == q )
                return p;

            for (i = log; i >= 0; i--) {
                if ( dp[p][i] != -1 && dp[p][i] != dp[q][i] ) {
                    p = dp[p][i];
                    q = dp[q][i];
                }
            }

            return parent[p];
        }
    }

    public static void main(String[] args) {
        E342_2 solver = new E342_2();
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
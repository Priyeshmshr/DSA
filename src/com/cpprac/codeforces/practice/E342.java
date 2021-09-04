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

public class E342 {

    int level[];
    long dist[];
    ArrayList<Integer> graph[];
    int vis[];
    ArrayList<Integer> redList;
    LcaBySqrt lca;

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt(), m = in.readInt();
        lca = new LcaBySqrt(n);
        for (int i = 0; i < n - 1; i++) {
            int a = in.readInt(), b = in.readInt();
            a--;
            b--;
            lca.addEdge(a, b);
        }
        lca.findParent();
        lca.preProcessDp();
        redList = new ArrayList<>();
        redList.add(0);
        dist = new long[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        int sq = (int) Math.sqrt(m);
        int i = 1;
        //m
        while (m-- > 0) {
            int ti = in.readInt(), vi = in.readInt();
            vi--;
            if ( ti == 1 ) {
                redList.add(vi);
                dist[vi] = 0;
            } else {
                long ans = Integer.MAX_VALUE;
                if ( dist[vi] > 0 ) {
                    // sqrt(m)*sqrt(n)
                    for (Integer reds : redList) {
                        int lcaa = lca.query(reds, vi);
                        ans = Math.min(ans, level[reds] + level[vi] - 2 * level[lcaa]);
                    }
                    ans = Math.min(ans, dist[vi]);
                    dist[vi] = ans;
                }
                out.printLine(dist[vi]);
            }
            if ( i == sq ) {
                vis = new int[n + 1];
                // n*sqrt(m)*sqrt(n)
                updateDistance(1, redList);
                redList = new ArrayList<>();
                i = 0;
            }
            i++;
        }
        //m*sqrt(m)*sqrt(n)+n*sqrt(m)*sqrt(n) if (m==n) then sqrt(m)=sqrt(n) -> sqrt(n)*sqrt(n) = n, so Complexity; O(m*n)
        out.flush();
    }

    private void updateDistance(int source, ArrayList<Integer> redList) {
        vis[source] = 1;
        if ( dist[source] > 0 ) {
            for (Integer reds : redList) {
                int lcaa = lca.query(reds, source);
                dist[source] = Math.min(dist[source], level[reds] + level[source] - 2 * level[lcaa]);
            }
        }
        for (Integer neighbours : graph[source]) {
            if ( vis[neighbours] == 0 ) {
                updateDistance(neighbours, redList);
            }
        }
    }

    public static void main(String[] args) {
        E342 solver = new E342();
        solver.solve();
    }

    class LcaBySqrt {
        int parent[];
        int sqrtParent[];
        int size;
        int dp[][];

        public LcaBySqrt(int size) {
            this.size = size;
            level = new int[size + 1];
            parent = new int[size + 1];
            sqrtParent = new int[size + 1];
            dp = new int[size + 1][50];
            graph = new ArrayList[size + 1];
            for (int i = 0; i <= size; i++) {
                graph[i] = new ArrayList<>();
            }
        }

        public void addEdge(int a, int b) {
            graph[a].add(b);
            graph[b].add(a);
        }

        public void findParent() {
            vis = new int[size + 1];
            dfs(0, 0, 0);
        }

        public void decompose() {
            vis = new int[size + 1];
            sqrtDecompose(0, (int) (Math.sqrt(size)), 0, 0);
        }

        public int findLca(int x, int y) {
            while (sqrtParent[x] != sqrtParent[y]) {
                if ( level[x] > level[y] ) {
                    x = sqrtParent[x];
                } else
                    y = sqrtParent[y];
            }
            while (x != y) {
                if ( level[x] > level[y] )
                    x = parent[x];
                else
                    y = parent[y];
            }
            return x;
        }

        private void preProcessDp() {
            int i, j;

            for (i = 0; i < size; i++)
                for (j = 0; 1 << j < size; j++)
                    dp[i][j] = -1;

            for (i = 0; i < size; i++)
                dp[i][0] = parent[i];

            for (j = 1; 1 << j < size; j++)
                for (i = 0; i < size; i++)
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

        private void sqrtDecompose(int source, int sq, int par, int lev) {
            vis[source] = 1;
            parent[source] = par;
            level[source] = lev;
            if ( level[source] < sq ) {
                sqrtParent[source] = 1;
            } else if ( level[source] % sq == 0 ) {
                sqrtParent[source] = parent[source];
            } else {
                sqrtParent[source] = sqrtParent[parent[source]];
            }
            for (Integer neighbours : graph[source]) {
                if ( vis[neighbours] == 0 )
                    sqrtDecompose(neighbours, sq, source, lev + 1);
            }
        }

        private void dfs(int source, int par, int lev) {
            vis[source] = 1;
            parent[source] = par;
            level[source] = lev;
            for (Integer neighbours : graph[source]) {
                if ( vis[neighbours] == 0 )
                    dfs(neighbours, source, lev + 1);
            }
        }
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
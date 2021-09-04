package com.cpprac.codeforces.CF1350;

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
import java.util.Queue;
import java.util.Set;

public class E {

    class Pair {
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    int dp[][];
    String s[];
    int n, m;

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        n = in.readInt();
        m = in.readInt();
        int t = in.readInt();

        s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = in.readString();
        }

        dp = new int[n][m];
        vis = new int[n + 1][m + 1];
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean done = false;
                if ( i < n - 1 && s[i + 1].charAt(j) == s[i].charAt(j) ) {
                    if ( vis[i + 1][j] == 0 ) {
                        queue.add(new Pair(i + 1, j));
                        vis[i + 1][j] = 1;
                        done = true;
                    }
                    dp[i + 1][j] = 1;
                }
                if ( j < m - 1 && s[i].charAt(j + 1) == s[i].charAt(j) ) {
                    dp[i][j + 1] = 1;
                    if ( vis[i][j + 1] == 0 ) {
                        queue.add(new Pair(i, j + 1));
                        vis[i][j + 1] = 1;
                        done = true;
                    }
                }
                if ( i > 0 && s[i - 1].charAt(j) == s[i].charAt(j) ) {
                    dp[i - 1][j] = 1;
                    if ( vis[i - 1][j] == 0 ) {
                        queue.add(new Pair(i - 1, j));
                        vis[i - 1][j] = 1;
                        done = true;
                    }
                }
                if ( j > 0 && s[i].charAt(j - 1) == s[i].charAt(j) ) {
                    dp[i][j - 1] = 1;
                    if ( vis[i][j - 1] == 0 ) {
                        queue.add(new Pair(i, j - 1));
                        vis[i][j - 1] = 1;
                        done = true;
                    }
                }
                if ( done && vis[i][j] == 0 ) {
                    vis[i][j] = 1;
                    dp[i][j]=1;
                    queue.add(new Pair(i, j));
                }
            }
        }

        if ( !queue.isEmpty() || queue.size() != n * m ) {
            bfs(queue);
        }

        while (t-- > 0) {
            int i = in.readInt(), j = in.readInt(), p = in.readInt();
            int color = dp[i - 1][j - 1] == 0 ? 0 : p - dp[i - 1][j - 1] + 1;
            out.printLine(color % 2 == 0 ? 0 : 1);
        }
        out.flush();
    }


    int vis[][];

    private void bfs(Queue<Pair> queue) {

        while(!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                Pair curr = queue.poll();
                int i = curr.i;
                int j = curr.j;
                if ( i < n - 1 ) {
                    if ( s[i + 1].charAt(j) == s[i].charAt(j) ) {
                        dp[i + 1][j] = Math.max(dp[i][j], dp[i + 1][j]);
                    } else {
                        dp[i + 1][j] = Math.max(dp[i][j] + 1, dp[i + 1][j]);
                    }
                    if ( vis[i + 1][j] == 0 ) {
                        queue.add(new Pair(i + 1, j));
                        vis[i + 1][j] = 1;
                    }
                }
                if ( j < m - 1 ) {
                    if ( s[i].charAt(j + 1) == s[i].charAt(j) ) {
                        dp[i][j + 1] = Math.max(dp[i][j], dp[i][j + 1]);
                    } else {
                        dp[i][j + 1] = Math.max(dp[i][j] + 1, dp[i][j + 1]);
                    }
                    if ( vis[i][j + 1] == 0 ) {
                        queue.add(new Pair(i, j + 1));
                        vis[i][j + 1] = 1;
                    }
                }
                if ( i > 0 ) {
                    if ( s[i - 1].charAt(j) == s[i].charAt(j) ) {
                        dp[i - 1][j] = Math.max(dp[i][j], dp[i - 1][j]);
                    } else {
                        dp[i - 1][j] = Math.max(dp[i][j] + 1, dp[i - 1][j]);
                    }
                    if ( vis[i - 1][j] == 0 ) {
                        queue.add(new Pair(i - 1, j));
                        vis[i - 1][j] = 1;
                    }
                }
                if ( j > 0 ) {
                    if ( s[i].charAt(j - 1) == s[i].charAt(j) ) {
                        dp[i][j - 1] = Math.max(dp[i][j], dp[i][j - 1]);
                    } else {
                        dp[i][j - 1] = Math.max(dp[i][j] + 1, dp[i][j - 1]);
                    }
                    if ( vis[i][j - 1] == 0 ) {
                        queue.add(new Pair(i, j - 1));
                        vis[i][j - 1] = 1;
                    }
                }
                size--;
            }
        }
    }

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
package com.cpprac.codeforces.CF1365;

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

public class D {

    int vis[][];
    char[][] maze;
    int n, m;

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            n = in.readInt();
            m = in.readInt();
            maze = new char[n][m];
            for (int i = 0; i < n; i++) {
                maze[i] = in.readString().toCharArray();
            }
            if(maze[n-1][m-1]=='B'|| maze[n-1][m-1]=='#'){
                out.printLine("NO");
                out.flush();
                System.exit(0);
            }
            vis = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if ( vis[i][j] == 0 && maze[i][j] == 'B' ) {
                        blockDfs(i, j);
                    }
                }
            }
            res = new boolean[n][m];
            vis = new int[n][m];
            boolean posssible = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if ( maze[i][j] == 'G' ) {
                        if ( !goodCan(i, j) ) {
                            posssible = false;
                            break;
                        }
                    }
                }
                if ( !posssible )
                    break;
            }
            out.printLine(posssible ? "Yes" : "No");
            out.flush();
        }
    }
    boolean res[][];
    private boolean goodCan(int i, int j) {
        if ( i < 0 || j < 0 || i > n - 1 || j > m - 1 )
            return false;
        if ( i == n - 1 && j == m - 1 && (maze[i][j] == 'G' || maze[i][j] == '.') ) {
            res[i][j] = true;
            return true;
        }
        if ( vis[i][j] == 1 )
            return res[i][j];
        if ( maze[i][j] == '#' ) {
            res[i][j] = false;
            return false;
        } else {
            vis[i][j] = 1;
            boolean possible = false;
            possible = goodCan(i + 1, j);
            if ( !possible )
                possible |= goodCan(i, j + 1);
            if ( !possible )
                possible |= goodCan(i - 1, j);
            if ( !possible )
                possible |= goodCan(i, j - 1);
            res[i][j] = possible;
            return possible;
        }
    }

    private void blockDfs(int i, int j) {
        if ( i < 0 || j < 0 || i > n - 1 || j > m - 1 )
            return;
        if ( vis[i][j] == 1 ) {
            return;
        }
        if ( maze[i][j] == '#' )
            return;
        if ( maze[i][j] == '.' ) {
            maze[i][j] = '#';
        } else {
            vis[i][j] = 1;
            blockDfs(i - 1, j);
            blockDfs(i, j - 1);
            blockDfs(i + 1, j);
            blockDfs(i, j + 1);
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
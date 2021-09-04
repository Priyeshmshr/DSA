package com.cpprac.codeforces.CF1345;

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

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt(), m = in.readInt();
        String[] mat = new String[n];
        for (int i = 0; i < n; i++) {
            mat[i] = in.readString();
        }
        boolean flag = true;
        boolean blackFoundOverall = false;
        for (int i = 0; i < n; i++) {
            int blackFound = 0, whiteFound = 0;
            for (int j = 0; j < m; j++) {
                if ( mat[i].charAt(j) == '#' ) {
                    blackFoundOverall = true;
                    blackFound = 1;
                    if ( whiteFound == 1 ) {
                        flag = false;
                        break;
                    }
                }
                if ( mat[i].charAt(j) == '.' ) {
                    if ( blackFound == 1 ) {
                        whiteFound = 1;
                    }
                }
            }
            if(blackFound==0){
                flag=false;
            }
        }
        if ( flag ) {
            for (int i = 0; i < m; i++) {
                int blackFound = 0, whiteFound = 0;
                for (int j = 0; j < n; j++) {
                    if ( mat[j].charAt(i) == '#' ) {
                        blackFound = 1;
                        if ( whiteFound == 1 ) {
                            flag = false;
                            break;
                        }
                    }
                    if ( mat[j].charAt(i) == '.' ) {
                        if ( blackFound == 1 ) {
                            whiteFound = 1;
                        }
                    }
                }
                if(blackFound==0){
                    flag=false;
                }
                if(!flag)
                    break;
            }
        }
        if ( flag  || !blackFoundOverall)
            out.printLine(dfsUtil(mat, n, m));
        else
            out.printLine(-1);
        out.flush();
    }

    int vis[][];

    private int dfsUtil(String[] mat, int n, int m) {
        vis = new int[n][m];
        int connectedComponents = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ( vis[i][j] == 0 && mat[i].charAt(j)=='#') {
                    dfs(mat, n, m, vis, i, j);
                    connectedComponents++;
                }
            }
        }
        return connectedComponents;

    }

    private void dfs(String[] mat, int n, int m, int[][] vis, int i, int j) {
        vis[i][j] = 1;
        if ( i > 0 && mat[i - 1].charAt(j) == '#' && vis[i - 1][j] == 0 ) {
            dfs(mat, n, m, vis, i - 1, j);
        }
        if ( i < n - 1 && mat[i + 1].charAt(j) == '#' && vis[i + 1][j] == 0 ) {
            dfs(mat, n, m, vis, i + 1, j);
        }
        if ( j > 0 && mat[i].charAt(j - 1) == '#' && vis[i][j - 1] == 0 ) {
            dfs(mat, n, m, vis, i, j - 1);
        }
        if ( j < m - 1 && mat[i].charAt(j + 1) == '#' && vis[i][j + 1] == 0  ) {
            dfs(mat, n, m, vis, i, j + 1);
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
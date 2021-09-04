package com.cpprac.HackerEarth;

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
import java.util.PriorityQueue;
import java.util.Set;

public class JulyCircuit20 {
    int n;
    long graph[][];
    long INF = Integer.MAX_VALUE;
    ArrayList<Long> s[][];

    public void buildST(int st, int end, int id) {
        if ( end - st < 2 ) {
            for (int i = 0; i < n; i++) {
                for (int j = st; j < end; j++) {
                    s[id][i] = new ArrayList<Long>();
                    s[id][i].add(graph[j][i]);
                }
            }
            return;
        }
        int mid = (st + end) / 2;
        buildST(st, mid, id * 2);
        buildST(mid, end, id * 2 + 1);
        for (int i = 0; i < n; i++) {
            ArrayList<Long> child = s[id * 2][i];
            child.addAll(s[id * 2 + 1][i]);
            if ( s[id][i] == null ) s[id][i] = new ArrayList<>();
            s[id][i].addAll(child);
        }
    }

    public ArrayList<Long> queryST(int x, int y, int id, int st, int end, int u) {
        if ( y <= st || x >= end ) {
            return new ArrayList<>();
        }
        if ( x <= st && y >= end ) {
            return s[id][u];
        }
        int mid = (st + end) / 2;
        ArrayList<Long> child = queryST(x, y, id * 2, st, mid, u);
        child.addAll(queryST(x, y, id * 2 + 1, mid, end, u));
        return child;
    }

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        n = in.readInt();
        graph = new long[n + 2][n + 2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if ( i == j )
                    graph[i][j] = 0;
                else
                    graph[i][j] = INF;
            }
        }
        int m = in.readInt();
        for (int i = 0; i < m; i++) {
            int a = in.readInt() - 1, b = in.readInt() - 1, c = in.readInt();
            graph[a][b] = c;
            graph[b][a] = c;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        int q = in.readInt();
        while (q-- > 0) {
            int u = in.readInt() - 1, l = in.readInt() - 1, r = in.readInt() - 1, k = in.readInt() - 1;
            PriorityQueue<Long> pq = new PriorityQueue<>();
            for (int i = l; i <= r; i++) {
                pq.add(graph[i][u]);
            }
            for (int i = 0; i < k; i++) {
                pq.poll();
            }
            out.printLine(pq.peek());
        }
        out.flush();
    }


    public static void main(String[] args) {
        new Thread(null, () -> {
            JulyCircuit20 solver = new JulyCircuit20();
            solver.solve();
        }, "1", 1 << 26).start();
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
package com.cpprac.codechef.DSALearningSession.AdvancedDS;

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

public class MSTICK {

    class Pair {
        long min, max;

        public Pair(long min, long max) {
            this.min = min;
            this.max = max;
        }
    }

    Pair s[];
    long a[];

    public void buildRangeMinQueryST(int st, int end, int id) {
        if ( end - st < 2 ) {
            s[id] = new Pair(a[st], a[st]);
            return;
        }
        int mid = (st + end) / 2;
        buildRangeMinQueryST(st, mid, id * 2);
        buildRangeMinQueryST(mid, end, id * 2 + 1);
        s[id] = new Pair(Math.min(s[id * 2].min, s[id * 2 + 1].min), Math.max(s[id * 2].max, s[id * 2 + 1].max));
    }

    public Pair queryST(int x, int y, int id, int st, int end) {
        if ( y <= st || x >= end ) {
            return new Pair(Long.MAX_VALUE, Long.MIN_VALUE);
        }
        if ( x <= st && y >= end ) {
            return s[id];
        }
        int mid = (st + end) / 2;
        Pair left = queryST(x, y, id * 2, st, mid);
        Pair right = queryST(x, y, id * 2 + 1, mid, end);
        return new Pair(Math.min(left.min, right.min), Math.max(left.max, right.max));
    }

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt();
        a = IOUtils.readLongArray(in, n);
        s = new Pair[4 * n + 5];
        buildRangeMinQueryST(0, n, 1);
        int q = in.readInt();
        while (q-- > 0) {
            int l = in.readInt(), r = in.readInt();
            Pair center = queryST(l, r + 1, 1, 0, n);
            Pair left = queryST(0, l, 1, 0, n);
            Pair right = queryST(r + 1, n, 1, 0, n);
            long min = center.min;
            double ans = 1.0 * min + Math.max(Math.max(left.max, right.max), ((center.max - min) / 2.0));
            out.printLine(String.format("%.1f",ans));
        }
        out.flush();
    }

    public static void main(String[] args) {
        MSTICK solver = new MSTICK();
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
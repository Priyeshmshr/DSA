package com.cpprac.codeforces.CF1368;

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

    /*public void solve5th() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt();
        TreeSet<Long> set = new TreeSet<>(Comparator.reverseOrder());
        HashMap<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long a = in.readLong();
            set.add(a);
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        ArrayList<Long> squares = new ArrayList<>();
        while (!set.isEmpty()) {
            Long curr = set.first();
            set.remove(curr);
            map.put(curr, map.getOrDefault(curr, 0) - 1);
            long x = curr;
            long mask = 1 << 30;
            while (mask > 0) {
                if ( mask < curr && (mask & curr) == 0 ) {
                    Long y = set.higher(mask << 1);
                    if ( y != null && y > mask ) {
                        map.put(y, map.getOrDefault(y, 0) - 1);
                        if ( map.get(y) <= 0 ) {
                            set.remove(y);
                            map.remove(y);
                        }
                        long newv = y & x;
                        set.add(newv);
                        map.put(newv, map.getOrDefault(newv, 0) + 1);
                        x = x | y;
                    }
                }
                mask = mask >> 1;
            }
            squares.add(x * x);
        }
        for (Map.Entry<Long, Integer> iter : map.entrySet()) {
            if ( iter.getValue() > 0 ) {
                squares.add((iter.getKey() * iter.getKey()) * iter.getValue());
            }
        }
        long ans = 0;
        for (Long aLong : squares) {
            ans += aLong;
        }
        out.printLine(ans);
        out.flush();

    }*/

    private void solve2() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt();
        int binary[][] = new int[n + 1][21];
        long ans[] = new long[n + 1];
        for (int i = 0; i < n; i++) {
            long ai = in.readLong();
            for (int j = 0; j < 20; j++) {
                long mask = 1 << j;
                binary[i][j] = (mask & ai) == 0 ? 0 : 1;
            }
        }
        for (int j = 0; j < 20; j++) {
            int nextZero = 0;
            int i;
            for (i = 0; i < n; i++) {
                if ( binary[i][j] == 0 ) {
                    break;
                }
                nextZero++;
                ans[i] = ans[i]+(long) Math.pow(2, j);
            }
            for (; i < n; i++) {
                if ( binary[i][j] == 1 ) {
                    binary[nextZero][j] = 1;
                    binary[i][j] = 0;
                    ans[nextZero] = ans[nextZero]+(long) Math.pow(2, j);
                    nextZero++;
                }
            }
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            res+=ans[i]*ans[i];
        }
        out.printLine(res);
        out.flush();
    }

    private long sumOf2Numbers(long a, long b) {
        return a * a + b * b + 2 * a * b;
    }

    public static void main(String[] args) {
        D solver = new D();
        solver.solve2();
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
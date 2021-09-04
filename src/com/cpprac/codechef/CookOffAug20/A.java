package com.cpprac.codechef.CookOffAug20;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class A {

    public void solveC() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        // 1) Try to color root with 0 and 1 and check possibility
        // 2) Iteratively traverse from all leaves to correct the tree greedily.
        // 3) If any leaf is not possible then

    }

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt();
            long a[] = IOUtils.readLongArray(in, n);
            LinkedList<Long> left = new LinkedList<>();
            LinkedList<Long> right = new LinkedList<>();
            left.add(a[0]);
            right.add(a[n / 2]);
            for (int i = 1; i < n / 2; i++) {
                while (!left.isEmpty() && left.peekLast() >= a[i]) {
                    left.removeLast();
                }
                if ( left.isEmpty() || left.peekLast() < a[i] ) {
                    left.addLast(a[i]);
                }
            }
            for (int i = n / 2; i < n; i++) {
                while (!right.isEmpty() && right.peekLast() <= a[i]) {
                    right.removeLast();
                }
                if ( right.isEmpty() || right.peekLast() > a[i] ) {
                    right.addLast(a[i]);
                }
            }
            int count = 0;
            for (int i = 0; i < n; i++) {
                left.peekFirst();
            }
            out.printLine(count);
        }
        out.flush();
    }

    public void solveB() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt();
            ArrayList<int[]> points = new ArrayList<>();
            HashMap<Integer, Integer> xcoord = new HashMap<>();
            HashMap<Integer, Integer> ycoord = new HashMap<>();
            int count = 0;
            for (int i = 0; i < n; i++) {
                int xi = in.readInt(), yi = in.readInt();
                if ( xcoord.getOrDefault(xi, 0) <= 1 && ycoord.getOrDefault(yi, 0) <= 1 ) {
                    count++;
                }
                xcoord.put(xi, xcoord.getOrDefault(xi, 0) + 1);
                ycoord.put(yi, ycoord.getOrDefault(yi, 0) + 1);
            }
            int res = count;
            while (count > 5) {
                count = count / 2;
                res += count;
            }
            out.printLine(res);
        }
        out.flush();
    }

    public static void main(String[] args) {
        A solver = new A();
        solver.solveB();
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
package com.cpprac.HackerEarth;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class NovCircuit20 {

    public void solve5th() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        //  N containers with min, max values each.
        //  Update ST for a container with min max value based on (x+y).
        //  query the segment tree for a range to find closest value based.
        int n = in.readInt();
        TreeSet<Long> containers[] = new TreeSet[n + 1];
        for (int i = 0; i < n; i++) {
            long x = in.readInt(), y = in.readInt();
            containers[i] = new TreeSet<>(Comparator.naturalOrder());
            containers[i].add(Math.abs(x + y));
        }
        int q = in.readInt();
        while (q-- > 0) {
            String s = in.readString();
            if ( s.equals("+") ) {
                int i = in.readInt() - 1;
                long val = Math.abs(in.readLong() + in.readLong());
                containers[i].add(val);
            } else {
                int l = in.readInt() - 1, r = in.readInt() - 1;
                long k = in.readLong();
                long min = Long.MAX_VALUE, max = Long.MIN_VALUE, closest = Long.MAX_VALUE;
                long closestNum = 0;
                for (int i = l; i <= r; i++) {
                    long currMin = containers[i].first();
                    long currMax = containers[i].last();
                    min = Math.min(currMin, min);
                    max = Math.max(currMax, max);
                    if ( k <= currMin ) {
                        if ( closest > Math.abs(k - currMin) ) {
                            closest = Math.abs(k - currMin);
                            closestNum = currMin;
                        }
                    } else if ( k >= currMax ) {
                        if ( closest > Math.abs(k - currMax) ) {
                            closest = Math.abs(k - currMax);
                            closestNum = currMax;
                        }
                    } else {
                        long ceil = containers[i].ceiling(k);
                        long floor = containers[i].floor(k);
                        if ( closest > Math.abs(k - ceil) ) {
                            closest = Math.abs(k - ceil);
                            closestNum = ceil;
                        }
                        if ( closest > Math.abs(k - floor) ) {
                            closest = Math.abs(k - floor);
                            closestNum = floor;
                        }
                    }
                }

                long farthest = min;
                ;
                if ( Math.abs(k - min) < Math.abs(k - max) ) {
                    farthest = max;
                }

                out.printLine(Math.abs(Math.abs(farthest - k) * Math.abs(farthest - k) -
                        Math.abs(closestNum - k) * Math.abs(closestNum - k)));
            }
        }
        out.flush();
    }


    public void solve7th() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt();
        int blacks[] = IOUtils.readIntegerArray(in,n);
        int whites[] = IOUtils.readIntegerArray(in, n);
        int redDiff = in.readInt();
        int initDiff[] = IOUtils.readIntegerArray(in, n);
        out.flush();
    }


    public static void main(String[] args) {
        NovCircuit20 solver = new NovCircuit20();
        solver.solve7th();
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
package com.cpprac.codeforces.CF1353;

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

public class D {

    class Pair {
        int l, r;

        public Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt();
            int arr[] = new int[n];
            PriorityQueue<Pair> pq = new PriorityQueue<>((i1, i2) -> {
                if ( i2.r - i2.l == i1.r - i1.l ) {
                    return Integer.compare(i1.l, i2.r);
                } else {
                    return Integer.compare(i2.r - i2.l, i1.r - i1.l);
                }
            });
            pq.add(new Pair(0, n - 1));
            int operation = 1;
            while (!pq.isEmpty()) {
                Pair p = pq.poll();
                if ( (p.r - p.l + 1) % 2 != 0 ) {
                    int mid = (p.r + p.l) / 2;
                    arr[mid] = operation;
                    if ( p.l <= mid - 1 )
                        pq.add(new Pair(p.l, mid - 1));
                    if ( mid + 1 <= p.r )
                        pq.add(new Pair(mid + 1, p.r));
                } else {
                    int mid = (p.r + p.l - 1) / 2;
                    arr[mid] = operation;
                    if ( p.l <= mid - 1 )
                        pq.add(new Pair(p.l, mid - 1));
                    if ( mid + 1 <= p.r )
                        pq.add(new Pair(mid + 1, p.r));
                }
                operation++;
            }
            for(int i=0;i<n;i++)
                out.print(arr[i]+" ");
            out.printLine();
        }
        out.flush();

    }

    private void binarySearch(int[] arr, int st, int end) {
        int operation = 1;
        while (st <= end) {
            int mid = (st + end) / 2;
            arr[mid] = operation;
            operation++;
            if ( operation % 2 == 0 ) {

            }
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
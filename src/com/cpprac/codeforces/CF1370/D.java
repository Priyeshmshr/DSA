package com.cpprac.codeforces.CF1370;

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
    long arr[];
    long maxSoFar[];
    int n, k;

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        n = in.readInt();
        k = in.readInt();
        arr = new long[n + 1];
        maxSoFar = new long[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = in.readLong();
            maxSoFar[i + 1] = Math.max(maxSoFar[i], arr[i]);
        }
        long low = 1, high = maxSoFar[n];
        while (low < high) {
            long mid = low + (high - low) / 2;
            if ( isPossible(mid, 0) || isPossible(mid, 1) ) { //is possible on odd position or even position?
                high = mid;
            } else
                low = mid + 1;
        }
        out.printLine(high);
        out.flush();
    }

    private boolean isPossible(long x, int parity) {
        int dp[] = new int[n + 1];
        int loc = -1;
        int st = 1, end = n;
        if ( parity != k % 2 ) end = n - 1;
        if ( parity == 0 ) st = 2;

        for (int i = st; i <= end; i++) {
            if ( arr[i - 1] <= x ) {
                dp[i] = i > 2 ? Math.max(dp[i - 1], dp[i - 2] + 1) : 1;
            } else {
                dp[i] = dp[i - 1];
            }
            if ( arr[i - 1] <= x ) {
                loc = i;
            }
        }
        if ( loc == -1 )
            return false;

        if ( parity == 1 ) { //odd
            if ( dp[loc] >= ((k + 1) / 2) ) {
                if ( k % 2 != 0 ) { //odd
                    return maxSoFar[loc - 1] >= x;
                } else {
                    return maxSoFar[n] >= x;
                }
            }
        } else { //even
            if ( dp[loc] >= (k / 2) ) {
                if ( k % 2 == 0 ) { //even
                    return maxSoFar[loc - 1] >= x;
                } else {
                    return maxSoFar[n] >= x;
                }
            }
        }
        return false;
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
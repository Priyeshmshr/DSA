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
import java.util.Set;

public class E {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt(), k = in.readInt();
            String s = in.readString();
            StringBuilder sb = new StringBuilder();
            n = 0;
            for (int i = 0; i < s.length(); i++) {
                if ( s.charAt(i) == '1' || sb.length() > 0 )
                    sb.append(s.charAt(i));
                if ( s.charAt(i) == '1' )
                    n = sb.length();
            }
            s = sb.toString();

            if ( n == 0 ) {
                out.printLine(0);
                out.flush();
                continue;
            }

            StringBuilder str[] = new StringBuilder[(n / k) + 1];

            for (int i = 0; i < (n / k) + 1; i++) {
                str[i] = new StringBuilder();
            }

            int totalOnes = 0;
            for (int i = 0; i < n; i++) {
                str[i / k].append(s.charAt(i));
                if ( s.charAt(i) == '1' )
                    totalOnes++;
            }
            if ( totalOnes == 0 ) {
                out.printLine(0);
                out.flush();
                continue;
            }
            int ans = totalOnes - 1;
            int len = (int) Math.ceil(n / (k * 1.0));
            for (int j = 0; j < k; j++) {
                int currOnes = 0, prevZeros = 0;
                int prefixZeros[] = new int[len + 5];
                for (int i = 0; i < len; i++) {
                    if ( j < str[i].length() ) {
                        if ( currOnes > 0 && str[i].charAt(j) == '0' )
                            prevZeros++;
                        else if ( str[i].charAt(j) == '1' ) {
                            currOnes++;
                            prefixZeros[currOnes] = prefixZeros[currOnes - 1] + prevZeros;
                            prevZeros = 0;
                        }
                    }
                }

                // ans = zeros[i]-zeros[i-1]+(totalOnes-2);
                // ans1 = totalOnes-1;
                // ans2 = dp[i-1] + (zeros[i]-zeros[i-1]-1//not flipping previous 1);
                // dp[i] = Math.min(ans, ans1, ans2);
                int dp[] = new int[currOnes + 5];
                dp[1] = totalOnes - 1;
                for (int i = 2; i <= currOnes; i++) {
                    int ans1 = prefixZeros[i] - prefixZeros[i - 1] + totalOnes - 2;
                    int ans2 = dp[i - 1] + (prefixZeros[i] - prefixZeros[i - 1]) - 1;
                    dp[i] = Math.min(totalOnes - 1, Math.min(ans1, ans2));
                    ans = Math.min(ans, dp[i]);
                }
            }
            out.printLine(ans);
        }
        out.flush();
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
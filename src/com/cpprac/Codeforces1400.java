package com.cpprac;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

public class Codeforces1400 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            long p = in.readLong(), f = in.readLong();
            long cnts = in.readLong(), cntw = in.readLong();
            long s = in.readLong(), w = in.readLong();

            long maxSForP = Math.min(p / s, cnts);
            long maxs = 0, maxw = 0;
            long maxsf = 0, maxwf = 0;
            for (int i = 0; i <= maxSForP; i++) {
                long currw = Math.max(0, Math.min((p - (i * s)) / w, cntw));
                long tcnts = cnts - i;
                long tcntw = cntw - currw;
                long maxSForF = Math.min(f / s, tcnts);
                for (int j = 0; j <= maxSForF; j++) {
                    long currrw = Math.max(0, Math.min((f - (j * s)) / w, tcntw));
                    if ( maxs + maxw + maxsf + maxwf < i + j + +currw + currrw ) {
                        maxsf = j;
                        maxs = i;
                        maxw = currw;
                        maxwf = currrw;
                    }
                }

            }
            out.printLine(maxs + maxsf + maxw + maxwf);
        }
        out.flush();
    }

    public void solve2() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            String s = in.readString();
            int x = in.readInt();
            int n = s.length();
            char res[] = new char[s.length()];
            Arrays.fill(res, 'a');
            for (int i = 0; i < x; i++) {
                res[i + x] = s.charAt(i);
            }
            boolean possible = true;
            for (int i = n - x; i < n; i++) {
                if ( res[i - x] != 'a' && res[i - x] != s.charAt(i) ) {
                    possible = false;
                    break;
                }
                res[i - x] = s.charAt(i);
            }
            for (int i = x; i < n; i++) {
                if ( s.charAt(i) == '0' ) {
                    if ( res[i - x] == '1' || (i + x < n && res[i + x] == '1') ) {
                        possible = false;
                        break;
                    } else {
                        res[i - x] = '0';
                        if(i+x<n)res[i + x] = '0';
                    }
                } else {
                    if ( res[i - x] == '0' && (i + x < n && res[i + x] == '0') ) {
                        possible = false;
                        break;
                    } else {
                        if ( res[i - x] == 'a' ) {
                            res[i - x] = s.charAt(i);
                        }
                        if ( i + x < n && res[i + x] == 'a' ) {
                            res[i + x] = s.charAt(i);
                        }
                    }
                }
            }
            if ( possible ) {
                out.printLine(String.valueOf(res));
            } else
                out.printLine(-1);
        }
        out.flush();
    }

    public static void main(String[] args) {
        Codeforces1400 solver = new Codeforces1400();
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
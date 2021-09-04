package com.cpprac.HackerEarth;

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
import java.util.List;
import java.util.Set;

public class PayUHiringContest {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
//        int t = in.readInt();
//        while (t-- > 0) {
//            int n = in.readInt(), k = in.readInt();
//            int a[] = IOUtils.readIntegerArray(in, n);
//            out.printLine(solve5th(n, k, a));
//        }
        int t = in.readInt();
        while (t-- > 0) {
            String n = in.readString(), k = in.readString();
            out.printLine(solve(n,k));
        }
        out.flush();
    }

    private String solve(String k, String n) {
        // Write your code here
        long kk = 0;
        int pos = 0;
        HashMap<Character, Long> map = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            if ( i < 10 ) map.put((char) ('0' + i), (long)i);
            else map.put((char) ('A' + (i - 10)), (long)i);
        }
        for (int i = k.length() - 1; i >= 0; i--) {
            kk += map.get(k.charAt(i)) * Math.pow(16, pos++);
        }

        long res = 0 ;
        pos=0;
        for (int i = n.length()-1; i >= 0; i--){
            res = (res + (map.get(n.charAt(i)) * modularExponentiation(16, pos++,kk))%kk)%kk ;
        }
        return Long.toHexString(res).toUpperCase();
    }
    private long modularExponentiation(long x, long n, long M) {
        long result = 1;
        while (n > 0) {
            if ( n % 2 == 1 )
                result = (result * x) % M;
            x = (x * x) % M;
            n = n / 2;
        }
        return result % M;
    }
    public static void main(String[] args) {
        PayUHiringContest solver = new PayUHiringContest();
        solver.solve();
    }

    private int solve(int N, int K, int[] A) {
        HashMap<Long, Long> map = new HashMap<>();
        long ans = 0;
        for (int i = 0; i < N; i++) {
            long toBe = (A[i] ^ K);
            map.put((long) A[i], map.getOrDefault(toBe, 0L) + 1);
            ans = Math.max(ans, map.get((long) A[i]));
        }
        return ((int) ans) >= 2 ? (int) ans : 0;
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
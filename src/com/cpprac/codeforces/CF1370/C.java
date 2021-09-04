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

public class C {

    boolean prime[];
    public static final int MAXN = 1000005;

    void sieve() {
        prime = new boolean[MAXN + 1];
        for (int i = 0; i < MAXN; i++)
            prime[i] = true;
        for (int p = 2; p * p <= MAXN; p++) {
            // If prime[p] is not changed, then it is a prime
            if ( prime[p] == true ) {
                // Update all multiples of p
                for (int i = p * p; i <= MAXN; i += p)
                    prime[i] = false;
            }
        }
    }

    private ArrayList<Integer> oddPrimeFactors(long x) {
        ArrayList<Integer> primeFactors = new ArrayList<>();
        for (int i = 3; i < MAXN; i++) {
            if ( prime[i] ) {
                if ( x % i == 0 ) {
                    long temp = x;
                    while (temp % i == 0) {
                        primeFactors.add(i);
                        temp = temp / i;
                    }
                }
            }
            if ( primeFactors.size() >= 2 )
                break;
        }
        return primeFactors;
    }

    boolean isPrime(long n) {
        if ( n <= 1 ) return false;
        if ( n <= 3 ) return true;
        if ( n % 2 == 0 || n % 3 == 0 ) return false;
        for (long i = 5; i * i <= n; i = i + 6)
            if ( n % i == 0 || n % (i + 2) == 0 )
                return false;
        return true;
    }

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            long n = in.readLong();
            if ( n == 1 ) {
                out.printLine("FastestFinger");
                out.flush();
                continue;
            }
            if ( n == 2 || n % 2 != 0 ) {
                out.printLine("Ashishgup");
                out.flush();
                continue;
            }
            if ( (n / 2) % 2 != 0 ) {
                if ( !isPrime(n / 2) ) {
                    out.printLine("Ashishgup");
                } else {
                    out.printLine("FastestFinger");
                }
            } else {
                while (n % 2 == 0) {
                    n = n / 2;
                }
                if ( n == 1 ) {
                    out.printLine("FastestFinger");
                } else {
                    out.printLine("Ashishgup");
                }
            }
            out.flush();
        }
    }

    public static void main(String[] args) {
        C solver = new C();
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
package com.cpprac.codechef.Snackdown19;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class MAXPRODU {

    public static final int MOD_VALUE = 1000000007;

    public void solve() {

        System.out.println((1000000000l * (1000000000 - 1)) % MOD_VALUE);

        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            long n = in.readLong();
            int k = in.readInt();

            if ((n / k) <= 1) {
                System.out.println(-1);
                continue;
            }

            long val = n / k;
            long rem = n % k;

            if (k == 1) {
                long res = ((val % MOD_VALUE) * ((val - 1) % MOD_VALUE)) % MOD_VALUE;
                System.out.println(res);
                continue;
            }

            if (k == 2) {
                long res;
                long a, b;
                if (rem == 1) {
                    a = val;
                    b = val + 1;
                } else {
                    a = val - 1;
                    b = val + 1;
//                    res = (((val - 1) % MOD_VALUE) * ((val - 2) % MOD_VALUE) * ((val + 1) % MOD_VALUE) * (val % MOD_VALUE)) % MOD_VALUE;
                }
                res = ((a % MOD_VALUE) * ((a - 1) % MOD_VALUE) * (b % MOD_VALUE) * ((b-1) % MOD_VALUE)) % MOD_VALUE;
                System.out.println(res);
                continue;
            }

            long a[] = new long[100005];

            for (int i = 0; i < k; i++) {
                a[i] = val;
            }

            long temp = rem;

            for (int i = k - 1; i >= 0 && temp > 0; i--) {
                a[i] = a[i] + 1;
                temp--;
            }

            int diff = 1;

            for (int i = k / 2 - 1, j = (int) Math.ceil(k / 2.0); i >= 0 && j < k; i--, j++) {

                a[i] = a[i] - diff;
                a[j] = a[j] + diff;
                diff++;

            }

            long res = 1;

            for (int i = 0; i < k; i++) {
                System.out.print(" " + a[i] + " ");
                if (a[i] <= 1) {
                    res = -1;
                    break;
                }
                res = (res * ((a[i] % MOD_VALUE) * ((a[i] - 1) % MOD_VALUE)) % MOD_VALUE) % MOD_VALUE;
            }
            System.out.println(res);

            /*long num=2,sum=0;
            for(int i=1;i<k;i++){
                res = (res *((2*2)-2))%MOD_VALUE;
                sum+=2;
            }
            long left = n-sum;
            if(sum>n){
                System.out.println(-1);
                continue;
            }
            res = (res*((left*left)-left))%MOD_VALUE;
            System.out.println(res);*/
        }
    }

    public static void main(String[] args) {
        MAXPRODU solver = new MAXPRODU();
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
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
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
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
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
            if (filter != null)
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
                if (i != 0)
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

        public static Set<Integer> readIntegerSet(InputReader in, int size) {
            Set<Integer> set = new HashSet<Integer>();
            for (int i = 0; i < size; i++) {
                set.add(in.readInt());
            }
            return set;
        }
    }
}
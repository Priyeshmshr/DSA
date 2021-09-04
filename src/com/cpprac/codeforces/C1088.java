package com.cpprac.codeforces;

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

public class C1088 {

    class Operations {
        long a, b, c;

        Operations(long a, long b, long c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        int n = in.readInt();
        int a[] = new int[2005];

        for (int i = 0; i < n; i++) {
            a[i] = in.readInt();
        }
        List<Operations> operations = new ArrayList<>();

        int sum=0;
        for (int i = n - 1; i >= 0; i--) {

            if((a[i]+sum)%(n+1)!=i+1){
                int toAdd = i+1-((a[i]+sum)%(n+1));
                if(toAdd<0){
                    toAdd+=n+1;
                }
                sum+=toAdd;
                operations.add(new Operations(1, i + 1, toAdd));
            }
            /*long res = check(a[i], n, i + 1, cummSum[i + 1]);

            cummSum[i] = res == 0 ? cummSum[i + 1] : res;

            if (cummSum[i] > cummSum[i + 1])
                operations.add(new Operations(1, i + 1, (cummSum[i] - cummSum[i + 1])));*/
        }

        operations.add(new Operations(2, n, (n + 1)));

        out.printLine(operations.size());
        for (Operations iter : operations) {
            out.printLine(iter.a + " " + iter.b + " " + iter.c);
        }

        out.close();
    }

   /* public long check(int a, int n, int val, long prevValue) {

        // 7%4 == (4-3) + 2
        int mod = n + 1;
        int modValue = (a+prevValue) % mod;
        if (modValue != val) {
            if (modValue < val) {
                long res = modValue + (val - modValue);
                return res < prevValue ? res * (long) (Math.ceil(prevValue / (res * 1.0))) : res;
            } else {
                long res = (mod - modValue) + val;
                return res < prevValue ? res * (long) (Math.ceil(prevValue / (res * 1.0))) : res;
            }
        } else
            return 0;

    }*/

    public static void main(String[] args) {
        C1088 solver = new C1088();
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
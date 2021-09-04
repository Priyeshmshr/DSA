package com.cpprac.codechef.LTIME67A;

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

public class BUCKETS {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt(), k = in.readInt();
        solution2(n, k, in, out);
    }

    private void solution2(int n, int k, InputReader in, OutputWriter out) {
        double count[] = new double[k];
        double prevSum = 0.0000000d;
        double currentSum = 0.0000000d;

        for (int i = 0; i < n; i++) {
            currentSum = 0.0000000d;
            for (int j = 0; j < k; j++) {
                double temp = in.readInt();
                count[j] += temp;
                prevSum += temp;
            }
            for (int j = 0; j < k; j++) {
                count[j] = (count[j] / prevSum);
                currentSum+=count[j];
            }
            prevSum=currentSum;
        }
        for (int j = 0; j < k; j++) {
            out.print(count[j] + " ");
        }
        out.close();
    }

/*
    private void solution(int n, int k, InputReader in, OutputWriter out) {

        double count[] = new double[k];
        long currentSum = 0;
        for (int i = 1; i <= n - 1; i++) {
            for (int j = 0; j < k; j++) {
                int temp = in.readInt();
                count[j] += temp;
                currentSum += temp;
            }
        }

        double prob[] = new double[k];

        for (int i = 0; i < k; i++) {
            prob[i] = (n != 1) ? (count[i] / (currentSum * 1.000000)) : 0.000000;
            count[i] = 0.000000;
        }

        currentSum = 0;
        for (int i = 0; i < k; i++) {
            int temp = in.readInt();
            count[i] += temp;
            currentSum += temp;
        }

        for (int i = 0; i < k; i++) {
            double res = (count[i] + (1 * prob[i])) / (((n != 1) ? (currentSum + 1) : currentSum) * 1.000000);
            out.print(res + " ");
        }
        out.close();
    }
*/

    public static void main(String[] args) {
        BUCKETS solver = new BUCKETS();
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
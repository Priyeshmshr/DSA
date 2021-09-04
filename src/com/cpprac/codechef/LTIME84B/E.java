package com.cpprac.codechef.LTIME84B;

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
            int n = in.readInt();
            int a[] = new int[n + 4];
            for (int i = 0; i < n; i++) {
                a[i] = in.readInt();
            }
            a[n] = a[0];
            a[n + 1] = a[1];
            a[n + 2] = a[2];
            int count0 = 0, count1 = 0;
            for (int i = 0; i < n; i++) {
                if ( a[i] == 0 )
                    count0++;
                else
                    count1++;
            }
            if ( count0 == n )
                out.printLine(0);
            else if ( count1 == 1 )
                out.printLine(-1);
            else if ( count1 == n )
                out.printLine(Math.ceil(count1 / (n * 1.0)));
            else {
                out.printLine(dp(a, n));
            }
        }
        out.flush();
    }


    private long dp(int a[], int n) {
        long ans = Long.MAX_VALUE;
        ArrayList<Integer> oneLocation = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ( a[i] == 1 ) {
                oneLocation.add(i);
            }
        }
        for (int j = 0; j <3; j++) {
            long dp[] = new long[oneLocation.size() + 1];
            dp[0] = 0;
            dp[1] = Integer.MAX_VALUE;
            dp[2] = oneLocation.get(1) - oneLocation.get(0);
            for (int i = 2; i < oneLocation.size(); i++) {
                dp[i + 1] = Math.min(dp[i - 1] + oneLocation.get(i) - oneLocation.get(i - 1), dp[i - 2] + oneLocation.get(i) - oneLocation.get(i - 2));
            }
            ans = Math.min(ans, dp[oneLocation.size()]);
            oneLocation.add(oneLocation.remove(0)+n);
        }
        return ans;
    }

    int ans;

    private void recur(int[] a, int changes, int count0, int count1) {
        if ( count1 == 0 ) {
            ans = Math.min(ans, changes);
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if ( a[i] == 1 ) {
                //left
                int index = i - 1;
                if ( i == 0 ) {
                    index = a.length - 1;
                }

                int tempChange = changes;
                while (a[index] == 0) {
                    tempChange++;
                    index--;
                    if ( index < 0 ) {
                        index = a.length - 1;
                    }
                }
                int tempCount0 = count0, tempCount1 = count1;
                if ( a[index] >= 1 ) {
                    tempCount0++;
                    tempCount1 = a[index] == 1 ? count1 - 2 : count1 - 1;
                }
                a[index] += 1;
                a[i] = 0;
                recur(a, tempChange + 1, tempCount0, tempCount1);
                a[index] -= 1;
                a[i] = 1;
                tempCount0 = count0;
                tempCount1 = count1;
                tempChange = changes;

                //right
                index = i + 1;
                if ( i == a.length - 1 ) {
                    index = 0;
                }
                while (a[index] == 0) {
                    index++;
                    tempChange++;
                    if ( index == a.length ) {
                        index = 0;
                    }
                }
                if ( a[index] >= 1 ) {
                    tempCount0++;
                    tempCount1 = a[index] == 1 ? count1 - 2 : count1 - 1;
                }
                a[index] += 1;
                a[i] = 0;
                recur(a, tempChange + 1, tempCount0, tempCount1);
                a[index] -= 1;
                a[i] = 1;
            }
        }
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
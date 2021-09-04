/*
package com.cpprac.codeforces.CF1335;

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

public class E1_2 {

    public void solve5th() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt();
            int arr[] = IOUtils.readIntegerArray(in, n);
            int res = 0;
            if ( arr[0] == arr[n - 1] )
                res = check(arr, 0, n - 1, arr[0]);
            else
                res = Math.max(check(arr, 1, n - 1, arr[n-1]), check(arr, 0, n - 2, arr[0]));
            out.printLine(res);
            out.flush();
        }
    }

    private int check(int[] arr, int st, int end) {

        if ( st > end || st > arr.length - 1 || end < 0 )
            return 0;

        if ( st == end )
            return 1;

        if ( arr[st] == arr[end] ) {
            int res = 0;
            int i = st, j = end;
            while(i<=j) {
                while (i <= j && arr[i] == arr[j]) {
                    i++;
                    j--;
                }
                res = Math.max(mostFreqNotEqual(arr, i - 1, j + 1) + Math.min(2 * (i - st), end - st + 1),
                        check(arr, i, j));
            }
        } else {
            return Math.max(check(arr, st + 1, end), check(arr, st, end - 1));
        }
    }

    private int checkIteratively(int[] arr, int st, int end) {

    }
    private int mostFreqNotEqual(int[] arr, int st, int end) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int maxFreq = 0;
        for (int i = st + 1; i < end; i++) {
            if ( arr[st] != arr[i] ) {
                freq.put(arr[i], freq.getOrDefault(arr[i], 0) + 1);
                maxFreq = Math.max(maxFreq, freq.get(arr[i]));
            }
        }
        return maxFreq;
    }

    public static void main(String[] args) {
        E1_2 solver = new E1_2();
        solver.solve5th();
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
                } catch (IOException E) {
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
}*/

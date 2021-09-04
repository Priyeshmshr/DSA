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
import java.util.Map;
import java.util.Set;

public class E2 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt();
            int arr[] = IOUtils.readIntegerArray(in, n);
            int freq[][] = new int[201][n + 5];
            Map<Integer, ArrayList<Integer>> mapOfLocations = new HashMap<>();
            for (int i = 1; i <= 200; i++) {
                mapOfLocations.put(i, new ArrayList<>());
            }
            for (int i = 1; i <= n; i++) {
                updateMapOfLocations(mapOfLocations, i, arr);
                for (int j = 1; j <= 200; j++) {
                    if ( arr[i - 1] == j ) {
                        freq[j][i] = freq[j][i - 1] + 1;
                    } else
                        freq[j][i] = freq[j][i - 1];
                }
            }

            int ans = 1;
            for (int i = 1; i <= 200; i++) {
                int size = mapOfLocations.get(i).size();
                for (int j = 0; j < size / 2; j++) {
                    int l = mapOfLocations.get(i).get(j);
                    int r = mapOfLocations.get(i).get(size-1-j);
                    int cntin = 0;
                    for (int k = 1; k <= 200; k++) {
                        cntin = Math.max(cntin, freq[k][r - 1] - freq[k][l]);
                    }
                    int cntout = j+1;
                    ans = Math.max(ans, cntin + cntout*2);
                }
            }
            out.printLine(ans);
            out.flush();
        }
    }

    private void updateMapOfLocations(Map<Integer, ArrayList<Integer>> mapOfLocations, int i, int[] arr) {
        ArrayList<Integer> arrayList = mapOfLocations.getOrDefault(arr[i - 1], new ArrayList<>());
        arrayList.add(i);
        mapOfLocations.put(arr[i - 1], arrayList);
    }

    private long forInterval(int i, int j, int freq[][], int n) {
        int cntin = 0, cntout = 0;
        for (int k = 1; k <= 200; k++) {
            cntin = Math.max(cntin, freq[k][j] - freq[k][i - 1]);
            cntout = Math.max(cntout, Math.min(freq[k][i - 1] - freq[k][0], freq[k][n] - freq[k][j]));
        }
        return cntin + 2 * cntout;
    }

    public static void main(String[] args) {
        E2 solver = new E2();
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
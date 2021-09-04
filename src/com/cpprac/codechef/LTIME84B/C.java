package com.cpprac.codechef.LTIME84B;

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

public class C {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt();
            String aa = in.readString(), b = in.readString();
            Map<Character, ArrayList<Integer>> map = new HashMap<>();
            Map<Character, ArrayList<Integer>> aIndexes = new HashMap<>();
            for (int i = 0; i < n; i++) {
                ArrayList<Integer> indexes = map.getOrDefault(b.charAt(i), new ArrayList<>());
                indexes.add(i);
                map.put(b.charAt(i), indexes);

            }
            char a[] = aa.toCharArray();
            boolean res = true;
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            for (int i = 25; i >= 0; i--) {
                char c = (char) ('a' + i);
                ArrayList<Integer> indexes = map.getOrDefault(c, new ArrayList<>());
                if ( indexes.size() > 0 ) {
                    boolean diff = false;
                    char smallest = 'z';
                    for (int index : indexes) {
                        if ( a[index] < smallest ) {
                            smallest = a[index];
                        }
                        if ( a[index] != b.charAt(index) )
                            diff = true;
                    }
                    if ( diff ) {
                        if ( smallest < c ) {
                            res = false;
                            break;
                        } else if ( smallest > c ) {
                            for (int j = 0; j < n; j++) {
                                if ( a[j] == c ) {
                                    smallest = c;
                                    indexes.add(j);
                                    break;
                                }
                            }
                        }
                        if ( smallest == c ) {
                            ans.add(indexes);
                            for (int ind : indexes) {
                                a[ind] = smallest;
                            }
                        } else {
                            res = false;
                            break;
                        }
                    }
                }
            }
            if ( res ) {
                out.printLine(ans.size());
                for (ArrayList<Integer> list : ans) {
                    out.print(list.size() + " ");
                    for (int i : list)
                        out.print(i + " ");
                    out.printLine();
                }
            } else
                out.printLine(-1);
        }
        out.flush();
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
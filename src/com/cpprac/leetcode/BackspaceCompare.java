package com.cpprac.leetcode;

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

public class BackspaceCompare {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        assert backspaceCompare("ab#c", "ad#c");
        assert backspaceCompare("ab##", "c#d#");
        assert backspaceCompare("a##c", "#a#c");
        assert (!backspaceCompare("a#c", "B"));
        assert (!backspaceCompare("bxj##tw", "bxj###tw"));
        assert (backspaceCompare("bxj##tw", "bxo#j##tw"));
    }

    public static void main(String[] args) {
        BackspaceCompare solver = new BackspaceCompare();
        solver.solve();
    }

    public boolean backspaceCompare1(String S, String T) {

        return false;
    }

    public boolean backspaceCompare(String S, String T) {

        for (int i = S.length() - 1, j = T.length() - 1; i >= 0 || j >= 0; i--, j--) {

            int jumpS = 0, jumpT = 0;

            while ( i >= 0 && (S.charAt(i) == '#'|| jumpS>0) ) {
                jumpS += S.charAt(i--) == '#' ? 1 : -1;
                /*jumpS = 1;
                i--;
                while (jumpS != 0) {
                    if ( i >= 0 ) {
                        jumpS += S.charAt(i--) == '#' ? 1 : -1;
                    } else {
                        i = i - jumpS;
                        jumpS = 0;
                    }
                }*/
            }

            while ( j >= 0 && (T.charAt(j) == '#' || jumpT>0)) {
                jumpT += T.charAt(j--) == '#' ? 1 : -1;
                /*jumpT = 1;
                j--;
                while ( jumpT != 0 ) {
                    if ( j >= 0 ) {
                        jumpT += T.charAt(j--) == '#' ? 1 : -1;
                    } else {
                        j = j - jumpT;
                        jumpT = 0;
                    }
                }*/
            }

            if ( i < 0 && j < 0 )
                return true;
            else if ( i < 0 || j < 0 || S.charAt(i) != T.charAt(j) )
                return false;

        }
        return true;
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
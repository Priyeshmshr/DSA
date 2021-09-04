package com.cpprac.codeforces;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

public class CF1374 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt();
        int m = in.readInt();
        int k = in.readInt();
        ArrayList<Integer> alice = new ArrayList<>();
        ArrayList<Integer> bob = new ArrayList<>();
        ArrayList<Integer> both = new ArrayList<>();
        alice.add(0);
        bob.add(0);
        both.add(0);
        for (int i = 0; i < n; i++) {
            int ti = in.readInt(), ai = in.readInt(), bi = in.readInt();
            if ( ai == 1 && bi == 1 ) {
                both.add(ti);
            } else if ( ai == 1 )
                alice.add(ti);
            else if ( bi == 1 )
                bob.add(ti);
        }
        Collections.sort(both);
        Collections.sort(alice);
        Collections.sort(bob);

        for (int i = 1; i < both.size(); i++) {
            both.set(i, both.get(i - 1) + both.get(i));
        }
        for (int i = 1; i < bob.size(); i++) {
            bob.set(i, bob.get(i - 1) + bob.get(i));
        }
        for (int i = 1; i < alice.size(); i++) {
            alice.set(i, alice.get(i - 1) + alice.get(i));
        }

        if ( alice.size() - 1 + both.size() - 1 < k || bob.size() - 1 + both.size() - 1 < k ) {
            out.printLine(-1);
            out.flush();
            System.exit(0);
        }
        long ans = Integer.MAX_VALUE;

        int st = 0 , midOne = m / 3, midTwo = (2 * m) / 3, end = m;
        for (int i = 0; i <= k; i++) {
            if ( both.size() > midOne && alice.size() > midTwo - midOne && bob.size() > end - midTwo ) {
                long val = both.get(midOne) + alice.get(midTwo - midOne) + bob.get(end - midTwo);
                ans = Math.min(ans, val);
            }
        }
        out.printLine(ans);
        out.flush();
    }

    public static void main(String[] args) {
        CF1374 solver = new CF1374();
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

        public static Long[] readLongArray(InputReader in, int size) {
            Long[] array = new Long[size];
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
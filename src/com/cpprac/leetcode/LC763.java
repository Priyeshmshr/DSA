package com.cpprac.leetcode;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

public class LC763 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        List<Integer> res = partitionLabels("caedbdedda");
        System.out.println(res);
    }

    public static void main(String[] args) {
        LC763 solver = new LC763();
        solver.solve();

    }

    class Pair {
        int st, end;

        Pair(int st, int end) {
            this.st = st;
            this.end = end;
        }
    }

    public List<Integer> partitionLabels(String S) {

        int[] last = new int[26];
        for (int i = 0; i < S.length(); i++) {
            int index = S.charAt(i) - 'a';
            last[index] = i;
        }

        ArrayList<Integer> res = new ArrayList<>();
        int prev = 0, currHigh =0;

        for (int i = 0; i < S.length(); i++) {
            currHigh = Math.max(currHigh, last[S.charAt(i) - 'a']);
            if ( i == currHigh || i==S.length()-1) {
                res.add(currHigh - prev + 1);
                prev = i+1;
            }
        }
//        res.add(currHigh - prev + 1);
        return res;
    }

    public List<Integer> partitionLabels1(String S) {

        Pair[] pairs = new Pair[26];
        for (int i = 0; i < S.length(); i++) {
            int index = S.charAt(i) - 'a';
            if ( pairs[index] == null )
                pairs[index] = new Pair(i, i);
            else
                pairs[index].end = i;
        }

        ArrayList<Pair> pairList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if ( pairs[i] != null )
                pairList.add(pairs[i]);
        }
        pairList.sort(Comparator.comparingInt(t -> t.st));

        int currHigh = pairList.get(0).end;
        int st = 0;
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i < pairList.size(); i++) {
            Pair curr = pairList.get(i);
            if ( curr.st < currHigh ) {
                currHigh = Math.max(currHigh, curr.end);
            } else {
                res.add(currHigh - st + 1);
                currHigh = curr.end;
                st = curr.st;
            }
        }
        res.add(currHigh - st + 1);
        return res;
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
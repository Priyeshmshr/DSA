package com.cpprac.HackerEarth;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class JulyCircuit21 {

    public List<String> rhymePoems(int N){

        ArrayList<String> poems = new ArrayList<>();
        poems.add("A");

        Queue<Integer> queue =  new LinkedList<>();
        queue.add(1);
        queue.add(null);
        Integer cur = queue.poll();
        StringBuilder snb = new StringBuilder();
        for(int i=2;i<=N;i++){ // 26

            ArrayList<String> newPoems = new ArrayList<>();

            for(String previousPoem : poems){
                for(int j = 0;j<=previousPoem.length();j++){ // A-> Z // 26
                    char newLine = (char)('A'+i);
                    newPoems.add(newLine+previousPoem);
                }
            }
            poems = newPoems;
        }
        return poems;
    }

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        solve1(in, out);
    }

    private void solve1(InputReader in, OutputWriter out) {
        int t = in.readInt();
        while (t-- > 0) {

            int n = in.readInt(), m = in.readInt();
            int inputs[][] = new int[n + 1][2];
            for (int i = 0; i < n; i++) {
                inputs[i][0] = in.readInt();
                inputs[i][1] = in.readInt();
            }

            int dp[] = new int[n + 2];
            dp[0] = 0;
            for (int j = 0; j < n; j++) {
                for (int i = m; i >= 0; i--) {
                int price = inputs[j][1];
                int vitamin = inputs[j][0];
                    if ( price <= i ) {
                        dp[i] = Math.max(dp[i], dp[i - price] + vitamin);
                    }
                    if ( (price / 2) <= i ) {
                        dp[i] = Math.max(dp[i], dp[i - (price / 2)] + vitamin);
                    }
                }
            }
            out.printLine(dp[m]);
            out.flush();
        }
    }


    public static void main(String[] args) {
        JulyCircuit21 solver = new JulyCircuit21();
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
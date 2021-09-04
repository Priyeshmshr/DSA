package com.cpprac.codechef.DSALearningSession.graph;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DIGJUMP {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        String s = in.readString();
        map = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            ArrayList<Integer> indexes = map.getOrDefault(digit, new ArrayList<>());
            indexes.add(i);
            map.put(digit, indexes);
        }
        vis = new boolean[10];
        steps = new int[n];
        Arrays.fill(steps, Integer.MAX_VALUE);
        steps[0] = 0;
        bfs(s);
        out.printLine(steps[n - 1]);
        out.flush();
    }

    private boolean vis[];
    private int steps[];
    private HashMap<Integer, ArrayList<Integer>> map;

    private void bfs(String s) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if ( index + 1 < s.length() && !vis[s.charAt(index + 1) - '0'] ) {
                steps[index + 1] = Math.min(steps[index] + 1, steps[index + 1]);
                queue.add(index + 1);
            }
            if ( index > 0 && !vis[s.charAt(index - 1) - '0'] ) {
                steps[index - 1] = Math.min(steps[index] + 1, steps[index - 1]);
                queue.add(index - 1);
            }
            if(!vis[s.charAt(index) - '0']) {
                ArrayList<Integer> indexes = map.get(s.charAt(index) - '0');
                for (int i=0;i<indexes.size();i++) {
                    int currIndex = indexes.get(i);
                    if ( currIndex != index && currIndex!=index-1 && currIndex!=index+1 && ((i==0 || i==indexes.size()-1) || ((currIndex-1)!=indexes.get(i-1) || (currIndex+1)!=indexes.get(i+1)))) {
                        queue.add(currIndex);
                        steps[currIndex] = Math.min(steps[index] + 1, steps[currIndex]);
                    }
                }
            }
            vis[s.charAt(index) - '0'] = true;
            if ( vis[s.charAt(s.length()-1)-'0'] )
                return;
        }
    }

    public static void main(String[] args) {
        new Thread(null, new Runnable() {
            public void run() {
                DIGJUMP solver = new DIGJUMP();
                solver.solve();
            }
        }, "1", 1 << 23).start();
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
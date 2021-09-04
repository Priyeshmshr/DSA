package com.cpprac.codeforces.CF1422;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class D {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);


        /*
            1) Create a graph using with starting position(Sx,Sy) and instant-movement locations as vertices.
            2) Sort the vertices based on x-axis:
            3) Add an edge (abs(Xi-Xj)) between every two consecutive vertices in the sorted list.
            4) Repeat 2 & 3 for y-axis. (Why?) -> while going from (1,1)->(n,n) We are going to move either right or up to find the optimal distance.
                                                  Hence adding only those edges which are moving right or up.
            5) Apply diskstra on above graph.


         */
        int n = in.readInt();
        int m = in.readInt();

        int s[] = new int[2], t[] = new int[2];
        s[0] = in.readInt();
        s[1] = in.readInt();
        t[0] = in.readInt();
        t[1] = in.readInt();

        ArrayList<long[]> graph[] = new ArrayList[m + 4];

        for (int i = 0; i < m + 3; i++) {
            graph[i] = new ArrayList<>();
        }

        int coords[][] = new int[m + 3][2];
        // Add starting location as first coordinate
        coords[0][0] = s[0];
        coords[0][1] = s[1];

        for (int i = 1; i <= m; i++) {
            int xi = in.readInt(), yi = in.readInt();
            coords[i][0] = xi;
            coords[i][1] = yi;
        }
        //find distance between destination vertex from all the vertices.
        for (int i = 0; i <= m; i++) {
            long dist = Math.abs(t[0] - coords[i][0]) + Math.abs(t[1] - coords[i][1]);
            graph[m + 1].add(new long[]{dist, i});
            graph[i].add(new long[]{dist, m + 1});
        }

        for (int axis = 0; axis < 2; axis++) {
            ArrayList<long[]> axes = new ArrayList<>();
            for (int i = 0; i <= m; i++) {
                axes.add(new long[]{coords[i][axis], i});
            }
            axes.sort(Comparator.comparing((t1 -> t1[0])));
            for (int i = 1; i <= m; i++) {
                long dist = axes.get(i)[0] - axes.get(i-1)[0];
                int x = (int) axes.get(i - 1)[1];
                int y = (int) axes.get(i)[1];
                graph[x].add(new long[]{dist, y});
                graph[y].add(new long[]{dist, x});
            }
        }

        // Dijsktra starts here
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparing(t1 -> t1[0]));
        long dist[] = new long[m + 3];
        Arrays.fill(dist, -1);
        long vis[] = new long[m + 3];
        dist[0] = 0;
        pq.add(new long[]{0L, 0L});
        while (!pq.isEmpty()) {
            long currDist = pq.peek()[0];
            int currVert = (int) pq.peek()[1];
            pq.poll();
            if ( vis[currVert] == 1 ) continue;
            vis[currVert] = 1;
            for (long[] v : graph[currVert]) {
                if ( dist[(int) v[1]] == -1 || currDist + v[0] < dist[(int) v[1]] ) {
                    dist[(int) v[1]] = currDist + v[0];
                    pq.add(new long[]{(currDist + v[0]), v[1]});
                }
            }
        }
        out.printLine(dist[m + 1]);
        out.flush();
    }


    public static void main(String[] args) {
        D solver = new D();
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
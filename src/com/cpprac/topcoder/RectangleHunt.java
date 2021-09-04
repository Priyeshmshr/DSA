package com.cpprac.topcoder;

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

public class RectangleHunt {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
    }

    public static void main(String[] args) {
        RectangleHunt solver = new RectangleHunt();
        solver.solve();
    }

    public void largest(int[] x, int[] y){
        int xs = x.length;
        for(int i=0;i<xs;i++){
            for(int j=i+1;j<xs;j++){
                for(int k=j+1;k<xs;k++){
                    for(int l=k+1;l<xs;l++){
                    }
                }
            }
        }
    }

    class Segment
    {
        int ax, ay;
        int bx, by;

        public Segment(int ax, int ay, int bx, int by) {
            this.ax = ax;
            this.ay = ay;
            this.bx = bx;
            this.by = by;
        }
    };

    class pair{
        int first;
        int second;

        public pair(int ax, int ay) {
            this.first = ax;
            this.second = ay;
        }
    }
    // Utility method to return square of distance
// between two points
    int getDis(pair a, pair b)
    {
        return (a.first - b.first)*(a.first - b.first) +
                (a.second - b.second)*(a.second - b.second);
    }

    // method returns true if line Segments make
// a rectangle
    boolean isPossibleRectangle(Segment segments[])
    {
        Set< pair> st = new HashSet<>();

        // putiing all end points in a set to
        // count total unique points
        for (int i = 0; i < 4; i++)
        {
            st.add(new pair(segments[i].ax, segments[i].ay));
            st.add(new pair(segments[i].bx, segments[i].by));
        }

        // If total unique points are not 4, then
        // they can't make a rectangle
        if (st.size() != 4)
            return false;

        // dist will store unique 'square of distances'
        Set<Integer> dist = new HashSet<>();

        // calculating distance between all pair of
        // end points of line segments
        for (pair it1 : st)
            for (pair it2 : st)
                if (it1 != it2)
                    dist.add(getDis(it1, it2));

        // if total unique distance are more than 3,
        // then line segment can't make a rectangle
        if (dist.size() > 3)
            return false;

        // copying distance into array. Note that set maintains
        // sorted order.
        int distance[ ] = new int[3];
        int i = 0;
        for (int it : dist)
            distance[i++] = it;

        // If line seqments form a square
        if (dist.size() == 2)
            return (2*distance[0] == distance[1]);

        // distance of sides should satisfy pythagorean
        // theorem
        return (distance[0] + distance[1] == distance[2]);
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
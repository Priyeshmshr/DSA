package com.cpprac.InterviewQuestions.Amazon;

import com.cpprac.leetcode.DSU;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

public class A {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        List<List<Integer> > grid = new ArrayList<>();
        for(int i=0;i<2;i++)
            grid.add(new ArrayList<>());
        grid.set(0, Arrays.asList(0,1));
        grid.set(1, Arrays.asList(0,0));
//        grid.set(0, Arrays.asList(0,1,1,0,1));
//        grid.set(1, Arrays.asList(0,1,0,1,0));
//        grid.set(2, Arrays.asList(0,0,0,0,1));
//        grid.set(3, Arrays.asList(0,1,0,0,0));
//        grid.set(4, Arrays.asList(0,0,0,0,1));
        int ans = minimumDays(2,2, grid);
        System.out.println(ans);
    }

    public static void main(String[] args) {
        A solver = new A();
        solver.solve();
    }

    public int numberAmazonGoStores(int rows, int column, List<List<Integer> > grid)
    {
        DSU dsu = new DSU(rows*column+5);

        for(int i=0;i<rows;i++){
            for(int j=0;j<column;j++){
                int curr = (column*i)+(j+1);
                if(grid.get(i).get(j)==1) {
                    if ( i > 0 && grid.get(i - 1).get(j) == 1 ) {
                        int prev = (column*(i-1))+(j+1);
                        dsu.union(prev, curr );
                    }
                    if ( j > 0 && grid.get(i).get(j - 1) == 1 ) {
                        int prev = (column*i)+(j);
                        dsu.union(prev, curr);
                    }
                }else {
                    dsu.union(0, curr);
                }
            }
        }

        int ans=0;
        for(int i=1;i<=rows * column;i++){
            if(dsu.parent[i]==i)
                ans++;
        }
        return ans;
    }


    int minimumDays(int rows, int columns, List<List<Integer> > grid)
    {
        int ans = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){

                grid.get(i).set(j,1^grid.get(i).get(j));

                if(grid.get(i).get(j)!=0 ) {
                    grid.get(i).set(j,Integer.MAX_VALUE-100000);
                    if ( i > 0 ) {
                        grid.get(i).set(j,Math.min(grid.get(i).get(j), grid.get(i - 1).get(j) + 1)) ;
                    }
                    if ( j > 0 ) {
                        grid.get(i).set(j, Math.min(grid.get(i).get(j), grid.get(i).get(j - 1) + 1));
                    }
                }
            }
        }
        for(int i=rows-1;i>=0;i--){
            for(int j=columns-1;j>=0;j--){
                if(grid.get(i).get(j)!=0) {
                    if ( i < rows - 1 ) {
                        grid.get(i).set(j,Math.min(grid.get(i).get(j), grid.get(i + 1).get(j) + 1)) ;
                    }
                    if ( j < columns - 1 ) {
                        grid.get(i).set(j, Math.min(grid.get(i).get(j), grid.get(i).get(j + 1) + 1));
                    }
                    ans = Math.max(ans,grid.get(i).get(j));
                }
            }
        }
        return ans==Integer.MAX_VALUE-100000? -1: ans;
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
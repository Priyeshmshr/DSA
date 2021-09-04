package com.cpprac.codeforces.CF1345;

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
import java.util.Stack;

public class D1 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt(), m = in.readInt();
        String[] mat = new String[n];
        for (int i = 0; i < n; i++) {
            mat[i] = in.readString();
        }

        boolean possible = true;
        Stack<Character>[] rows = new Stack[n];
        Stack<Character>[] coll = new Stack[m];
        DSU dsu = new DSU(n * m + 1);
        int emptyRows = 0, emptyColl=0;
        for (int i = 0; i < n; i++) {
            rows[i] = new Stack<Character>();
            if ( i == 0 ) {
                for (int j = 0; j < m; j++) {
                    coll[j] = new Stack<Character>();
                }
            }
            for (int j = 0; j < m; j++) {
                if ( mat[i].charAt(j) == '.' ) {
                    if ( !rows[i].isEmpty() && rows[i].peek().equals('#') ) {
                        rows[i].push('.');
                    }
                    if ( !coll[j].isEmpty() && coll[j].peek().equals('#') ) {
                        coll[j].push('.');
                    }
                    dsu.union(n * m, i * m + j);
                } else {
                    if ( rows[i].isEmpty() || rows[i].peek().equals('.') ) {
                        rows[i].push('#');
                    }
                    if ( coll[j].isEmpty() || coll[j].peek().equals('.') ) {
                        coll[j].push('#');
                    }
                    //Add in dsu
                    if ( i - 1 >= 0 && mat[i - 1].charAt(j) == '#' ) {
                        dsu.union(i * m + j, (i - 1) * m + j);
                    }
                    if ( j - 1 >= 0 && mat[i].charAt(j - 1) == '#' ) {
                        dsu.union(i * m + j, i * m + (j - 1));
                    }
                }
            }
            if ( rows[i].size() > 2) possible = false;
            if ( rows[i].size() == 0 ) emptyRows++;
        }


        //Calculate ans:
        if ( emptyRows == n ) {
            out.printLine(0);
        } else {
            if ( possible ) {
                for (int i = 0; i < m; i++) {
                    if ( coll[i].size() > 2) {
                        possible = false;
                        break;
                    }
                    if ( coll[i].size() == 0 ) emptyColl++;
                }
            }
            //Print answers
            if(!possible){
                out.printLine(-1);
            } else if ( (emptyColl == 0 && emptyRows == 0) || (emptyColl > 0 && emptyRows > 0) ) {
                int ans = 0;
                for (int i = 0; i < n * m; i++) {
                    if ( dsu.parent[i] == i ) {
                        ans++;
                    }
                }
                out.printLine(ans);
            } else {
                out.printLine(-1);
            }
        }
        out.flush();
    }

    class DSU {

        public int[] parent;
        int rank[];
        int connectedComponents;

        public DSU(int size){
            parent = new int[size];
            for(int i=0;i<size;i++)
                parent[i]=i;
            rank = new int[size];
            connectedComponents = size;
        }

        public int find(int x){

            if(parent[x]!=x)
                parent[x]=find(parent[x]);

            return parent[x];
        }

        public boolean union(int x, int y){
            int parentOfX = find(x), parentOfY= find(y);
            if(parentOfX==parentOfY)
                return false;
            else if(rank[parentOfX]<rank[parentOfY]){
                parent[parentOfX] = parentOfY;
            }
            else if(rank[parentOfX]>rank[parentOfY]){
                parent[parentOfY] = parentOfX;
            }else {
                parent[parentOfY] = parentOfX;
                rank[parentOfX]++;
            }
            connectedComponents--;
            return true;
        }
    }

    public static void main(String[] args) {
        D1 solver = new D1();
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
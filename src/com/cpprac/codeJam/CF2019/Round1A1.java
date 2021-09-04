package com.cpprac.codeJam.CF2019;

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
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class Round1A1 {

    class Pair {
        int st, end;

        public Pair(int st, int end) {
            this.st = st;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if ( this == o ) return true;
            if ( o == null || getClass() != o.getClass() ) return false;
            Pair pair = (Pair) o;
            return st == pair.st &&
                    end == pair.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(st, end);
        }
    }

    private Stack<Pair> order;
    private int repeats = 8;
    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        int caseNo = 1;
        while (t-- > 0) {
            order = new Stack<>();
            int r = in.readInt(), c = in.readInt();
            boolean possible = true;
            int vis[][] = new int[r][c];
            int count[][] = new int[r][c];
            order.push(new Pair(0, 0));
            vis[0][0] = 1;
            possible = dfs(vis,count, 0, 0, r, c,1);
            if ( !possible ) {
                out.printLine("Case #" + caseNo++ + ": " + "IMPOSSIBLE");
            } else {
                out.printLine("Case #" + caseNo++ + ": " + "POSSIBLE");
                for (int i = 0; i < order.size(); i++) {
                    out.printLine((order.get(i).st + 1) + " " + (order.get(i).end + 1));
                }
            }
            out.flush();
        }
    }

    int vis[];
    public void solve1() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        int caseNo = 1;
        while (t-- > 0) {
            order = new Stack<>();
            int r = in.readInt(), c = in.readInt();
            boolean possible = true;
            int vis[][] = new int[r][c];
            int count[][] = new int[r][c];
            order.push(new Pair(0, 0));
            vis[0][0] = 1;
            possible = dfs(vis,count, 0, 0, r, c,1);
            if ( !possible ) {
                out.printLine("Case #" + caseNo++ + ": " + "IMPOSSIBLE");
            } else {
                out.printLine("Case #" + caseNo++ + ": " + "POSSIBLE");
                for (int i = 0; i < order.size(); i++) {
                    out.printLine((order.get(i).st + 1) + " " + (order.get(i).end + 1));
                }
            }
            out.flush();
        }
    }

    public void dfs(HashMap<Integer, ArrayList<Integer>> graph, int currNode){

        if(vis[currNode]==1){
            return;
        }

        if(graph.containsKey(currNode)) {
            for (int i : graph.get(currNode)) {
                dfs(graph, i);
            }
        }
    }

    private boolean dfs(int[][] vis, int[][] count, int ri, int ci, int r, int c, int visitedCount) {

        int dx[] = {1, -1};
        for (int i = 2; i <= Math.max(r, c); i++) {
            for (int j = 1; j < i; j++) {

                if ( ri - i >= 0 || ri + i < r || ci - i >= 0 || ci + i < c ) {
                    boolean placed;

                    for (int l = 0; l < 2; l++) {
                        for (int k = 0; k < 2; k++) {

                            int xiDisp = ri + dx[l] * i, yjDisp = ci + dx[k] * j, xjDisp = ri + dx[k] * j, yiDisp = ci + dx[l] * i;

                            if ( xiDisp >= 0 && xiDisp < r && yjDisp >= 0 && yjDisp < c && vis[xiDisp][yjDisp] == 0 && count[xiDisp][yjDisp] <= repeats ) {
                                vis[xiDisp][yjDisp] = 1;
                                count[xiDisp][yjDisp]++;
                                visitedCount++;
                                order.push(new Pair(xiDisp, yjDisp));
                                placed = dfs(vis, count, xiDisp, yjDisp, r, c, visitedCount);
                                if ( !placed ) {
                                    vis[xiDisp][yjDisp] = 0;
                                    visitedCount--;
                                    order.pop();
                                    if(ri==r && ci==c){
                                        count = new int[r][c];
                                    }
                                } else
                                    return true;
                            }
                            if ( xjDisp >= 0 && xjDisp < r && yiDisp >= 0 && yiDisp < c && vis[xjDisp][yiDisp] == 0 && count[xjDisp][yiDisp] <= repeats ) {
                                vis[xjDisp][yiDisp] = 1;
                                count[xjDisp][yiDisp]++;
                                visitedCount++;
                                order.push(new Pair(xjDisp, yiDisp));
                                placed = dfs(vis, count, xjDisp, yiDisp, r, c, visitedCount);
                                if ( !placed ) {
                                    vis[xjDisp][yiDisp] = 0;
                                    visitedCount--;
                                    order.pop();
                                    if(ri==r && ci==c){
                                        count = new int[r][c];
                                    }
                                } else
                                    return true;
                            }
                            if ( visitedCount == r * c )
                                return true;
                        }
                    }
                } else return visitedCount == r * c;
            }
        }
        return visitedCount == r * c;

    }

    public static void main(String[] args) {
        Round1A1 solver = new Round1A1();
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

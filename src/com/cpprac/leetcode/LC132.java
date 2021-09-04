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

public class LC132 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
    }

    public static void main(String[] args) {
        LC132 solver = new LC132();
        solver.solve();
    }

    int dp[][];

    public int minCut(String s) {
        return dpSolution(s);
    }

    private int dpSolution(String s){

        int cost[] = new int[s.length()];
        boolean isPalin[][] = new boolean[s.length()][s.length()];

        for(int i=0;i<s.length();i++){
            isPalin[i][i]=true;
        }

        for(int l=2;l<s.length()+1;l++){

            for (int i = 0; i < s.length() - l + 1; i++) {  // 5-2+1 = 4 , 5 - 5 +1 = 1

                int j = i + l - 1;   // 3+2-1 = 4, 0+5-1 = 4

                if(l==2)
                    isPalin[i][j] = s.charAt(i)==s.charAt(j);
                else
                    isPalin[i][j] = s.charAt(i)==s.charAt(j) && isPalin[i+1][l-1];
            }
        }

        for(int i=0;i<s.length();i++){
            if(isPalin[0][i])
                cost[i]=0;
            else{
                cost[i] = 1+cost[i-1];
                for (int j = 0; j < i; j++) {
                    if ( isPalin[j+1][i] && 1+cost[j]<cost[i])
                        cost[i]=1+cost[j];
                }
            }
        }
        return cost[s.length()-1];
    }

    private int solveRecursively(String s, int st, int end) {

        if ( dp[st][end] != -1 )
            return dp[st][end];

        if ( isPalin(s, st, end) )
            return 0;

        int min = Integer.MAX_VALUE;
        for (int k = st; k < end; k++) {
            int currMin = (dp[st][k] == -1 ? solveRecursively(s, st, k) : dp[st][k]) + 1 +
                    (dp[k + 1][end] == -1 ? solveRecursively(s, k + 1, end) : dp[k + 1][end]);
            min = Math.min(min, currMin);
        }
        dp[st][end] = min;
        return dp[st][end];
    }

    private boolean isPalin(String s, int st, int end) {

        for (int i = st, j = end; i <= j; i++, j--) {
            if ( s.charAt(i) != s.charAt(j) )
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
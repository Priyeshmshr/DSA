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

public class LC1130 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        mctFromLeafValues(new int[]{6, 2, 5, 4});
    }

    public static void main(String[] args) {
        LC1130 solver = new LC1130();
        solver.solve();
    }

    int dp[][]=new int[40][40];
    int max[][] = new int[40][40];
    private int mctFromLeafValues(int[] arr) {
        for(int i=0;i<40;i++){
            for(int j=0;j<40;j++){
                dp[i][j]=-1;
            }
        }
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++) {

                int maxSoFar = Integer.MIN_VALUE;

                for (int k = i; k <= j; k++) {
                    if ( maxSoFar < arr[k] )
                        maxSoFar = arr[k];
                }
                max[i][j] = maxSoFar;
            }
        }


        // 6, 2, 4, 5
        //
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){

            }
        }

        dp[0][arr.length-1]=solveRecursively(arr, 0, arr.length - 1);
//        dp1[0][arr.length-1] = tabulation(arr,0,arr.length-1);
        System.out.println(dp[0][arr.length-1]);
        return 0;
    }

//    private int tabulation(int[] arr, int st, int end) {
//
//    }

    private int solveRecursively(int[] arr, int st, int end) {

        if ( end - st <= 0 ) { // only two elements left
            return 0;
        }

        if(dp[st][end]!=-1)
            return dp[st][end];

        int minSoFar = Integer.MAX_VALUE;

        for (int pivot = st; pivot < end; pivot++) {

            int leftLargest = max[st][pivot];
            int rightLargest = max[pivot + 1][end];
            int valueForCurrentPivotedTree = leftLargest * rightLargest;

            if(dp[st][pivot]==-1){
                dp[st][pivot]= solveRecursively(arr, st, pivot);
            }
            if(dp[pivot+1][end]==-1){
                dp[pivot+1][end] = solveRecursively(arr, pivot + 1, end);
            }

            valueForCurrentPivotedTree += dp[st][pivot]+dp[pivot+1][end];

            minSoFar = Math.min(minSoFar,valueForCurrentPivotedTree);
            dp[st][end]= minSoFar;
        }

        return minSoFar;
    }

    private int findLargest(int[] arr, int st, int end) {
        int max = 0;
        for (int i = st; i <= end; i++) {
            max = arr[i] > max ? arr[i] : max;
        }
        return max;
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
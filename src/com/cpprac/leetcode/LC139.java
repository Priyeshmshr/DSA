package com.cpprac.leetcode;

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

public class LC139 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        boolean ans = wordBreak("abcd", Arrays.asList("a","abc","B","cd"));
        System.out.println(ans);
    }

    public static void main(String[] args) {
        LC139 solver = new LC139();
        solver.solve();
    }

    int dp[];

    public boolean wordBreak(String s, List<String> wordDict) {
        if ( s.length() == 0 )
        dp = new int[s.length()];
//        Arrays.fill(dp1,-1);
        HashSet<String> wordDictSet = new HashSet<>(wordDict);
        return tabulation(s, wordDictSet);
//        return solveRecursivelyEff(0, s, wordDict)==1;
    }

    private boolean tabulation(String s, HashSet<String> wordDictSet) {

        for (int i = 0; i < s.length(); i++) {

            for (int j = i; j >= 0; j--) {
                if ( (j == 0 || dp[j - 1] == 1) && wordDictSet.contains(s.substring(j, i + 1)) ) {
                    dp[i] = 1;
                    break;
                }
            }
        }
        return dp[s.length() - 1] == 1;
    }

    private int solveRecursivelyEff(int ind, String s, List<String> wordDict) {

        if ( s.length() == 0 ) {
            return 1;
        }
        if(dp[ind]!=0){
            return dp[ind];
        }
        for (String word : wordDict) {
            if(s.startsWith(word)){
                dp[ind]=solveRecursivelyEff(ind+word.length(),s.substring(word.length()),wordDict);
                if(dp[ind]==1)
                    return 1;
            }
        }
        return dp[ind];
    }

    private int solveRecursively(int ind, String s, HashSet<String> wordDict) {

        if ( ind == s.length() )
            return 2;

        StringBuilder temp = new StringBuilder();

        int ans = 2;

        while (ans == 2 && ind < s.length()) {
            temp.append(s.charAt(ind++));
            if ( wordDict.contains(temp.toString()) ) {
                if ( ind == s.length() )
                    ans = 1;
                else {
                    if ( dp[ind] == 0 )
                        dp[ind] = solveRecursively(ind, s, wordDict);
                    ans = dp[ind];
                }
            }
        }
        return ans;
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
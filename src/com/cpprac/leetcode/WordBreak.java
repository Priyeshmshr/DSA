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

public class WordBreak {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        out.printLine(wordBreak2("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
        out.printLine(wordBreak2("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
        out.printLine(wordBreak2("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        out.flush();

    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean dp[] = new boolean[s.length() + 1];
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            for (String word : wordDict) {
                int end = i + word.length();
                if ( end <= s.length() && s.substring(i, end).equals(word) ) {
                    dp[i] = end == s.length() || dp[end];
                }
                if ( dp[i] )
                    break;
            }
        }
        return dp[0];
    }

    public List<String> wordBreak2(String s, List<String> wordDict) {
        boolean dp[] = new boolean[s.length() + 1];
        int n = s.length();
        ArrayList<String>[] ans = new ArrayList[s.length() + 1];
        ans[n] = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            ans[i] = new ArrayList<>();
            for (String word : wordDict) {
                int end = i + word.length();
                if ( end <= s.length() && s.substring(i, end).equals(word) ) {
                    dp[i] = dp[i] || end == s.length() || dp[end];
                    if ( dp[i] ) {
                        ans[i].add(word);
                         if ( end == s.length() ) {
                             ans[i].add(word);
                         } else {
                             StringBuilder sb = new StringBuilder(word);
                             sb.append(" ");
                             for (int j = 0; j < ans[end].size(); j++) {
                                 ans[i].add(sb.toString() + ans[end].get(j));
                             }
                         }
                    }
                }
            }
        }
        ArrayList<String>[] res = new ArrayList[s.length() + 1];
        res[0] = new ArrayList<>();
        if ( dp[0] ) {
            for (int i = n - 1; i >= 0; i--) {
                res[i] = new ArrayList<>();
                for (int j = 0; j < ans[i].size(); j++) {
                    int end = i + ans[i].get(j).length();
                    if ( end < s.length() ) {
                        for (int k = 0; k < res[end].size(); k++) {
                            res[i].add(ans[i].get(j) + " " + res[end].get(k));
                        }
                    } else {
                        res[i].add(ans[i].get(j));
                    }
                }
            }
        }
        return res[0];
    }

    public static void main(String[] args) {
        WordBreak solver = new WordBreak();
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
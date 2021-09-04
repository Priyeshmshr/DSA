package com.cpprac.leetcode.Contests;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

public class Virt_WeeklyContest192 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        /*BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
        browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
        browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
        out.printLine(browserHistory.back(1));                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
        out.printLine(browserHistory.back(1));                   // You are in "facebook.com", move back to "google.com" return "google.com"
        browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
        browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
        browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
        browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
        browserHistory.back(7);            */       // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
        out.printLine(minCost(new int[]{3, 1, 2, 3}, new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}}, 4, 3, 3));
        out.flush();
    }


    public static void main(String[] args) {
        Virt_WeeklyContest192 solver = new Virt_WeeklyContest192();
        solver.solve();
    }

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int dp[][][] = new int[m + 2][m + 2][n + 2]; // m , target, n   100 *100*20

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(dp[i][j], 1000010);
            }
        }

        int unColoredHouse = 1;
        while (unColoredHouse<m && houses[unColoredHouse-1] != 0)
            unColoredHouse++;
        if(unColoredHouse<=m) {
            for (int i = 1; i <= n; i++) {
                dp[unColoredHouse][1][i] = cost[unColoredHouse - 1][i - 1];
            }
        }
        for (int i = 2; i <= m; i++) {
            for (int j = 1; j < i; j++) {
                if ( houses[i - 1] != 0 ) {
                    int color = houses[i - 1];
                    dp[i][j][color] = Math.min(dp[i][j][color], dp[i - 1][j][color]);
                    for (int k = 1; k <= n; k++) {
                        if ( color != k )
                            dp[i][j + 1][color] = Math.min(dp[i][j + 1][color], dp[i - 1][j][k]);
                    }
                } else {
                    for (int k = 1; k <= n; k++) {
                        for (int kprev = 1; kprev <= n; kprev++) {
                            // if already painted just change target and update cost.
                            if ( k == kprev ) {
                                dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][kprev] + cost[i - 1][k - 1]);
                            } else {
                                dp[i][j + 1][k] = Math.min(dp[i][j + 1][k], dp[i - 1][j][kprev] + cost[i - 1][k - 1]);
                            }
                        }
                    }
                }
            }
        }
        int ans = 1000010;
        for (int i = 1; i <= n; i++) {
            ans = Math.min(ans, dp[m][target][i]);
        }
        return ans == 1000010 ? -1 : ans;
    }

    class BrowserHistory {

        ArrayList<String> arrayList = new ArrayList<>();
        int top = -1;
        int pointer = -1;

        public BrowserHistory(String homepage) {
            arrayList = new ArrayList<>();
            arrayList.add(homepage);
            top++;
            pointer++;
        }

        public void visit(String url) {
            while (pointer < top) {
                top--;
            }
            top++;
            pointer++;
            arrayList.add(top, url);
        }

        public String back(int steps) {
            pointer = Math.max(0, pointer - steps);
            return arrayList.get(pointer);
        }

        public String forward(int steps) {
            pointer = Math.min(top, pointer + steps);
            return arrayList.get(pointer);
        }
    }

    public int[] getStrongest(int[] arr, int k) {
        Arrays.sort(arr);

        int m = arr[(arr.length - 1) / 2];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer t1, Integer t2) {
                if ( Math.abs(t2 - m) == Math.abs(t1 - m) ) {
                    return Integer.compare(t2, t1);
                } else
                    return Integer.compare(Math.abs(t1 - m), Math.abs(t2 - m));
            }
        });
        int res[] = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = list.get(i);
        }
        return res;
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
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

public class LC983 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int[] days = IOUtils.readIntegerArray(in, in.readInt()), costs = IOUtils.readIntegerArray(in, in.readInt());
        out.printLine(minCostTickets(days, costs));
        out.close();
    }

    public static void main(String[] args) {
        LC983 solver = new LC983();
        solver.solve();
    }

    public int minCostTickets(int[] days, int[] costs) {
        return dp(days,costs);
//        int currentDay = 1;
//        return Math.min(costs[0] + recur(days, currentDay + 1, costs), Math.min(costs[1] + recur(days, currentDay + 7, costs), costs[2] + recur(days, currentDay + 30, costs)));
    }

    private int recur(int[] days, int day, int[] costs) {

        if (day > 365)
            return 0;

        int currentDay = 0;
        for (int i = 0; i < days.length; i++) {
            if (day <= days[i]) {
                currentDay = days[i];
                break;
            }
        }
        if (currentDay == 0)
            return 0;

        return Math.min(costs[0] + recur(days, currentDay + 1, costs), Math.min(costs[1] + recur(days, currentDay + 7, costs), costs[2] + recur(days, currentDay + 30, costs)));
    }

    private int dp(int[] days, int[] costs) {

        //dp1(i) = Math.min(dp1[i+1]+costs[0], Math.min((dp1[i+7]+ costs[1]),(dp1[i+30]+ costs[2])));

        int dp[] = new int[days[days.length - 1] + 1];

        for (int i = days.length - 1; i >= 0; i--) {

            int j1 = days[i] + 1, j7 = days[i] + 7, j30 = days[i] + 30;

            if (i == days.length - 1) {
                dp[days[i]] = Math.min(costs[0], Math.min(costs[1], costs[2]));
            }else{
                int cost1 = dp[addToCurrentDay(days,j1)], cost7 = dp[addToCurrentDay(days,j7)], cost30 = dp[addToCurrentDay(days,j30)];
                dp[days[i]] = Math.min(costs[0]+cost1,Math.min(costs[1]+cost7,costs[2]+cost30));
            }
        }
        return dp[days[0]];
    }

    private int addToCurrentDay(int[] days, int day) {
        int currentDay = 0;
        for (int i = 0; i < days.length; i++) {
            if (day <= days[i]) {
                currentDay = days[i];
                break;
            }
        }
        return currentDay;
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
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
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
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
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
            if (filter != null)
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
                if (i != 0)
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
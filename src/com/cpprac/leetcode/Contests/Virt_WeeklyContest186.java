package com.cpprac.leetcode.Contests;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Virt_WeeklyContest186 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        List<List<Integer>> input = new ArrayList<>();
        ArrayList<Integer> arr; /*= new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);
        input.add(arr);
        arr = new ArrayList<>();
        arr.add(6);
        arr.add(7);
        input.add(arr);
        arr = new ArrayList<>();
        arr.add(8);
        input.add(arr);
        arr = new ArrayList<>();
        arr.add(9);
        arr.add(10);
        arr.add(11);
        input.add(arr);
        arr = new ArrayList<>();
        arr.add(12);
        arr.add(13);
        arr.add(14);
        arr.add(15);
        arr.add(16);
        input.add(arr);
        findDiagonalOrderOpt(input);*/

        /*arr = new ArrayList<>();
        arr.add(20);
        arr.add(17);
        arr.add(13);
        arr.add(14);
        input.add(arr);
        arr = new ArrayList<>();
        arr.add(12);
        arr.add(6);
        input.add(arr);
        arr = new ArrayList<>();
        arr.add(3);
        arr.add(4);
        input.add(arr);
        findDiagonalOrderMoreOpt(input);*/

        constrainedSubsetSum(new int[]{-7609,249,-1699,2385,9125,-9037,1107,-8228,856,-9526},9);
    }

    public int maxScore(String s) {
        int preZero[] = new int[s.length()];
        int preOnes[] = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if ( i == 0 ) {
                if ( s.charAt(0) == '0' )
                    preZero[0] = 1;
                else
                    preOnes[0] = 1;
            } else {
                if ( s.charAt(i) == '0' ) {
                    preZero[i] = preZero[i - 1] + 1;
                    preOnes[i] = preOnes[i - 1];
                } else {
                    preOnes[i] = preOnes[i - 1] + 1;
                    preZero[i] = preZero[i - 1];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = Math.max(res, preZero[i] + (preOnes[s.length() - 1] - preOnes[i]));
        }
        return res;
    }

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int prefSum[] = new int[n + 1];
        prefSum[1] = cardPoints[0];
        for (int i = 1; i < n; i++) {
            prefSum[i + 1] += prefSum[i] + cardPoints[i];
        }
        int rem = n - k, st = 0, end = rem;
        int ans = 0;
        while (end < n + 1) {
            ans = Math.max(prefSum[n] - (prefSum[end] - prefSum[st]), ans);
            st++;
            end++;
        }
        return ans;
    }

    private int recur(int[] cardPoints, int st, int end, int k) {

        if ( st > end ) return 0;
        if ( k == 0 ) return 0;
        if ( st == end ) return cardPoints[st];

        return Math.max(cardPoints[st] + recur(cardPoints, st + 1, end, k - 1),
                cardPoints[end] + recur(cardPoints, st, end - 1, k - 1));
    }

    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int dp[] = new int[n];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(nums[0]);
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            int curr = deque.peekFirst() + nums[i];
            dp[i] = Math.max(curr, nums[i]);
            res = Math.max(dp[i], res);
            if ( deque.size()==k ) {
                deque.removeFirst();
            }
            while (!deque.isEmpty() && dp[i] > deque.getLast()) {
                deque.removeLast();
            }
            deque.add(dp[i]);
        }
        return res;
    }


    public static void main(String[] args) {
        Virt_WeeklyContest186 solver = new Virt_WeeklyContest186();
        solver.solve();
    }

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int n = nums.size();
        int count = 0;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ans.add(nums.get(i).get(0));
            for (int j = 1, k = i - 1; j < i && k >= 0; j++, k--) {
                count++;
                if ( nums.get(k).size() > j )
                    ans.add(nums.get(k).get(j));
            }
        }
        int j = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (; j < nums.get(i).size(); j++) {
                for (int k = j, ii = i; k < (j + i) && ii >= 0; k++, ii--) {
                    count++;
                    if ( nums.get(ii).size() > k )
                        ans.add(nums.get(ii).get(k));
                }
            }
            j++;
        }
        System.out.println(count);
        int rs[] = new int[ans.size()];
        int it = 0;
        for (int i : ans)
            rs[it++] = i;
        return rs;
    }

    public int[] findDiagonalOrderOpt(List<List<Integer>> nums) {
        int n = nums.size();
        int count = 0;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            count += nums.get(i).size();
            if ( i == n - 1 ) {
                for (int j = nums.get(i).size() - 1; j >= 0; j--) {
                    ArrayList<Integer> arr = new ArrayList<>();
                    arr.add(nums.get(i).get(j));
                    ans.add(arr);
                }
            } else {
                for (int j = nums.get(i).size() - 1; j >= 0; j--) {
                    if ( j < ans.size() ) {
                        if ( j == 0 ) {
                            ArrayList<Integer> arr = new ArrayList<>();
                            arr.add(nums.get(i).get(j));
                            ans.add(arr);
                        } else {
                            ArrayList<Integer> arr = ans.get(ans.size() - j);
                            arr.add(nums.get(i).get(j));
                        }
                    } else {
                        ArrayList<Integer> arr = new ArrayList<>();
                        arr.add(nums.get(i).get(j));
                        ans.add(0, arr);
                    }
                }
            }
        }
        int rs[] = new int[count];
        int it = 0;
        for (int i = ans.size() - 1; i >= 0; i--)
            for (int j = 0; j < ans.get(i).size(); j++) {
                rs[it++] = ans.get(i).get(j);
            }
        return rs;
    }

    public int[] findDiagonalOrderMoreOpt(List<List<Integer>> nums) {
        int n = nums.size();
        Map<Integer, ArrayList<Integer>> ans = new HashMap<>();
        int maxN = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                count++;
                maxN = Math.max(maxN, i + j);
                ArrayList<Integer> arr = ans.getOrDefault(i + j, new ArrayList<>());
                arr.add(nums.get(i).get(j));
                ans.put(i + j, arr);
            }
        }
        int rs[] = new int[count];
        int it = 0;
        for (int i = 0; i <= maxN; i++) {
            for (int j = ans.get(i).size() - 1; j >= 0; j--) {
                rs[it++] = ans.get(i).get(j);
            }
        }
        return rs;
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
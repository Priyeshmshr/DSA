package com.cpprac.leetcode.Contests;

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

public class Virt_WeeklyContest188 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        System.out.println(ways1(new String[]{
                "A..",
                "AAA",
                "..."}, 3));
        System.out.println(ways1(new String[]{
                "A..", "AA.", "..."}, 3));
        System.out.println(ways1(new String[]{
                "A..", "A..", "..."}, 1));
    }

    public static void main(String[] args) {
        Virt_WeeklyContest188 solver = new Virt_WeeklyContest188();
        solver.solve();
    }

    int dp[][][];

    public int ways1(String[] pizza, int k) {
        int n = pizza.length;
        int m = pizza[0].length();
        dp = new int[n + 1][m + 1][k + 1];

        if ( pizza[n - 1].charAt(m - 1) == 'A' )
            dp[n - 1][m - 1][1] = 1;
        else
            dp[n - 1][m - 1][1] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if ( i != n - 1 || j != m - 1 ) {

                    //horizontal Cut possible
                    boolean horPoss = false;
                    for (int l = j; l < m; l++) {
                        if ( pizza[i].charAt(l) == 'A' ) {
                            horPoss = true;
                            break;
                        }
                    }
                    //vertical Cut possible
                    boolean verPoss = false;
                    for (int l = i; l < n; l++) {
                        if ( pizza[l].charAt(j) == 'A' ) {
                            verPoss = true;
                            break;
                        }
                    }
                    dp[i][j][1] = (horPoss || verPoss ||
                            (i < n - 1 && j < n - 1 && dp[i + 1][j + 1][1] > 0)) ? 1 : 0;

                    for (int ki = 2; ki <= k; ki++) {
                        int horCut = horPoss ? dp[i + 1][j][ki - 1] : 0;
                        int verCut = verPoss ? dp[i][j + 1][ki - 1] : 0;
                        dp[i][j][ki] = horCut + verCut + dp[i + 1][j][ki] + dp[i][j + 1][ki]; // no cut;
                    }
                }
            }
        }
        return dp[0][0][k];
    }

    public List<String> buildArray(int[] target, int n) {
        int curr = 1;
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < target.length; i++) {
            if ( curr == target[i] ) {
                list.add("PUSH");
                curr++;
            } else {
                while (curr != target[i]) {
                    list.add("PUSH");
                    list.add("POP");
                    curr++;
                }
                list.add("PUSH");
                curr++;
            }
        }
        return list;
    }


    boolean hasAppleInChild[];
    int vis[];
    ArrayList<ArrayList<Integer>> graph;

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(0, new ArrayList<>());
        for (int[] edge : edges) {
            ArrayList<Integer> list = graph.get(edge[0]);
            list.add(edge[0], edge[1]);
        }
        hasAppleInChild = new boolean[n];
        vis = new int[n];
        dfs(0, hasApple);
        if ( hasAppleInChild[0] )
            dfs2(0, hasApple);
        return res;
    }

    int res = 0;

    private void dfs2(int i, List<Boolean> hasApple) {
        if ( i != 0 )
            res++;

        for (Integer child : graph.get(i)) {
            if ( hasAppleInChild[child] ) {
                dfs2(child, hasApple);
            }
        }

        if ( i != 0 )
            res++;
    }

    private boolean dfs(int i, List<Boolean> hasApple) {
        hasAppleInChild[i] = hasApple.get(i);
        for (Integer child : graph.get(i)) {
            boolean has = dfs(child, hasApple);
            hasAppleInChild[child] = has;
            hasAppleInChild[i] |= has;
        }
        return hasAppleInChild[i];
    }


    public int countTriplets(int[] arr) {

        long prefXor[] = new long[arr.length];
        prefXor[0] = 0;
        prefXor[1] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefXor[i + 1] = prefXor[i] ^ arr[i];
        }

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = j; k < arr.length; k++) {
                    long a = prefXor[j - 1] ^ prefXor[i - 1];
                    long b = prefXor[k] ^ prefXor[j - 1];
                    if ( a == b )
                        res++;
                }
            }
        }

        return res;
    }

    int MOD = 1000000007;

    int r, c;


    public int ways(String[] pizza, int k) {
        if ( pizza.length == 0 )
            return 0;
        r = pizza.length;
        c = pizza[0].length();
        int totalApples = 0;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if ( pizza[i].charAt(j) == 'A' ) totalApples++;
            }
        }

        if ( (totalApples + 1) <= k )
            return 0;

        if ( k == 1 )
            return totalApples >= 2 ? 1 : 0;
        recur(pizza, 0, 0, k);
        return total % MOD;
    }

    int total = 0;

    private void recur(String[] pizza, int ri, int ci, int k) {
        if ( k == 1 ) {
            for (int i = ri; i < r; i++) {
                for (int j = ci; j < c; j++) {
                    if ( pizza[i].charAt(j) == 'A' ) {
                        total++;
                        return;
                    }
                }
            }
            return;
        }

        for (int i = ri; i < r; i++) {
            for (int j = ci; j < c; j++) {
                if ( pizza[i].charAt(j) == 'A' ) {
//                    if(dp[r+j+1][k]==0) {
                    recur(pizza, i + 1, ci, k - 1); //cut horizontally
//                        dp[r+j+1][k]=1;
//                    }
//                    if(dp[i+1][k]==0) {
                    recur(pizza, ri, j + 1, k - 1); //cut vertically
//                        dp[i+1][k]=1;
//                    }
                }
            }
        }
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
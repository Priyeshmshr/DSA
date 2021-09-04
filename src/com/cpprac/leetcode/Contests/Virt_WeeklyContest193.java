package com.cpprac.leetcode.Contests;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Virt_WeeklyContest193 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
//        TreeAncestor treeAncestor = new TreeAncestor(7, new int[]{-1, 0, 0, 1, 1, 2, 2});
//
//        treeAncestor.getKthAncestor(3, 1);  // returns 1 which is the parent of 3
//        treeAncestor.getKthAncestor(5, 2);  // returns 0 which is the grandparent of 5
//        treeAncestor.getKthAncestor(6, 3);  // returns -1 because there is no such ancestor
        minDays(new int[]{56, 89, 16, 25, 33, 49, 6, 95, 16, 8, 63, 53, 38, 1, 91},
                5,
                3);
    }


    class TreeAncestor {
        ArrayList<Integer> graph[];
        int level[];
        int sqrtParent[];
        int parent[];

        public TreeAncestor(int n, int[] parent) {
            this.parent = parent;
            level = new int[n + 1];
            sqrtParent = new int[n + 1];
            graph = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 1; i < n; i++) {
                graph[i].add(parent[i]);
                graph[parent[i]].add(i);
            }
            dfs(0, -1, 0);
            sqrtDecompose(0, (int) Math.sqrt(n), -1);
        }

        private void sqrtDecompose(int source, int sq, int par) {
            if ( level[source] < sq ) {
                sqrtParent[source] = 1;
            } else if ( level[source] % sq == 0 ) {
                sqrtParent[source] = parent[source];
            } else {
                sqrtParent[source] = sqrtParent[parent[source]];
            }
            for (Integer v : graph[source]) {
                if ( v != par )
                    sqrtDecompose(v, sq, source);
            }
        }

        private void dfs(int source, int par, int lev) {
            level[source] = lev;
            for (Integer v : graph[source]) {
                if ( v != par )
                    dfs(v, source, lev + 1);
            }
        }

        public int getKthAncestor(int node, int k) {
            if ( level[node] < k ) {
                return -1;
            }
            while (level[sqrtParent[node]] > k) {
                node = sqrtParent[node];
            }
            while (level[node] > k) {
                node = parent[k];
            }
            return node;
        }
    }

    public int findLeastNumOfUniqueInts(int[] arr, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            set.add(arr[i]);
        }

        List<Map.Entry<Integer, Integer>> list = map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue)).collect(Collectors.toList());
        for (Map.Entry<Integer, Integer> entry : list) {
            if ( entry.getValue() < k ) {
                k -= entry.getValue();
                set.remove(entry.getKey());
            }
        }
        return set.size();
    }

    class Pair {
        int i;
        int j;
        int max;

        public Pair(int i, int j, int max) {
            this.i = i;
            this.j = j;
            this.max = max;
        }
    }

    public int minDays(int[] bloomDay, int m, int k) {

        int n = bloomDay.length;
        if ( n < m * k ) {
            return -1;
        }

        int l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            l = Math.min(l, bloomDay[i]);
            r = Math.max(r, bloomDay[i]);
        }

        int res = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ( isPossible(bloomDay, m, k, n, mid) ) {
                res = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return res;
    }

    private boolean isPossible(int[] bloomDay, int m, int k, int n, int day) {
        int count = 0;
        int consec = k;
        for (int i = 0; i < n; i++) {
            if ( bloomDay[i] <= day ) {
                consec--;
                if ( consec == 0 ) {
                    count++;
                    consec = k;
                }
            }else{
                consec=k;
            }
        }
        if(count>=m)
            return true;
        else return false;
    }


    public static void main(String[] args) {
        Virt_WeeklyContest193 solver = new Virt_WeeklyContest193();
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
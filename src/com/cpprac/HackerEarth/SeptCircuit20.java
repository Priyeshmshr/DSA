package com.cpprac.HackerEarth;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SeptCircuit20 {

    int mod = (int) 1e9 + 7;
    long s[];
    long a[];
    long lazy[];

    public void buildRangeMinQueryST(int st, int end, int id) {
        if ( end - st < 2 ) {
            s[id] = a[st];
            return;
        }
        int mid = (st + end) / 2;
        buildRangeMinQueryST(st, mid, id * 2);
        buildRangeMinQueryST(mid, end, id * 2 + 1);
        s[id] = s[id * 2] * s[id * 2 + 1];
    }

    public long queryST(int x, int y, int id, int st, int end) {
        if ( y <= st || x >= end ) {
            return 0;
        }
        if ( x <= st && y >= end ) {
            return s[id];
        }
        shift(id, st, end);
        int mid = (st + end) / 2;
        long left = queryST(x, y, id * 2, st, mid);
        long right = queryST(x, y, id * 2 + 1, mid, end);
        return left * right;
    }

    private void upd(int id, int l, int r, long x) {//	increase all members in this interval by x
        lazy[id] = lazy[id] * x;
        s[id] = s[id] * (long) Math.pow(x, (r - l));
    }

    private void shift(int id, int l, int r) {//pass update information to the children
        int mid = (l + r) / 2;
        upd(id * 2, l, mid, lazy[id]);
        upd(id * 2 + 1, mid, r, lazy[id]);
        lazy[id] = 1;// passing is done
    }

    private void increase(int x, int y, int v, int id, int l, int r) {
        if ( x >= r || l >= y ) return;
        if ( x <= l && r <= y ) {
            upd(id, l, r, v);
            return;
        }
        shift(id, l, r);
        int mid = (l + r) / 2;
        increase(x, y, v, id * 2, l, mid);
        increase(x, y, v, id * 2 + 1, mid, r);
        s[id] = s[id * 2] * s[id * 2 + 1];
    }

    public void solve7() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt(), k = in.readInt(), q = in.readInt();
        a = IOUtils.readLongArray(in, n);
//        buildRangeMinQueryST(0, n, 1);
        while (q-- > 0) {
            int type = in.readInt();
            int l = in.readInt(), r = in.readInt();
            if ( type == 1 ) {
//                queryST(l - 1, r, 1, 0, n);
                out.printLine("Yes");
            } else if ( type == 2 ) {
                int x = in.readInt(), y = in.readInt();
            } else {

            }
        }
        out.flush();
    }


    LinkedList<Long> cost;
    LinkedList<long[]> sleepStamina;  // time, stamina
    ArrayList<Long> costAr;
    ArrayList<long[]> sleepStaminaAr;
    long maxValue = (long) (1e9) * 1100;
    long dp[];

    private long recur(int n, int ind, long currStamina) {
        if ( currStamina < 0 ) return maxValue;
        if ( ind == n ) return 0;
//        if(dp[ind]!=0) return dp[ind];
        long ans = Math.min(recur(n, ind + 1, currStamina + sleepStaminaAr.get(ind)[1] - costAr.get(ind)) + sleepStaminaAr.get(ind)[0],
                recur(n, ind + 1, currStamina - costAr.get(ind)));
//        dp[ind] = ans;
        return ans;
    }

    ArrayList<long[]> graph[];
    ArrayList<long[]> sleeps;

    public void solve5() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        cost = new LinkedList<>();
        sleepStamina = new LinkedList<>();
        sleeps = new ArrayList<>();
        //dp = new long[3];

        int n = in.readInt(), m = in.readInt(), a = in.readInt(), b = in.readInt();
        graph = new ArrayList[n + 3];

        for (int i = 0; i < n + 2; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int xi = in.readInt(), yi = in.readInt();
            long zi = in.readInt();
            graph[xi].add(new long[]{yi, zi});
            graph[yi].add(new long[]{xi, zi});
        }
        for (int i = 0; i < n; i++) {
            long ci = in.readLong(), hi = in.readLong();
            sleeps.add(new long[]{hi, ci});
        }
        vis = new int[n + 2];
        dfs(a, b, n);
        out.printLine(ans == maxValue ? -1 : ans);
        out.flush();
    }

    int vis[];

    long ans = Long.MAX_VALUE;

    private void dfs(int u, int end, int n) {
        if ( vis[u] == 1 ) return;
        if ( u == end ) {
            costAr = new ArrayList<Long>(cost);
            sleepStaminaAr = new ArrayList<>(sleepStamina);
            ans = Math.min(ans, recur(sleepStamina.size(), 0, 0));
            return;
        }
        vis[u] = 1;
        sleepStamina.addLast(sleeps.get(u-1));
        for (long[] v : graph[u]) {
            if(vis[(int)v[0]]==0) {
                cost.addLast(v[1]);
                dfs((int) v[0], end, n);
                cost.removeLast();
            }
        }
        vis[u] = 0;
        sleepStamina.removeLast();
    }

    public static void main(String[] args) {
        SeptCircuit20 solver = new SeptCircuit20();
        solver.solve5();
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
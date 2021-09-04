package com.cpprac.HackerEarth;

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

public class XennyAndRandomCubes {

    public static int MOD = 1000000007;

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt(), k = in.readInt();
        int freq[][] = new int[n][26];// freq[dice][character]
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 6; j++) {
                char ch = in.readString().charAt(0);
                freq[i][ch - 'a']++;
            }
        }
        String s = in.readString();
//        int dp[][] = new int[n][1 << n]; // dp[setPositions][mask];
//        Arrays.fill(dp[0],1);
        int res = 0;
        for (int i = 0; i <= (1<<n)-1; i++) {

            ArrayList<Integer> dices = getDices(i);
            if ( dices.size() == k ) {
                int dp2[][] = new int[k+1][1<<k];
                Arrays.fill(dp2[0],1);
                for (int j = 0; j <= (1<<k)-1; j++) {
                    int setBits = checkSetBits(j);
                    if(setBits<k) {
                        for (int l = 0; l < k; l++) {
                            if ( (j & (1 << l)) == 0 ) {
//                                System.out.println(i+" "+j+" "+l);
                                dp2[setBits+1][j|1<<l] = (dp2[setBits+1][j|1<<l] + dp2[setBits][j]*freq[dices.get(l)][s.charAt(setBits) - 'a'])%MOD;
                            }
                        }
                    }
                }
                res = (res + dp2[dices.size()][(1<<k)-1])%MOD;
            }
        }
//        out.printLine((dp[k][(1<<k)-1]) % MOD);
        out.printLine(res);
        out.flush();
    }

    private int checkSetBits(int i) {
        int res = 0;
        while (i > 0) {
            res += i & 1;
            i = i >> 1;
        }
        return res;
    }

    private ArrayList<Integer> getDices(int i) {
        ArrayList<Integer> dices = new ArrayList<>();
        int shift = 0;
        while (i >= (1 << shift)) {
            if ( ((1 << shift) & i) != 0 ) {
                dices.add(shift);
            }
            shift++;
        }
        return dices;
    }


    public static void main(String[] args) {
        XennyAndRandomCubes solver = new XennyAndRandomCubes();
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
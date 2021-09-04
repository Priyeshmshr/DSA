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

public class Codeforces {

    long mod = 998244353L;

    long[] ans;

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt();
        int a[] = IOUtils.readIntegerArray(in, n);
        /*
            A = [0 0 0 0 1 1 1 1 0 1 1 0]
            A = [0 0 1 0 1 1 1 1 0 1 0 0] 4*2+3*1+2*1 = 13 = 15 - 2
            B = [0 1 0 1 1 0 1 0 1 0 1 0]
            PosA = [0,1,2,3,8,11]
            PosB = [0,2,5,7,9,11]
            # of exchanges = sum( abs( posA[i] - posB[i] ) ) = 0+1+3+4+1+0 = 9, protection = 15.

            protection = (zeroes*(zeroes-1))/2 - (consecutiveZeros* (consecutiveZeros-1)/2)
            dp[i][zeroes][exchanges][consecutiveZeroes] = protection.

            print -> d[12][6][1-to-(n*n-1)/2]
            dp[pattern][exchanges] = protection.
         */
        int maxZeros = 0, maxExchanges = (n * (n - 1)) / 2;
        int posA[] = new int[n];
        for (int i = 0; i < n; i++) {
            if ( a[i] == 0 ) {
                posA[maxZeros] = i;
                maxZeros++;
            }
        }

        int dp[][][][] = new int[n + 1][maxZeros + 2][(n * n) + 2][maxZeros + 2];

        for (int i = 0; i < n; i++) {

            for (int z = 0; z <= i; z++) {

                for (int ex = 0; ex <= ((i - 1) * (i - 2)) / 2; ex++) {

                    for (int consec = 0; consec <= z; consec++) {
                        if ( z < i ) {
                            //Keep zero here:
                            int newZeroes = z + 1;
                            int newExchanges = ex + Math.abs(posA[z] - i);
                            int lastConsec = consec + 1;
                            int newProtection = dp[i][z][ex][consec] + newZeroes - consec;
                            dp[i][newZeroes][newExchanges][lastConsec] = Math.max(dp[i][newZeroes][newExchanges][lastConsec], dp[i][z][ex][consec] + newProtection);
                        }
                        {
                            // Keep one here:
                            int newZeroes = z;
                            int newExchanges = ex;
                            int lastConsec = 0;
                            int newProtection = dp[i][z][ex][consec];
                            dp[i][newZeroes][newExchanges][lastConsec] = Math.max(dp[i][newZeroes][newExchanges][lastConsec], newProtection);
                        }
                    }
                }
            }
        }

        for (int k = 0; k <= maxExchanges; k++) {
            long ans = 0;
            for (int i = 0; i < maxZeros; i++) {
                ans = Math.max(dp[n - 1][maxZeros][k][i], ans);
            }
            out.print(ans + " ");
        }
        out.printLine();
        out.flush();
    }


    public void solve2() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt();
        int a[] = IOUtils.readIntegerArray(in, n);
        int orders = (n * (n - 1)) / 2;
        ans = new long[orders + 2];
        prefixZeros = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixZeros[i] = prefixZeros[i - 1] + (a[i - 1] == 0 ? 1 : 0);
        }
        long maxSoFar = calculate(a, n);
        out.print(maxSoFar + " ");
        solve(a, orders, n, 0, -2, -2);
        for (int i = 1; i <= orders; i++) {
            maxSoFar = Math.max(maxSoFar, ans[i]);
            out.print(maxSoFar + " ");
        }
        out.flush();
    }

    private int prefixZeros[];

    private void solve(int[] a, int orders, int n, int curOrder, int previ, int prevj) {
        if ( curOrder > orders ) return;
        ans[curOrder] = Math.max(ans[curOrder], calculate(a, n));
        for (int i = 0; i < n; i++) {
            if ( a[i] == 1 ) {
                //move left
                if ( i > 0 && a[i - 1] == 0 && previ != i - 1 && prevj != i ) {
                    a[i - 1] = 1;
                    a[i] = 0;
                    prefixZeros[i] = prefixZeros[i] - 1;
                    solve(a, orders, n, curOrder + 1, i, i - 1);
                    a[i - 1] = 0;
                    a[i] = 1;
                    prefixZeros[i] = prefixZeros[i] + 1;
                }
                //move right
                if ( i < n - 1 && a[i + 1] == 0 && previ != i + 1 && prevj != i ) {
                    a[i + 1] = 1;
                    a[i] = 0;
                    prefixZeros[i + 1] = prefixZeros[i + 1] + 1;
                    solve(a, orders, n, curOrder + 1, i, i + 1);
                    a[i + 1] = 0;
                    a[i] = 1;
                    prefixZeros[i + 1] = prefixZeros[i + 1] - 1;
                }
            }
        }
    }

    private long calculate(int[] a, int n) {
        long ans = 0, zerosSoFar = 0;
        for (int i = n; i >= 1; i--) {
            if ( a[i - 1] == 1 ) {
                ans += zerosSoFar * (prefixZeros[i]);
                zerosSoFar = 0;
            } else
                zerosSoFar++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Codeforces solver = new Codeforces();
//        System.out.println(217871987498122L + 2128012501878L);
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
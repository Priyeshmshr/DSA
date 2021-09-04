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

public class Codeforces2 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt(), m = in.readInt();
            long a[][] = new long[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    a[i][j] = in.readLong();
                }
            }
            long ans = 0;
            for (int i = 0; i < Math.ceil(n / 2.0); i++) {
                for (int j = 0; j < Math.ceil(m / 2.0); j++) {
                    ArrayList<Long> list = new ArrayList<>();
                    list.add(a[i][j]);
                    list.add(a[i][m - j - 1]);
                    list.add(a[n - i - 1][j]);
                    list.add(a[n - i - 1][m - j - 1]);
                    long val1 = 0, finalVal = Long.MAX_VALUE ;
                    for (Long avg1 : list) {
                         val1 = Math.abs(avg1 - a[i][j]) + Math.abs(avg1 - a[i][m - j - 1]) + Math.abs(avg1 - a[n - i - 1][j]) + Math.abs(avg1 - a[n - i - 1][m - j - 1]);
                        if ( (n % 2 == 1 && i == n / 2) || (m % 2 == 1 && j == m / 2) ) {
                            val1 = val1 / 2;
                        }
                        if ( (n % 2 == 1 && i == n / 2) && (m % 2 == 1 && j == m / 2) ) {
                            val1 = 0;
                        }
                        finalVal = Math.min(finalVal, val1);
                    }
                    ans+=finalVal;
                }
            }
            out.printLine(ans);
        }
        out.flush();
    }

    public void solve1() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        String n = in.readString();
//            long prefixSum[] = new long[n.length() + 2];
        int len = n.length();
        long toSubtract = 0, mod = (long) (1e9 + 7);
        long total = 0;
        for (int i = 0; i < len; i++) {
            total = (total + ((n.charAt(i) - '0') * modularExponentiation(10, len - i - 1, mod)) % mod) % mod;
        }
        long ans = 0;
        for (int i = 0; i < len; i++) {
            toSubtract = (toSubtract + ((i + 1) * modularExponentiation(10, len - i - 1, mod)) % mod) % mod;
            ans = (ans + ((((i + 1) * total) % mod) - toSubtract)) % mod;
        }
        out.printLine(ans);

        out.flush();
    }

    long nCrModPFermat(int n, int r,
                       long p) {
        if ( r == 0 )
            return 1;
        long[] fac = new long[n + 1];
        fac[0] = 1;

        for (int i = 1; i <= n; i++)
            fac[i] = fac[i - 1] * i % p;

        return (fac[n] * modInverse(fac[r], p)
                % p * modInverse(fac[n - r], p)
                % p)
                % p;
    }

    long modInverse(long n, long p) {
        return modularExponentiation(n, p - 2, p);
    }

    private long modularExponentiation(long x, long n, long M) {
        long result = 1;
        while (n > 0) {
            if ( n % 2 == 1 )
                result = (result * x) % M;
            x = (x * x) % M;
            n = n / 2;
        }
        return result % M;
    }

    public static void main(String[] args) {
        Codeforces2 solver = new Codeforces2();
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
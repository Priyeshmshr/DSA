import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * @author priyesh.mishra
 */
public class CSES1 {
    int mod = (int) 1e9 + 7;

    public void solve() {
        int n = inpi();
        int x = inpi();
        int c[] = inpia(n);
        Arrays.sort(c);
        ArrayList<int[]> dp = new ArrayList<>();
        dp.add(new int[]{1, 0});
        for (int i = 1; i <= x; i++) {
            int val = 0, lar = 0;
            for (int j = 0; j < n; j++) {
                if ( i >= c[j] && dp.get(i - c[j])[0] > 0 && dp.get(i - c[j])[1] <= c[j] ) {
                    val = (val + dp.get(i - c[j])[0]) % mod;
                    lar = Math.max(lar, c[j]);
                }
            }
            dp.add(new int[]{val, lar});
        }
        out.println(dp.get(x)[0]);
        out.flush();
    }

    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) {
        new Thread(null, () -> {
            new CSES1().main1();
        }, "1", 1 << 26).start();
    }

    void main1() {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        solve();
        out.flush();
        out.close();
    }

    private byte[] inbuffer = new byte[1024];
    private int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if ( lenbuffer == -1 ) {
            throw new InputMismatchException();
        }
        if ( ptrbuffer >= lenbuffer ) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if ( lenbuffer <= 0 ) {
            return -1;
        }
        return inbuffer[ptrbuffer++];
    }

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    String inps() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    int inpi() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if ( b == '-' ) {
            minus = true;
            b = readByte();
        }
        while (true) {
            if ( b >= '0' && b <= '9' ) {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    long inpl() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
        if ( b == '-' ) {
            minus = true;
            b = readByte();
        }
        while (true) {
            if ( b >= '0' && b <= '9' ) {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    float inpf() {
        return Float.parseFloat(inps());
    }

    double inpd() {
        return Double.parseDouble(inps());
    }

    char inpc() {
        return (char) skip();
    }

    int[] inpia(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = inpi();
        }
        return a;
    }

    long[] inpla(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = inpl();
        }
        return a;
    }

    String[] inpsa(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = inps();
        }
        return a;
    }

    double[][] inpdm(int n, int m) {
        double a[][] = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = inpd();
            }
        }
        return a;
    }

    int[][] inpim(int n, int m) {
        int a[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = inpi();
            }
        }
        return a;
    }
}

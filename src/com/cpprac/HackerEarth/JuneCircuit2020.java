package com.cpprac.HackerEarth;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

/**
 * I/O template picked from shubham.saxena
 */
class JuneCircuit2020 {

    public void solve() {
        int q = inpi();
        while (q-- > 0) {
            long ans = 0;
            int n = inpi();
            int level = (int) (Math.log(n) / Math.log(2));
            for (int i = 0; i < level; i++) {
                int oddNodes = (int) Math.ceil(Math.pow(2, i) / 2.0);
                int nodes = level + 1 - i;
                ans += oddNodes * (nodes - 1);
            }
            int leafNodesAtLastLevel = n - ((int) Math.pow(2, level) - 1);
            int totalLeafNodesAtLastLevel = (int) Math.pow(2, level);
            if ( leafNodesAtLastLevel < totalLeafNodesAtLastLevel ) {
                int remainingLeaf = totalLeafNodesAtLastLevel - leafNodesAtLastLevel;
                int currLevel = level - 1;
                int noOfOddNodes = (((remainingLeaf + 1) / 2) + 1) / 2;
                while (currLevel >= 0) {
                    ans -= noOfOddNodes;
                    noOfOddNodes = (noOfOddNodes + 1) / 2;
                    currLevel--;
                }
            }
            out.println(ans);
        }
        out.flush();

    }

    InputStream obj;
    PrintWriter out;
    String check = "";
    public static void main(String[] args) {
        new Thread(null, () -> {
            new JuneCircuit2020().main1();
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
        if (lenbuffer == -1) {
            throw new InputMismatchException();
        }
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0) {
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
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
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
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
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
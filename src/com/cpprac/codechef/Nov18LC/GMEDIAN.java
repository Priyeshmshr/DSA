package com.cpprac.codechef.Nov18LC;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class GMEDIAN {

    public static final long MOD= 1000000007;

    long ncr[][] = new long[1005][1005];
    long prefSumNcr[][] = new long[1005][1005];

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        calcNCR();
        calcPrefSumOfNcr();
        int t= in.readInt();
        while(t-->0){
            int n = in.readInt();
            int arr[] = new int[n];
            int freq[] = new int[2*n+5];
            for(int i=0;i<n;i++){
                arr[i] = in.readInt();
                freq[arr[i]]++;
            }
            //Calculating for only odd length
            long res = 0;
            for(int i=1;i<=n;i+=2){
                res = (res+ncr[n][i])%MOD;
            }

            //Calculating for only even length
            /**
             * Sort array, Iterate over all elements and perform below steps:
             *  if(freq[curr] > 1) them perform below steps:
             *      1) l = elements on left side of curr;
             *      2) r = elements on right side of curr - 1. (-1 is to exclude the duplicate one which is to be kept fixed at the center.
             *         Eg: 1 2 2 3, here 2nd '2' is fized.
             *      3) k = min(g,l).
             *      4) dup = number of dup elements of arr[i] on right side.
             *      5) ans = ((lc1*gc1) + (lc2*gc2) + ... +(lck*gck))* dup.
             *
             *      ans: 2 2 2 2
             *
             */
            Arrays.sort(arr);
            for(int i=0;i<n;i++){
                if(freq[arr[i]]>1){
                    int l = i,r=n-i-1,dup=0;
                    if(i<n-1 && arr[i+1]==arr[i]){
                        for(int j=i+1;j<n && arr[i]==arr[j];j++)
                            dup++;
                        r= r-1;
                        long tempAns=0;
                        if(dup>=1) {
                            int k = Math.min(l,r);
                            for (int li = 0; li <= k; li++) {
                                int e = r-dup+1;
                                if(e>0)
                                    tempAns = (tempAns + (ncr[l][li] % MOD * ((prefSumNcr[r][li]-prefSumNcr[e-1][li]) % MOD)) %MOD ) % MOD;
                                else
                                    tempAns = (tempAns + (ncr[l][li] % MOD * ((prefSumNcr[r][li]) % MOD)) %MOD ) % MOD;
                            }
                        }
                        res = (res+tempAns)%MOD;
                    }
                }
            }

            out.printLine(res);
        }
        out.close();
    }

    /**
     *
     * ncr = (n-1)c(r-1) + (n-1)c(r)
     */
    private void calcNCR() {
        Arrays.fill(ncr[0],0l);
        ncr[0][0]=1;
        for(int n=1;n<=1003;n++){
            Arrays.fill(ncr[n],0l);
            ncr[n][0]= 1;
            for(int r = 1; r<= Math.min(n,r);r++){
                if(n==r)
                    ncr[n][r]=1;
                else
                    ncr[n][r]= (ncr[n-1][r-1]%MOD + ncr[n-1][r]%MOD)%MOD;
            }
        }
    }

    private void calcPrefSumOfNcr(){

        for(int i=0;i<=1003;i++){
            if (i == 0) {
                for(int j=0;j<=1003;j++){
                    prefSumNcr[i][j] = ncr[i][j]%MOD;
                }
            }else {
                for (int j = 0; j <= 1003; j++) {
                    prefSumNcr[i][j] = (prefSumNcr[i-1][j]+ ncr[i][j])%MOD;
                }
            }
        }
    }

    public static void main(String[] args) {
        GMEDIAN solver = new GMEDIAN();
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

        public static Set<Integer> readIntegerSet(InputReader in, int size) {
            Set<Integer> set = new HashSet<Integer>();
            for (int i = 0; i < size; i++) {
                set.add(in.readInt());
            }
            return set;
        }
    }
}
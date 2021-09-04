package com.cpprac.codeforces.CF1363;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class D {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt(), k = in.readInt();
            int s[][] = new int[k][n];

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < k; i++) {
                int c = in.readInt();
                s[i][0]=c;
                for (int j = 1; j <= c; j++) {
                    s[i][j] = in.readInt();
                }
            }
            print(1, n / 2, out);
            int leftMax = in.readInt();
            print(n / 2 + 1, n, out);
            int rightMax = in.readInt();
            int max = Math.max(leftMax, rightMax);
            int st = 1, end = n;
            if ( leftMax >= rightMax )
                end = n / 2;
            else
                st = n / 2 + 1;

            while (st < end) {
                int mid = (st + end) / 2;
                print(st, mid, out);
                if ( in.readInt() == max ) {
                    end = mid;
                } else {
                    st = mid + 1;
                }
            }
            int maxIndex = st;

            ArrayList<Integer> res = new ArrayList<>();
            for(int i=0;i<k;i++){
                boolean contains = false;
                for (int j = 1; j <= s[i][0]; j++) {
                    if(s[i][j]==maxIndex){
                        contains=true;
                    }
                }
                if(contains){
                    HashSet<Integer> set = new HashSet<>();
                    for (int j = 1; j <= s[i][0]; j++) {
                        set.add(s[i][j]);
                    }

                    out.print("? " + (n-set.size()));
                    for(int j=1;j<=n;j++){
                        if(!set.contains(j))
                            out.print(" " + j);
                    }
                    out.printLine();
                    out.flush();
                    int val = in.readInt();
                    res.add(val);
                }
                else{
                    res.add(max);
                }
            }
            out.print("!");
            for(int i:res)
                out.print(" " + i);
            out.printLine();
            out.flush();
            String ans = in.readString();
            if(ans.equals("Incorrect")){
                System.exit(0);
            }
            out.flush();
        }
    }

    private void print(int st, int end, OutputWriter out) {
        out.print("? " + (end - st + 1));
        for (int i = st; i <= end; i++) {
            out.print(" "+i);
        }
        out.printLine();
        out.flush();
    }

    public static void main(String[] args) {
        D solver = new D();
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
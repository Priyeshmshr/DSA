package com.cpprac.codechef.Snackdown19;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.TreeSet;

class SNCK1A19C1 {

    public static final int MOD_VALUE = 1000000007;

    public static void main(String[] args) {

        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while(t-->0){
            int n = in.readInt();
            int a[] = new int[n];
            Set<Integer> set = new TreeSet<>();
            int countElements[] = new int[51];
            for(int i=0;i<n;i++){
                a[i]= in.readInt();
                set.add(a[i]);
                countElements[a[i]]++;
            }
            if(n==1){
                out.printLine(0);
                out.printLine(a[0]);
                continue;
            }
            if(countElements[47]==n) {
                out.printLine(1);
                a[0]=43;
                for(int i=0;i<n;i++){
                    out.print(a[i] + " ");
                }
                out.printLine();
                continue;
            }

            if(replacementRequired(set)){
                boolean replaced = false;
                out.printLine(1);
                for(int i=0;i<n;i++){
                    if(!replaced && a[i]!=47){
                        out.print(47+ " ");
                        replaced= true;
                    }else
                        out.print(a[i]+ " ");
                }
            }else{
                out.printLine(0);
                for(int i=0;i<n;i++){
                    out.print(a[i]+ " ");
                }
            }
            out.printLine();
        }
        out.flush();
    }

    private static boolean replacementRequired(Set<Integer> set) {

        Integer a[] = new Integer[set.size()];
        a = set.toArray(a);
        boolean eleHasCoPrime[] = new boolean[51];
        for(int i=0;i<a.length;i++){
            if(!eleHasCoPrime[a[i]]) {
                for (int j = 0; j < a.length; j++) {
                    if (i != j && gcd(a[i], a[j]) == 1) {
                        eleHasCoPrime[a[j]] = true;
                        eleHasCoPrime[a[i]] = true;
                    }
                }
            }
            if (!eleHasCoPrime[a[i]])
                return true;
        }
        return false;
    }

    private static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
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

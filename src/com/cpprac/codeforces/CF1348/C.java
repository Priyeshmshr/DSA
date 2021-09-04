package com.cpprac.codeforces.CF1348;

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

public class C {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
//        char ch[] = new char[]{'a','B','c','d','E','f','g','h','i','j','k','l','m',
//                'n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt(), k = in.readInt();
            String s = in.readString();
            int count[] = new int[27];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            int len = 0;
            int offset = 0;
            int prevPos = 0;
            for (int i = 0; i < 26; i++) {
                if ( count[i] > 0 ) {
                    if ( len < k ) {
                        if ( len + count[i] >= k ) {
                            count[i] -= (k - len);
                            int currLen = (int) Math.ceil(count[i] / (k * 1.0));
                            while (currLen-- > 0)
                                sb.append((char) (  'a' + i));
                            prevPos = ((count[i] % k) + offset) % k;
                        } else {
                            len += count[i];
                            offset = len;
                        }
                    } else {
                        int currLen = (count[i + 1] == 0) ? (int) Math.floor(count[i] / (k * 1.0)) : count[i];
                        while (currLen-- > 0)
                            sb.append((char) ('a' + i));
                        if ( prevPos + (count[i] % k) > offset ) {
                            sb.append((char) ('a' + i));
                        }
                        prevPos = ((count[i] % k) + prevPos) % k;
                    }
                }
            }
            out.printLine(sb.toString());
            out.flush();
        }
    }

    public void solve1() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt(), k = in.readInt();
            String s = in.readString();
            char charArr[] = s.toCharArray();
            Arrays.sort(charArr);
            StringBuilder sb = new StringBuilder();
            sb.append(charArr[k - 1]);
            int count[] = new int[27];
            for (int i = k; i < s.length(); i++) {
                count[charArr[i]-'a']++;
            }
            if(s.length() >= 2*k || charArr[0]==sb.charAt(0) ) {

                for (int i = charArr[k] - 'a'; i < 26; i++) {
                    if ( count[i + 1] == 0 && sb.length() == 1 ) {
                        int currLen;
                        if(charArr[0]!=sb.charAt(0)) currLen = count[i] / k;
                        else currLen = (int) Math.ceil(count[i]/(1.0*k));
                        while (currLen-- > 0)
                            sb.append((char) ('a' + i));
                    } else {
                        int currLen = count[i];
                        while (currLen-- > 0)
                            sb.append((char) ('a' + i));
                    }
               /* else{
                    int currLen = (int) Math.floor(count[i] / (k * 1.0));
                    while (currLen-- > 0)
                        sb.append((char) ('a' + i));
                    if ( prevPos + (count[i] % k) > k ) {
                        sb.append((char) ('a' + i));
                    }
                    prevPos = ((count[i] % k) + prevPos) % k;
                }*/
                }
            }/*else if(charArr[0]==sb.charAt(0)){
                int currLen = (int)Math.ceil(count[charArr[0]-'a']/(1.0*k));
                while (currLen-- > 0)
                    sb.append(charArr[0]);
            }*/
            out.printLine(sb.toString());
            out.flush();
        }
    }

    public static void main(String[] args) {
        C solver = new C();
        solver.solve1();
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
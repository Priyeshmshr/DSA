package com.cpprac.codeforces;

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

public class D1055 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        int n = in.readInt();

        String ini[] = new String[n], tar[] = new String[n];
        for (int i = 0; i < n; i++) {
            ini[i] = in.readString();
        }
        for (int i = 0; i < n; i++) {
            tar[i] = in.readString();
        }
        String toReplace = new String(), replaceWith = new String() ;

        for(int k=0;k<n;k++){
            int prev = 0,flag = 0;
            StringBuilder tempToReplace = new StringBuilder(), tempReplaceWith = new StringBuilder();
            if(!ini[k].equals(tar[k])) {
                for (int i = 0; i < ini[k].length(); i++) {
                    if (ini[k].charAt(i) != tar[k].charAt(i)) {
                        tempReplaceWith.append(tar[k], prev, i + 1);
                        tempToReplace.append(ini[k], prev, i + 1);
                        prev = i + 1;
                        flag = 1;
                    }
                    if (flag == 0)
                        prev = i + 1;
                }
                if (tempToReplace.length() > toReplace.length()) {
                    toReplace = tempToReplace.toString();
                    replaceWith = tempReplaceWith.toString();
                }
            }
        }
        /*for (int i = 0; i < n; i++) {
            int index = stringEquals(ini[i],tar[i]);
            if(index!=-1) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(ini[i], 0, index);
                String tempString = ini[i].substring(index);
                String result = tempString.replaceFirst(toReplace, replaceWith);
                result = stringBuilder.append(result).toString();
                if (!result.equals(tar[i])) {
                    out.printLine("NO");
                    out.close();
                    return;
                }
            }
        }*/
        for(int i=0;i<n;i++){
            String result = ini[i].replaceFirst(toReplace, replaceWith);
            if(!result.equals(tar[i])){
                out.printLine("NO");
                out.close();
                return;
            }
        }
        out.printLine("YES");
        out.printLine(toReplace);
        out.printLine(replaceWith);
        out.close();
    }

    private int stringEquals(String s, String s1) {
        for(int i=0;i<s.length();i++){
                if(s.charAt(i)!=s1.charAt(i))
                    return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        D1055 solver = new D1055();
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
            boolean isSpaceChar(int ch);
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
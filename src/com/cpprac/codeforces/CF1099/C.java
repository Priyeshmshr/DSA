package com.cpprac.codeforces.CF1099;

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
import java.util.Stack;

public class C {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        String s = in.readString();
        int k = in.readInt();

        int extraChar = 0,temp=0;

        StringBuilder sb = new StringBuilder();
        String res;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!='*' && s.charAt(i)!='?') {
                sb.append(s.charAt(i));
            }
            else{
                extraChar++;
            }
        }
        temp = extraChar;

        if((s.length()-temp)>k){

            Stack<Character> stack = new Stack<>();
            int i;

            for(i=0;i<s.length();i++){
                if(s.charAt(i)=='*'){
                    stack.pop();
                    extraChar--;
                }else if(s.charAt(i)=='?'){
                    stack.pop();
                    extraChar--;
                }else
                    stack.push(s.charAt(i));

                if((stack.size()+(s.length()-i-1)-extraChar)==k)
                    break;
            }

            StringBuilder gsb = new StringBuilder();
            while(!stack.isEmpty())
                gsb.append(stack.pop());
            gsb.reverse();
            for(i=i+1;i<s.length();i++){
                append(gsb,i,s);
            }
            res = gsb.toString();

        }
        else if((s.length()-temp)<k){
            int i ;
            StringBuilder lsb = new StringBuilder();

            int diff = k - (s.length()-temp);

            for(i=0;i<s.length();i++) {
                if (s.charAt(i) == '*') {
                    while(diff>0) {
                        lsb.append(s.charAt(i-1));
                        diff--;
                    }
                    break;
                }else if(s.charAt(i)!='?'){
                    lsb.append(s.charAt(i));
                }

            }
            for(;i<s.length();i++){
                append(lsb,i,s);
            }
            res= lsb.toString();
        }
        else
            res=sb.toString();

        if(res.length()==k){
            out.printLine(res);
        }else
            out.printLine("Impossible");

        out.close();
    }

    private void append(StringBuilder gsb, int i, String s) {
        if(s.charAt(i)!='*' && s.charAt(i)!='?') {
            gsb.append(s.charAt(i));
        }
    }

    public static void main(String[] args) {
        C solver = new C();
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
package com.cpprac.codechef.Dec18LC;

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

public class INTXOR {

    InputReader in = new InputReader(System.in);
    public void solve() {
        OutputWriter out = new OutputWriter(System.out);
        long t = in.readInt();
        while(t-->0){
            int n = in.readInt();
            List<Long> res = new ArrayList<>();
            /*four(res,out);

            out.print(2);
            for(long i: res){
                out.print(" " + i);
            }
            long last = res.get(res.size()-1);
            for(int i=res.size();i<n;i++) {
                out.print(" "+ ++last);
            }*/
            while(n>=8) {
                four(res,out);
                n= n-4;
            }
            int rem = n%4;
            switch (rem){
                case 0:
                    four(res,out);
                    break;
                case 1:
                    five(res,out);
                    break;
                case 2:
                    six(res,out);
                    break;
                case 3:
                    seven(res,out);
                    break;
            }

            out.print(2);
            for(long i : res){
                out.print(" " +i);
            }
            out.printLine();
            out.flush();
            in.readInt();
        }
    }

    private void seven(List<Long> res, OutputWriter out) {

        long size = res.size();
        out.printLine( 1+" "+(size+1) + " " + (size+2) + " " + (size+3) ); out.flush();
        long a = in.readInt();
        out.printLine( 1+" "+(size+1) + " " + (size+2) + " " + (size+4) ); out.flush();
        long b = in.readInt();
        out.printLine( 1+" "+(size+3) + " " + (size+4) + " " + (size+5) ); out.flush();
        long c = in.readInt();
        out.printLine( 1+" "+(size+3) + " " + (size+4) + " " + (size+6) ); out.flush();
        long d = in.readInt();
        out.printLine( 1+" "+(size+5) + " " + (size+6) + " " + (size+7) ); out.flush();
        long e = in.readInt();
        out.printLine( 1+" "+(size+6) + " " + (size+7) + " " + (size+1) ); out.flush();
        long f = in.readInt();
        out.printLine( 1+" "+(size+5) + " " + (size+7) + " " + (size+2) ); out.flush();
        long h = in.readInt();

        long arr[]= new long[8];

        arr[5] = (a^b^c)  ;
        arr[6] = (a^b^d)  ;
        arr[7] = (arr[5]^arr[6]^e)  ;
        arr[1] = (arr[6]^arr[7]^f)  ;
        arr[2] = (arr[5]^arr[7]^h)  ;
        arr[3] = (arr[1]^arr[2]^a)  ;
        arr[4] = (arr[1]^arr[2]^b)  ;

        for(int i=1;i<=7;i++){
            res.add(arr[i]);
        }
    }

    private void six(List<Long> res, OutputWriter out) {

        long size = res.size();

        out.printLine( 1+" " +(size+1) + " " +(size+2)+ " " +(size+3)) ; out.flush();
        long a = in.readInt();
        out.printLine( 1+" " +(size+1) + " " +(size+2)+ " " +(size+4)) ; out.flush();
        long b = in.readInt();
        out.printLine( 1+" " +(size+3) + " " +(size+4)+ " " +(size+5)) ; out.flush();
        long c = in.readInt();
        out.printLine( 1+" " +(size+3) + " " +(size+4)+ " " +(size+6)) ; out.flush();
        long d = in.readInt();
        out.printLine( 1+" " +(size+1) + " " +(size+5)+ " " +(size+6)) ; out.flush();
        long e = in.readInt();
        out.printLine( 1+" " +(size+2) + " " +(size+5)+ " " +(size+6)) ; out.flush();
        long f = in.readInt();

        long ar[]= new long[8];

        ar[5] = (a^b^c) ;
        ar[6] = (a^b^d) ;
        ar[1] = (ar[5]^ar[6]^e) ;
        ar[2] = (ar[5]^ar[6]^f) ;
        ar[3] = (ar[1]^ar[2]^a) ;
        ar[4] = (ar[1]^ar[2]^b) ;

        for(int i=1;i<=6;i++){
            res.add(ar[i]);
        }
    }

    private void five(List<Long> res, OutputWriter out) {

        long size = res.size();

        out.printLine( 1+" "+(size+1)+" "+(size+2)+" "+(size+3) ) ;out.flush();
        int a = in.readInt();
        out.printLine( 1+" "+(size+2)+" "+(size+3)+" "+(size+4) ) ;out.flush();
        int b = in.readInt();
        out.printLine( 1+" "+(size+3)+" "+(size+4)+" "+(size+5) ) ;out.flush();
        int c = in.readInt();
        out.printLine( 1+" "+(size+1)+" "+(size+4)+" "+(size+5) ) ;out.flush();
        int d = in.readInt();
        out.printLine( 1+" "+(size+1)+" "+(size+2)+" "+(size+5) ) ;out.flush();
        int e = in.readInt();

        long ar[]= new long[8];

        ar[4] = (a^e^c);
        ar[2] = (c^d^a);
        ar[3] = (ar[4]^ar[2]^b);
        ar[1] = (a^ar[3]^ar[2]);
        ar[5] = (ar[3]^ar[4]^c);

        for(int i=1;i<=5;i++){
            res.add(ar[i]);
        }
    }

    private void four(List<Long> res,  OutputWriter out) {

        long size = res.size();

        out.printLine(1 + " " + (size+1)+ " " + (size+2)+ " " + (size+3)) ; out.flush();
        long a = in.readInt();
        out.printLine(1 + " " + (size+1)+ " " + (size+2)+ " " + (size+4)) ; out.flush();
        long b = in.readInt();
        out.printLine(1 + " " + (size+2)+ " " + (size+3)+ " " + (size+4)) ; out.flush();
        long c = in.readInt();
        out.printLine(1 + " " + (size+1)+ " " + (size+3)+ " " + (size+4)) ; out.flush();
        long d = in.readInt();

        long ar[]= new long[8];

        ar[2] = (a^b^c);
        ar[1] = (a^b^d);
        ar[3] = (ar[1]^ar[2]^a);
        ar[4] = (ar[1]^ar[2]^b);

        for(int i=1;i<=4;i++){
            res.add(ar[i]);
        }
    }

    public static void main(String[] args) {
        INTXOR solver = new INTXOR();
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
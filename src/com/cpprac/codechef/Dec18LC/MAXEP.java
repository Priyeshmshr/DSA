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

public class MAXEP {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt(), c = in.readInt();
        out.printLine(3+ " " + optimizedBinarySearch(n,out,in));
        out.flush();
    }

    public int optimizedBinarySearch(int n,
                                     OutputWriter out,
                                     InputReader in ){

        int x = (int)Math.ceil((-1.00 - Math.sqrt(1+4*n))/2.0);
        int x1 = (int)Math.ceil(Math.abs(-1.00 - Math.sqrt(1+4*n))/2.0);
        System.out.println(x1);
        x= Math.max(Math.abs(x),Math.abs(x1));
        System.out.println(Math.sqrt(1+4*n));
        System.out.println((-1.00 - Math.sqrt(1+4*n))/2.0);
        x = Math.min(x,n);
        System.out.println(x);
        out.printLine(1+" "+ x);
        out.flush();
        int res = in.readInt() , addX = x-1, prevX = 1;
        while(res!=1){
            if(res==0){
                prevX = x;
                x = x + addX;
                if(addX>0)
                    addX--;

                if(x>=n){
                    x = n;
                    break;
                }
                out.printLine(1+" "+ x);
                out.flush();
                res = in.readInt();
            }
            else{
                out.printLine(3 + " " + x);
                out.flush();
                System.exit(0);
            }
        }
        if(res==1) {
            out.printLine(2);
            out.flush();
        }
        for(int i=prevX; i<x;i++){
            out.printLine(1+" "+ i);
            out.flush();
            res = in.readInt();
            if(res==1){
                out.printLine(2);
                out.flush();
                return i;
            }
        }
        return x;
    }

    public int binarySearch(int low,
                            int high,
                            OutputWriter out,
                            InputReader in ){
        int mid = (low+high)/2;
        while(Math.abs(low-high)>0){
            out.printLine(1+" "+ mid);
            out.flush();
            int res = in.readInt();
            if(res==0){
                low = mid+1;
            }else if(res==1){
                high = mid;
                out.printLine(2);
                out.flush();
            }else{
                out.printLine(3 +" " + mid);
                out.flush();
            }
            mid = (low+high)/2;
        }
        return low;
    }

    public int binarySearch(int n,
                            OutputWriter out,
                            InputReader in ){

        int low = 1, high = n;
        int mid = (low+high)/2;
        while(Math.abs(low-high)>0){
            out.printLine(1+" "+ mid);
            out.flush();
            int res = in.readInt();
            if(res==0){
                low = mid+1;
            }else if(res==1){
                high = mid;
                out.printLine(2);
                out.flush();
            }else{
                out.printLine(3 +" " + mid);
                out.flush();
            }
            mid = (low+high)/2;
        }
        return low;
    }

    public static void main(String[] args) {
        MAXEP solver = new MAXEP();
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
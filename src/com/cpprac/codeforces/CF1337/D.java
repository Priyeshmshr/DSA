package com.cpprac.codeforces.CF1337;

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

public class D {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();


        while(t-->0){
            int nr= in.readInt(),ng = in.readInt(), nb = in.readInt();

            long []nrr = IOUtils.readLongArray(in,nr);
            long []ngg = IOUtils.readLongArray(in,ng);
            long []nbb = IOUtils.readLongArray(in,nb);

            Arrays.sort(nrr);
            Arrays.sort(ngg);
            Arrays.sort(nbb);

            long res = Long.MAX_VALUE;
            for(int i=0;i<nr;i++){
                int l1 ,l2,u1,u2;
                l1 = lowerBound(ngg, nrr[i]);
                l2 = lowerBound(nbb, nrr[i]);
                u1 = upperBound(ngg, nrr[i]);
                u2 = upperBound(nbb, nrr[i]);

                res = Math.min(res,Math.min(equation(ngg[l1],nrr[i],nbb[u2]),equation(ngg[u1],nrr[i],nbb[l2])));
            }
            for(int i=0;i<nb;i++){
                int l1 ,l2,u1,u2;
                l1 = lowerBound(ngg, nbb[i]);
                l2 = lowerBound(nrr, nbb[i]);
                u1 = upperBound(ngg, nbb[i]);
                u2 = upperBound(nrr, nbb[i]);

                res = Math.min(res,Math.min(equation(ngg[l1],nbb[i],nrr[u2]),equation(ngg[u1],nbb[i],nrr[l2])));
            }
            for(int i=0;i<ng;i++){
                int lr ,lb,ur,ub;
                lr = lowerBound(nrr, ngg[i]);
                lb = lowerBound(nbb, ngg[i]);
                ur = upperBound(nrr, ngg[i]);
                ub = upperBound(nbb, ngg[i]);

                res = Math.min(res,Math.min(equation(nrr[lr],ngg[i],nbb[ub]),equation(nbb[lb],ngg[i],nrr[ur])));
            }
            out.printLine(res);
            out.flush();
        }
    }

    private long equation(long x, long y, long z) {
        return (x-y)*(x-y) + (y-z)*(y-z) + (z-x)*(z-x);
    }


    private int lowerBound(long arr[], long x){
        int st = 0, end = arr.length-1;
        int res = 0;
        while(st<=end){
            int mid = (st+end)/2;
            if(arr[mid]==x) {
                res = mid;
                break;
            }
            else if(arr[mid]>x)
                end = mid-1;
            else if(arr[mid]<x) {
                st = mid+1;
                res = mid;
            }
        }
        return res;
    }

    private int upperBound(long arr[], long x){
        int st = 0, end = arr.length-1;
        int res = end;
        while(st<=end){
            int mid = (st + end) / 2;
            if(arr[mid]==x) {
                res = mid;
                break;
            }
            else if(arr[mid]>x) {
                end = mid-1;
                res = mid;
            }
            else if(arr[mid]<x) {
                st = mid+1;
            }
        }
        return res;
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
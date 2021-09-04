package com.cpprac.HackerEarth;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AugCircuit20 {
    Long a[], b[], t;
    int n;
    ArrayList<Long> list;
    LinkedList<Long> temp;
    ArrayList<Long> ans;

    private void solve6() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        n = in.readInt();
        t = in.readLong();
        a = IOUtils.readLongArray(in, n);
        b = IOUtils.readLongArray(in, n);
        Arrays.sort(a, Comparator.reverseOrder());
        long ind = (long) Math.ceil(t / (n * 1.0));
        list = new ArrayList<>(Arrays.asList(b).subList(0, n));
        list.sort(Comparator.naturalOrder());
        long c[] = new long[n];
        //Try 10! for n=10.
        if ( n <= 10 ) {
            diffSoFar = t;
            temp = new LinkedList<>();
            ans = new ArrayList<>();
            recurse(0, 0);
            for (int i = 0; i < n; i++) {
                c[i] = ans.get(i);
            }
        } else if ( n == 1000 ) {
            for (int i = 0; i < n; i++) {
                int cur = upperBound(list, (long) Math.ceil(ind / (1.0 * a[i])));
                c[i] = list.get(cur);
                list.remove(list.get(cur));
                t = t - cur;
                ind = (long) Math.ceil(t / ((n - i + 1) * 1.0));
            }
        } else {
            int k = 0;
            for (int i = 0, j = n - 1; i <= j; i++, j--) {
                if ( i == j )
                    c[k++] = list.get(i);
                else {
                    c[k++] = list.get(i);
                    c[k++] = list.get(j);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(a[i] + " ");
        }
        out.printLine();
        for (int i = 0; i < n; i++) {
            out.print(c[i] + " ");
        }
        out.flush();
    }

    long diffSoFar;


    HashSet<Integer> set = new HashSet<>();
    int recurse = 0;

    private void recurse(int aPos, long sum) {
        if ( recurse == 80 ) return;
        if ( aPos == n ) {
            if ( diffSoFar > (Math.abs(sum - t)) ) {
                ans = new ArrayList<>(temp);
                diffSoFar = (Math.abs(sum - t));
                recurse++;
            }
            return;
        }
        for (Integer j = 0; j < n; j++) {
            if ( !set.contains(j) ) {
                Long cur = list.get((int) j);
                set.add(j);
                temp.addLast(cur);
                recurse(aPos + 1, sum + (cur * a[aPos]));
                temp.removeLast();
                set.remove(j);
            }
        }
    }


    private int upperBound(ArrayList<Long> list, long x) {
        int st = 0, end = list.size() - 1;
        int res = 0;
        while (st <= end) {
            int mid = (st + end) / 2;
            if ( list.get(mid) == x ) {
                res = mid;
                break;
            } else if ( list.get(mid) > x ) {
                end = mid - 1;
            } else if ( list.get(mid) < x ) {
                st = mid + 1;
                res = mid;
            }
        }
        return res;
    }

    private int lowerBound(ArrayList<Long> list, long x) {
        int st = 0, end = list.size() - 1;
        int res = 0;
        while (st <= end) {
            int mid = (st + end) / 2;
            if ( list.get(mid) == x ) {
                res = mid;
                break;
            } else if ( list.get(mid) > x )
                end = mid - 1;
            else if ( list.get(mid) < x ) {
                st = mid + 1;
                res = mid;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        AugCircuit20 solver = new AugCircuit20();
        solver.solve6();
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

        public static Long[] readLongArray(InputReader in, int size) {
            Long[] array = new Long[size];
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
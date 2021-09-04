package com.cpprac.leetcode;

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

public class LC315 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
//        List<Integer> res = countSmaller(new int[]{51,32,51,9,21,36,41});
//        List<Integer> res = countSmaller(new int[]{2,1,2,1});
//        System.out.println(res);
        double a = 0.02;
        double b = 0.03;
        Double c = b-a;
        System.out.println(Math.round(c));

    }

    public static void main(String[] args) {
        LC315 solver = new LC315();
        solver.solve();
    }

    class Pair {
        int val, index;

        public Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    Integer[] ans;

    public List<Integer> countSmaller(int[] nums) {

        Pair[] pairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new Pair(nums[i], i);
        }
        ans = new Integer[nums.length];
        Arrays.fill(ans,0);
        mergeSort(pairs, 0, pairs.length - 1);
        return Arrays.asList(ans);
    }

    private Pair[] mergeSort(Pair[] nums, int st, int end) {

        int len = end-st+1;

        Pair res[] = new Pair[end-st+1];
        if(len==0)return res;
        if(len==1){
            res[0]= nums[st];
            return res;
        }

        int mid = (st + end) / 2;
        Pair l[] = mergeSort(nums, st, mid);
        Pair r[] = mergeSort(nums, mid + 1, end);

        for(int i=0,j=0;i<l.length || j<r.length;){

            if(j==r.length || (i<l.length && l[i].val<=r[j].val)){
                res[i+j] = l[i];
                ans[l[i].index] += j;
                i++;
            }else{
                res[i+j] = r[j];
                j++;
            }
        }
        return res;
    }

    private void merge(Pair[] nums, int st, int mid, int end) {
        Pair l[] = new Pair[end - st + 1];

        int i = st, j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if ( nums[i].val < nums[j].val ) {
                ans[nums[i].index] += j - (mid + 1);
                l[k++] = nums[i++];
            } else if ( nums[i].val > nums[j].val ) {
                l[k++] = nums[j++];
            } else {
                ans[nums[i].index] += j - (mid + 1);
                l[k++] = nums[i++];
                l[k++] = nums[j++];
            }
        }
        while (i <= mid) {
            ans[nums[i].index] += j - (mid + 1);
            l[k++] = nums[i++];
        }
        while (j <= end) {
            l[k++] = nums[j++];
        }

        k = 0;
        for (int m = st; m <= end; m++) {
            nums[m] = l[k++];
        }
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
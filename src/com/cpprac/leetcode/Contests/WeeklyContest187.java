package com.cpprac.leetcode.Contests;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class WeeklyContest187 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
//        System.out.println(longestSubarray(new int[]{5,10,5,7,2,2,1,4,4,4,4,4,1}, 5));
//        System.out.println(longestSubArray1(new int[]{5,10,5,7,2,2,1,4,4,4,4,4,1}, 5));
        createTestCase();
    }

    private void createTestCase() {
        int i=1;
        for(;i<100000;i++){
            System.out.print(7+",");
        }
        System.out.println(1);
        System.out.println(i);

    }

    public static void main(String[] args) {
        WeeklyContest187 solver = new WeeklyContest187();
        solver.solve();
    }

    public String destCity(List<List<String>> paths) {
        HashSet<String> set = new HashSet<String>();
        for (List<String> path : paths) {
            set.add(path.get(1));
        }
        for (List<String> path : paths) {
            set.remove(path.get(0));
        }
        return (String) set.toArray()[0];
    }

    public int longestSubArray1(int nums[], int limit){
        Deque<Integer> max = new LinkedList<Integer>();
        Deque<Integer> min = new LinkedList<Integer>();
        int i = 0;
        int j = 0;
        while(j < nums.length) {
            while(max.size() > 0 && nums[j] > max.peekLast()) {
                max.removeLast();
            }

            while(min.size() > 0 && nums[j] < min.peekLast()) {
                min.removeLast();
            }
            max.add(nums[j]);
            min.add(nums[j]);

            if(Math.abs(max.peekFirst() - min.peekFirst()) > limit) {
                if(max.peekFirst() == nums[i]) {
                    max.removeFirst();
                }
                if(min.peekFirst() == nums[i]) {
                    min.removeFirst();
                }
                i += 1;
            }

            j += 1;
        }
        return j - i;
    }

    public int longestSubArray(int[] nums, int limit){
        int mi=nums[0],ma=nums[0],l=0,ans=1,prev=1;
        int i=0;
        while(i<nums.length){
            if(Math.abs(nums[i]-mi)<=limit && Math.abs(nums[i]-ma)<=limit){
                l++;
                ans=Math.max(l,ans);
                mi=Math.min(nums[i],mi);
                ma=Math.max(nums[i],ma);
                i++;
            }
            else{
                l=0;
                i=prev;
                mi=nums[prev];
                ma=nums[prev];
                prev++;
                if(prev>=nums.length) return ans;
            }
        }

        return ans;
    }

    public int longestSubarray(int[] nums, int limit) {

        int st = 0, end = 0, res = 1;

        PriorityQueue<Integer> minPr = new PriorityQueue<>();
        minPr.add(nums[0]);
        PriorityQueue<Integer> maxPr = new PriorityQueue<>(Comparator.reverseOrder());
        maxPr.add(nums[0]);
        while (end < nums.length - 1) {
            end++;
            minPr.add(nums[end]);
            maxPr.add(nums[end]);
            while (maxPr.peek() - minPr.peek() > limit) {
                minPr.remove(nums[st]);
                maxPr.remove(nums[st]);
                st++;
            }
            res = Math.max(res, end - st + 1);
        }
        return res;
    }

    public boolean kLengthApart(int[] nums, int k) {

        int count = 0, i = 0;

        for (; i < nums.length; i++) {
            if ( i != 1 ) {
                count++;
            } else
                break;
        }
        for (; i < nums.length; i++) {
            if ( nums[i] == 1 ) {
                if ( count < k ) {
                    return false;
                }
                count = 0;
            } else
                count++;
        }
        return true;

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
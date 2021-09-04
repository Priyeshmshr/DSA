package com.cpprac.leetcode.Contests;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Virt_BiWeeklyContest32 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
//        System.out.println(canConvertString("z", "b", 3));
//        System.out.println(minInsertions("(()))")); // ())
        System.out.println(longestAwesome("12345678"));
    }

    public boolean canConvertString(String s, String t, int k) {
        if ( s.length() != t.length() )
            return false;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if ( s.charAt(i) < t.charAt(i) ) {
                int diff = t.charAt(i) - s.charAt(i);
                map.put(diff, map.getOrDefault(diff, 0) + 1);
            } else if ( s.charAt(i) > t.charAt(i) ) {
                int z = 'z' - 'a' + 1;
                int diff = (z - s.charAt(i)) + t.charAt(i);
                map.put(diff, map.getOrDefault(diff, 0) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            if ( (entry.getKey() + (val - 1) * 26) > k ) return false;
        }
        return true;
    }


    public int minInsertions(String s) {
        Stack<Character> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if ( s.charAt(i) == '(' ) {
                stack.push(s.charAt(i));
            } else {
                if ( stack.size()>0 ) {
                    if ( i < s.length() - 1 && s.charAt(i + 1) == ')' ) {
                        stack.pop();i++;
                    }else{
                        stack.pop();res++;
                    }
                }else {
                    if ( i < s.length() - 1 && s.charAt(i + 1) == ')' ) {
                        res++;
                        i++;
                    } else {
                        res += 2;
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            stack.pop();res+=2;
        }
        return res;
    }

    public int longestAwesome(String s) {
        if(s.length()==1)return 1;
        int xor=0;
        Map<Integer,Integer> xorPos = new HashMap<>();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            xor = xor ^(s.charAt(i)-'0');
            if(!xorPos.containsKey(xor)){
                xorPos.put(xor, i);
            }else{
                res = Math.max(i - xorPos.get(xor) + 2, res);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Virt_BiWeeklyContest32 solver = new Virt_BiWeeklyContest32();
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
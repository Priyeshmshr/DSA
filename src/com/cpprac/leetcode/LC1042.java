package com.cpprac.leetcode;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class LC1042 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
    }

//    public static void main(String[] args) {
//        LC1042 solver = new LC1042();
//        solver.solve5th();
//    }

    static class Solution {

        int vis[];
        int answer[];
        Map<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();

        public int[] gardenNoAdj(int N, int[][] paths) {
            vis = new int[N + 1];
            answer = new int[N];

            for (int i = 0; i < paths.length; i++) {
                ArrayList<Integer> neighbours = graph.getOrDefault(paths[i][0], new ArrayList<Integer>());
                neighbours.add(paths[i][1]);
                graph.put(paths[i][0], neighbours);
                neighbours = graph.getOrDefault(paths[i][1], new ArrayList<>());
                neighbours.add(paths[i][0]);
                graph.put(paths[i][1], neighbours);
            }

            for (int i = 1; i <= N; i++) {

                Set<Integer> nums = new HashSet<>(Arrays.asList(1,2,3,4));

                for (Integer iter : graph.getOrDefault(i, new ArrayList<>())) {
                    if ( answer[iter - 1] > 0 )
                        nums.remove(answer[iter - 1]);
                }

                answer[i - 1] = nums.iterator().next();

//                if ( vis[i] == 0 ) {
//                    dfs(i);
//                }
            }
            return answer;
        }

        private void dfs(int currentNode) {

            if ( vis[currentNode] == 1 )
                return;

            vis[currentNode] = 1;
            TreeSet<Integer> nums = new TreeSet<>(Arrays.asList(1,2,3,4));

            for (Integer iter : graph.getOrDefault(currentNode, new ArrayList<>())) {
                if ( answer[iter - 1] > 0 )
                    nums.remove(answer[iter - 1]);
            }

            answer[currentNode - 1] = nums.first() ;

            for (Integer iter : graph.getOrDefault(currentNode, new ArrayList<>())) {
                if ( vis[iter] != 1 )
                    dfs(iter);
            }
        }
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if ( input.length() == 0 ) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static int[][] stringToInt2dArray(String input) throws JSONException {
        JSONArray jsonArray = new JSONArray(input);
        if (jsonArray.length() == 0) {
            return new int[0][0];
        }

        int[][] arr = new int[jsonArray.length()][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = stringToIntegerArray(jsonArray.get(i).toString());
        }
        return arr;
    }

    public static String integerArrayToString(int[] nums, int length) {
        if ( length == 0 ) {
            return "[]";
        }

        String result = "";
        for (int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException, JSONException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int N = Integer.parseInt(line);
            line = in.readLine();
            int[][] paths = stringToInt2dArray(line);

            int[] ret = new Solution().gardenNoAdj(N, paths);

            String out = integerArrayToString(ret);

            System.out.print(out);
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
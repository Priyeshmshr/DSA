package com.cpprac.codeforces.CF1099;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class D {

    int n;
    long sum[];
    Map<Integer,ArrayList<Integer>> graph = new HashMap<>();
    Map<Integer,Integer> minValueInChild = new HashMap<>();

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        n= in.readInt() ;
        int parent[] = new int[n+1];
        sum = new long[n+1];
        for(int i=2;i<=n;i++) {
            parent[i] = in.readInt();
            ArrayList<Integer> list = graph.getOrDefault(parent[i],new ArrayList<>());
            list.add(i);
            graph.put(parent[i],list);
        }

        for(int i=1;i<=n;i++){
            sum[i]=in.readInt();
        }
        bfs();

        long av[] = new long[n+1];
        Arrays.fill(av,-1L);
        av[1]=sum[1];

        boolean possible = true;
        for(int i=1;i<=n;i++){
            if(sum[i]==-1)
                av[i] = 0;
            else {
                long diff = sum[i] - sum[parent[i]];
                if(diff<0){
                    possible=false;
                    break;
                }
                av[i] = diff;
            }
        }
        if(possible) {
            long res = 0;
            for (int i = 1; i <= n; i++) {
                res += av[i];
            }
            out.printLine(res);
        }
        else
            out.printLine(-1);

        out.close();
    }

    private void bfs() {

        Queue<Integer> queue = new LinkedList<>();
        int node = 1;
        queue.add(node);

        while(!queue.isEmpty()){
            node = queue.remove();
            ArrayList<Integer> children = graph.getOrDefault(node,new ArrayList<>());
            if(children.size()!=0){
                long minValue = Long.MAX_VALUE;
                for (int i : children) {
                    minValue = Math.min(minValue, sum[i]);
                    queue.add(i);
                }
                if (sum[node] == -1) {
                    sum[node] = minValue;
                    /*for (int i : children) {
                        sum[i] = sum[i] - minValue;
                    }*/
                }
            }
        }
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
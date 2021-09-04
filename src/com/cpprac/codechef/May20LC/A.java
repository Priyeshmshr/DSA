package com.cpprac.codechef.May20LC;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class A {

    class Triple {
        int i1, i2, i3;

        public Triple(int i1, int i2, int i3) {
            this.i1 = i1;
            this.i2 = i2;
            this.i3 = i3;
        }
    }

    int vis[];
    Map<Integer, Integer> indexOf;
    PriorityQueue<Queue<Integer>> cycles;
    ArrayDeque<Integer> cycle;

    public void solveDfs() {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt(), k = in.readInt();
            int arr[] = IOUtils.readIntegerArray(in, n);
            indexOf = new HashMap<>();
            cycles = new PriorityQueue<>((c1, c2) -> Integer.compare(c2.size(), c1.size()));
            for (int i = 1; i <= n; i++) {
                indexOf.put(arr[i], i);
            }
            vis = new int[n+1];

            Queue<ArrayDeque<Integer>> evenSwaps = new LinkedList<>();
            Queue<ArrayDeque<Integer>> oddSwaps = new LinkedList<>();

            for (int i = 1; i <= n; i++) {
                if ( arr[i] != i && vis[i]==0) {
                    cycle = new ArrayDeque<>();
                    dfs(arr, i);
                    if(cycle.size()%2!=0){
                        evenSwaps.add(cycle);
                    }else
                        oddSwaps.add(cycle);
                }
            }
            ArrayList<Triple> res = new ArrayList<>();
            for (int i = 0; i < evenSwaps.size(); i++) {
                ArrayDeque<Integer> cycle = evenSwaps.poll();
                int i1 = cycle.pollFirst();
                while(!cycle.isEmpty()){
                    int i3 = cycle.pollLast();
                    int i2 = cycle.pollLast();
                    res.add(new Triple(i1,i2,i3));
                }
            }

            int previ, prevj;
            //TODO implement below code:
            for (int i = 0; i < oddSwaps.size(); i++) {
                ArrayDeque<Integer> cycle = oddSwaps.poll();
                if(i!=0){

                }
                cycle = evenSwaps.poll();
                int i1 = cycle.pollFirst();
                while(cycle.size()>2){
                    int i3 = cycle.pollLast();
                    int i2 = cycle.pollLast();
                    res.add(new Triple(i1,i2,i3));
                }
                previ= cycle.pollLast();
                prevj= cycle.pollLast();
            }
            for (int i = 0; i < cycles.size(); i++) {
                Queue<Integer> cycle = cycles.poll();
                if(cycle.size()<3){
                    cycles.add(cycle);
                    break;
                }
                while(cycle.size()>=3){
                    int i1 = cycle.poll();
                    int i2 = cycle.poll();
                    int i3 = cycle.size()==1?cycle.poll(): cycle.peek();
                    res.add(new Triple(i1,i2,i3));
                }
                if(!cycle.isEmpty()){
                    cycles.add(cycle);
                }
            }
            if(cycles.size()%2!=0 || res.size()+cycles.size() > k ){
                out.println(-1);
            }else{
                while(!cycles.isEmpty()){
                    Queue<Integer> cycle1 = cycles.poll();
                    Queue<Integer> cycle2 = cycles.poll();
                    int i1 = cycle1.poll();
                    int i2 = cycle2.poll();
                    int i3 = cycle1.poll();
                    res.add(new Triple(i1,i2,i3));
                    res.add(new Triple(i2,cycle2.poll(),i1));
                }
                out.println(res.size());
                for (Triple triple : res) {
                    out.println(triple.i1 + " " + triple.i2 + " " + triple.i3 + " ");
                }
            }
        }
        out.close();
    }

    private void dfs(int[] arr, int i) {
        vis[i] = 1;
        cycle.addLast(i);
        if ( vis[arr[i]] == 0 )
            dfs(arr, arr[i]);
    }

/*
    public void solve5th() {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt(), k = in.readInt();
            int arr[] = IOUtils.readIntegerArray(in, n);
            Map<Integer, Integer> indexOf = new HashMap<>();
            PriorityQueue<Integer> outOrderIndexes = new PriorityQueue<>();
            for (int i = 1; i <= n; i++) {
                indexOf.put(arr[i], i);
                if ( i != arr[i] ) {
                    outOrderIndexes.add(i);
                }
            }
            if ( outOrderIndexes.size() > k * 3 ) {
                out.println(-1);
            } else {
                ArrayList<Triple> res = new ArrayList<>();
                while (res.size() < k && outOrderIndexes.size() >= 3) {
                    int i1 = outOrderIndexes.poll();
                    int i3 = indexOf.get(i1);
                    outOrderIndexes.remove(i3);
                    int i2;
                    if ( i3 == arr[i1] ) {
                        i2 = outOrderIndexes.poll();
                    } else {
                        i2 = arr[i1];
                        outOrderIndexes.remove(i2);
                    }
                    res.add(new Triple(i1, i2, i3));
                    int temp = arr[i1];
                    arr[i1] = arr[i3];
                    arr[i3] = arr[i2];
                    arr[i2] = temp;
                    indexOf.put(arr[i1], i1);
                    indexOf.put(arr[i3], i3);
                    indexOf.put(arr[i2], i2);
                    if ( arr[i2] != i2 ) {
                        outOrderIndexes.add(i2);
                    }
                    if ( arr[i3] != i3 )
                        outOrderIndexes.add(i3);
                }
                if ( !outOrderIndexes.isEmpty() ) {
                    out.println(-1);
                } else {
                    out.println(res.size());
                    for (Triple triple : res) {
                        out.println(triple.i1 + " " + triple.i2 + " " + triple.i3 + " ");
                    }
                }
            }
        }
        out.close();
    }
*/

    public static void main(String[] args) {
        A solver = new A();
//        solver.solve5th();
        solver.solveDfs();
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
            int[] array = new int[size + 1];
            for (int i = 1; i <= size; i++) {
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
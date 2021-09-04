package com.cpprac.codeforces.CF1341;

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class C {
    /*public void solve5th() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt();
            int arr[] = IOUtils.readIntegerArray(in, n);
            DSU dsu = new DSU(n+1);
            Map<Integer, Integer> count = new HashMap<>();
            Map<Integer, Integer> pmap = new HashMap<>();
            PriorityQueue<Integer> allCounts = new PriorityQueue<>(Comparator.reverseOrder());
            for(int i=0;i<n;i++) {
                allCounts.add(1);
                count.put(i, 1);
                pmap.put(arr[i],i);
            }
            boolean possible = true;
            for(int i=0;i<n;i++){
                int maxCount = !allCounts.isEmpty()?allCounts.poll():0;
                int dest = pmap.get(i+1);
                if(count.get(dest)==maxCount){
                    int nextEmpty = findNextEmpty(arr,dest,dsu);
                    dsu.union(dest,nextEmpty);
                    count.put(dest,0);
                    if(nextEmpty!=n) {
                        int newSize = dsu.size(nextEmpty);
                        count.put(nextEmpty, newSize);
                        allCounts.add(newSize);
                    }
                }else{
                    possible=false;
                }
            }
            out.printLine(possible? "Yes":"No");
            out.flush();
        }
    }*/

    public void solve1() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt();
            int arr[] = IOUtils.readIntegerArray(in, n);
            Map<Integer, Integer> pmap = new HashMap<>();
            int countArr[] = new int[n+1];
            Arrays.fill(countArr,1);
            PriorityQueue<Integer> allCounts = new PriorityQueue<>(Comparator.reverseOrder());
            for(int i=0;i<n;i++) {
                allCounts.add(1);
                pmap.put(arr[i],i);
            }
            boolean possible = true;

            for(int i=0;i<n;i++){
                int maxCount = !allCounts.isEmpty()?allCounts.poll():0;
                int dest = pmap.get(i+1);
                if(countArr[dest]==maxCount){
                    int nextEmpty = findNextEmpty(dest,countArr);
                    countArr[nextEmpty] += countArr[dest];
                    countArr[dest]=0;
                    if(nextEmpty!=n) {
                        allCounts.add(countArr[nextEmpty]);
                    }
                }else{
                    possible=false;
                }
            }
            out.printLine(possible? "Yes":"No");
            out.flush();
        }
    }

    private int findNextEmpty(int dest, int[] countArr) {
        for (int i = dest + 1; i < countArr.length; i++) {
            if ( countArr[i] == 1 )
                return i;
        }
        return countArr.length-1;
    }


    public static void main(String[] args) {
        C solver = new C();
        solver.solve1();
    }


    class DSU {

        public int[] parent;
        int rank[];

        public DSU(int size){
            parent = new int[size];
            rank = new int[size];
            for(int i=0;i<size;i++) {
                parent[i] = i;
                rank[i]=1;
            }
        }

        public int find(int x){

            if(parent[x]!=x)
                parent[x]=find(parent[x]);

            return parent[x];
        }

        public int size(int x){
            int parent = find(x);
            return rank[parent];
        }

        public boolean union(int child, int par) {
            int parentOfX = find(child), parentOfY= find(par);
            parent[parentOfX] = parentOfY;
            rank[parentOfY]+=rank[parentOfX];
            /*if(parentOfX==parentOfY)
                return false;
            else if(rank[parentOfX]<rank[parentOfY]){
                parent[parentOfX] = parentOfY;
            }
            else if(rank[parentOfX]>rank[parentOfY]){
                parent[parentOfY] = parentOfX;
            }else {
                parent[parentOfY] = parentOfX;
                rank[parentOfX]++;
            }*/

            return true;
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
package com.cpprac.codechef.Nov18LC;

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
import java.util.Map;
import java.util.Set;

public class PRITREE {

    boolean primes[] = new boolean[1000005]; 
    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt();
        sieve();
        int a[] = new int[n+1];

        ArrayList<Integer> singlePrimeIndexes= new ArrayList<>();
        ArrayList<Integer> compositeIndexes = new ArrayList<>();
        int vis[] = new int[n+1];
        Map<Integer,ArrayList<Integer>> tree = new HashMap<>();
        for(int i=1;i<=n;i++){
            tree.put(i, new ArrayList<>());
        }

        for(int i=1;i<=n;i++){
            a[i]=in.readInt();
            if(primes[a[i]]) {
                singlePrimeIndexes.add(i);
                vis[i]=1;
            }
            else {
                compositeIndexes.add(i);
            }
        }
        ArrayList<Integer> temp = new ArrayList<>(singlePrimeIndexes);
        int root=-1;
        if(compositeIndexes.size()>0) {
            for(int j=0;j<compositeIndexes.size();j++){
                if(vis[compositeIndexes.get(j)]==0) {
                    for (int k = j + 1; k < compositeIndexes.size(); k++) {
                        int edgeSize = tree.get(compositeIndexes.get(k)).size();
                        if (vis[compositeIndexes.get(k)] == 0) {
                            if (primes[a[compositeIndexes.get(j)] + a[compositeIndexes.get(k)]]) {
                                int tempRoot = addEdge(tree, compositeIndexes.get(j), compositeIndexes.get(k));
                                if (vis[tempRoot] == 0)
                                    temp.add(tempRoot);
                                vis[compositeIndexes.get(k)]++;
                                break;
                            }
                        } else if (edgeSize == 0) {
                            if (primes[a[compositeIndexes.get(j)] + a[compositeIndexes.get(k)]]) {
                                int tempRoot = addEdge(tree, compositeIndexes.get(k), compositeIndexes.get(j));
                                if (vis[tempRoot] == 0)
                                    temp.add(tempRoot);
                                vis[compositeIndexes.get(j)]++;
                                break;
                            }
                        }
                    }
                }
            }
            int tempRoot=-1,indexToRemove=-1;
            for(int j=0;j<compositeIndexes.size();j++){
                if(vis[compositeIndexes.get(j)]==0) {
                    if (tempRoot == -1) {
                        tempRoot = compositeIndexes.get(j);
                        indexToRemove = j;
                    }
                    if(tree.get(compositeIndexes.get(j)).size()==0) {
                        tempRoot = compositeIndexes.get(j);
                        indexToRemove = j;
                    }
                    if(!temp.contains(compositeIndexes.get(j)))
                        temp.add(compositeIndexes.get(j));
                }
            }
            root = tempRoot;
            if(temp.contains(compositeIndexes.get(indexToRemove)))
                temp.remove(compositeIndexes.get(indexToRemove));
        }
        else{
            root = singlePrimeIndexes.get(0);
            temp.remove(0);
        }

        if(tree.get(root).size()>0){
            ArrayList<Integer> edges = tree.get(root);
            edges.addAll(temp);
            tree.put(root,edges);
        }else {
            tree.put(root, temp);
        }
        for(Map.Entry<Integer, ArrayList<Integer>> iter: tree.entrySet()){
            ArrayList<Integer> edges = iter.getValue();
            if(edges.size()>0) {
                for(int value : edges)
                out.printLine(iter.getKey()+ " " + value);
            }
        }
        out.close();
    }

    private int addEdge(Map<Integer, ArrayList<Integer>> tree, int j, int k) {
        ArrayList<Integer> edges = tree.get(j);
        edges.add(k);
        tree.put(j,edges);
        return j;
    }

    private void sieve() {
        Arrays.fill(primes,true);
        for(int i=2;i*i<=1000000;i++){
            if(primes[i]){
                for(int j=i+i;j<=1000000;j=j+i){
                    primes[j]=false;
                }
            }
        }
    }

    public static void main(String[] args) {
        PRITREE solver = new PRITREE();
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

        public static Set<Integer> readIntegerSet(InputReader in, int size) {
            Set<Integer> set = new HashSet<Integer>();
            for (int i = 0; i < size; i++) {
                set.add(in.readInt());
            }
            return set;
        }
    }
}
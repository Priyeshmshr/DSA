package com.cpprac.codechef.Dec18LC;

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

public class DLDAG {

    int vis[] = new int[1000005];
    HashMap< Integer, ArrayList<Integer>> graph = new HashMap<>();
    HashMap<Integer,ArrayList<Integer>> prevTopMap = new HashMap<>();

    List<String> ans= new ArrayList<>();

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n = in.readInt(), m = in.readInt();

        List<Integer> zeroInDegree = new ArrayList<>();
        int inDegree[] = new int[1000005];

        for(int i=0;i<m;i++){
            int u = in.readInt(),v = in.readInt();
            addInGraph(graph,u,v);
            inDegree[v]++;
        }

        for(int i=1;i<=n;i++){
            if(inDegree[i]==0){
                zeroInDegree.add(i);
            }
        }

        for(int i=0;i<zeroInDegree.size();i++) {
            int start = zeroInDegree.get(i);
            if(vis[start]==0) {
                dfs(start,-1,start,out);
                printTwos(out,prevTopMap,-1);
            }
        }
        for(Map.Entry<Integer,ArrayList<Integer>> entry : prevTopMap.entrySet()){
            StringBuilder sb = new StringBuilder();
            ans.add(sb.append(1).append(" ").append(entry.getValue().get(0)).toString());
        }

        out.printLine(ans.size());
        for(String s: ans){
            out.printLine(s);
        }
        out.printLine();
        out.flush();
    }
    private void dfs(int start, int prevTop, int top, OutputWriter out) {

        if (vis[start] == 1)
            return;

        ArrayList<Integer> neighbours = graph.containsKey(start) ? graph.get(start) : new ArrayList<>();
        
        for (int j = 0; j < neighbours.size(); j++) {
            dfs(neighbours.get(j),top,neighbours.get(j),out);
            addInGraph(prevTopMap,prevTop, start);
            printTwos(out,prevTopMap,prevTop);
            return ;
        }

        addInGraph(prevTopMap,prevTop, start);
        printTwos(out,prevTopMap,prevTop);
    }

    private void printTwos(OutputWriter out, HashMap<Integer, ArrayList<Integer>> prevTopMap, int prevTop) {
        if(prevTopMap.containsKey(prevTop) && prevTopMap.get(prevTop).size()==2){
            ArrayList<Integer> list = prevTopMap.get(prevTop);
            StringBuilder sb = new StringBuilder();
            ans.add(sb.append(2).append(" ").append(list.get(0)).append(" ").append(list.get(1)).toString());
            prevTopMap.remove(prevTop);
        }
    }

    private void addInGraph(HashMap<Integer, ArrayList<Integer>> graph, int u, int v) {
        ArrayList<Integer> list = new ArrayList<>();
        if(graph.containsKey(u)) {
            list = graph.get(u);
            list.add(v);
            graph.put(u,list);
        }else{
            list.add(v);
            graph.put(u,list);
        }
    }

    public static void main(String[] args) {
        DLDAG solver = new DLDAG();
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
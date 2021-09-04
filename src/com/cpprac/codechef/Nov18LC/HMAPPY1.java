package com.cpprac.codechef.Nov18LC;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Set;

class Pair{
    int count;
    int stPoint;
}
public class HMAPPY1 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int n= in.readInt(),q=in.readInt(),k=in.readInt();
        int a[] = new int[200005];
        for(int i=0;i<n;i++){
            a[i]=in.readInt();
        }
        for(int i=0;i<n;i++){
            a[n+i]=a[i];
        }
        int window[] = new int[200005];
        int currMax=0,currSt=0,tempMax=0,tempSt=0;

        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.count,o1.count));

        for(int i=0;i<n;i++){
            if(a[i]==1){
                if(tempMax==0)
                    tempSt=i;
                tempMax++;
                if(tempMax>=currMax) {
                    currMax = tempMax;
                    currSt = tempSt;
                }
            }
            if (a[i] == 0) {
                if(tempMax!=0) {
                    Pair p = new Pair();
                    p.count = tempMax;
                    p.stPoint = tempSt;
                    pq.add(p);
                }
                tempMax = 0;
                tempSt = i + 1;
            }
        }

        window[n-1]=currMax;

        int windowSt = 0;

        for(int i=n;i<2*n;i++){
            windowSt++;

            while(!pq.isEmpty() && pq.peek().stPoint<windowSt){
                pq.poll();
            }
            if(a[windowSt-1]==1 && currSt==windowSt-1){
                currSt++;
                currMax--;
                if(tempMax>=currMax) {
                    currMax = tempMax;
                    currSt = tempSt;
                }
            }
            if(a[i]==1){
                if(tempMax==0)
                    tempSt=i;
                tempMax++;
                if(tempMax>=currMax) {
                    currMax = tempMax;
                    currSt = tempSt;
                }
            }
            if(a[i]==0){
                if(tempMax!=0) {
                    Pair p = new Pair();
                    p.count = tempMax;
                    p.stPoint = tempSt;
                    pq.add(p);
                }
                tempMax=0;
                tempSt=i+1;
            }

            if(!pq.isEmpty()) {
                int midMax = pq.peek().count, midSt = pq.peek().stPoint;
                if (currMax <= midMax && midSt >= windowSt) {
                    currMax = midMax;
                    currSt = midSt;
                    pq.poll();
                }
            }
            window[i]=currMax;
        }

        String s = in.readString();
        int end=0;
        for(int i=0;i<s.length();i++){
            if(Character.valueOf(s.charAt(i)).equals('?')){
                int index=2*n-1-end;
                out.printLine(Math.min(window[index],k));
            }else{
                end++;
                end= end%n;
            }
        }
        out.close();
    }

    public static void main(String[] args) {
        HMAPPY1 solver = new HMAPPY1();
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
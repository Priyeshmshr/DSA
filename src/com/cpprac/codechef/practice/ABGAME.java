package com.cpprac.codechef.practice;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ABGAME {

    class Node{
        int left ;
        int right;
        int currIndex;
        int dist;
    }
    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t  = in.readInt();
        while(t-->0) {
            String s = in.readString();
            Map<Integer,Node> mpA = new HashMap<>();
            Map<Integer,Node> mpB = new HashMap<>();
            int k = 0;

            int path[] = new int[s.length()];
            int stA=-1,stB=-1;
            PriorityQueue<Integer> indexA = new PriorityQueue<>();
            PriorityQueue<Integer> indexB = new PriorityQueue<>();

            for(int i=0;i<s.length();i++){
                if(s.charAt(i)!='.')
                    k++;

                if(s.charAt(i)=='A')
                    indexA.add(i);
                if(s.charAt(i)=='B')
                    indexB.add(i);

                if(k%2!=0 && s.charAt(i)=='.'){
                    path[i]=1;
                }
            }

            char nextRight[] = new char[s.length()];
            char nextLeft[] = new char[s.length()];
            char prev = '.';
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)!='.') {
                    prev= s.charAt(i);
                }
                nextRight[i] = prev;
            }
            prev = '.';
            for(int i=s.length()-1;i>=0;i--){
                if(s.charAt(i)!='.') {
                    prev= s.charAt(i);
                }
                nextLeft[i] = prev;
            }

            char move = 'A';
            StringBuilder sb = new StringBuilder(s);
            while (true){
                if(move =='A'){
                    int replaced = 0;
                    while(replaced==0 && !indexA.isEmpty()) {
                        int index = indexA.poll();
                        int direction = 0;

                        if (index > 0 && path[index - 1] == 1) {
                            direction = -1;
                        } else
                            direction = 1;

                        if (direction == -1) {
                            if ((nextLeft[index - 1] == 'A' || nextLeft[index - 1] == '.') && sb.charAt(index - 1) != 'A') {
                                sb.replace(index - 1, index, "A");
                                sb.replace(index, index + 1, ".");
                                replaced = 1;
                                if (sb.charAt(index - 2) == '.')
                                    indexA.add(index - 1);
                            } else {
                                for (int i = index - 2; i >= 0; i--) {
                                    if (sb.charAt(i) == 'B') {
                                        sb.replace(i + 1, i + 2, "A");
                                        sb.replace(index, index + 1, ".");
                                        replaced = 1;
                                        break;
                                    }
                                }
                            }
                        } else {
                            if (index < s.length() - 1 && (nextRight[index + 1] == 'A' || nextLeft[index + 1] == '.') && sb.charAt(index + 1) != 'A') {
                                sb.replace(index + 1, index + 2, "A");
                                sb.replace(index, index + 1, ".");
                                replaced = 1;
                                if (sb.charAt(index + 2) == '.')
                                    indexA.add(index + 1);
                            } else {
                                for (int i = index + 2; i < sb.length(); i++) {
                                    if (sb.charAt(i) == 'B') {
                                        sb.replace(i - 1, i, "A");
                                        sb.replace(index, index + 1, ".");
                                        replaced = 1;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if(replaced==1){
                        move = 'B';
                    }else
                        break;
                }else {
                    int replaced = 0;
                    while(replaced==0 && !indexB.isEmpty()) {
                        int index = indexB.poll();
                        int direction = 0;

                        if (index > 0 && path[index - 1] == 1) {
                            direction = -1;
                        } else
                            direction = 1;


                        if (direction == -1) {
                            if ((nextLeft[index - 1] == 'B' || nextLeft[index - 1] == '.') && sb.charAt(index - 1) != 'B') {
                                sb.replace(index - 1, index, "B");
                                sb.replace(index, index + 1, ".");
                                replaced = 1;
                                if (sb.charAt(index - 2) == '.')
                                    indexB.add(index - 1);
                            } else {
                                for (int i = index - 2; i >= 0; i--) {
                                    if (sb.charAt(i) == 'A') {
                                        sb.replace(i + 1, i + 2, "B");
                                        sb.replace(index, index + 1, ".");
                                        replaced = 1;
                                        break;
                                    }
                                }
                            }
                        } else {
                            if (index < s.length() - 1 && (nextRight[index + 1] == 'B' || nextLeft[index + 1] == '.') && sb.charAt(index + 1) != 'B') {
                                sb.replace(index + 1, index + 2, "B");
                                sb.replace(index, index + 1, ".");
                                replaced = 1;
                                if (sb.charAt(index + 2) == '.')
                                    indexB.add(index + 1);
                            } else {
                                for (int i = index + 2; i <= sb.length(); i++) {
                                    if (sb.charAt(i) == 'A') {
                                        sb.replace(i - 1, i, "B");
                                        sb.replace(index, index + 1, ".");
                                        replaced = 1;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (replaced == 1) {
                        move = 'A';
                    } else
                        break;
                }

            }

            if(move == 'A')
                out.printLine("B");
            else
                out.printLine("A");
        }
        out.close();
    }

    public static void main(String[] args) {
        ABGAME solver = new ABGAME();
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
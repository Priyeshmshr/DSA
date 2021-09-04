package com.cpprac.codechef.Snackdown19;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class AVGMAT {

    int dx[] = {-1,0,1,0};
    int dy[] = {0 ,1,0,-1};

    int n,m;
    boolean vis[][] = new boolean[2005][2005];
    String a[];
    int distances[] = new int[605];

    void dfs(int sr, int sc, int i,int j){

        for(int k=0;k<4;k++){
            int tx = sr+dx[k];
            int ty = sc+dy[k];

            if(tx>=0 && ty>=0 && tx<n && ty<m && !vis[tx][ty] && Character.valueOf(a[tx].charAt(ty)).equals('1')){
                vis[tx][ty]=true;
                int dis = Math.abs((i-tx)+(j-ty));
                for(int l=1;l<=dis;l++) {
                    distances[l]++;
                }
                dfs(tx,ty,i,j);
            }
        }
    }

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t;
        t = in.readInt();
        int dx[] = {-1, -1, 1, 1};
        int dy[] = {-1, 1, -1, 1};

        while (t-- > 0) {

            n = in.readInt();
            m = in.readInt();
            a = new String[n];

            for (int i = 0; i < n; i++) {
                a[i] = in.readString();
            }

            distances = new int[605];
            vis = new boolean[2005][2005];
            for (int d = 1; d <= n + m - 2; d++) {  //100
                boolean vis[][] = new boolean[n][m];
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) { //60000
                        vis[i][j]=true;
                        if (Character.valueOf(a[i].charAt(j)).equals('1')) {
                            for (int di = 0, dj = d; di <= d && dj>=0; di++, dj--) { //25000000
                                if (di == 0) {
                                    int xi = i, yi = j + dj;
                                    check(distances,vis,xi,yi,n,m,d,i,j,a);
                                    yi = j - dj;
                                    check(distances,vis,xi,yi,n,m,d,i,j,a);
                                } else if(dj==0){
                                    int xi = i+di, yi = j;
                                    check(distances,vis,xi,yi,n,m,d,i,j,a);
                                    xi = i - di;
                                    check(distances,vis,xi,yi,n,m,d,i,j,a);
                                }
                                else{
                                    for (int x = 0; x <= 3; x++) {
                                        int xi = i + dx[x] * di, yi = j + dy[x] * dj;
                                        check(distances,vis,xi,yi,n,m,d,i,j,a);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for (int i = 1; i <= n + m - 2; i++) {
                out.print(distances[i] + " ");
            }
        }
        out.flush();
    }

    private void check(int[] distances, boolean[][] vis, int xi, int yi, int n, int m,int d,int i, int j,String a[]) {
        if (xi >= 0 && xi < n && yi >= 0 && yi < m && (xi != i || yi != j) && !vis[xi][yi] && Character.valueOf(a[xi].charAt(yi)).equals('1')) {
            distances[d]++;
//            vis[xi][yi]=true;
        }
    }

    public static void main(String[] args) {
        AVGMAT solver = new AVGMAT();
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
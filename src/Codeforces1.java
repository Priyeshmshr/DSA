import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;

public class Codeforces1 {

//    int n;
//    long a[], b[];

    ArrayList<Integer>[] graph;
    int vis[];
    int deg[];
    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt(), m = in.readInt(), k = in.readInt();
            graph = new ArrayList[n + 1];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                int u = in.readInt(), v = in.readInt();
                graph[u].add(v);
                graph[v].add(u);
            }

            deg = new int[n+1];
            for (int i = 1; i <= n; i++) {
                deg[i] = graph[i].size();
            }
            ArrayList<Integer> res = findClique();
            if(res.size()>0){
                out.printLine(2);
                for (Integer u : res) {
                    out.print(u+" ");
                }
                out.printLine();
            }else{
                res = findSubSet();
                if(res.size()>0) {
                    out.printLine(1 + " " + res.size());
                    for (Integer u : res) {
                        out.print(u + " ");
                    }
                    out.printLine();
                }else{
                    out.printLine(-1);
                }
            }
        }
        out.flush();
    }

    private void degree(int u) {
        if(vis[u]==1)return;
        vis[u] = 1;
//        int deg =
        for (Integer v : graph[u]) {

        }
    }

    private ArrayList<Integer> findSubSet() {
        return null;
    }

    private ArrayList<Integer> findClique() {
        return null;
    }

    public static void main(String[] args) {
        Codeforces1 solver = new Codeforces1();
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

        public static Integer[] readWrapperIntegerArray(InputReader in, int size) {
            Integer[] array = new Integer[size];
            for (int i = 0; i < size; i++) {
                array[i] = in.readInt();
            }
            return array;
        }

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
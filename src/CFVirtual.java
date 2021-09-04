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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CFVirtual {

    ArrayList<HashMap<Integer, Long>> graph;

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        int t = in.readInt();
        while (t-- > 0) {
            int n = in.readInt();
            long s = in.readLong();

            graph = new ArrayList<>(n + 2);
            for (int i = 0; i <= n; i++) {
                graph.add(new HashMap<>());
            }
            for (int i = 0; i < n - 1; i++) {
                int u = in.readInt(), v = in.readInt();
                long w = in.readLong();
                graph.get(u).put(v, w);
                graph.get(v).put(u, w);
            }
            ans = 0;
            dfs(1, -1, s, 0, new LinkedList<>());
            out.printLine(ans);
        }
        out.flush();

    }

    int ans = 0;

    /*void sample(){
        int n = in.readInt(), m = in.readInt();
        int dp[][] = new int[n + 2][m + 2];
        int a[] = CFVirt.IOUtils.readIntegerArray(in, n);
        int b[] = CFVirt.IOUtils.readIntegerArray(in, m);
        for (int j = 0; j < m; j++) {
            dp[0][j] = a[0] & b[j];
        }
        for (int i = 1; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for (int j = 0; j < m; j++) {
                int curr = a[i]&b[j];
                for (int k = 0; k < m ; k++) {
                    dp[i][j] = Math.min(dp[i][j], (dp[i-1][k]|curr));
                }
            }
        }
        int ans =Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            ans = Math.min(ans,dp[n-1][i]);
        }
        out.printLine(ans);
        out.flush();
    }*/


    private void dfs(int u, int par, long s, long currSum, LinkedList<Integer> path) {
        path.add(u);
        for (Map.Entry<Integer, Long> edge : graph.get(u).entrySet()) {
            if ( edge.getKey() != par )
                dfs(edge.getKey(), u, s, currSum + edge.getValue(), path);
        }
        int currAns = Integer.MAX_VALUE;
        if ( currSum > s && graph.get(u).size() == 1 ) { //leaf
            int st = 0, end = 0;
            long soFar = 0;
            long diff = currSum - s;
            while (end <= path.size() - 1) {
                while (st <= end && soFar > diff) {
                    currAns = Math.min(currAns, end - st + 1);
                    soFar -= path.get(st);
                    st++;
                }
                if ( st > end || soFar < diff ) {
                    end++;
                    if ( end < path.size() ) soFar += path.get(end);
                }
            }
        }
        ans += currAns;
//        path.removeLast();
    }

    public static void main(String[] args) {
        CFVirtual solver = new CFVirtual();
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
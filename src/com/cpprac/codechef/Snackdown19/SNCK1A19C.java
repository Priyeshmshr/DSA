package com.cpprac.codechef.Snackdown19;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class SNCK1A19C {

    int spf[] = new int[51];
    public static final int MAXN = 51;
    void sieve()
    {
        spf[1] = 1;
        for (int i=2; i<MAXN; i++)
            spf[i] = i;

        for (int i=4; i<MAXN; i+=2)
            spf[i] = 2;

        for (int i=3; i*i<MAXN; i++)
        {
            if (spf[i] == i)
            {
                for (int j=i*i; j<MAXN; j+=i)
                    if (spf[j]==j)
                        spf[j] = i;
            }
        }
    }
    ArrayList<Integer> getFactorization(int x)
    {
        Set<Integer> ret = new HashSet<>();
        while (x != 1) {
            ret.add(spf[x]);
            x = x / spf[x];
        }
        return new ArrayList<>(ret);
    }

    Map<Integer, ArrayList<Integer>>  elemFactors = new HashMap<>();
    Map<Integer,ArrayList<String>> elementKeyMap = new HashMap<>();

    public void solve() throws IOException{
        sieve();
        for(int i=2;i<=50;i++){
            elemFactors.put(i, getFactorization(i));
        }
        createKeysForEachPrimes();
        InputReader sn = new InputReader(System.in);
        int t = sn.readInt();
        OutputWriter prnt = new OutputWriter(System.out);
        while(t-->0){
            int n = sn.readInt();
            int a[] = new int[n];
            int countElements[] = new int[51];
            Set<Integer> set = new HashSet<>();
            for(int i=0;i<n;i++){
                a[i]= sn.readInt();
                set.add(a[i]);
                countElements[a[i]]++;
            }
            if(countElements[47]==n) {
                prnt.println(1);
                a[0]=43;
                for(int i=0;i<n;i++){
                    prnt.print(a[i] + " ");
                }
                prnt.print("\n");
                continue;
            }

            Map<String, Integer>  comb = new HashMap<>();
            for(Integer iter: set){
                ArrayList<Integer> factors = elemFactors.get(iter);
                StringBuilder s = new StringBuilder("A");
                for(int j: factors) {
                    if(j!=1)
                        s.append(j);
                }
                String key = s.toString();
                if(comb.containsKey(key)){
                    comb.put(key,comb.get(key)+1);
                }
                else
                    comb.put(key,1);
            }

            boolean replace = false;

            for(Integer iter: set){
                List<Integer> factors = elemFactors.get(iter);
                StringBuilder s = new StringBuilder("A");
                int countElementsWithNotCoPrimes = 0;

                if(factors.size()==1){
                    for(String keys :elementKeyMap.get(factors.get(0))){
                        if(comb.containsKey(keys))
                            countElementsWithNotCoPrimes += comb.get(keys);
                    }
                }
                else if(factors.size()==2){
                    for(String keys :elementKeyMap.get(factors.get(0))){
                        if(comb.containsKey(keys))
                            countElementsWithNotCoPrimes += comb.get(keys);
                    }
                    for(String keys :elementKeyMap.get(factors.get(1))){
                        if(!keys.contains(String.valueOf(factors.get(0)))) {
                            if(comb.containsKey(keys))
                                countElementsWithNotCoPrimes += comb.get(keys);
                        }
                    }
                }
                else if(factors.size()==3){
                    for(String keys :elementKeyMap.get(factors.get(0))){
                        if(comb.containsKey(keys))
                            countElementsWithNotCoPrimes += comb.get(keys);
                    }
                    for(String keys :elementKeyMap.get(factors.get(1))){
                        if(!keys.contains(String.valueOf(factors.get(0)))) {
                            if(comb.containsKey(keys))
                                countElementsWithNotCoPrimes += comb.get(keys);
                        }
                    }
                    for(String keys :elementKeyMap.get(factors.get(2))){
                        if(!(keys.contains(String.valueOf(factors.get(0))) || keys.contains(String.valueOf(factors.get(1)))) ) {
                            if(comb.containsKey(keys))
                                countElementsWithNotCoPrimes += comb.get(keys);
                        }
                    }
                }
                if(countElementsWithNotCoPrimes>=n){
                    replace = true;
                    break;
                }
            }

            if(replace){
                boolean replaced = false;
                prnt.println(1);
                for(int i=0;i<n;i++){
                    if(!replaced && a[i]!=47){
                        prnt.print(47+ " ");
                        replaced= true;
                    }else
                        prnt.print(a[i]+ " ");
                }
            }else{
                prnt.println(0);
                for(int i=0;i<n;i++){
                    prnt.print(a[i]+ " ");
                }
            }
            prnt.print("\n");
        }
        prnt.close();
    }

    private void createKeysForEachPrimes() {
        /**
         *         combinations: (2,3,..,47) (2,3) (2,5) (2,7) (2,11) (2,13) (2,17) (2,19) (2,23)
         *                                       (3,5) (3,7) (3,11) (3,13) (5,7) (2,3,5) (2,3,7)
         */
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47);
        for(Integer iter : primes){
            ArrayList<String> temp = new ArrayList<>();
            StringBuilder sb = new StringBuilder("A");
            temp.add("A"+iter);
            for(int i=0;i<primes.size();i++){
                if(!primes.get(i).equals(iter) && primes.get(i)*iter<=50) {
                    if (primes.get(i) < iter)
                        temp.add("A" + primes.get(i) + iter);
                    else
                        temp.add("A" + iter + primes.get(i));
                }
            }
            if(iter==2){
                temp.add("A235");
                temp.add("A237");
            }
            else if(iter == 3){
                temp.add("A235");
                temp.add("A237");
            }
            else if(iter == 5){
                temp.add("A235");
            }
            else if(iter ==7){
                temp.add("A237");
            }
            elementKeyMap.put(iter,temp);
        }
    }

    public static void main(String[] args) throws IOException {
        SNCK1A19C solver = new SNCK1A19C();
        solver.solve();
    }

    class Scan {
        BufferedReader br;
        StringTokenizer st;

        public Scan() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
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
    class Print
    {
        private final BufferedWriter bw;
        public Print()
        {
            this.bw=new BufferedWriter(new OutputStreamWriter(System.out));
        }
        public void print(Object object)throws IOException
        {
            bw.append(""+object);
        }
        public void println(Object object)throws IOException
        {
            print(object);
            bw.append("\n");
        }
        public void close()throws IOException
        {
            bw.close();
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

        public void println(Object... objects) {
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
    /*private boolean replacementRequired(Set<Integer> set) {

        Integer a[] = new Integer[set.size()];
        a = set.toArray(a);
//        int vis[]= new int[a.length];
        boolean eleHasCoPrime[] = new boolean[51];
        for(int i=0;i<a.length;i++){
            if(!eleHasCoPrime[a[i]])
            for (int j = 0; j < a.length; j++) {
                if (i != j && gcd(a[i], a[j]) == 1) {
                    eleHasCoPrime[a[j]]=true;
                    eleHasCoPrime[a[i]]=true;
                }
//                    vis[j] = 1;
            }
            if (!eleHasCoPrime[a[i]])
                return true;
            if(vis[i]==0) {
                vis[i] = 1;
//                element[a[i]]=1;

            }
        }
        return false;
    }*/

}
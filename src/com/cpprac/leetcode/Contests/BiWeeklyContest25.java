package com.cpprac.leetcode.Contests;

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
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BiWeeklyContest25 {

    public void solve() {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
//        System.out.println(maxDiff(100505));
        ArrayList<List<Integer>> input = new ArrayList<>();
        input.add(Arrays.asList(3,4));
        input.add(Arrays.asList(4,5));
        input.add(Arrays.asList(5));
        System.out.println(numberWays(input));
    }

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Integer.MIN_VALUE;
        for(Integer i: candies)
            max = Math.max(max,i );
        List<Boolean> ans = new ArrayList<>();
        for(Integer i:candies){
            if(i+extraCandies>=max)
                ans.add(true);
            else ans.add(false);
        }
        return ans;
    }

    int modulo = 1000000007;
    int dp[][] = new int[1025][41];
    Map<Integer, ArrayList<Integer>> capToPerson = new HashMap<>();

    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        for(int i=1;i<=41;i++){
            capToPerson.put(i,new ArrayList<>());
        }
        int totalCap=0;
        for(int person =0;person<n;person++){
            for(int hat : hats.get(person)){
                totalCap= Math.max(totalCap,hat);
                ArrayList<Integer> personsWearingCap = capToPerson.getOrDefault(hat,new ArrayList<>());
                personsWearingCap.add(person);
                capToPerson.put(hat,personsWearingCap);
            }
        }
        for (int i=0;i<dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }
        return solveDp(0, 1, n, totalCap)%modulo;
    }

    public int solveDp(int mask, int capNo, int n, int totalCap){
        if (mask == (1 << n) - 1) return 1;
        if (capNo > totalCap) return 0;
        if (dp[mask][capNo] != -1) return dp[mask][capNo];
        int ways = solveDp(mask, capNo + 1, n, totalCap);

        for (int j = 0; j < capToPerson.get(capNo).size(); j++)
        {
            if ((mask & (1 << capToPerson.get(capNo).get(j))) != 0)
                continue;
            else ways += solveDp(mask | (1 << capToPerson.get(capNo).get(j)), capNo + 1, n, totalCap);
            ways %= modulo;
        }

        return dp[mask][capNo] = ways;

    }

    public boolean checkIfCanBreak(String s1, String s2) {
        char s11[]  = s1.toCharArray(), s22[] = s2.toCharArray();
        Arrays.sort(s11);
        Arrays.sort(s22);
        boolean possible = true;
        for(int i=0;i<s1.length();i++){
            if(s11[i]<s22[i]) {
                possible = false;
                break;
            }
        }
        if(!possible) {
            possible = true;
            for (int i = 0; i < s1.length(); i++) {
                if ( s11[i] > s22[i] ) {
                    possible = false;
                    break;
                }
            }
        }
        return possible;
    }


    public int maxDiff(int num) {
        String s = String.valueOf(num);
        char firstNon9=s.charAt(0), firstNon1=s.charAt(0);
        int replaceFirst =0;
        if(s.charAt(0)=='9'){
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)!='9'){
                    firstNon9=s.charAt(i);
                    break;
                }
            }
        }
        if(s.charAt(0)=='1'){
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)!='1' && s.charAt(i)!='0'){
                    firstNon1=s.charAt(i);
                    replaceFirst=i;
                    break;
                }
            }
        }
        String high = "", low ="";
        low = s.replaceAll(String.valueOf(firstNon1),replaceFirst==0?"1":"0");
        high = s.replaceAll(String.valueOf(firstNon9),"9");
        return Integer.valueOf(high)-Integer.valueOf(low);
    }

    public static void main(String[] args) {
        BiWeeklyContest25 solver = new BiWeeklyContest25();
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
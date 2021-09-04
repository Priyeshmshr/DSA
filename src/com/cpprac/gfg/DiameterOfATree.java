package com.cpprac.gfg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Node{

    int data;
    Node left,right;
    public Node(int value){
        data = value;
        left = right = null;
    }

}
public class DiameterOfATree {

    int height(Node node)
    {
        if(node==null)
            return 0;

        return 1+ Math.max(height(node.left),height(node.right));
    }

    int diameter(Node node){

        if(node == null)
            return 0;

        int lheight = height(node.left);
        int rheight = height(node.right);
        int data = lheight+rheight+1;

        int diameter = Math.max(data,Math.max(diameter(node.left), diameter(node.right)));
        return diameter;

    }
    public void solve() {
        Scan sn = new Scan();

        int t = sn.nextInt();
        while(t-->0)
        {

        }

    }

    public static void main(String[] args) {
        DiameterOfATree solver = new DiameterOfATree();
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
}

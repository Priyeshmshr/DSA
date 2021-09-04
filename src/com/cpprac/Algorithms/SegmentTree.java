package com.cpprac.Algorithms;

/**
 * @author priyesh.mishra
 */
public class SegmentTree {

    long s[];
    long a[];

    public SegmentTree(long a[]) {
        s = new long[4 * a.length + 5];
        this.a = a;
    }

    public void buildRangeMinQueryST(int st, int end, int id) {
        if ( end == st ) {
            s[id] = a[st];
            return;
        }
        int mid = (st + end) / 2;
        buildRangeMinQueryST(st, mid, id * 2);
        buildRangeMinQueryST(mid + 1, end, id * 2 + 1);
        s[id] = Math.min(s[id * 2], s[id * 2 + 1]);
    }

    public long queryST(int x, int y, int id, int st, int end) {
        if ( y < st || x > end ) {
            return 0;
        }
        if ( x<=st && y>=end ){
            return s[id];
        }
        int mid = (st+end)/2;
        return Math.min(queryST(x, y, id * 2, st, mid), queryST(x, y, id * 2 + 1, mid + 1, end));
    }
}

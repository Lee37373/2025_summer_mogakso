import java.math.*;
import java.util.*;
import java.io.*;

public class Main
{
    static int t, n, m, k, p, s, e, c, w, h, q, g, l, r, count, d;
    static long answer = 0, mod = 1000000007, L;
    static long max = 0, min = Long.MAX_VALUE;
    static int[] parent;
    static boolean f = false;
    static int[][] dir = {{-1,0},{0,1},{1,0},{0,-1},{1,1},{-1,1},{1,-1},{-1,-1}};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String ss;
        int i0, i1, i2, i3, i4, t0, t1, t2, t3, ll, rr, len;
        n = Integer.parseInt(br.readLine());
        long l0, l1;
        String[] list = new String[n];
        HashSet<String> set = new HashSet<>();
        for(i0 = 0; i0 < n; i0++)
            list[i0] = br.readLine();
        for(i0 = 0; i0 < n; i0++)
            for(i1 = 0; i1 < n; i1++)
                if(i0 != i1)
                {
                    set.add(list[i0]+list[i1]);
                }
        System.out.print(set.size());
    }
}

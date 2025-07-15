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
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        for(i0 = 0; i0 < n; i0++)
        {
            st = new StringTokenizer(br.readLine());
            t0 = Integer.parseInt(st.nextToken());
            t1 = Integer.parseInt(st.nextToken());
            if((l>=t0&&t1>=r))
                answer++;
        }
        System.out.print(answer);
    }
}
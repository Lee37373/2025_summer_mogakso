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
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String ss;
        int i0, i1, i2, i3, i4, t0, t1, t2, t3, ll, rr, len;
        n = Integer.parseInt(br.readLine());
        long sum = 0;
        for(i0 = 0; i0 < n; i0++)
        {
            st = new StringTokenizer(br.readLine());
            ss = st.nextToken();
            long l0 = Long.parseLong(st.nextToken());
            if((sum+=l0) > 100)
            {
                System.out.print("Too Long");
                return;
            }
            while(l0-->0)
                sb.append(ss);
        }
        System.out.print(sb.toString());
    }
}
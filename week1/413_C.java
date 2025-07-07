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
        Deque<int[]> queue = new LinkedList<>();
        q = Integer.parseInt(br.readLine());
        while(q-->0)
        {
            st = new StringTokenizer(br.readLine());
            if(st.nextToken().charAt(0)=='1')
                queue.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
            else
            {
                t0 = Integer.parseInt(st.nextToken());
                answer = 0;
                while(t0 > 0)
                {
                    int[] s0 = queue.poll();
                    if(s0[0] <= t0)
                    {
                        t0 -= s0[0];
                        answer += 1l*s0[0]*s0[1];
                    }
                    else
                    {
                        s0[0] -= t0;
                        answer += 1l*t0*s0[1];
                        queue.addFirst(s0);
                        t0 = 0;
                    }
                }
                sb.append(answer).append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}

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
        t = Integer.parseInt(br.readLine());
        Long[] list = new Long[200000];
        while(t-->0)
        {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            l1:{
                for(i0 = 0; i0 < n; i0++)
                    list[i0] = Long.parseLong(st.nextToken());
                Arrays.sort(list, 0, n, (o1,o2)->{
                    return Math.abs(o1)>Math.abs(o2)?1:-1;
                });
                t0 = 0;
                for(i0 = 0; i0 < n; i0++)
                    if(Math.abs(list[i0]) != Math.abs(list[0]))
                        break;
                    else if(list[i0] < 0)
                        t0++;
                if(i0 == n && (t0 == n || t0 == 0 || (t0 == n>>1) || ((n&1) == 1 && t0 == (n+1)>>1)))
                {
                    sb.append("Yes\n");
                    break l1;
                }
                for(i0 = 1; i0 < n-1; i0++)
                    if(list[i0]*list[i0] != list[i0-1]*list[i0+1])
                    {
                        sb.append("No\n");
                        break l1;
                    }
                sb.append("Yes\n");
            }
        }
        System.out.print(sb.toString());
    }
}
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
        int a = Integer.parseInt(br.readLine());
        long n = Long.parseLong(br.readLine()), l0;
        for(i0 = 1; i0 < 1000000; i0++)
        {
            l0 = i0;
            t0 = i0;
            while(t0>0)
            {
                l0*=10;
                l0+=t0%10;
                t0/=10;
            }
            if(l0<=n)
            l1:
            {
                ss = Long.toString(l0, a);
                l = 0; r = ss.length()-1;
                while(l<r)
                    if(ss.charAt(l++)!=ss.charAt(r--))
                        break l1;
                answer += l0;
            }
            //
            l0 = i0;
            t0 = i0/10;
            while(t0>0)
            {
                l0*=10;
                l0+=t0%10;
                t0/=10;
            }
            if(l0<=n)
            l1:
            {
                ss = Long.toString(l0, a);
                l = 0; r = ss.length()-1;
                while(l<r)
                    if(ss.charAt(l++)!=ss.charAt(r--))
                        break l1;
                answer += l0;
            }
        }
        System.out.print(answer);
    }
}

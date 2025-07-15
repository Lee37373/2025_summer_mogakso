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
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[][] list = new int[k][3];
        int[] s0 = new int[2], v = new int[k];
        TreeMap<int[], Integer> map = new TreeMap<>((o1,o2)->{
            if(o1[0]==o2[0])
                return o1[1]-o2[1];
            return o1[0]-o2[0];
        });
        Queue<Integer>[] next = new LinkedList[k];
        Queue<int[]> queue = new LinkedList<>();
        
        for(i0 = 0; i0 < k; i0++)
            next[i0] = new LinkedList<>();
        for(i0 = 0; i0 < k; i0++)
        {
            st = new StringTokenizer(br.readLine());
            list[i0][0] = Integer.parseInt(st.nextToken());
            list[i0][1] = Integer.parseInt(st.nextToken());
            list[i0][2] = i0;
            map.put(list[i0], i0);
        }
        for(i0 = 0; i0 < k; i0++)
        {
            for(i1 = 0; i1 < 8; i1++)
            {
                s0[0] = list[i0][0]+dir[i1][0];
                s0[1] = list[i0][1]+dir[i1][1];
                if(map.containsKey(s0))
                    next[i0].add(map.get(s0));
            }
        }
        //
        for(i0 = 0; i0 < k; i0++)
            if(list[i0][0] == 1)
            {
                v[i0] = 1;
                queue.add(list[i0]);
            }
        while(!queue.isEmpty())
        {
            s0 = queue.poll();
            if(s0[0] == n || s0[1] == 1)
            {
                System.out.print("No");
                return;
            }
            for(Integer i : next[s0[2]])
            {
                if(v[i]!=1)
                {
                    v[i] = 1;
                    queue.add(list[i]);
                }
            }
        }
        queue.clear();
        for(i0 = 0; i0 < k; i0++)
            if(list[i0][1] == m)
            {
                v[i0] = 2;
                queue.add(list[i0]);
            }
        while(!queue.isEmpty())
        {
            s0 = queue.poll();
            if(s0[0] == n || s0[1] == 1)
            {
                System.out.print("No");
                return;
            }
            for(Integer i : next[s0[2]])
            {
                if(v[i]!=2)
                {
                    v[i] = 2;
                    queue.add(list[i]);
                }
            }
        }
        System.out.print("Yes");
    }
}
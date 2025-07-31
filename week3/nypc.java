import java.util.*;
import java.io.*;
import java.math.*;

// 게임 상태를 관리하는 클래스
class Game {
    int ROW = 10, COL = 17;
    private int[][] board; // 게임 보드 (2차원 리스트)
    private boolean first;             // 선공 여부
    private boolean passed;            // 마지막 턴에 패스했는지 여부
    public int myCount = 0, oppCount = 0;
    private int[][][][] dp = new int[ROW][COL][ROW][COL];
    
    public Game(int[][] board, boolean first) {
        this.board = board;
        this.first = first;
        this.passed = false;
    }
    
    public void setBoard(int[][] a, int[][] b)
    {
        for(int i = 0; i < ROW; i++)
            System.arraycopy(a[i], 0, b[i], 0, COL);
    }
    
    public void printBoard()
    {
        for(int i = 0; i < ROW; i++)
            System.out.println(Arrays.toString(board[i]));
        System.out.println();
    }
    
    // 사각형 (r1, c1) ~ (r2, c2)이 유효한지 검사 (합이 10이고, 네 변을 모두 포함)
    private int isValid(int r1, int c1, int r2, int c2, int turn) {
        int sums = 0, count = 0, oppCount = 0;
        boolean r1fit = false, c1fit = false, r2fit = false, c2fit = false;
        
        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                if (board[r][c] > 0) {
                    sums += board[r][c];
                    if(sums > 10)
                        return -2;
                    if (r == r1) r1fit = true;
                    if (r == r2) r2fit = true;
                    if (c == c1) c1fit = true;
                    if (c == c2) c2fit = true;
                    count++;
                }
                if(board[r][c] <= 0 && board[r][c] != turn)
                    oppCount++;
            }
        }
        if((sums == 10) && r1fit && r2fit && c1fit && c2fit)
            return (oppCount<<10)+count;
        return -1;
    }
    
    // ================================================================
    // ===================== [필수 구현] ==============================
    // 합이 10인 유효한 사각형을 찾아 (r1, c1, r2, c2) 반환
    // 없으면 null을 반환하여 패스를 의미함
    // ================================================================
    public int[] calculateMove(int myTime, int turn) {
        // 가로로 인접한 두 칸을 선택했을 때 유효하면 선택하는 전략
        int[] re = new int[4], in = new int[4];
        int max = -1, oppMin = Integer.MAX_VALUE, tmp1, tmp2, tmp3, tmp4, mc = myCount, oc = oppCount, tmc, toc;
        re[0] = -1;
        int[][] ori = new int[ROW][COL], map1 = new int[ROW][COL], map2 = new int[ROW][COL];
        final int fmy = myCount, fopp = oppCount;
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)->{
            if(o1[4]-o1[5] != o2[4]-o2[5])
                return (o2[4]-o2[5])-(o1[4]-o1[5]);
            if(o1[4]!=o2[4])
                return o2[4]-o1[4];
            return o1[5]-o2[5];
        });
        setBoard(board, ori);
        
        for(int r1 = 0; r1 < 10; r1++)
        for(int c1 = 0; c1 < 17; c1++)
        for(int r2 = r1; r2 < 10; r2++)
        for(int c2 = c1; c2 < 17; c2++)
            if((tmp1 = isValid(r1, c1, r2, c2, turn)) >= 0)
            {
                in[0] = r1; in[1] = c1; in[2] = r2; in[3] = c2;
                updateMove(in, 0);
                int myMax = -1, oppMax = -1;
                for(int rr1 = 0; rr1 < 10; rr1++)
                for(int cc1 = 0; cc1 < 17; cc1++)
                for(int rr2 = rr1; rr2 < 10; rr2++)
                for(int cc2 = cc1; cc2 < 17; cc2++)
                    if((tmp2 = isValid(rr1, cc1, rr2, cc2, -1)) >= 0)
                    {
                        int my = tmp2>>10, op = tmp2&0b1111111111;
                        if(oppMax+myMax < op+my)
                        {
                            myMax = my;
                            oppMax = op;
                        }
                    }
                    else if(tmp2 == -2)
                        break;
                if(myMax != -1)
                    queue.add(new int[]{r1, c1, r2, c2, myCount-myMax, oppCount+myMax+oppMax, myCount-mc, oppCount});
                setBoard(ori, board);
                myCount = mc; oppCount = oc;
            }
            else if(tmp1 == -2)
                break;
        if(queue.isEmpty())
        {
            for(int r1 = 0; r1 < 10; r1++)
            for(int c1 = 0; c1 < 17; c1++)
            for(int r2 = r1; r2 < 10; r2++)
            for(int c2 = c1; c2 < 17; c2++)
                if((tmp1 = isValid(r1, c1, r2, c2, turn)) >= 0)
                {
                    in[0] = r1; in[1] = c1; in[2] = r2; in[3] = c2;
                    return in;
                }
            return null;
        }
        if(!queue.isEmpty() && queue.peek()[4] <= myCount)
        {
            queue.clear();
            for(int r1 = 0; r1 < ROW; r1++)
            for(int c1 = 0; c1 < COL; c1++)
            for(int r2 = r1; r2 < ROW; r2++)
            for(int c2 = c1; c2 < COL; c2++)
                if((tmp1 = isValid(r1, c1, r2, c2, turn)) >= 0)
                {
                    in[0] = r1; in[1] = c1; in[2] = r2; in[3] = c2;
                    updateMove(in, 0);
                    setBoard(board, map1);
                    int tmp1MyCount = myCount, tmp1OppCount = oppCount;
                    int myMax = -1, oppMax = -1, or1 = 0, oc1 = 0, or2 = 0, oc2 = 0;
                    for(int rr1 = 0; rr1 < ROW; rr1++)
                    for(int cc1 = 0; cc1 < COL; cc1++)
                    for(int rr2 = rr1; rr2 < ROW; rr2++)
                    for(int cc2 = cc1; cc2 < COL; cc2++)
                        if((tmp2 = isValid(rr1, cc1, rr2, cc2, -1)) >= 0)
                        {
                            in[0] = rr1; in[1] = cc1; in[2] = rr2; in[3] = cc2;
                            updateMove(in, -1);
                            setBoard(board, map2);
                            int tmp2MyCount = myCount, tmp2OppCount = oppCount;
                            for(int rrr1 = 0; rrr1 < ROW; rrr1++)
                            for(int ccc1 = 0; ccc1 < COL; ccc1++)
                            for(int rrr2 = rrr1; rrr2 < ROW; rrr2++)
                            for(int ccc2 = ccc1; ccc2 < COL; ccc2++)
                                if((tmp3 = isValid(rrr1, ccc1, rrr2, ccc2, 0)) >= 0)
                                {
                                    in[0] = rrr1; in[1] = ccc1; in[2] = rrr2; in[3] = ccc2;
                                    updateMove(in, 0);
                                    for(int rrrr1 = 0; rrrr1 < ROW; rrrr1++)
                                    for(int cccc1 = 0; cccc1 < COL; cccc1++)
                                    for(int rrrr2 = rrr1; rrrr2 < ROW; rrrr2++)
                                    for(int cccc2 = ccc1; cccc2 < COL; cccc2++)
                                        if((tmp4 = isValid(rrrr1, cccc1, rrrr2, cccc2, -1)) >= 0)
                                        {
                                            int my = tmp4>>10, op = tmp4&0b1111111111;
                                            if(oppMax+myMax < op+my)
                                            {
                                                myMax = my;
                                                oppMax = op;
                                            }
                                        }
                                        else if(tmp4 == -2)
                                            break;
                                    setBoard(map2, board);
                                    myCount = tmp2MyCount;
                                    oppCount = tmp2OppCount;
                                }
                                else if(tmp3 == -2)
                                    break;
                            setBoard(map1, board);
                            myCount = tmp1MyCount;
                            oppCount = tmp1OppCount;
                        }
                        else if(tmp2 == -2)
                            break;
                    if(myMax != -1)
                        queue.add(new int[]{r1, c1, r2, c2, myCount-myMax, oppCount+myMax+oppMax, myCount-mc, oppCount});
                    setBoard(ori, board);
                    myCount = mc; oppCount = oc;
                }
                else if(tmp1 == -2)
                    break;
        }
        
        if(!queue.isEmpty())
        {
            //if(myCount >= oppCount && queue.peek()[4] == myCount)
            //    return null;
            //System.out.println("=========================================");
            int[] ret = queue.peek();
            //while(!queue.isEmpty())
            //System.out.println(Arrays.toString(queue.poll()));
            return ret;
        }
        return null; // 유효한 사각형이 없으면 패스
    }
    // =================== [필수 구현 끝] =============================
    
    // 상대방의 수를 받아 보드에 반영
    public boolean updateOpponentAction(int[] action, int time) {
        updateMove(action, -1);
        if(passed && myCount > oppCount)
            return true;
        return false;
    }
    
    // 주어진 수를 보드에 반영 (칸을 0으로 지움)
    public void updateMove(int[] action, int isMyMove) {
        if (action == null) {
            passed = true;
            return;
        }
        int r1 = action[0], c1 = action[1], r2 = action[2], c2 = action[3];
        for (int r = r1; r <= r2; r++)
        {
            for (int c = c1; c <= c2; c++)
                if(board[r][c] != isMyMove)
                {
                    if(isMyMove == 0)
                    {
                        if(board[r][c] <= 0)
                            oppCount--;
                        myCount++;
                    }
                    else
                    {
                        if(board[r][c] <= 0)
                            myCount--;
                        oppCount++;
                    }
                    board[r][c] = isMyMove;
                }
        }
        passed = false;
    }
}

// 표준 입력을 통해 명령어를 처리하는 메인 클래스
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Game game = null;
        boolean first = false, fin = false;
        String line;
        int ROW = 10, COL = 17;
        int[][] map = new int[ROW][COL];
        
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) continue;
            
            st = new StringTokenizer(line);
            String command = st.nextToken();
            
            switch (command) {
                case "READY":
                    // 선공 여부 확인
                    String turn = st.nextToken();
                    first = turn.equals("FIRST");
                    System.out.println("OK");
                    System.out.flush();
                    break;
                
                case "INIT":
                    // 보드 초기화
                    int[][] board = new int[ROW][COL];
                    for(int i = 0; i < ROW; i++)
                    {
                        String row = st.nextToken();
                        for(int j = 0; j < row.length(); j++)
                            board[i][j] = row.charAt(j)-'0';
                    }
                    game = new Game(board, first);
                    break;
                case "TIME":
                    // 내 턴: 수 계산 및 실행
                    int myTime = Integer.parseInt(st.nextToken());
                    int oppTime = Integer.parseInt(st.nextToken());
                    int[] ret = game.calculateMove(myTime, 0);
                    game.updateMove(ret, 0);
                    if (!fin && ret != null) {
                        System.out.printf("%d %d %d %d\n", ret[0], ret[1], ret[2], ret[3]);
                    } else {
                        System.out.println("-1 -1 -1 -1"); // 패스
                    }
                    System.out.flush();
                    break;
                
                case "OP":
                case "OOP":
                case "OPP":
                    // 상대 턴 반영
                    int r1 = Integer.parseInt(st.nextToken());
                    int c1 = Integer.parseInt(st.nextToken());
                    int r2 = Integer.parseInt(st.nextToken());
                    int c2 = Integer.parseInt(st.nextToken());
                    int time = Integer.parseInt(st.nextToken());
                    int[] opponentMove;
                    if (r1 == -1 && c1 == -1 && r2 == -1 && c2 == -1) {
                        opponentMove = null;
                    } else {
                        opponentMove = new int[]{r1, c1, r2, c2};
                    }
                    fin = game.updateOpponentAction(opponentMove, time);
                    
                    break;
                
                case "FINISH":
                    // 게임 종료
                    return;
                
                default:
                    // 잘못된 명령 처리
                    System.err.println("Invalid command: " + command);
                    System.exit(1);
                    break;
            }
            //System.out.println(game.myCount+" "+game.oppCount);
        }
    }
}

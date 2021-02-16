package me.maru.week11;

import java.util.Scanner;

public class YouthShark {
    static int[][] fool = new int[4][4];
    static Fish[] fishList = new Fish[16];
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int result = 0;

    static public class Fish {
        int y;
        int x;
        int direction;

        public Fish() {
        }

        public Fish(int y, int x, int direction) {
            this.y = y;
            this.x = x;
            this.direction = direction;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int no = sc.nextInt();
                int di = sc.nextInt();
                no = no - 1;
                di = di - 1;
                fishList[no] = new Fish();
                fishList[no].y = i;
                fishList[no].x = j;
                fishList[no].direction = di;
                fool[i][j] = no;
            }
        }
        solution(0, 0, 0, fool, fishList);
        System.out.println("결과 : " + result);
    }

    private static void solution(int sharkY, int sharkX, int sum, int[][] fool, Fish[] fishList) {
        // back tracking matrix copy
        Fish[] fishList2 = new Fish[16];
        int[][] fool2 = new int[4][4];
        // initialize matrix , fool, matrix
        for (int i = 0; i < 4; i++) {
            System.arraycopy(fool[i], 0, fool2[i], 0, 4);
        }
        System.arraycopy(fishList, 0, fishList2, 0, fishList.length);


        // eating fish
        int eatNo = fool2[sharkY][sharkX];
        // the shark change with fish feature
        int sharkDi = fishList2[eatNo].direction;
        // the dead fish feature is -1
        fishList2[eatNo].y = -1;
        fishList2[eatNo].x = -1;
        fishList2[eatNo].direction = -1;
        fool2[sharkY][sharkX] = -1;

        sum += (eatNo + 1);
        if (result < sum) result = sum;
        // moving fish
        for (int i = 0; i < 16; i++) {
            if (fishList2[i].y == -1) continue;
            int y = fishList2[i].y;
            int x = fishList2[i].x;
            int d = fishList2[i].direction;
            int my = y + dy[d];
            int mx = x + dx[d];
            int md = d;

            while (my < 0 || mx < 0 || my >= 4 || mx >= 4 || (my == sharkY && mx == sharkX)) {

                //if(md + 1 == 7) md = 0;
                //else md = md + 1;
                md = (md + 1) % 8;
                my = y + dy[md];
                mx = x + dx[md];
            }

            // There is another fish
            if (fool2[my][mx] != -1) {
                //fish y, x position swap
                int tempNo = fool2[my][mx];

                fishList2[tempNo].y = y;
                fishList2[tempNo].x = x;
                fishList2[i].y = my;
                fishList2[i].x = mx;
                fishList2[i].direction = md;

                fool2[my][mx] = i;
                fool2[y][x] = tempNo;
            } else {
                // There is no fish
                fishList2[i].y = my;
                fishList2[i].x = mx;
                fishList2[i].direction = md;

                fool2[my][mx] = i;
                fool2[y][x] = -1;

            }
        }
        ;
        // moving shark
        for (int i = 1; i < 4; i++) {

            int my = sharkY + dy[sharkDi] * i;
            int mx = sharkX + dx[sharkDi] * i;
            // if shark face the wall of matrix process end.
            if (my < 0 || mx < 0 || my >= 4 || mx >= 4) {
                break;
            }
            if (fool2[my][mx] != -1) {
                solution(my, mx, sum, fool2, fishList2);
            }
        }
    }
}
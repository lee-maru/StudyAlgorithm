package me.maru.week10;

import java.util.Scanner;

public class EachOtherAlgorithm {
    static int[] parent = new int[1001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        for (int i = 1; i < v + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            unionParent(x, y);
        }
        System.out.print("각 원소가 속한 집합: ");
        for (int i = 1; i <= v; i++) {
            System.out.print(findParent(i) + " ");
        }
        System.out.println();

        // 부모 테이블 내용 출력하기
        System.out.print("부모 테이블: ");
        for (int i = 1; i <= v; i++) {
            System.out.print(parent[i] + " ");
        }
        System.out.println();
    }

    private static void unionParent(int x, int y) {
        x = findParent(x);
        y = findParent(y);
        if(x > y) parent[x] = y;
        else parent[y] = x;
    }

    private static int findParent(int y) {
        if(parent[y] == y) return y;
        else return findParent(parent[y]);
    }
}

package me.maru.week10;

import java.util.Scanner;

public class NewEachOtherAlgorithm {
    static int[] parent = new int[1001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //vertex
        int v = sc.nextInt();
        //간선
        int e = sc.nextInt();

        for(int i = 1; i < v; i++){
            parent[i] = i;
        }

        for(int i = 0; i < e; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            unionParent(x, y);
        }
    }

    private static void unionParent(int x, int y) {
        x = findParent(x);
        y = findParent(y);
        if(x > y) parent[x] = y;
        else parent[y] = x;
    }

    private static int findParent(int a) {
        if(parent[a] == a) return a;
        return parent[a] = findParent(parent[a]);
        /*return findParent(parent[a]);*/
    }

}

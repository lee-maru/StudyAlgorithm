package me.maru.week10;

import java.util.Scanner;

public class CycleEachOtherAlgorithm {
    static int[] parent = new int[10001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // vertex
        int v = sc.nextInt();
        // edge
        int e = sc.nextInt();
        // cycle boolean
        boolean cycle = false;

        for(int i = 1; i < v; i++){
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(findParent(x) == findParent(y)){
                cycle = true;
                break;
            }
            else unionParent(x, y);
        }
        System.out.println("cycle's status : " + cycle);
    }

    private static void unionParent(int x, int y) {
        x = findParent(x);
        y = findParent(y);

        if(x > y) parent[y] = x;
        else parent[x] = y;
    }

    private static int findParent(int y) {
        if(parent[y] != y) return parent[y] = findParent(parent[y]);
        else return y;
    }
}

package me.maru.week10;

import java.util.*;

public class TopologicalSort {

    public static int e,v;
    public static int[] inDegree = new int[100001];
    public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //vertex
        v = sc.nextInt();
        //edge
        e = sc.nextInt();

        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < e; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph.get(x).add(y);
            inDegree[y]++;
        }

        topologicalSort();
    }

    private static void topologicalSort() {
        // result list
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < v; i++) {
            if(inDegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()){
            int now = q.poll();
            result.add(now);
            for (int i = 0; i < graph.get(now).size(); i++) {
                int now2 = graph.get(now).get(i);
                inDegree[now2] = inDegree[now2] - 1;
                if(inDegree[now2] == 0){
                    q.offer(now2);
                }
            }
        }

        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}

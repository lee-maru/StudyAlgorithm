package me.maru.week9;

import java.util.ArrayList;
import java.util.Scanner;

public class DijkstraAlgorithm {
    // vertex node
    public static class Node{
        private int index;
        private int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        public int getIndex() {
            return index;
        }

        public int getDistance() {
            return distance;
        }
    }

    static final int INF = (int) 1e9;
    // vertex
    public static int v;
    // edg e
    public static int e;
    // start
    public static int start;
    // 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    // 방문정보 배열
    public static boolean[] visited = new boolean[100_001];
    // 최단 거리 테이블
    public static int[] d = new int[100_001];
    // 방문하지 않은 노드 중에서, 가장 최단 거리가 짧은 노드의 번호를 반환
    public static int getSmallestNode(){
        int min_value = INF;
        int index = 0; // 가장 최단 거리가 짧은 노드 (index of Node)
        for (int i = 1; i <= v; i++) {
            if(d[i] < min_value && !visited[i]){
                min_value = d[i];
                index = i;
            }
        }
        return index;
    }

    public static void dikstra(int start){
        d[start] = 0;
        visited[start] = true;
        for(int i = 0; i<graph.get(start).size(); i++){
            d[graph.get(start).get(i).getIndex()] = graph.get(start).get(i).getDistance();
        }
        // 시작 노드를 제외한 전체 n-1 개의 노드에 대해서 반복
        for (int i = 0; i < v-1; i++) {
            int now = getSmallestNode();
            visited[now] = true;
            for (int j = 0; j < graph.get(now).size(); j++) {
                int cost = d[now] + graph.get(now).get(j).getDistance();
                //
            }

        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        v = sc.nextInt();
        e = sc.nextInt();

    }
}

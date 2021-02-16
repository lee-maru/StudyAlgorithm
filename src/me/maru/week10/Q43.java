package me.maru.week10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// Q43 어두운 길

/**
 teteCase:
 7 11
 0 1 7
 0 3 5
 1 2 8
 1 3 9
 1 4 7
 2 4 5
 3 4 15
 3 5 6
 4 5 8
 4 6 9
 5 6 11
 */
public class Q43 {
    public static class Edge1 implements Comparable<Edge1>{
        int nodeX; // 집 x
        int nodeY; // 집 y
        int distance;  // x -> y  도로의 길이라고

        public Edge1(int nodeX, int nodeY, int distance) {
            this.nodeX = nodeX;
            this.nodeY = nodeY;
            this.distance = distance;
        }

        public int getNodeX() {
            return nodeX;
        }

        public int getNodeY() {
            return nodeY;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Edge1 o) {
            if(this.distance < o.distance){
                return -1;
            }
            return 1;
        }
    }
    static int[] parent = new int[2000000];
    static ArrayList<Edge1> edgeList = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //vertex
        int v = sc.nextInt();
        //edge
        int e = sc.nextInt();
        // result 원래 이알고리즘을 생각안했을 때 드는  - 최소한의 길이
        int result = 0;

        for (int i = 0; i < v; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            int nodeX = sc.nextInt();
            int nodeY = sc.nextInt();
            int distance = sc.nextInt();
            edgeList.add(new Edge1(nodeX, nodeY, distance));
        }

        Collections.sort(edgeList);

        int total = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            int nodeX = edgeList.get(i).getNodeX();
            int nodeY = edgeList.get(i).getNodeY();
            total = total + edgeList.get(i).getDistance();
            if(findParent(nodeX) != findParent(nodeY)){
                unionPaent(nodeX, nodeY);
                result = result + edgeList.get(i).getDistance();
            }
        }

        System.out.println(" distance of city : " + (total - result));
    }

    private static void unionPaent(int nodeX, int nodeY) {
        nodeX = findParent(nodeX);
        nodeY = findParent(nodeY);
        if(nodeX > nodeY) parent[nodeX] = nodeY;
        else parent[nodeY] = nodeX;
    }

    private static int findParent(int a) {
        if(parent[a] == a) return a;
        else{
            return parent[a] = findParent(parent[a]);
        }
    }

}

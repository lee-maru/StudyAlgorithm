package me.maru.week10;import java.util.*;public class KruskalAlgorithm {    static int[] parent = new int[10001];    static class Edge implements Comparable<Edge>{        int distance;        int nodeX;        int nodeY;        public int getDistance() {            return distance;        }        public int getNodeX() {            return nodeX;        }        public int getNodeY() {            return nodeY;        }        public Edge(int distance, int nodeX, int nodeY) {            this.distance = distance;            this.nodeX = nodeX;            this.nodeY = nodeY;        }        @Override        public int compareTo(Edge o) {            if(this.distance < o.distance){                return -1;            }            return 1;        }    }    public static void main(String[] args) {        Scanner sc = new Scanner(System.in);        //vertex        int v = sc.nextInt();        //edge        int e = sc.nextInt();        //list        List<Edge> edges = new ArrayList<>();        //result        int result = 0;        for (int i = 1; i < v; i++) {            parent[i] = i;        }        for (int i = 0; i < e; i++) {            edges.add(new Edge(sc.nextInt(),sc.nextInt(),sc.nextInt()));        }        Collections.sort(edges);        for (int i = 0; i < edges.size(); i++) {            int x = edges.get(i).getNodeX();            int y = edges.get(i).getNodeY();            if(findParent(x) != findParent(y)){                unionParent(x,y);                result = result + edges.get(i).getDistance();            }        }        System.out.println("Kruskal distance result : " + result);    }    private static void unionParent(int x, int y) {        x = findParent(x);        y = findParent(y);        if(x > y) parent[x] = y;        else parent[y] = x;    }    private static int findParent(int x) {        if(parent[x] == x) return x;        else return parent[x] = findParent(parent[x]);    }}
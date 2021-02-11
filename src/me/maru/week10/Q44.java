package me.maru.week10;

import java.util.*;

/**
 testcase:
 5
 11 -15 -15
 14 -5 -15
 -1 -1 -5
 10 -4 -1
 19 -4 19
 */
public class Q44 {
    public static int[] planets = new int[100_001];
    public static class Edge2 implements Comparable<Edge2> {
        int nodeX;
        int nodeY;
        int distance;

        public Edge2(int nodeX, int nodeY, int distance) {
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
        public int compareTo(Edge2 o) {
            if (this.distance < o.distance) return -1 ;
            else return 1;
        }
    }
    public static class Location implements Comparable<Location> {
        int planet;
        int location;

        public int getPlanet() {
            return planet;
        }

        public int getLocation() {
            return location;
        }

        public Location(int planet, int location) {
            this.planet = planet;
            this.location = location;
        }

        @Override
        public int compareTo(Location o) {
            if(this.location == o.location) return Integer.compare(this.planet,o.planet);
            else return Integer.compare(this.location, o.location);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //vertex
        int v = sc.nextInt();
        // location x
        for (int i = 0; i < v; i++) {
            planets[i] = i;
        }
        List<Location> listX = new ArrayList<>();
        List<Location> listY = new ArrayList<>();
        List<Location> listZ = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            listX.add(new Location(i, x));
            listY.add(new Location(i, y));
            listZ.add(new Location(i, z));
        }
        Collections.sort(listX);
        Collections.sort(listY);
        Collections.sort(listZ);

        List<Edge2> edgeList = new ArrayList<>();

        for (int i = 0; i < v-1; i++){
            edgeList.add(new Edge2(listX.get(i).getPlanet(),listX.get(i+1).getPlanet(),listX.get(i+1).getLocation()-listX.get(i).getLocation()));
            edgeList.add(new Edge2(listY.get(i).getPlanet(),listY.get(i+1).getPlanet(),listY.get(i+1).getLocation()-listY.get(i).getLocation()));
            edgeList.add(new Edge2(listZ.get(i).getPlanet(),listZ.get(i+1).getPlanet(),listZ.get(i+1).getLocation()-listZ.get(i).getLocation()));
        }

        Collections.sort(edgeList);

        int result = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            int distance = edgeList.get(i).getDistance();
            int nodeX = edgeList.get(i).getNodeX();
            int nodeY = edgeList.get(i).getNodeY();

            if(findParent(nodeX) != findParent(nodeY)){
                result = result + distance;
                unionParent(nodeX, nodeY);
            }
        }

        System.out.println(result);

    }

    private static void unionParent(int nodeX, int nodeY) {
        nodeX = findParent(nodeX);
        nodeY = findParent(nodeY);
        if(nodeX < nodeY) planets[nodeY] = nodeX;
        else planets[nodeX] = nodeY;
    }

    private static int findParent(int a) {
        if(planets[a] == a) return a;
        else return planets[a] = findParent(planets[a]);
    }
}



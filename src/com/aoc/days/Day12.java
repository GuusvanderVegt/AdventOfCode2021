package com.aoc.days;

import com.aoc.AOC;

import java.util.*;

public class Day12 extends AOC {
    public Day12(){
        super("12");
    }

    @Override
    public void solve(List<String> input) {
        Graph g = new Graph();

        for(String line : input){
            String[] caves = line.split("-");
            g.addEdge(caves[0], caves[1]);
        }
    }

    public class Graph {
//        private ArrayList<Vertex>[] adjList;
       private Map<Vertex, List<Vertex>> map = new HashMap<>();

       public void addVertex(Vertex s){
           map.put(s, new LinkedList<Vertex>());
       }

       public void addEdge(String source, String destination){
           Vertex s = null, d = null;

           for(Vertex v : map.keySet()){
               if(v.caveName.equals(source)){
                   s = v;
                   break;
               }

               if(v.caveName.equals(destination)){
                   d = v;
                   break;
               }
           }

           if(s == null){
               s = new Vertex(source);
               addVertex(s);
           }

           if(d == null){
               d = new Vertex(destination);
               addVertex(d);
           }

           map.get(s).add(d);
           map.get(d).add(s);
       }

    }

    public class Vertex {
        String caveName;
        boolean smallCave, visited;

        public Vertex(String caveName) {
            this.caveName = caveName;

            this.smallCave = isSmallCave(caveName);
            this.visited = false;
        }

        private boolean isSmallCave(String name){
            boolean isSmallCave = true;
            for (int c = 0; c < name.length(); c++) {
                isSmallCave = !Character.isUpperCase(name.charAt(c));
            }

            return isSmallCave;
        }
    }

}

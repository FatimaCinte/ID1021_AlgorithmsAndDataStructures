public class Paths {
        
    City[] path;
    int sp;

    public Paths() {
        path = new City[52];
        sp = 0;
    }

    private Integer shortest(City from, City to) {
        if(from == to)
            return 0;

        for (int i = 0; i < sp; i++) {
            if (path[i] == from)
                return null;
        }

        path[sp++] = from;

        Integer shrt = null;

        for (Connection conn : from.neighbours) { 
            
            Integer dst = shortest(conn.city, to);
            if(dst != null){

                if ((shrt == null) || (shrt > dst + conn.distance)){
                    shrt = dst + conn.distance;

                }
            }
            
        }

        path[sp--] = null;
        return shrt;
    }

    public static void main(String[] args) {
        Map map = new Map("trains.csv");
        Paths path = new Paths();
        Better betterpath = new Better();

        
        String from = "Malmö";
        String to = "Kiruna";

        long t0 = System.nanoTime();
        Integer dist = path.shortest(map.lookup(from), map.lookup(to));
        long time = (System.nanoTime() - t0)/1_000_000;
        
        System.out.println("shortest: " + dist + " min (" + time + " ms)");

        Integer max = null;
        t0 = System.nanoTime();
        Integer distbetter = betterpath.shortest(map.lookup(from), map.lookup(to), max);
        time = (System.nanoTime() - t0)/1_000_000;

        System.out.println("shortest: " + distbetter + " min (" + time + " ms)");

        /*String[] cities = {"Oslo", "Köpenhamn", "Helsingfors", "Berlin", "Warsawa", "Paris", "Milano", "Madrid", "Barcelona", "Kiruna", "Bologna", "Köln"};

        City from = map.lookup("Berlin");
        for(String to : cities){
                    Integer max = null;

            City dest = map.lookup(to);
            long t0 = System.nanoTime();
            Better dij = new Better();
            Integer distbetter = betterpath.shortest(map.lookup(from), map.lookup(dest), max);
            long time = (System.nanoTime() - t0)/1000;

            System.out.println("From " + from.name + " to " + dest.name + ", shortest path: " + dij.dist(dest) + " min (" + time + " us)" + " ((" + dij.cities() + "))");
        }*/


        
        /*for(String to : cities){
            City dest = map.lookup(to);
            Dijkstra dij = null;

            long min = 0;
            //double min = Double.POSITIVE_INFINITY;

        for(int k = 0; k < 1000; k++){
            long t0 = System.nanoTime();
            dij = new Dijkstra(map);
            dij.search(from, dest);
            long t1 = System.nanoTime();
            long t = t1 - t0;

            if(min == 0 || t < min)
                min = t;
            //if (t < min)
             //   min = t;

        }*/
    }
}
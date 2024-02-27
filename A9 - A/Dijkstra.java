import java.util.PriorityQueue;

public class Dijkstra{
    private Path[] done;
    private PriorityQueue<Path> queue;
    //private Map map;

    public static int counter= 0;
    private class Path implements Comparable<Path> {
        private City city;
        private City prev;
        private Integer dist;
    
        //röda i done[], svarta i queue
        //duplicates i queue, bubbla upp den som redan finns
        private Path(City cty){
            city = cty;
            prev = null;
            dist = 0;
        }
        
        private Path(City cty, City prv, Integer dst){
            city = cty;
            prev = prv;
            dist = dst;
        }

        @Override
        public int compareTo(Path pt){
            if(this.dist == pt.dist)
                return 0;
            else if(this.dist > pt.dist)
                return 1;
            else
                return -1;
        }
    }

    public Dijkstra(Map map){
        done = new Path[map.size()];
        queue = new PriorityQueue<Path>();
    }

    //returns distance to city
    public Integer shortestDist(City city){
        if(city != null && done[city.id] != null)
            return done[city.id].dist;
        else
            return null;
    }

    //antal cities
    public Integer cities(){
        Integer n = 0;
        for(int i = 0; i < done.length; i++)
            if(done[i] != null)
                n++;
        return n;
    }

    //returns previous city, the "from" city
    public City from(City city){
        return done[city.id].prev;
    }

    public void search(City from, City dest){
        counter = 0;
        queue.add(new Path(from));
        shortest(dest);
    }

    public void shortest(City dest){

        while(!queue.isEmpty()){
            Path entry = queue.remove();
            City city = entry.city;

            if(done[city.id] == null){
                counter++;
             done[city.id] = entry;
            }

            if(city == dest)
                break;
            
            Integer sofar = entry.dist;
            for(Connection con : city.neighbours){
                City to = con.city;
                if(done[to.id] == null){
                    Path newpath = new Path(to, city, sofar + con.distance);
                    queue.add(newpath);
                }

            }
        }
        
    }

    
    public static void main(String[] args){

        Map map = new Map("europe.csv");
    
        /*String[] cities = {"Oslo", "Köpenhamn", "Helsingfors", "Berlin", "Warsawa", "Paris", "Milano", "Madrid", "Barcelona", "Kiruna", "Bologna", "Köln"};

        City from = map.lookup("Malmö");
        City dest = map.lookup("Kiruna");

        long t0 = System.nanoTime();
        Dijkstra dij = new Dijkstra(map);
        dij.search(from, dest);
        long time = (System.nanoTime() - t0)/1_000_000;

        System.out.println("shortest: " + dij.shortestDist(dest) + " min (" + time + " ms)");*/


        //String[] cities = {"Oslo", "Köpenhamn", "Helsingfors", "Berlin", "Warsawa", "Paris", "Milano", "Madrid", "Manchester", "Kiruna", "Amsterdam", "Köln"};
        //String[] cities = {"Jönköping", "Stockholm","Warsawa", "London", "Wien", "Trondheim", "Tammerfors", "Venedig", "Kiruna", "Valencia", "Narvik", "Glasgow", "Bukarest"};
        String[] cities = {"Köpenhamn", "Oslo", "Berlin", "Helsingfors", "Köln", "Kiruna", "Amsterdam", "Warsawa", "Paris", "Manchester", "Milano", "Madrid"};

        City from = map.lookup("Stockholm");
        /*for(String to : cities){

            City dest = map.lookup(to);
            long t0 = System.nanoTime();
            Dijkstra dij = new Dijkstra(map);
            dij.search(from, dest);
            long time = (System.nanoTime() - t0)/1000;

            System.out.println("From " + from.name + " to " + dest.name + ", shortest path: " + dij.shortestDist(dest) + " min (" + time + " us)" + " ((" + dij.cities() + "))");
        }*/

        for(String to : cities){
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

            }

            int n = dij.cities();


            //System.out.println("From " + from.name + " to " + dest.name + ", shortest path: " + dij.shortestDist(dest) + " min (" + min/1000 + " us)" + " ((" + dij.cities() + "))");
            System.out.println(dest.name + "    &    " + dij.shortestDist(dest) + "    & " + min/1000 + "         & " + n + "\\"); // + "      " + (double)min/n + "       " + (double)min/(n*Math.log(n)));

        }

        
    }

}
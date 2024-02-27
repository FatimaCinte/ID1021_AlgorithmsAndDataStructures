public class Paths {
        
    City[] path;
    int sp;

    public Paths() {
        path = new City[54];
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

        for (Connection conn : from.neighbours) { //conn förbindelsen till Lund, Connection har staden och avstånd
            Integer dist = shortest(conn.city, to);
            if(dist != null){
                if ((shrt == null) || (shrt > dist + conn.distance)){
                    shrt = dist + conn.distance;
                }
            }
            }
        path[sp--] = null;
        return shrt;
    }
     

    public static void main(String[] args) {
        Map map = new Map("trains.csv");
        Paths path = new Paths();
        /*String from = args[0];
        String to = args[1];
        Integer max = Integer.valueOf(args[2]);


        long t0 = System.nanoTime();
        Integer dist = shortest(map.lookup(from), map.lookup(to), max);
        long time = (System.nanoTime() - t0)/1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");*/
        String from = "Malmö";
        String to = "Göteborg";


        long t0 = System.nanoTime();
        Integer dist = path.shortest(map.lookup(from), map.lookup(to));
        long time = (System.nanoTime() - t0)/1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");
    }
}
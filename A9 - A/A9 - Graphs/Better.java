public class Better {
        
    City[] path;
    int sp;

    public Better() {
        path = new City[1024];
        sp = 0;
    }

    private Integer shortest(City from, City to, Integer max) {
        if((max != null) && (max < 0))
            return null;

        if(from == to)
            return 0;

        for (int i = 0; i < sp; i++) {
            if (path[i] == from)
                return null;
        }

        path[sp++] = from;
        
        Integer shrt = null;

        for (Connection conn : from.neighbours) { 
            Integer dist = shortest(conn.city, to, (max != null) ? max - conn.distance : null);
            if(dist != null){
                if ((shrt == null) || (shrt > dist + conn.distance)){
                    shrt = dist + conn.distance;
                    max = shrt;
                }
            }
            }
        path[sp--] = null;
        return shrt;
    }
     

    public static void main(String[] args) {
        Map map = new Map("trains.csv");
        Better path = new Better();
        
        Integer max = null;
        String from = "Göteborg";
        String to = "Umeå";

        long t0 = System.nanoTime();
        Integer dist = path.shortest(map.lookup(from), map.lookup(to), max);
        long time = (System.nanoTime() - t0)/1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");
    }
}
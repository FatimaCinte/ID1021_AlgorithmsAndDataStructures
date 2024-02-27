public class Naive {

    //public Integer max = 600;

    public static int r = 0;
    private static Integer shortest(City from, City to, Integer max) {
        if (max < 0)
            return null;

        if (from == to)
            return 0;

        Integer shrt = null;
        //System.out.println("conn: " + from.name);

        for (Connection conn : from.neighbours) {
            Integer dist = shortest(conn.city, to, max - conn.distance); //finds the shortest distance from city to destination,max - neighbour - neighbour's neighbour - etc
            r++;
            if(dist != null){
                if ((shrt == null) || (shrt > dist + conn.distance)){
                    shrt = dist + conn.distance;
                    max = shrt; //better?
                }
            }
        }
            return shrt;
        }

        
    public static void main(String[] args) {
        Map map = new Map("trains.csv");

        String from = "Stockholm";
        String to = "Umeå";
        Integer max = 800;

        long t0 = System.nanoTime();
        Integer dist = shortest(map.lookup(from), map.lookup(to), max);
        long time = (System.nanoTime() - t0)/1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");
        //System.out.println(r);

    }
}

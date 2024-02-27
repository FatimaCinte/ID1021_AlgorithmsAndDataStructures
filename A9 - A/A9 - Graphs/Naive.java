public class Naive {

    //public Integer max = 600;

    private static Integer shortest(City from, City to, Integer max) {
        if (max < 0)
            return null;

        if (from == to)
            return 0;

        Integer shrt = null;

        for (Connection conn : from.neighbours) { 
            Integer dist = shortest(conn.city, to, max - conn.distance);
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

        /*String from = args[0];
        String to = args[1];
        Integer max = Integer.valueOf(args[2]);


        long t0 = System.nanoTime();
        Integer dist = shortest(map.lookup(from), map.lookup(to), max);
        long time = (System.nanoTime() - t0)/1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");*/
        String from = "Malmö";
        String to = "Göteborg";
        Integer max = 600;


        long t0 = System.nanoTime();
        Integer dist = shortest(map.lookup(from), map.lookup(to), max);
        long time = (System.nanoTime() - t0)/1_000_000;

        System.out.println("shortest: " + dist + " min (" + time + " ms)");
    }
}

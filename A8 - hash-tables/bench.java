
class bench{

        public static <T> double BENCHlin(lookups<T> zip, int tries, int n, T code1, T code2){
            double min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < tries; i++) {
                double t0 = System.nanoTime();
                    
                for(Integer j = 0; j < n; j++){
                    zip.linear(code1);    
                    zip.linear(code2);    
                }

                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            return min;

        }
        public static <T> double BENCHbin(lookups<T> zip, int tries, int n, T code1, T code2){
            double min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < tries; i++) {
                double t0 = System.nanoTime();
                    
                for(Integer j = 0; j < n; j++){
                    zip.binary(code1);    
                    zip.binary(code2);    
                }

                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            return min;

        }


    public static void benchone(){
        
        Zip zipbnch = new Zip("postnummer.csv");
        Zap zapbnch = new Zap("postnummer.csv");
        Zop zopbnch = new Zop("postnummer.csv");
        System.out.printf("#%7s%8s%8s%8s%8s%8s\n", "n", "\t  ziplin", "   zipbin "," zaplin", "  zapbin", "  zopbin");

        for (int n = 100; n <= 3200; n*= 2){
            System.out.printf("%8d", n);
            int tries = 100;

            double minzi = BENCHlin(zipbnch, tries, n, "111 15", "984 99");                    
            double minzii = BENCHbin(zipbnch, tries, n, "111 15", "984 99");

            double minza = BENCHlin(zapbnch, tries, n, 11115, 98499);                    
            double minzaa = BENCHbin(zapbnch, tries, n, 11115, 98499);

            double minzo = BENCHbin(zopbnch, tries, n, 11115, 98499);

            System.out.printf("\t%8.0f%8.0f%8.0f%8.0f%8.0f\n" , minzi/1000, minzii/1000, minza/1000, minzaa/1000, minzo/1000);
        }        

    }

    public static void hashbench(){

    }



    public static void main(String[] args){


        Zip bench = new Zip("postnummer.csv");
        Zap intbench = new Zap("postnummer.csv");
        /*String b = bench.binary("121 31");
        String l = bench.linear("121 31");
        String ib = intbench.binary(12131);
        String il = intbench.linear(12131);

        System.out.println("binary: " + b + ", linear: " + l);
        System.out.println("binary: " + ib + ", linear: " + il);*/

        //benchone();

        Zip zipbnch = new Zip("postnummer.csv");
        Zap zapbnch = new Zap("postnummer.csv");
        Zop zopbnch = new Zop("postnummer.csv");

        String code1 = "111 15";
        Integer code2 = 11115;
        String code12= "984 99";
        Integer code22 = 98499;

        int tries = 1000;
        int fuck = 1000;
        for (int i = 0; i < tries; i++) 
            zipbnch.linear(code1); 

        double t0 = System.nanoTime();
        for (int i = 0; i < tries; i++) 
            zipbnch.linear(code1); 
        double t1 = System.nanoTime();
        double st = (t1 - t0);
        
         t0 = System.nanoTime();
        for (int i = 0; i < tries; i++) 
            zipbnch.linear(code12); 
         t1 = System.nanoTime();
        double paj = (t1 - t0);
        System.out.println("Zip lin, 100 searches of 111 15: " + st/fuck);
        System.out.println("Zip lin, 100 searches of 984 99: " + paj/fuck);


        t0 = System.nanoTime();
        for (int i = 0; i < tries; i++) 
            zipbnch.binary(code1); 
        t1 = System.nanoTime();
        double zipbint = (t1 - t0);
        t0 = System.nanoTime();
        for (int i = 0; i < tries; i++) 
            zipbnch.binary(code12); 
        t1 = System.nanoTime();
        double zipbint2 = (t1 - t0);

        System.out.println("Zip bin, 100 searches of 111 15: " + zipbint/fuck);
        System.out.println("Zip bin, 100 searches of 984 99: " + zipbint2/fuck);

        System.out.println();

        t0 = System.nanoTime();
        for (int i = 0; i < tries; i++) 
            zapbnch.linear(code2); 
         t1 = System.nanoTime();
        double zaplint = (t1 - t0);
        System.out.println("Zap lin, 100 searches of 111 15: " + zaplint/fuck + " mikros");
        t0 = System.nanoTime();
        for (int i = 0; i < tries; i++) 
            zapbnch.linear(code22); 
         t1 = System.nanoTime();
        double zaaplint = (t1 - t0);
        System.out.println("Zap lin, 100 searches of 984 99: " + zaaplint/fuck + " mikros");
        
        t0 = System.nanoTime();
        for (int i = 0; i < tries; i++) 
            zapbnch.binary(code2); 
        t1 = System.nanoTime();
        double zapbint = (t1 - t0);

        System.out.println("Zap bin, 100 searches of 111 15: " + zapbint/fuck + " mikros");

        t0 = System.nanoTime();
        for (int i = 0; i < tries; i++) 
            zapbnch.binary(code22); 
        t1 = System.nanoTime();
        double zaapbint = (t1 - t0);

        System.out.println("Zap bin, 100 searches of 948 99: " + zaapbint/fuck + " mikros");

        t0 = System.nanoTime();
        for (int i = 0; i < tries; i++) 
            zopbnch.lookup(code2); 
        t1 = System.nanoTime();
        double zopbint = (t1 - t0);

        System.out.println("Zop lookup, 100 searches of 111 15: " + zopbint/fuck + " mikros");
        
        t0 = System.nanoTime();
        for (int i = 0; i < tries; i++) 
            zopbnch.lookup(code22); 
        t1 = System.nanoTime();
        double zoopbint = (t1 - t0);

        System.out.println("Zop lookup, 100 searches of 984 99: " + zoopbint/fuck + " mikros");





        
        t0 = System.nanoTime();
        for (int i = 0; i < tries; i++) 
            zopbnch.lookupx(code1); 
        t1 = System.nanoTime();
         zopbint = (t1 - t0);

        System.out.println("Zop lookup, 100 searches of 111 15: " + zopbint/fuck + " mikros");
        
        t0 = System.nanoTime();
        for (int i = 0; i < tries; i++) 
            zopbnch.lookupx(code12); 
        t1 = System.nanoTime();
         zoopbint = (t1 - t0);

        System.out.println("Zop lookup, 100 searches of 984 99: " + zoopbint/fuck + " mikros");

    }
}
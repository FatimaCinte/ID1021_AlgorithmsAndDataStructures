import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Hash {
    static Node[] data;
    static Integer[] keys;

    static int maximum;
    static int mod = 3;


    static int count = 0;
    public class Node {
        Integer code;
        String name; 
        Integer population;

        Node[] bucket;

        public Node(Integer c, String n, Integer p){
            code = c;
            name = n;
            population = p;
            bucket = null;
        }

        public void bucketInsert(Node key){
            if(bucket == null)
                bucket = new Node[1];

            if(bucket[bucket.length - 1] != null){
                Node[] newbucket = new Node[bucket.length*2];
                for(int j = 0; j < bucket.length; j++)
                    newbucket[j] = bucket[j];
                
                bucket = newbucket;
            }
            for(int i = 0; i < bucket.length; i++){
                if(bucket[i] == null){
                    bucket[i] = key;
                    count++;
                    return;
                }
            }
        }

        public String bucketSearch(Integer zipcode){
            for (int i = 0; i < bucket.length; i++) {
                if (zipcode.equals(bucket[i].code)) {
                    return bucket[i].name;
                }
            }
            return null;
        } 
    }

        public void hashKeys(int mod){
            Node[] copydata = new Node[mod];
            for (int i = 0; i < maximum; i++) {
                int indx = hash(keys[i], mod);
                if(copydata[indx] == null)
                    copydata[indx] = data[keys[i]];
                else{
                   copydata[indx].bucketInsert(data[keys[i]]);
                }
            }
                data = copydata;

        }

        public String lookup(Integer zipcode, int mod){
            Integer indx = hash(zipcode, mod);
            while (true) {
                if (data[indx] == null) 
                    return null;
                if (zipcode.equals(data[indx].code)) 
                    return data[indx].name;
                else
                    return data[indx].bucketSearch(zipcode);
            }
        }

        
    public Hash(String file) {
        keys = new Integer[10000];
        data = new Node[100000];

        try (BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer key = Integer.valueOf(row[0].replaceAll("\\s",""));
                data[key] = new Node(key, row[1], Integer.valueOf(row[2]));
                keys[i++] = key;
            }
            maximum = i;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }        

    }

    static int R = 31;

    public static Integer hashString (String key , int M) {
        char [] chars = key.toCharArray ();
        int value = 0;

        for (int i = 0; i < chars.length; i ++) {
            value = (R * value + chars [i]) % M;
        }
        return value ;
    }

    public int hash(Integer key, int M){
        return Math.floorMod(key, M);
        //return key % M;
    }
    

    public void collisions(int mod) {
        int[] data = new int[mod];
        int[] cols = new int[10];

        for (int i = 0; i < maximum; i++) {
            Integer index = keys[i] % mod;
            cols[data[index]]++;
            data[index]++;
        }

        System.out.print("mod is: " + mod + ": ");
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + cols[i] + " ");
        }
        System.out.println();

    }

    public static void main(String[] args){
        Hash hash = new Hash("postnummer.csv");

        /*hash.hashKeys(mod);
        System.out.println(hash.lookup(12841, mod));*/

        /*int[] sizes = {10000, 13513, 13600, 14000, 12345, 10000,20000,40000,80000};//, 1000000, 500000, 250000, 125000};

        for (int n : sizes){
            hash.collisions(n);
                hash.hashKeys(mod);

                hash.collisions(n);

        }*/
        

        //for(int i = 0; i < max; i++)
        //    System.out.print(keys[i].code + ", ");

        //hash.collisions(mod);
        hash.hashKeys(mod);
        System.out.print(count);


    }

    


}

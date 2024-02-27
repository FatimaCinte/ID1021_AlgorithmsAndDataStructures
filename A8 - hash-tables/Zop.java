import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Zop implements lookups<Integer> {
    Node[] data;
    int max;

    int size = 12345;
    Integer[] keys = new Integer[size];

    public class Node {
        public Integer code;
        public String name;
        public Integer pop;

        private Node(Integer c, String n, Integer p){
            code = c;
            name = n;
            pop = p;
        }
    }

    public Zop(String file) {
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
            max = i-1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    
    public String lookup(Integer key){
        //Integer indx = Integer.valueOf(key.replaceAll("\\s",""));

        if(data[key] == null)
            return null;
        return data[key].name;
        
    }

        
    public String lookupx(String key){
        Integer indx = Integer.valueOf(key.replaceAll("\\s",""));

        if(data[indx] == null)
            return null;
        return data[indx].name;
        
    }
    
    public String linear(Integer zip){

        for(int i = 0; i < data.length; i++){
            if(zip == i)   //zip.equals(data[i].code)
                return data[i].name;    
        }
        return "not found in file";
    }

    public String binary(Integer zip){

        int min = 0;
        int max = data.length - 1;  

        while(min <= max){
            int mid = (max + min)/2;

            if(zip == mid)      
                return data[mid].name;
            else if(zip <= mid){
                max = mid - 1;
            }
            else{                           
                min = mid + 1;
            }
        }
        
        return "not found in file";
    }

    static int R = 31;

    public static Integer hashS (String key , int M) {
        char [] chars = key.toCharArray ();
        int value = 0;

        for (int i = 0; i < chars.length; i ++) {
            value = (R * value + chars [i]) % M;
        }
        return value ;
    }

    public int hash(Integer key, int M){
        return key % M;
    }
    

    public void collisions(int mod) {
        int[] data = new int[mod];
        int[] cols = new int[10];

        for (int i = 0; i < max; i++) {
            Integer index = keys[i] % mod;
            cols[data[index]]++;
            data[index]++;
        }

        System.out.print("mod is: " + mod + ": ");
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();

    }

    int n = 1;
    int[] buckets = new int[n];

    public void bucketadd(int key, int mod){
        int indx = hash(key,mod); //key 111 15 ---> indx = 11115/mod

        while(true){    //går igenom bucket arrayn
            if(buckets[buckets.length - 1] == 0){    //om det finns tom plats
                buckets[buckets.length - 1] = indx; //lägg till hash value av key
                break;
            }
            else{  //om buckets är full --> vi är i slutet 
                int[] newbucket = new int[n*2];//allokera en ny större array för att lägga till key
                for(int i = 0; i < buckets.length; i++)
                    newbucket[i] = buckets[i];
                
                newbucket[buckets.length] = indx;
                buckets = newbucket;
                n*=2;
                break;
            }
        }


        /*if(bucket[bucket.length - 1] != 0){
            int[] newbucket = new int[n*2];
            for(int i = 0; i < bucket.length; i++)
                newbucket[i] = bucket[i];
            
            bucket = newbucket;
            n*=2;
        }*/
        
    }

    
    public int lookuphash(Integer key, int mod){
        int indx = hash(key, mod);
        while(true){
            if(buckets == null)
                break;
            if(key == buckets[indx])
                return buckets[indx];

            indx = (indx+1) % mod;
        }
        return -1;
    }

    public static void main(String[] args){
        Zop zop = new Zop("postnummer.csv");

        int[] sizes = {12345, 10000,20000,40000,80000};//, 1000000, 500000, 250000, 125000};

        for (int n : sizes){
            zop.collisions(n);
        }
        
        
        /*for(int i = 0; i < 10000; i++)
            System.out.print(keys[i] + ", ");*/

        /*String b = bench.binary(11115);
        String l = bench.linear(12556);

        b.hashCode();

        System.out.print("binary: " + b + ", linear: " + l);*/

    }


}

import java.util.Iterator;

class Test{

    private static void print (Keylist<String,String> map, String[] keys){
        for (String k : keys){
            System.out.println("key " + k + " value " + map.lookup(k));
        }
    }

    private static void pront (Keylist<String,String> map){
        for (String v : map){
            System.out.println("found " + v);
        }
    }
    public static void main(String[] args){
        Keylist<String, String> map = new Keylist<>();

        map.add("åtta", "eight");
        map.add("sex", "six");
        map.add("fyra", "four");
        map.add("tre", "three");

        map.remove("tre");

        String[] keys = {"tre", "fyra", "sex", "åtta"};

        print(map, keys);
        


    }
}
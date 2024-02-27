import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Map {
        
    private City[] cities;
    private final int mod = 541;
    private int size;

    public Map(String file) {
        cities = new City[mod];
        size = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");

                City c1 = lookup(row[0]);
                City c2 = lookup(row[1]);

                Integer dst = Integer.valueOf(row[2]);

                c1.connect(c2, dst);
                c2.connect(c1, dst);

            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found or corrupt");
        }


    }

    private Integer hash(String name) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
        hash = (hash*31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }

    public City lookup(String name){
        Integer indx = hash(name);

        while(true){
            if(cities[indx] == null){
                size++;
                City city = new City(name);
                cities[indx] = city;
                //System.out.println(cities[indx].name + " ");

                return city;
            }

            if(cities[indx].name.equals(name)){
                //System.out.println(cities[indx].name + " ");

                return cities[indx];

            }

            indx = (indx + 1) % mod;
        }


    }


    private Integer size(){
        return size;
    }

    public static void main(String[] args){
        Map map = new Map("trains.csv");

        //collisions
        System.out.println("added:  " + map.size() + " cities");
        for(int i = 0; i < map.mod; i++){
            if(map.cities[i] != null){
                System.out.println(map.cities[i].name + " " + map.cities[i].neighbours.size());
            }
        }
        
    }
    
}
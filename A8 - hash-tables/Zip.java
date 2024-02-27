import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Zip implements lookups<String> {
    Node[] data;
    int max;

    public class Node {
        public String code;
        public String name;
        public Integer pop;

        private Node(String c, String n, Integer p){
            code = c;
            name = n;
            pop = p;
        }
    }

    public Zip(String file) {
        data = new Node[10000];
        
        try (BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                data[i++] = new Node(row[0], row[1], Integer.valueOf(row[2]));
            }
            max = i-1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }
    
    public String linear(String zip){

        for(int i = 0; i < data.length; i++){
            if(zip.equals(data[i].code))    //zip.equals(data[i].code)
                return (data[i]).name;    
        }
        return "not found in file";
    }

    public String binary(String zip){
        /*int min = 0;
        int max = this.max;

        while(true){
            int mid = (max + min)/2;

            int comparison = zip.compareTo(data[mid].code);

            if (comparison == 0) {
                return data[mid].name;
            } else if (comparison > 0 && mid < max) {
                min = mid + 1;
                continue;
            } 
            if(comparison < 0 && mid > min){
                max = mid - 1;
                continue;
            }
            break;
            
        }
        return null;*/


        int min = 0;
        int max = this.max;  

        while(min <= max){
            int mid = (max + min)/2;

            if(zip.compareTo(data[mid].code) == 0)      
                return data[mid].name;
            else if(zip.compareTo(data[mid].code) < 0){
                max = mid - 1;
            }
            else {                           
                min = mid + 1;
            }
        }
        
        return "not found in file";
    }



    public static void main(String[] args){


        Zip bench = new Zip("postnummer.csv");

        String b = bench.binary("125 56");
        String l = bench.linear("121 31");

        System.out.print("binary: " + b + ", linear: " + l);

    }


}

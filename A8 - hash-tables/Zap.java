import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Zap implements lookups<Integer> {
    Node[] data;
    int max;

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

    public Zap(String file) {
        data = new Node[10000];
        
        try (BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s",""));
                data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
            }
            max = i-1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }
    
    public String linear(Integer zip){

        for(int i = 0; i < data.length; i++){
            if(zip.equals(data[i].code))    //zip.equals(data[i].code)
                return (data[i]).name;    
        }
        return "not found in file";
    }

    public String binary(Integer zip){

        int min = 0;
        int max = this.max; 
         
        while(min <= max){
            int mid = (max + min)/2;

            if(zip.compareTo(data[mid].code) == 0)      
                return data[mid].name;
            else if(zip.compareTo(data[mid].code) < 0){
                max = mid - 1;
            }
            else{                           
                min = mid + 1;
            }
        }
        
        return "not found in file";
    }

    public static void main(String[] args){


        Zap zap = new Zap("postnummer.csv");
        //bench.codekeys();


        /*String b = bench.binary(12556);
        String l = bench.linear(11115);

        System.out.print("binary: " + b + ", linear: " + l);*/

    }


}

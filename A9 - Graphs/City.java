import java.util.ArrayList;

public class City{
    String name;
    //Connection[] neighbours;    
    public ArrayList<Connection> neighbours;


    public City(String n){ 
        name = n;
        neighbours = new ArrayList<Connection>();
    }

    public void connect(City nxt, int dst){
        neighbours.add(new Connection(nxt, dst));
        
        /*if(neighbours == null){
            neighbours = new Connection[4];
        }
        
        if(neighbours[neighbours.length - 1] != null){
            Connection[] copy = new Connection[neighbours.length*2];
            for(int j = 0; j < neighbours.length; j++)
                copy[j] = neighbours[j];
                
            neighbours = copy;
        }

        for(int i = 0; i < neighbours.length; i++){
            if(neighbours[i] == null)
                neighbours[i] = new Connection(nxt, dst);
            }*/
    }



    
}
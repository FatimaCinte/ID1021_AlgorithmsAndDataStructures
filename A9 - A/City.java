import java.util.ArrayList;

public class City{
    public String name;
    public Integer id;
    public ArrayList<Connection> neighbours;

    public City(String n, Integer i){ 
        name = n;
        id = i;
        neighbours = new ArrayList<Connection>();
    }

    public void connect(City nxt, int dst){
        neighbours.add(new Connection(nxt, dst));
    }



    
}
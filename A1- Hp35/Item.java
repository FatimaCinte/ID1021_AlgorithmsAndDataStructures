

public class Item { //objekt, varje objekt har två element, en type ocjh en value
    
    private  ItemType type;
    private int value = 0;

    public Item(ItemType type, int value) {    
        this.type = type;   
        this.value = value;        
    }

    //metoder som bildar olika items för att sedan konstruera expressions
    public static Item Add(){
        return new Item(ItemType.ADD, 0);
    }

    public static Item Sub(){
        return new Item(ItemType.SUB, 0);
    }

    public static Item Mul(){
        return new Item(ItemType.MUL, 0);
    }
    public static Item Div(){
        return new Item(ItemType.DIV, 0);
    }

    public static Item Value(int val){
        
        return new Item(ItemType.VALUE, val);
    }

    public ItemType type(){
        
        return this.type;

    }

    public int getvalue(){
        return this.value;
    }


}

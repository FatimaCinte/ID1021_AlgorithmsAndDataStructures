
class Uppgift1Hp35 {
    public static void main (String[] args) {

        // 10 + 2 * 5
        // 10 2 5 * + in reversed Polish notation
        /* Item[] expr = {
            Item.Value(10),
            Item.Value(2),
            Item.Value(5),
            Item.Mul(),
            Item.Add()
        };*/

        //(3 × 4) + (5 × 6) ,,,,, 3 4 × 5 6 × + 
       /* Item[] expr = {
            Item.Value(3),
            Item.Value(4),
            Item.Mul(),
            Item.Value(5),
            Item.Value(6),
            Item.Mul(),
            Item.Add()

        };*/

        // (2 × 2) + (3 × 6) ,,,,, 2 2 × 3 6 × +

        //test
           Item[] expr = {
            Item.Value(2),
            Item.Value(2),
            Item.Mul(),
            Item.Value(3),
            Item.Value(6),
            Item.Mul(),
            Item.Add(),

            Item.Value(1),
            Item.Value(2),
            Item.Mul(),
            Item.Add(),
            Item.Value(1),
            Item.Value(2),
            Item.Mul(),
            Item.Add(),
            Item.Value(1),
            Item.Value(2),
            Item.Mul(),
            Item.Add(),
            Item.Value(1),
            Item.Value(2),
            Item.Mul(),
            Item.Add(),

        };

        Calculator calc = new Calculator(expr);
        int res = calc.run();
        System.out.println("Calculator: res = " + res);


    } 
}

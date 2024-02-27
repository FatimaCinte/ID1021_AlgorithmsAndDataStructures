
class Linear{

    public static boolean search(int[] array, int key){
        for (int index = 0; index < array.length ; index++) {
            if (array[index] == key) {
                return true;
            }
            if(array[index] > key)
                break;
        }
            return false;
    }

    public static boolean search_unsorted(int[] array, int key) {
        
        for (int index = 0; index < array.length ; index++) {
            if (array[index] == key) {
                return true;
            }
        }
            return false;
    }


    public static int unsorted_dup(int[] arrayOne, int[] arrayTwo) {

        int sum = 0; 
        for (int i = 0; i < arrayOne.length; i++) {
            for (int j = 0; j < arrayOne.length; j++) { 
                if (arrayOne[i] == arrayTwo[j]){
                    sum++;
                    break;    
                }
            }
        }
            return sum;
    }

    public static int twopointer_dup(int[] arrayOne, int[] arrayTwo) {

        int sum = 0; 

        int counter = 0;
        int i = 0;
        while(i < arrayOne.length){
            if (arrayOne[counter] == arrayTwo[i]){
                counter++;
                i++;
                sum++;
                continue;
                }
            else if(arrayTwo[i] > arrayOne[counter]){
                counter++;
                i++;
            }
            else{      
                i++;
            }
            
        }
            return sum;
    }
}
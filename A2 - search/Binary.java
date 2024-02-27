
class Binary{

    public static boolean search(int[] array, int key){
            
        int first = 0;
        int last = array.length - 1;
                    
        while(true){
            int index = (first + last)/2; //mitten
            
            if (array[index] == key){ //if the element is found in the middle index
                return true;
            }
            if (array[index] < key && index < last){ //if the element is larger than the element in the middle
                first = index + 1;
                continue;
            }
            if (array[index] > key && index < last){  //
                last = index - 1;
                continue;
            }
            return false;
        }

    }

  
    public static int binary_dup(int[] arrayOne, int[] arrayTwo) {

        int sum = 0;
        for(int i = 0; i < arrayOne.length; i++){
            if(search(arrayTwo, arrayOne[i])){
                sum++;
                }
        }

        return sum;
}

}

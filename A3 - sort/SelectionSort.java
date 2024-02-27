import java.util.Arrays;

class SelectionSort{

    private static void swap(int[] array, int i, int c){
        int temp = array[i];
        array[i] = array[c];
        array[c] = temp;
    }

    public static void sort(int[] array){
        for (int i = 0; i < array.length -1; i++) {
            //element at candidate index is considered the min value
            int candidate = i; 
            for (int j = i; j < array.length ; j++) {   //iterate rest of the array
                //if element at index candidate is larger than element at the next index j
                if(array[candidate] > array[j]) 
                    candidate = j;  //update the candidate index
                }
            swap(array, i, candidate); //swap elements at indices i and candidate
                            System.out.println(Arrays.toString(array));

        }
    }
}
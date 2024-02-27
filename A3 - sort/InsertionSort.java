import java.util.Arrays;

class InsertionSort{

    private static void swap(int[] array, int i, int c){
        int temp = array[i];
        array[i] = array[c];
        array[c] = temp;
    }

    public static void sort(int[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = i; 0 < j; j--) { //         for (int j = i; (0 < j) && (array[j-1] > array[j]); j--) {

                if(array[j-1] < array[j])
                    break;
            
                swap(array, j-1, j);
                System.out.println(Arrays.toString(array));

            }
        }
    }
    
}
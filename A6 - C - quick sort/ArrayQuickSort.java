class ArrayQuickSort{
    
    public static void sort(int[] arr, int min, int max){

        if(min < max){
            int mid = partition(arr, min, max);
            sort(arr, min, mid-1);
            sort(arr, mid+1, max);
        }
        else
            return;


    }

    public static void swap(int[] array, int i, int c){
        int temp = array[i];
        array[i] = array[c];
        array[c] = temp;
    }
    
    public static int partition(int[] arr, int min, int max){
        int i = min;
        int j = max;
        int pivot = arr[min];

        while(i < j){
            while(arr[j] > pivot)
                j--;
            while(arr[i] <= pivot && i < j)
                i++;    
            
            if(i<j)
                swap(arr, i, j);
            
        }
        swap(arr, min, j);

        return j;
    }


}
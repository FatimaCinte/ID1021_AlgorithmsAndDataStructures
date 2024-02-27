public class ArrayList {

    public static int[] makeArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static int[] appendArray(int[] arrA, int[] arrB){
        int[] newArr = new int[arrA.length + arrB.length];

        for(int i = 0; i < arrA.length; i++)
            newArr[i] = arrA[i];
        
        for(int i = 0; i < arrB.length; i++)
            newArr[i + arrA.length] = arrB[i];
        
        return newArr;
    }
    
    
}

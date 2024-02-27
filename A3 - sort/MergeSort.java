class MergeSort{

    private static void sort(int[] org, int[] aux, int lo, int hi) {
        if (lo != hi) {
            int mid = (lo + hi)/2;
            // sort the items from lo to mid
            sort(org, aux, lo, mid);
            // sort(aux, org, mid+1, hi);

            // sort the items from mid+1 to hi
            sort(org, aux, mid+1, hi);
            // sort(aux, org, mid+1, hi);

            // merge the two sections using the additional array
            merge(org, aux, lo, mid, hi);
    }
}

    public static void sort(int[] org) {
        if (org.length == 0)
        return;
        int[] aux = new int[org.length];
        sort(org, aux, 0, org.length -1);

        /*if (org.length == 0)
        return;
        int[] aux = new int[org.length];
        for (int i = lo; i <= hi; i++) 
            aux[i] = org[i];
        sort(org, aux, 0, org.length -1);*/
    }

    private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {
        // copy all items from lo to hi from org to aux
        for (int i = lo; i <= hi; i++) 
            aux[i] = org[i];
        
        // let's do the merging
        int i = lo; // the index in the first part
        int j = mid+1; // the index in the second part

        // for all indices from lo to hi
        for (int k = lo; k <= hi; k++) {
            if(i > mid)
                org[k] = aux[j++];
            else if(j > hi)
                org[k] = aux[i++];
            else if(aux[i] < aux[j])
                org[k] = aux[i++];
            else
                org[k] = aux[j++];
        }
    }
}
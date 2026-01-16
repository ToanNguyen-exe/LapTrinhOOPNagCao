package selecLogic;

public class SelectionSort {
    static double[] arr={1,9,4.5,6.6,5.7,-4.5};

    public static void selectionSort(double[] arr) {
        for (int i = 0;i<arr.length-1;i++){
            double currentMin = arr[i];
            int currentMinIndex = i;
            for (int j =i+1;j<arr.length;j++){
                if(currentMin>arr[j]){
                    currentMin = arr[j];
                    currentMinIndex = j;
                }
            }
            if (currentMinIndex !=i){
                arr[currentMinIndex]=arr[i];
                arr[i]=currentMin;
            }
        }

    }

    public static void main(String[] args) {
        selectionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

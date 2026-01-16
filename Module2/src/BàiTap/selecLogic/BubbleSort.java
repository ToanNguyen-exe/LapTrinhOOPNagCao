package selecLogic;

public class BubbleSort {
    static int[] arr = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};

    public static void bubbleSort(int[] arr) {
        boolean needNextPass = true;
        for (int k = 1; k < arr.length && needNextPass; k++) {
            needNextPass = false;
            for (int i = 0; i < arr.length - k; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    needNextPass = true;
                }
            }
        }

    }

    public static void main(String[] args) {
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

package selecLogic;

import java.util.Scanner;

public class BubbleSortByStep {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("nhập số lượng phần tử của mảng: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];
        System.out.print("nhập " + arr.length + " phần tử cho mảng: ");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.print("phần tử trong mảng là: ");
        for (int value : arr) {
            System.out.print(value + "\t");
        }
        System.out.println();

        bubbleSortByStep(arr);
    }

    public static void bubbleSortByStep(int[] arr) {
        boolean needNextPass = true;

        for (int k = 1; k < arr.length && needNextPass; k++) {
            needNextPass = false;

            for (int i = 0; i < arr.length - k; i++) {
                if (arr[i] > arr[i + 1]) {
                    System.out.println("đổi chỗ " + arr[i] + " với " + arr[i + 1]);
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;

                    needNextPass = true;
                }
            }

            if (!needNextPass) {
                System.out.println("mảng đã được sắp xếp - không cần pass tiếp theo");
                break;
            }
            System.out.print("mảng sau pass thứ " + k + ": ");
            for (int j = 0; j < arr.length; j++) {
                System.out.print(arr[j] + "\t");
            }
            System.out.println();
        }
    }
}

package MangTrongJava;

import java.util.Scanner;

public class DeleteElementInArr {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Nhập vị trí phần tử cần xóa (1-" + (arr.length -2) + "): ");
        int index = scanner.nextInt();
        System.out.println("Mảng ban đầu:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        for (int i = index - 1; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = 0;
        System.out.println("Mảng sau khi xóa phần tử tại vị trí " + index + ":");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}

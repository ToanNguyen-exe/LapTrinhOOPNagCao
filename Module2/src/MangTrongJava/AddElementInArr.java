package MangTrongJava;

import java.util.Scanner;

public class AddElementInArr {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = {1, 2, 3, 4, 5};
        System.out.print("Số cần chèn: ");
        int element = sc.nextInt();
        System.out.print("Vui lòng viết vị trí cần chèn: ");
        int position = sc.nextInt();

        if (position < 1 || position > arr.length + 1) {
            System.out.println("Not add element in array");
        } else {
            int newArr[] = new int[arr.length + 1];
            for (int i = 0; i < newArr.length; i++) {
                if (i < position - 1) {
                    newArr[i] = arr[i];
                } else if (i == position - 1) {
                    newArr[i] = element;
                } else {
                    newArr[i] = arr[i - 1];
                }
            }

            System.out.println("New array is: ");
            for (int i = 0; i < newArr.length; i++) {
                System.out.print(newArr[i] + "\t");
            }
        }
    }
}

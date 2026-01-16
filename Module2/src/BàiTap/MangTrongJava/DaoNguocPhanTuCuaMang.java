package MangTrongJava;

import java.util.Scanner;

public class DaoNguocPhanTuCuaMang {
    public static void main(String[] args) {
        int size;
        int[] array;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Nhập kích thước mảng (không quá 20): ");
            size = sc.nextInt();
            if (size > 20)
                System.out.println("Độ dài của mảng đã vượt quá 20");
        } while (size > 20);
        array = new int[size];
        int i =0;
        while (i<array.length){
            System.out.println("enter element" +(i+1)+": ");
            array[i]= sc.nextInt();
            i++;
            System.out.printf("%-20%s ", "Elements is array: ","");
            for (int j =0; j<array.length;j++){
                System.out.printf(array[j]+"\t");
            }
        }
    }
}

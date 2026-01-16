package MangTrongJava;

import java.util.Scanner;

public class TimGiaTriLonNhatTrongMang {
    public static void main(String[] args) {
        int size;
        int[]array;
        Scanner sc =new Scanner(System.in);
        do {
            System.out.print("viết ra 1 độ lớn: ");
            size = sc.nextInt();
            if (size < 20)
                System.out.println("độ lớn không được quá 20");
        }while (size >20);
        array = new int[size];
        int i=0;
        while (i<array.length){
            System.out.print("Enter element"+(i+1)+":");
            array[i]= sc.nextInt();
            i++;
        }
        System.out.print("property list: ");
        for (int j= 0; j< array.length;j++){
            System.out.print(array[j]+"\t");
        }
        int max = array[0];
        int index = 1;
        for (int j =0; j<array.length; j++){
            if (array[j]>max){
                max = array[j];
                index=j+1;
            }
    }
        System.out.println("The largest property value in the list is " + max + " ,at position " + index);
}}

package MangTrongJava;

import java.util.Scanner;

public class TimGiaTriTrongMang {
    public static void main(String[] args) {
        String[] students = {"Christian", "Michael", "Camila", "Sienna", "Tanya", "Connor", "Zachariah", "Mallory", "Zoe", "Emily"};
        Scanner sc = new Scanner(System.in);
        System.out.print("Viết tên sinh viên: ");
        String input_name = sc.nextLine();

        boolean isExist = false;
        for (int i = 0; i < students.length; i++) {
            if (students[i].equals(input_name)) {
                System.out.println("Sinh viên có tên:  " + input_name + " thuộc vị trí : " + (i+1));
                isExist = true;
                break;
            }
        }
    if (!isExist){
        System.out.println("not found"+input_name+"in the list");
    }
    }
}

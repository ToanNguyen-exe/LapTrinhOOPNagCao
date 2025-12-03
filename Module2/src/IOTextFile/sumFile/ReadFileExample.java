package IOTextFile.sumFile;

import java.util.Scanner;

public class ReadFileExample {
    public static void main(String[] args) {
        System.out.println("Nhập đường dẫn file: ");
        Scanner sc=new Scanner(System.in);
        String path=sc.nextLine();
        ReadFileExample readFileEx =new ReadFileExample();
        readFileEx.ReadFileText(path);
    }

    private void ReadFileText(String path) {

    }

}

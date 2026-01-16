package seachLogic;

import java.util.LinkedList;
import java.util.Scanner;

public class FindTheLongestIncreasingSubsequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập vào chuỗi bất kỳ: ");
        String input = sc.nextLine();

        LinkedList<Character> max = new LinkedList<>();

        for (int i = 0; i < input.length(); i++) {
            LinkedList<Character> list = new LinkedList<>();
            list.add(input.charAt(i));

            for (int j = i + 1; j < input.length(); j++) {
                if (input.charAt(j) > list.getLast()) {
                    list.add(input.charAt(j));
                }
            }

            if (list.size() > max.size()) {
                max.clear();
                max.addAll(list);
            }
        }

        System.out.print("Chuỗi tăng dần lớn nhất: ");
        for (Character ch : max) {
            System.out.print(ch);
        }
        System.out.println();
    }
}

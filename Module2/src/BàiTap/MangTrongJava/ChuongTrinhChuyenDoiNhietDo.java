package MangTrongJava;

import java.util.Scanner;

public class ChuongTrinhChuyenDoiNhietDo {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double fahrenheit;
        double celsius;
        int choice;
        do {
            System.out.println("menu.");
            System.out.println("1. chuyển độ f thành độ C");
            System.out.println("2. Chuyển độ c thành độ F");
            System.out.println("0.Exit");
            System.out.println("viết ra lựa chọn của bạn:  ");
            choice= input.nextInt();
            switch (choice){
                case 1:{
                    System.out.println("Viết ra độ F: ");
                    fahrenheit =input.nextDouble();
                    System.out.println("độ F qua độ C: "+ fahrenheitToCelsius(fahrenheit));
                }
                break;
                case 2: {
                    System.out.println("viết ra độ C:");
                    celsius = input.nextDouble();
                    System.out.println("độ C qua độ F:"+ celsiusToFahrenheit(celsius));
                }
                    break;
                case 0:
                    System.exit(0);
            }
        }while (choice !=0);
    }
    public static double celsiusToFahrenheit(double celsius){
        double fahrenheit =(9.0/5)*celsius+32;
        return fahrenheit;

    }
    public static double fahrenheitToCelsius(double fahrenheit){
        double celsius=(5.0/9)*(fahrenheit-32);
        return celsius;
    }

}


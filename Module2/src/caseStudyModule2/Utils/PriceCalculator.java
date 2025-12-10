package caseStudyModule2.Utils;

import java.util.ArrayList;

public class PriceCalculator {
    public static int calculateTotal(ArrayList<Integer> prices) {
        int total = 0;
        for (int price : prices) {
            total += price;
        }
        return total;
    }

    public static String formatPrice(int price) {
        return String.format("%,d VNĐ", price);
    }
}

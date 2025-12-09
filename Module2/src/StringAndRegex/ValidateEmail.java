package StringAndRegex;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidateEmail {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String PHONE_REGEX = "^(03|05|07|08|09)\\d{8}$";
    private static final String CLASS_REGEX = "^[CAP]\\d{4}[GHIKLM]$";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("===== STRING & REGEX CONSOLE APPLICATION =====");
            System.out.println("1. Kiểm tra Email");
            System.out.println("2. Kiểm tra Số điện thoại Việt Nam");
            System.out.println("3. Chuẩn hoá Họ tên");
            System.out.println("4. Kiểm tra Mã lớp học");
            System.out.println("5. Tách tiêu đề bài hát từ HTML");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    checkEmail(sc);
                    break;
                case 2:
                    checkPhone(sc);
                    break;
                case 3:
                    normalizeName(sc);
                    break;
                case 4:
                    checkClassCode(sc);
                    break;
                case 5:
                    extractSong(sc);
                    break;
                case 0:
                    System.out.println("Thoát chương trình...");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }

    private static void checkEmail(Scanner sc) {
        System.out.print("Nhập email: ");
        String email = sc.nextLine();

        if (Pattern.matches(EMAIL_REGEX, email)) {
            System.out.println("✔ Email hợp lệ!");
        } else {
            System.out.println("✘ Email không hợp lệ!");
        }
    }

    private static void checkPhone(Scanner sc) {
        System.out.print("Nhập số điện thoại: ");
        String phone = sc.nextLine();

        if (Pattern.matches(PHONE_REGEX, phone)) {
            System.out.println("✔ Số điện thoại hợp lệ!");
        } else {
            System.out.println("✘ Số điện thoại không hợp lệ!");
        }
    }

    private static void normalizeName(Scanner sc) {
        System.out.print("Nhập họ tên: ");
        String name = sc.nextLine().trim().toLowerCase();

        // Chuẩn hóa
        String[] parts = name.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (String p : parts) {
            sb.append(Character.toUpperCase(p.charAt(0)))
                    .append(p.substring(1))
                    .append(" ");
        }

        System.out.println("✔ Tên chuẩn hóa: " + sb.toString().trim());
    }

    private static void checkClassCode(Scanner sc) {
        System.out.print("Nhập mã lớp: ");
        String code = sc.nextLine();

        if (Pattern.matches(CLASS_REGEX, code)) {
            System.out.println("✔ Mã lớp hợp lệ!");
        } else {
            System.out.println("✘ Mã lớp không hợp lệ!");
        }
    }

    private static void extractSong(Scanner sc) {
        System.out.println("Nhập HTML có thẻ <a>: ");
        String html = sc.nextLine();

        String regex = "<a[^>]*>(.*?)</a>";
        var matcher = Pattern.compile(regex).matcher(html);

        while (matcher.find()) {
            System.out.println("✔ Tiêu đề bài hát: " + matcher.group(1));
        }
    }

}

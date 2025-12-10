package caseStudyModule2.Admin;

import caseStudyModule2.Utils.InputHandler;

public class AdminAuth {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final int MAX_ATTEMPTS = 3;

    private InputHandler inputHandler;

    public AdminAuth(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public boolean login() {
        System.out.println("\n===== ĐĂNG NHẬP ADMIN =====");
        System.out.println("(Username: admin, Password: admin123)");

        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Username: ");
            String username = inputHandler.getStringInput();

            System.out.print("Password: ");
            String password = inputHandler.getStringInput();

            if (authenticate(username, password)) {
                System.out.println("\n✓ Đăng nhập thành công!\n");
                return true;
            } else {
                attempts++;
                int remaining = MAX_ATTEMPTS - attempts;

                if (remaining > 0) {
                    System.out.println("✗ Sai username hoặc password! Còn " + remaining + " lần thử.\n");
                } else {
                    System.out.println("✗ Đã hết số lần thử. Quay về menu chính.\n");
                }
            }
        }

        return false;
    }

    private boolean authenticate(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
}

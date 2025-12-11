package caseStudyModule2.services;

import java.io.*;

public class FileManager {

    public static void saveRoom(String roomKey, boolean[][] seats) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("room_" + roomKey + ".dat"));
            oos.writeObject(seats);
            oos.close();
        } catch (Exception var3) {
            System.out.println("Lỗi lưu file phòng!");
        }

    }

    public static boolean[][] loadRoom(String roomKey) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("room_" + roomKey + ".dat"));
            boolean[][] seats = (boolean[][])ois.readObject();
            ois.close();
            return seats;
        } catch (Exception var3) {
            return null;
        }
    }

    public static void deleteRoom(String roomKey) {
        try {
            File file = new File("room_" + roomKey + ".dat");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception var2) {
            System.out.println("Lỗi xóa file phòng!");
        }

    }

}

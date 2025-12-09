package caseStudyModule2;

import java.io.*;

public class FileManager {

    public static void saveRoom(String roomKey, boolean[][] seats) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("room_" + roomKey + ".dat")
            );
            oos.writeObject(seats);
            oos.close();
        } catch (Exception e) {
            System.out.println("Lỗi lưu file phòng!");
        }
    }

    public static boolean[][] loadRoom(String roomKey) {
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream("room_" + roomKey + ".dat")
            );
            boolean[][] seats = (boolean[][]) ois.readObject();
            ois.close();
            return seats;
        } catch (Exception e) {
            return null;
        }
    }
}

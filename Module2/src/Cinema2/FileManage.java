package Cinema2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManage {
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

    public static void deleteRoom(String roomKey) {
        try {
            java.io.File file = new java.io.File("room_" + roomKey + ".dat");
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            System.out.println("Lỗi xóa file phòng!");
        }
    }

}

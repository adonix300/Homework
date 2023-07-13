package Saving;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        String savegamesPath = "G:/Games/savegames";
        String zipFilePath = "G:/Games/savegames/zip.zip";

        GameProgress gameProgress1 = new GameProgress(42, 10, 10, 12321);
        GameProgress gameProgress2 = new GameProgress(87, 120, 640, 76521421);
        GameProgress gameProgress3 = new GameProgress(74, 75, 120, 674523);

        saveGame(savegamesPath + "/save1.dat", gameProgress1);
        saveGame(savegamesPath + "/save2.dat", gameProgress2);
        saveGame(savegamesPath + "/save3.dat", gameProgress3);

        List<String> filesToZip = List.of(
                savegamesPath + "/save1.dat",
                savegamesPath + "/save2.dat",
                savegamesPath + "/save3.dat"
        );

        zipFiles(zipFilePath, filesToZip);
        deleteFiles(zipFilePath,filesToZip);
    }

    public static void saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void zipFiles(String path, List<String> filePaths) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path));) {

            for (String filePath : filePaths) {
                File file = new File(filePath);

                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());
                    zout.putNextEntry(zipEntry);

                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    zout.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFiles (String path, List<String> filePaths) {
        for (String filePath : filePaths) {
            File file = new File(filePath);
            file.delete();
        }
    }
}

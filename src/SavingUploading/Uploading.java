package SavingUploading;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Uploading {
    public static void main(String[] args) {
        openZip("G:/Games/savegames/zip.zip","G:/Games/savegames");
        openProgress("G:/Games/savegames/save1.dat");
    }
    public static void openZip(String path, String folder) {
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(path))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = folder + "/" + entry.getName();
                FileOutputStream fout = new FileOutputStream(name);
                for (int i = zin.read(); i != -1 ; i = zin.read()) {
                    fout.write(i);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void openProgress(String path) {

        try (FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            GameProgress gameProgress = (GameProgress) ois.readObject();
            System.out.println(gameProgress);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}

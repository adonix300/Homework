package Installation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        StringBuilder logs = new StringBuilder();

        String path = "G:/Games";
        createDirectories(path, logs);
        createSrcDirectories(path, logs);
        createSrcMainFiles(path, logs);
        createResDirectories(path, logs);
        createTempFile(path, logs);

        writeLogsToFile(path, logs);
    }

    private static void createDirectories(String path, StringBuilder logs) {
        String[] dirs = {"src", "res", "savegames", "temp"};
        for (String directory : dirs) {
            File file = new File(path + "/" + directory);
            if (file.mkdir()) {
                logs.append(file.getAbsoluteFile()).append(" created\n");
            } else {
                logs.append(file.getAbsoluteFile()).append(" cannot be created\n");
            }
        }
    }

    private static void createSrcDirectories(String path, StringBuilder logs) {
        String srcPath = path + "/src";
        String[] srcDirs = {"main", "test"};

        for (String directory : srcDirs) {
            File file = new File(srcPath + "/" + directory);
            if (file.mkdir()) {
                logs.append(file.getAbsoluteFile()).append(" created\n");
            } else {
                logs.append(file.getAbsoluteFile()).append(" cannot be created\n");
            }
        }
    }

    private static void createSrcMainFiles(String path, StringBuilder logs) {
        String srcMainPath = path + "/src/main";
        String[] srcMainFileNames = {"Main", "Util"};

        for (String name : srcMainFileNames) {
            File file = new File(srcMainPath + "/" + name + ".java");
            try {
                if (file.createNewFile()) {
                    logs.append(file.getAbsoluteFile()).append(" created\n");
                } else {
                    logs.append(file.getAbsoluteFile()).append(" cannot be created\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void createResDirectories(String path, StringBuilder logs) {
        String resPath = path + "/res";
        String[] resDirs = {"drawables", "vectors", "icons"};

        for (String directory : resDirs) {
            File file = new File(resPath + "/" + directory);
            if (file.mkdir()) {
                logs.append(file.getAbsoluteFile()).append(" created\n");
            } else {
                logs.append(file.getAbsoluteFile()).append(" cannot be created\n");
            }
        }
    }

    private static void createTempFile(String path, StringBuilder logs) {
        String tempPath = path + "/temp";
        try {
            File file = new File(tempPath + "/temp.txt");
            if (file.createNewFile()) {
                logs.append(file.getAbsoluteFile()).append(" created\n");
            } else {
                logs.append(file.getAbsoluteFile()).append(" cannot be created\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeLogsToFile(String path, StringBuilder logs) {
        String tempPath = path + "/temp";
        try (FileWriter fileWriter = new FileWriter(tempPath + "/temp.txt")) {
            fileWriter.write(logs.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.ll.wisesaying.global.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileUtil {

    public static Path fullDirPath;

    public static boolean checkFileDirExist(String fileDir) {

        boolean isExist = false;
        Path path = Paths.get(fileDir);

        if (Files.exists(path) && Files.isDirectory(path)) {
            fullDirPath = path;
            isExist = true;
        }

        return !isExist;
    }

    public static void createFileDir(String fileDir) {

        File dir = new File(fileDir);
        if (!dir.exists()) {
            dir.mkdirs();
            fullDirPath = Paths.get(fileDir);
        }
    }

    public static void writeStringInFile(String fileName, String data) {

        try (FileOutputStream output = new FileOutputStream(fullDirPath.toAbsolutePath() + "/" + fileName)) {

            DataOutputStream dataOutput = new DataOutputStream(output);
            dataOutput.writeUTF(data);
            dataOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeIntInFile(String fileName, int data) {

        try (FileOutputStream output = new FileOutputStream(fullDirPath.toAbsolutePath() + "/" + fileName)) {

            DataOutputStream dataOutput = new DataOutputStream(output);
            dataOutput.writeInt(data);
            dataOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Optional<String> getStringInFile(String fileName) {

        StringBuilder data = new StringBuilder();

        try (FileInputStream input = new FileInputStream(fullDirPath.toAbsolutePath() + "/" + fileName)) {

            DataInputStream dataInput = new DataInputStream(input);
            data.append(dataInput.readUTF());
            dataInput.close();

        } catch (FileNotFoundException e) {
            // 파일이 존재하지 않을 경우, skip
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(data)
                .filter(d -> !d.isEmpty())
                .map(Object::toString);
    }

    public static int getIntInFile(String fileName) {

        int data = 0;

        try (FileInputStream input = new FileInputStream(fullDirPath.toAbsolutePath() + "/" + fileName)) {

            DataInputStream dataInput = new DataInputStream(input);
            data = dataInput.readInt();
            dataInput.close();

        } catch (FileNotFoundException e) {
            // 파일이 존재하지 않을 경우, skip
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void deleteFile(String fileName) {

        Path filePath = Paths.get(fullDirPath.toAbsolutePath() + "/" + fileName);

        try {
            Files.delete(filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteDir(Path path) throws IOException {

        if (Files.isDirectory(path)) {

            Files.list(path).forEach(nextPath -> {

                try {
                    deleteDir(nextPath);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        Files.delete(path);
    }
}

package com.ll.wisesaying.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileUtil {

    private final String fileDir;
    private String fileName;

    public FileUtil(String fileDir) {
        this.fileDir = setDefaultDirPath(fileDir);
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void writeStringInFile(String data) {

        try (FileOutputStream output = new FileOutputStream(fileDir + fileName)) {

            DataOutputStream dataOutput = new DataOutputStream(output);
            dataOutput.writeUTF(data);
            dataOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeIntInFile(int data) {

        try (FileOutputStream output = new FileOutputStream(fileDir + fileName)) {

            DataOutputStream dataOutput = new DataOutputStream(output);
            dataOutput.writeInt(data);
            dataOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Optional<String> getStringInFile() {

        StringBuilder data = new StringBuilder();

        try (FileInputStream input = new FileInputStream(fileDir + fileName)) {

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

    public int getIntInFile() {

        int data = 0;

        try (FileInputStream input = new FileInputStream(fileDir + fileName)) {

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

    public void deleteFile() {

        Path filePath = Paths.get(fileDir + fileName);

        try {
            Files.delete(filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String setDefaultDirPath(String fileDir) {

        Path defaultDir = Paths.get("src/main/resources/" + fileDir)
                .toAbsolutePath();

        File dir = new File(defaultDir.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        return defaultDir + "/";
    }
}

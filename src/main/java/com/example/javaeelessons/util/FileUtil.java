package com.example.javaeelessons.util;

import java.io.*;
import java.util.Arrays;
import java.util.Optional;

public class FileUtil {
    private static final String BASE_DIR = "/Users/karendarbinyan/Downloads/JavaEELessons/files";

    public static boolean fileExists(String url) {
        File dir = new File(BASE_DIR);
        if (dir.exists() && dir.isDirectory()) {
            String[] fileList = dir.list();
            if (fileList != null) {
                return Arrays.asList(fileList).contains(url);
            }
        }
        return false;
    }

    private static File getFile(String fileId) {

        File dir = new File(BASE_DIR);
        if (dir.exists() && dir.isDirectory()) {
            Optional<File> any = Arrays.stream(dir.listFiles()).filter(item -> item.getName().equals(fileId)).findAny();
            if (any.isPresent()) {
                return any.get();
            }

        }
        return new File(BASE_DIR + File.separator + fileId);
    }

    public static String readFromFile(String url) {
        StringBuilder result = new StringBuilder();
        FileReader fileReader;
        try {
            fileReader = new FileReader(BASE_DIR + File.separator + url);
        } catch (FileNotFoundException e) {
            return null;
        }
        BufferedReader reader = new BufferedReader(fileReader);
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                result.append("\n").append(line);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return result.toString();
    }

    public static boolean saveOrUpdate(String url, String content) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        File file = getFile(url);

        try {
            fileWriter = new FileWriter(file.getAbsoluteFile());
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);

            bufferedWriter.flush();
            fileWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(String id) {
        if (fileExists(id)) {
            File file = getFile(id);
            return file.delete();
        }
        return false;
    }
}

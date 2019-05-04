package org.bajiepka.courseApp.services;

import org.bajiepka.courseApp.configuration.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileService {

    @Value(value = "${json.defaultPath}")
    private String directory;

    @Value(value = "${json.defaultSuffix}")
    private String prefix;

    @Autowired
    private CommonConfig commonConfig;

    @Value("json")
    private String defaultExtension;

    public String writeToFile(String content) {

        String path = String.format("%s%s_%s.%s",
                directory,
                prefix,
                new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss").format(new Date()),
                defaultExtension);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public String readFile(String filepath) {

        File newFile = new File(filepath);
        if (newFile.exists()) {

            try (FileInputStream inputStream = new FileInputStream(newFile)) {

                byte data[] = new byte[inputStream.available()];
                inputStream.read(data);
                return new String(data);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";

    }
}

package org.example.restapitest.utilities;

import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class FileUtility {

    Logger logger = Logger.getLogger(FileUtility.class.getName());

    /**
     * Retrieves the data from provided file path
     * @param filePath-Route path
     * @return String content of file
     */
    public String getDataFromFile(String filePath){
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        }catch (IOException exception){
            logger.log(Level.SEVERE, "Unable to read file from path: {0}", exception.getMessage());
            Assert.fail(exception.getMessage());
        }
        return null;
    }
}

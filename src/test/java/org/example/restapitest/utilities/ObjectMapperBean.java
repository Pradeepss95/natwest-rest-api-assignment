package org.example.restapitest.utilities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.restapitest.domain.object.ObjectItem;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.testng.Assert;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Scope("cucumber-glue")
public class ObjectMapperBean {

    Logger logger = Logger.getLogger(ObjectMapperBean.class.getName());
    private ObjectMapper objectMapper;

    public ObjectMapperBean() {
        prepareObjectMapper();
    }

    private void prepareObjectMapper() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public String convertToString(ObjectItem objectItem) {
        try {
            return objectMapper.writeValueAsString(objectItem);
        } catch (Exception exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            Assert.fail("Something went wrong while converting ObjectItem into String");
        }
        return null;
    }

    public ObjectItem convertStringToObjectItem(String objectItemString) {
        try {
            return objectMapper.readValue(objectItemString, ObjectItem.class);
        } catch (Exception exception) {
            logger.log(Level.SEVERE, exception.getMessage());
            Assert.fail("Something went wrong while converting String into ObjectItem object");
        }
        return null;
    }
}

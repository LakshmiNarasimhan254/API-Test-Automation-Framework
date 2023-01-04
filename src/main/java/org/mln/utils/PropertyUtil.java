package org.mln.utils;


import org.mln.constants.FrameworkConstants;
import org.mln.enums.ConfigProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * It loads the properties file and stores it in a map
 */
public class PropertyUtil {

    private  static final Properties properties = new Properties();
    private static final Map<String,String> CONFIGMAP = new HashMap<>();
    private PropertyUtil() {
    }

    static {
        try(FileInputStream fileInputStream = new FileInputStream(FrameworkConstants.getCONFIGPATH())){
            properties.load(fileInputStream);
            properties.entrySet().forEach(entry->CONFIGMAP.put(String.valueOf(entry.getKey()).toLowerCase(),String.valueOf(entry.getValue()).trim()));

        } catch (IOException e) {
           e.printStackTrace();
           System.exit(0);
        }
    }
        /**
         * It takes a key as an argument and returns the value of the key from the config file
         *
         * @param key The key of the property file.
         * @return The value of the key in the config file.
         */
        public static String getValue(ConfigProperties key)  {
        if (Objects.isNull(CONFIGMAP.get(key.name().toLowerCase())) ||(Objects.isNull(CONFIGMAP.get(key.name().toLowerCase())))) {
            throw new RuntimeException("Property Named " + key + " is not found.Please check in :"+ FrameworkConstants.getCONFIGPATH() +".");

        }
        return CONFIGMAP.get(key.toString().toLowerCase());
    }
}

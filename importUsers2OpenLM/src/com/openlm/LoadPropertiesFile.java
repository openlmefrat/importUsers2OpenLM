package com.openlm;

/**
 * Created by adi on 09/07/2014.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadPropertiesFile {

    public static String OpenLMServerURL;
    public static boolean useAuthentication;
    public static String username;
    public static String password;
    public static String delimiter;
    public static String inputCSVFile;




    public void loader(String file) {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream(file);

            // load a properties file
            prop.load(input);

            // get the property value and print it out

            OpenLMServerURL = prop.getProperty("OpenLMServerURL");
            useAuthentication = Boolean.parseBoolean(prop.getProperty("useAuthentication"));
            password = prop.getProperty("password");
            delimiter = prop.getProperty("delimiter");
            username = prop.getProperty("username");
            inputCSVFile = prop.getProperty("inputCSVFile");


        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getInputCSVFile() {
        return inputCSVFile;
    }

    public static String getOpenLMServerURL() {
        return OpenLMServerURL;
    }

    public String getPassword() {
        return password;
    }

    public boolean getUseAuthentication() {
        return useAuthentication;
    }

    public String getUsername() {
        return username;
    }

}


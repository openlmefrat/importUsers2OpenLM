package com.openlm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by adi on 29/06/2014.
 */
public class readCSV {

    private String csvFileToRead;
    private String delimiter;
    private String mapFile;

    private BufferedReader br = null;
    private String line = "";
    //String splitBy = ",";
    //File file = new File("C:\\Users\\Joe\\image.jpg");


    public void setInputFile(String csvFileToRead) {
        this.csvFileToRead = csvFileToRead;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getNext() {
    String line = null;
        try {

            line = br.readLine();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br == null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return line;
    }

    public void openFile() {
        try {

            br = new BufferedReader(new FileReader(csvFileToRead));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }





}

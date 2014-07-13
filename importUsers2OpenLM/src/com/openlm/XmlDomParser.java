package com.openlm;

import org.w3c.dom.Document;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.StringReader;

import java.io.IOException;
import java.net.SocketPermission;

/**
 * Created by adi on 01/07/2014.
 */
public class XmlDomParser {

    private Document dom;

    private String title;
    private String default_project;
    private String default_group;
    private boolean enabled;
    private String email;
    private String description;
    private String office;
    private String phone;
    private String display_name;
    private String department;
    private String last_name;
    private String first_name;
    private String username;
    private String id;


    public XmlDomParser(String xmlList) {
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            //dom = db.parse("C:\\Users\\adi\\IdeaProjects\\adi1\\users.xml");
            InputSource is = new InputSource(new StringReader(xmlList));
            dom = db.parse(is);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void parseDocument() {
        //get the root element
        Element docEle = dom.getDocumentElement();

        //get a nodelist of elements
        NodeList nl;
        nl = docEle.getElementsByTagName("USER");
        if (nl != null && nl.getLength() > 0) for (int i = 0; i < nl.getLength(); i++) {

            //get the employee element
            Element el = (Element) nl.item(i);

            //get the Employee object
            Employee e = getEmployee(el);

            //add it to list
            //System.out.println(e.getusername());

        }
    }

    // Find a user by username attribute
    public String findUserByUsername(String username) {
        //get the root element
        Element docEle = dom.getDocumentElement();

        //get a nodelist of elements
        NodeList nl;
        String userID = "0";
        nl = docEle.getElementsByTagName("USER");
        if (nl != null && nl.getLength() > 0) for (int i = 0; i < nl.getLength(); i++) {

            //get the employee element
            Element el = (Element) nl.item(i);

            //get the Employee object
            Employee e = getEmployee(el);
            if (e.getusername().equals(username)) {
                //System.out.println(true);
                userID = e.getId();
                break;
            }
        }

        return userID;

    }

    /*
        // Find a user by username attribute
        public String findUserDetails(String username) {
            //get the root element
            Element docEle = dom.getDocumentElement();

            //get a nodelist of elements
            NodeList nl;
            boolean found = false;
            nl = docEle.getElementsByTagName("USER");
            if (nl != null && nl.getLength() > 0) for (int i = 0; i < nl.getLength(); i++) {

                //get the employee element
                Element el = (Element) nl.item(i);

                //get the Employee object
                Employee e = getEmployee(el);
                System.out.println("Checking user" + e.getusername());
                if (e.getusername().equals(username)) {
                 return;
                    break;
                }
            }

        }
    *\
        /**
         * I take an employee element and read the values in, create
         * an Employee object and return it
         */
    private Employee getEmployee(Element empEl) {

        //for each <employee> element get text or int values of
        //name ,id, age and name
        String title = getTextValue(empEl, "title");
        String default_project = getTextValue(empEl, "default_project");
        String default_group = getTextValue(empEl, "default_group");
        boolean enabled = getbooleanValue(empEl, "enabled");
        String email = getTextValue(empEl, "email");
        String description = getTextValue(empEl, "description");
        String office = getTextValue(empEl, "office");
        String phone = getTextValue(empEl, "phone");
        String display_name = getTextValue(empEl, "display_name");
        String department = getTextValue(empEl, "department");
        String last_name = getTextValue(empEl, "last_name");
        String first_name = getTextValue(empEl, "first_name");
        String username = getTextValue(empEl, "username");
        String id = getTextValue(empEl, "id");


        String type = empEl.getAttribute("type");

        //Create a new Employee with the value read from the xml nodes
        Employee e = new Employee(title, default_project, default_group, enabled, email, description, office, phone, display_name, department, last_name, first_name, username, id);

        return e;
        //System.out.println(e);
    }


    /**
     * I take a xml element and the tag name, look for the tag and get
     * the text content
     * i.e for <employee><name>John</name></employee> xml snippet if
     * the Element points to employee node and tagName is 'name' I will return John
     */
    private String getTextValue(Element ele, String tagName) {
        /*String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }
        */
        return ele.getAttribute(tagName);

        //return textVal;
    }


    private boolean getbooleanValue(Element ele, String tagName) {
        boolean booleanval = true;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            booleanval = Boolean.parseBoolean(el.getFirstChild().getNodeValue());
        }

        return booleanval;
    }


    /**
     * Calls getTextValue and returns a int value
     */
    private int getIntValue(Element ele, String tagName) {
        //in production application you would catch the exception
        return Integer.parseInt(getTextValue(ele, tagName));
    }

    //private void printData(){


    public boolean compareUsers(String username, String first_name, String last_name, String Email) {
        // Locates a user according to username and compare attributes.
        // Stages:
        //  - Locate the user in the list
        //  - compare attributes
        //
        Element docEle = dom.getDocumentElement();

        //get a nodelist of elements
        NodeList nl;
        boolean found = false;
        nl = docEle.getElementsByTagName("USER");
        if (nl != null && nl.getLength() > 0)
            for (int i = 0; i < nl.getLength(); i++) {

                //get the employee element
                Element el = (Element) nl.item(i);

                //get the Employee object
                Employee e = getEmployee(el);
                if (e.getFirst_name().equals(first_name) && e.getLast_name().equals(last_name) && e.getEmail().equals(Email)&& e.getusername().equals(username)) {
                    found = true;
                    break;
                } else {
                    found = false;
                }
            }

        return found;

    }

}




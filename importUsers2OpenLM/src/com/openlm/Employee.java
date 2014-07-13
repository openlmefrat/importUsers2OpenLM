package com.openlm;

/**
 * Created by adi on 03/07/2014.
 */
public class Employee {

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
    private String  first_name;
    private String username;
    private String id;


    public Employee(String title, String default_project, String default_group, boolean enabled, String email, String description, String office, String phone, String display_name, String department, String last_name, String first_name, String username, String id) {
        this.title = title;
        this.default_project = default_project;
        this.default_group = default_group;
        this.enabled = enabled;
        this.email = email;
        this.description = description;
        this.office = office;
        this.phone = phone;
        this.display_name = display_name;
        this.department = department;
        this.last_name = last_name;
        this.first_name = first_name;
        this.username = username;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getusername() {
        return username;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }
}

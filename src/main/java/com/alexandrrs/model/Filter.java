package com.alexandrrs.model;

import java.io.UnsupportedEncodingException;

public class Filter
{
    private Integer id;
    private Boolean isAdmin;
    private String name;
    private String age;

    public Filter() { }

    public Filter(User user)
    {
        this.isAdmin = user.getIsAdmin();
        this.name = user.getName();
        this.age = user.getAge().toString();
        this.id = user.getId();
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void updateUser(User user)
    {
        user.setId(this.id);
        user.setName(this.name);
        user.setIsAdmin(this.isAdmin);
        user.setAge(Integer.parseInt(this.age));
    }

    public User generateUser()
    {
        User user = new User();
        user.setName(this.name);
        user.setIsAdmin(this.isAdmin);
        user.setAge(Integer.parseInt(this.age));
        return user;
    }
    public void validateData()
    {
        try
        {
            name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }
}

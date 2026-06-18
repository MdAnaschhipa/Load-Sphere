package com.rpc.model;

public class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String role; // "owner" or "admin"

    public User() {}

    public User(int userId, String name, String email, String password, String phone, String role) {
        this.userId   = userId;
        this.name     = name;
        this.email    = email;
        this.password = password;
        this.phone    = phone;
        this.role     = role;
    }

    public int    getUserId()            { return userId; }
    public void   setUserId(int id)      { this.userId = id; }
    public String getName()              { return name; }
    public void   setName(String n)      { this.name = n; }
    public String getEmail()             { return email; }
    public void   setEmail(String e)     { this.email = e; }
    public String getPassword()          { return password; }
    public void   setPassword(String p)  { this.password = p; }
    public String getPhone()             { return phone; }
    public void   setPhone(String ph)    { this.phone = ph; }
    public String getRole()              { return role; }
    public void   setRole(String r)      { this.role = r; }
}

//package com.udayanga.timetableio.model;
//
//
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Size;
//import java.util.Set;
//
//public class UserWeb {
//    @NotBlank
//    @Size(min = 3, max = 20)
//    private String name;
//
//    @NotBlank
//    @Size(max = 50)
//    @Email
//    private String email;
//
//    private Set<String> role;
//
//    @NotBlank
//    @Size(min = 6, max = 40)
//    private String password;
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Set<String> getRole() {
//        return this.role;
//    }
//
//    public void setRole(Set<String> role) {
//        this.role = role;
//    }
//}
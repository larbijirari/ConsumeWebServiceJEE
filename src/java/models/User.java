/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author larix
 */
public class User {
    private String id;
    private String name;
    private String age;
    public User(){};
    public User(String name , String age){
  this.name=name;
  this.age=age;
  id=null;
    }
    public User(String id,String name , String age){
  this.name=name;
  this.age=age;
  this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }
    
}

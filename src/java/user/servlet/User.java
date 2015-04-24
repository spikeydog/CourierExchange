/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.servlet;


/**
 *
 * @author pooja
 */
public class User {
    private String fName;//First Name
    private String lName;
    private String email;
    private String addOne;
    private String addTwo;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    private String city;
    private String state;
    private Integer userId;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    private Integer pin;
    private String country;
    private String pwd;
    private String userType;
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddOne() {
        return addOne;
    }

    public void setAddOne(String addOne) {
        this.addOne = addOne;
    }

    public String getAddTwo() {
        return addTwo;
    }

    public void setAddTwo(String addTwo) {
        this.addTwo = addTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    /*
        public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    
    */
     public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
      public User()
    {
        
        fName="";
        lName="";
        email="";
        addOne="";
        addTwo="";
        city="";
        state="";
        pin=0;
        pwd="";
        country="";
    }
       public User(Integer userId, String emailId,String password,String lName,String addOne,String addTwo,String city,String state,Integer pin,String country,String userType) {
       this.userId=userId;
       this.email = emailId;
       this.pwd = password;
       this.fName=fName;
       this.lName=lName;
       this.addOne=addOne;
       this.addTwo=addTwo;
       this.city=city;
       this.state=state;
       this.pin=pin;
       this.country=country;
       this.userType=userType;

   } 
}

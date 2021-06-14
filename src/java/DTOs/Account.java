/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Account implements Serializable{
    private String accUserName;
    private String accPassword;
    private String accFullName;
    private Date accDob;
    private String accAddress;
    private String accPhone;
    private String accGender;

    public Account() {
        this.accUserName = "";
        this.accPassword = "";
        this.accFullName = "";
        this.accDob =new java.sql.Date(System.currentTimeMillis());
        this.accAddress = "";
        this.accPhone = "";
        this.accGender = "";
    }

    public Account(String accUserName, String accPassword, String accFullName, Date accDob, String accAddress, String accPhone, String accGender) {
        this.accUserName = accUserName;
        this.accPassword = accPassword;
        this.accFullName = accFullName;
        this.accDob = accDob;
        this.accAddress = accAddress;
        this.accPhone = accPhone;
        this.accGender = accGender;
    }

    public String getAccUserName() {
        return accUserName;
    }

    public String getAccPassword() {
        return accPassword;
    }

    public String getAccFullName() {
        return accFullName;
    }

    public Date getAccDob() {
        return accDob;
    }

    public String getAccAddress() {
        return accAddress;
    }

    public String getAccPhone() {
        return accPhone;
    }

    public String getAccGender() {
        return accGender;
    }

    public void setAccAddress(String accAddress) {
        this.accAddress = accAddress;
    }

    public void setAccGender(String accGender) {
        this.accGender = accGender;
    }

    public void setAccUserName(String accUserName) {
        this.accUserName = accUserName;
    }

    public void setAccPassword(String accPassword) {
        this.accPassword = accPassword;
    }

    public void setAccFullName(String accFullName) {
        this.accFullName = accFullName;
    }

    public void setAccDob(Date accDob) {
        this.accDob = accDob;
    }

    public void setAccAdress(String accAddress) {
        this.accAddress = accAddress;
    }

    public void setAccPhone(String accPhone) {
        this.accPhone = accPhone;
    }

    public void setGender(String accGender) {
        this.accGender = accGender;
    }

    @Override
    public String toString() {
        return accFullName + ", " + accUserName + ", " + accPassword + ", " + accGender + ", " + accDob + ", " + accPhone+ ", " + accAddress   ;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.accUserName, other.accUserName)) {
            return false;
        }
        return true;
    }
    
}

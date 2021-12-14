package com.SEP3.CarRentalAPI.Model;

public class UserLogin
{
    private boolean isSuccessful;
    private long userId;
    private boolean isEmployee;
    public String email;
    public String password;



    public UserLogin(boolean isSuccessful, long userID, boolean isEmployee)
    {
        this.isSuccessful = isSuccessful;
        this.userId = userID;
        this.isEmployee = isEmployee;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public boolean isSuccessful()
    {
        return isSuccessful;
    }
    public void setSuccessful(boolean successful)
    {
        isSuccessful = successful;
    }

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public boolean isEmployee()
    {
        return isEmployee;
    }

    public void setEmployee(boolean employee)
    {
        isEmployee = employee;
    }
}

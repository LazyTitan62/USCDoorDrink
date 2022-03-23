package com.csci310.uscdoordrink;

class User {
    private String usrName;
    private String usrPassword;
    private Integer usrID;

    public User(String name, String pw){
        usrName = name;
        usrPassword = pw;
        // TO DO: FILL IN THE QUERY FOR USERID
        usrID = 0;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public Integer getUsrID() {
        return usrID;
    }

    public void setUsrID(Integer usrID) {
        this.usrID = usrID;
    }

}

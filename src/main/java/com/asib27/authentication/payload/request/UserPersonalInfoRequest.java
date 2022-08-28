package com.asib27.authentication.payload.request;

public class UserPersonalInfoRequest{
    private String first_name;
    private String middle_name;
    private String last_name;
    private String phone_number;
    private String backup_phone_number;

    public UserPersonalInfoRequest(String first_name, String middle_name, String last_name, String phone_number, String backup_phone_number) {
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.backup_phone_number = backup_phone_number;
    }

    public UserPersonalInfoRequest() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getBackup_phone_number() {
        return backup_phone_number;
    }

    public void setBackup_phone_number(String backup_phone_number) {
        this.backup_phone_number = backup_phone_number;
    }
}

package com.company.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashMap;

@Entity
public class Client {

    // imitate in-memory authenticated sessions
    public static final HashMap<String, Long> ACTIVE_CLIENT_SESSIONS = new HashMap<>();
    public static final String AUTH_CLIENT_ID = "io.company.auth.client.id";

    static {
        ACTIVE_CLIENT_SESSIONS.put("LdI0d0D2dQ9n3XGat6H5J2SUcgcSxnxBEiVlv9eQnIpB7VbrIZM2B3iKQzwd6nlNgynCz8BhV5cIY7aaCTpCMgtd2Vbwpu2JOrRlgRrSrUVyi1ucWkauJXESUvcM4bX3", 1L);
        ACTIVE_CLIENT_SESSIONS.put("PKmDJrHF0ypgDwtARTeeaDOWjlN5Cg4ddDu1TM8MFPAhH71n7FaqtLKMSATBKMAwQ7cZ1c3kk81aXxGhqp2ErA3JCcgxhwV5b0WpXKkLkVqlbttQ3C7n9rYmpMtCAZTd", 2L);
    }

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String personalCode;
    private String email;
    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}

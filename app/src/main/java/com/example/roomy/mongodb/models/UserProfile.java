package com.example.roomy.mongodb.models;
import org.bson.codecs.*;
import org.bson.types.ObjectId;

import java.util.Objects;

public class UserProfile {
    private ObjectId _id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String pNumber;

    public UserProfile(ObjectId id, String fullName, String username, String password, String email, String pNumber) {
        setId(id);
        setFullName(fullName);
        setUsername(username);
        setPassword(password)
        setEmail(email);
        setPNumber(pNumber);
    }

    public ObjectId getId() {
        return _id;
    }

    public UserProfile setId(ObjectId id) {
        this._id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfile setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserProfile setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserProfile setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfile setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPNumber() {
        return pNumber;
    }

    public UserProfile setPNumber(String pNumber) {
        this.pNumber = pNumber;
        return this;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(_id);
        sb.append(", fullName=").append(fullName);
        sb.append(", username=").append(username);
        //purposeful lack of password
        sb.append(", email=").append(email);
        sb.append(", pNumber=").append(pNumber);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserProfile userProfile = (UserProfile) o;
        return (Objects.equals(_id, userProfile._id)
                && Objects.equals(fullName, userProfile.fullName)
                && Objects.equals(username, userProfile.username)
                && Objects.equals(email, userProfile.email)
                && Objects.equals(pNumber, userProfile.pNumber)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id, fullName, username, email, pNumber);
    }
}


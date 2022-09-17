package com.example.roomy.mongodb.models;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.Objects;

public class Room {
    private ObjectId _id;

    private List<UserProfile> roommates;
    private List<Task> allTasks;
    private String address;

    public Room(ObjectId id, List<UserProfile> roommates, List<Task> allTasks, String address){
        setId(id);
        setRoommates(roommates);
        setAllTasks(allTasks);
        setAddress(address);
    }
    public ObjectId getId() {
        return _id;
    }

    public Room setId(ObjectId id) {
        this._id = id;
        return this;
    }

    public List<UserProfile> getRoommates() {
        return roommates;
    }

    public Room setRoommates(List<UserProfile> roommates) {
        this.roommates = roommates;
        return this;
    }

    public List<Task> getAllTasks() {
        return allTasks;
    }

    public Room setAllTasks(List<Task> allTasks) {
        this.allTasks = allTasks;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Room setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Room room = (Room) o;
        return (
                Objects.equals(_id, room._id)
                        && Objects.equals(roommates, room.roommates)
                        && Objects.equals(allTasks, room.allTasks)
                        && Objects.equals(address, room.address)
        );
    }

}

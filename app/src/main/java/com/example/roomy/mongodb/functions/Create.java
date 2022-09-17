package com.example.roomy.mongodb.functions;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import com.example.roomy.mongodb.models.Task;
import com.example.roomy.mongodb.models.UserProfile;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

import java.time.LocalDate;

public class Create {

    private static final Random rand = new Random();

    public static void main(String[] args) {
        ConnectionString connectionString = new ConnectionString(System.getProperty("mongodb.uri"));
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            CodecRegistry pojoCodecRegistry = org.bson.codecs.configuration.CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), org.bson.codecs.configuration.CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
            MongoDatabase usersDB = mongoClient.getDatabase("users").withCodecRegistry(pojoCodecRegistry);
            MongoDatabase roomsDB = mongoClient.getDatabase("rooms").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Document> profilesCollection = usersDB.getCollection("profiles");
            MongoCollection<Document> roomsCollection = roomsDB.getCollection("rooms");

            insertOneProfileDocument(profilesCollection, "Test Test", "test", "test@email.com", "1");

            List<UserProfile> roommates = new ArrayList<UserProfile>();
            UserProfile me = new UserProfile(new ObjectId(), "Fan Yang", "fyang", "fyang@gmail.com", "100");
            roommates.add(me);

            List<Task> allTasks = new ArrayList<Task>();
            allTasks.add(new Task(
                            new ObjectId(),
                            "vacuuming",
                            "vacuum floor",
                            LocalDate.now(),
                            me,
                            false
                    )
            );
            insertOneRoomDocument(
                    roomsCollection,
                    roommates,
                    allTasks,
                    "address"
            );
        }
    }

    private static void insertOneProfileDocument(
            MongoCollection<Document> profilesCollection,
            String fullName,
            String username,
            String email,
            String pNumber
    ) {
        ObjectId id = new ObjectId();
        profilesCollection.insertOne(generateNewUserProfile(id, fullName, username, email, pNumber));
        System.out.println("One profile inserted for user " + fullName);
    }

    private static void insertOneRoomDocument(
            MongoCollection<Document> roomsCollection,
            List<UserProfile> roommates,
            List<Task> allTasks,
            String address
    ) {
        ObjectId id = new ObjectId();
        roomsCollection.insertOne(generateNewRoom(id, roommates, allTasks, address));
        System.out.println("One document inserted for room at " + address);
    }

    // private static void insertManyDocuments(MongoCollection<Document> userProfileCollection) {
    //     List<Document> userProfiles = new ArrayList<>();
    //     for (double classId = 1d; classId <= 10d; classId++) {
    //         grades.add(generateNewUserProfile(10001d, classId));
    //     }

    //     gradesCollection.insertMany(grades, new InsertManyOptions().ordered(false));
    //     System.out.println("Ten grades inserted for studentId 10001.");
    // }

    private static Document generateNewUserProfile(ObjectId id, String fullName, String username, String email, String pNumber) {
        return new Document("_id", id).append("full_name", fullName)
                .append("username", username)
                .append("email", email)
                .append("pNumber", pNumber);
    }

    private static Document generateNewRoom(ObjectId id, List<UserProfile> roommates, List<Task> allTasks, String address) {
        return new Document("_id", id).append("roommates", roommates)
                .append("all_tasks", allTasks)
                .append("address", address);
    }
}

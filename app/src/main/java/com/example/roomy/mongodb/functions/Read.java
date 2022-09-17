package com.example.roomy.mongodb.functions;

import com.mongodb.client.*;
import com.example.roomy.mongodb.models.UserProfile;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;

public class Read {

    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
            MongoDatabase usersDB = mongoClient.getDatabase("users");
            MongoCollection<Document> profilesCollection = usersDB.getCollection("profiles");

            // find one document with new Document
            Document user1 = profilesCollection.find().first();
            System.out.println("User1: " + user1.toJson());

            // find one document with Filters.eq() for fullName == "Test Test"
            Document test = profilesCollection.find(eq("full_name", "Test Test")).first();
            System.out.println("Test User: " + test.toJson());

            // find a list of documents and iterate throw it using an iterator.
            // FindIterable<Document> iterable = profilesCollection.find(gte("student_id", 10000));
            // MongoCursor<Document> cursor = iterable.iterator();
            // System.out.println("Student list with a cursor: ");
            // while (cursor.hasNext()) {
            //     System.out.println(cursor.next().toJson());
            // }

            // find a list of documents and use a List object instead of an iterator
            // List<Document> studentList = profilesCollection.find(gte("student_id", 10000)).into(new ArrayList<>());
            // System.out.println("Student list with an ArrayList:");
            // for (Document student : studentList) {
            //     System.out.println(student.toJson());
            // }

            // find a list of documents and print using a consumer
            // System.out.println("Student list using a Consumer:");
            // Consumer<Document> printConsumer = document -> System.out.println(document.toJson());
            // profilesCollection.find(gte("student_id", 10000)).forEach(printConsumer);

            // find a list of documents with sort and projection
            List<Document> docs = profilesCollection.find()
                    .projection(fields(excludeId(), include("username", "email")))
                    .sort(descending("username"))
                    .into(new ArrayList<>());

            System.out.println("Users sorted and projected: ");
            for (Document user : docs) {
                System.out.println(user.toJson());
            }
        }
    }

    public static Document getByFieldNameAndValue(MongoCollection<Document> collection, String fieldName, Object value) {
        // find one document with specific fieldName and value
        // need to make sure that the type of value matches with the typing for field
        Document doc = collection.find(eq(fieldName, value)).first();
        return doc;
    }

    public static Document getById(MongoCollection<Document> collection, ObjectId id) {
        // find one document with specific fieldName and value
        // need to make sure that the type of value matches with the typing for field
        Document doc = collection.find(eq("_id", id)).first();
        return doc;
    }
}
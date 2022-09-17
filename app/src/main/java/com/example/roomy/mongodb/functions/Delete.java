package com.example.roomy.mongodb.functions;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gte;

public class Delete {
    public static void main(String[] args) {
        ConnectionString connectionString = new ConnectionString(System.getProperty("mongodb.uri"));
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            MongoDatabase usersDB = mongoClient.getDatabase("users");
            MongoCollection<Document> profilesCollection = usersDB.getCollection("profiles");

            MongoDatabase roomsDB = mongoClient.getDatabase("rooms");
            MongoCollection<Document> roomsCollection = roomsDB.getCollection("rooms");

            // delete one document
            Bson testUserFilter = eq("name", "Test Test");
            DeleteResult result = profilesCollection.deleteOne(testUserFilter);
            // System.out.println(result);

            // findOneAndDelete operation
            testUserFilter = eq("name", "Test Test");
            Document doc = profilesCollection.findOneAndDelete(testUserFilter);
            // System.out.println(doc.toJson(JsonWriterSettings.builder().indent(true).build()));

            // delete many documents using a filter like gte and then profilesCollection.deleteMany

            // delete the entire collection and its metadata (indexes, chunk metadata, etc).
            // profilesCollection.drop();
        }
    }

    public static DeleteResult deleteById(MongoCollection<Document> collection, ObjectId id) {
        Bson idFilter = eq("_id", id);
        DeleteResult result = collection.deleteOne(idFilter);
        return result;
    }
}
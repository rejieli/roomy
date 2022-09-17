package com.example.roomy.mongodb.functions;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonWriterSettings;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class Update {

    public static void main(String[] args) {
        JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

        try (MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"))) {
            MongoDatabase usersDB = mongoClient.getDatabase("users");
            MongoCollection<Document> profileCollection = usersDB.getCollection("profiles");

            // update one document
            Bson filter = eq("full_name", "Test Test");
            Bson updateOperation = set("update_comment", "You should learn MongoDB!");
            UpdateResult updateResult = profileCollection.updateOne(filter, updateOperation);
            System.out.println("=> Updating the doc with {\"full-name\": \"Test Test\"}. Adding comment.");
            System.out.println(profileCollection.find(filter).first().toJson(prettyPrint));
            System.out.println(updateResult);

            // upsert
            // if document exists from filter, update the document. If it does not exist, create the document and update it.
            filter = and(eq("username", "test"), eq("p_number", "1"));
            updateOperation = push("upsert_comment", "You will learn a lot if you read the MongoDB blog!");
            UpdateOptions options = new UpdateOptions().upsert(true);
            updateResult = profileCollection.updateOne(filter, updateOperation, options);
            System.out.println("\n=> Upsert document with {\"p_number\":\"1\", \"username\": \"test\"} because it doesn't exist yet.");
            System.out.println(updateResult);
            System.out.println(profileCollection.find(filter).first().toJson(prettyPrint));

            // update many documents, replace fieldName, value and gradesCollection with respective values
            // filter = eq("student_id", 10001);
            // updateResult = gradesCollection.updateMany(filter, updateOperation);
            // System.out.println("\n=> Updating all the documents with {\"student_id\":10001}.");
            // System.out.println(updateResult);

            /*
            TLDR
            You can combine multiple updates,
            and return either the old version or the updated version of the document
            */
            // findOneAndUpdate
            // filter = eq("student_id", 10000);
            // Bson update1 = inc("x", 10); // increment x by 10. As x doesn't exist yet, x=10.
            // Bson update2 = rename("class_id", "new_class_id"); // rename variable "class_id" in "new_class_id".
            // Bson update3 = mul("scores.0.score", 2); // multiply the first score in the array by 2.
            // Bson update4 = addToSet("comments", "This comment is uniq"); // creating an array with a comment.
            // Bson update5 = addToSet("comments", "This comment is uniq"); // using addToSet so no effect.
            // Bson updates = combine(update1, update2, update3, update4, update5);
            // // returns the old version of the document before the update.
            // Document oldVersion = gradesCollection.findOneAndUpdate(filter, updates);
            // System.out.println("\n=> FindOneAndUpdate operation. Printing the old version by default:");
            // System.out.println(oldVersion.toJson(prettyPrint));

            // // but I can also request the new version
            // filter = eq("student_id", 10001);
            // FindOneAndUpdateOptions optionAfter = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
            // Document newVersion = gradesCollection.findOneAndUpdate(filter, updates, optionAfter);
            // System.out.println("\n=> FindOneAndUpdate operation. But we can also ask for the new version of the doc:");
            // System.out.println(newVersion.toJson(prettyPrint));
        }
    }

    public static UpdateResult updateOneDocument(Bson filter, Bson updateOperation, MongoCollection<Document> collection) {
        UpdateResult updateResult = collection.updateOne(filter, updateOperation);
        return updateResult;
    }
}

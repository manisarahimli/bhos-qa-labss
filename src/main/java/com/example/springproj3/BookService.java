package com.example.springproj3;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
@Service
public class BookService {


    public static String saveBook(Book book) throws ExecutionException, InterruptedException {

        Firestore databaseFirestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collectionApiFuture = databaseFirestore.collection("books").document(book.name).set(book);

        return collectionApiFuture.get().getUpdateTime().toString();

    }

    public static Book getBookDetails(String name) throws ExecutionException, InterruptedException {

        Firestore databaseFirestore = FirestoreClient.getFirestore();

        DocumentReference documentReference = databaseFirestore.collection("books").document(name);

        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Book book;

        if (document.exists()) {

            book = document.toObject(Book.class);

            return book;

        } else {

            return null;

        }
    }
}
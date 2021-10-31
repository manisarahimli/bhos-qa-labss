package com.example.springproj3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
public class FirebaseTest {

    @Test
    public void testingSave() {
        Book postedBook = new Book("Ali and Nino", "Kurban Said");
        try {
            BookService.saveBook(postedBook);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Book theBook = null;
        try {
            theBook = BookService.getBookDetails("Ali and Nino");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assert theBook != null;

        assertEquals(postedBook.name, theBook.name);
        assertEquals(postedBook.author, theBook.author);
    }

}

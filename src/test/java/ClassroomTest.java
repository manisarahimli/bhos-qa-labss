import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClassroomTest {
    @Test
    void toplama() {
        assertEquals(13, Classroom.toplama(5,8));
    }

    @Test
    void vurma() {
        assertEquals(40, Classroom.vurma(5,8));
    }

    @Test
    void bolme() {
        assertEquals(2.0, Classroom.bolme(10,5));
    }

    @Test
    void kvadrat() {
        assertEquals(25, Classroom.kvadrat(5));
    }

    @Test
    void kub() {
        assertEquals(27, Classroom.kub(3));
    }
}
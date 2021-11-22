import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Classroom {
    public static int toplama(int a, int b) {
        return a + b;
    }

    public static int chixma(int a, int b) {
        return a - b;
    }

    public static int vurma(int a, int b) {
        return a * b;
    }

    public static double bolme(int a, int b) {
        return (double) a / b;
    }

    public static int kvadrat(int n) {
        return n * n;
    }

    public static int kub(int n) {
        return n * n * n;
    }

    public double kokalti(double a) {
        if (a >= 0)
            return Math.sqrt(a);
        return 0;
    }

    public double loqarifma(double a) {
        return Math.log(a);
    }

    public static int qaliq(int a, int b) {
        return a % b;
    }

    public static int quvvet4(int n) {
        return n * n * n * n;
    }

    public byte[] hashFunction (String data) throws NoSuchAlgorithmException {
        MessageDigest msgDigest = MessageDigest.getInstance("SHA-1");
        return msgDigest.digest(data.getBytes());
    }
}

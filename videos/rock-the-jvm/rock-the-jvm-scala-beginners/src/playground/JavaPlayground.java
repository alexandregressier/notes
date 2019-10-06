package playground;

public class JavaPlayground {

    public static void main(String[] args) {
        System.out.println("Hello, Java!");
        System.out.println(Person.NB_OF_EYES);
    }

}

class Person {
    public static final int NB_OF_EYES = 2; // A /class variable/ (as opposed to an /instance variable/)
}

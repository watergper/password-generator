import library.Password;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Password Generator");

        Password password = new Password(3, true, false, true, false);

        
        System.out.println(password.getCharList());
        password.generate();
        System.out.println(password.getPassword());
        

    }
}

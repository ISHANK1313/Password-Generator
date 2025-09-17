import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordGenerator {


    public static void prompt(){
        try {
            Scanner sc = new Scanner(System.in);
            int k=sc.nextInt();
            System.out.println(PasswordGenerator.getPassword(k));
        }
        catch (IllegalArgumentException g){
            System.out.println(g.getMessage());
        }
        catch (Exception e){
            System.out.println("Invalid Length...");
        }

    }
    public static StringBuilder getPassword(int length){

        StringBuilder password= new StringBuilder();
        SecureRandom r= new SecureRandom();

            if(length<=0||length>100000){
                 throw new IllegalArgumentException("Length of password can not be <1 or >100000");
            }
            for (int i = 0; i < length; i++) {
                int decimal = r.nextInt(32, 127);
                password.append((char)decimal) ;
            }

        return password;
    }
    public static void main(String[] args){


        System.out.println("Welcome to random password generator... ");
        System.out.println("Please enter the length of the password you want... ");

        PasswordGenerator.prompt();

    }
}

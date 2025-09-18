import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Scanner;

public class PasswordGenerator {

private static final String characterset="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static void prompt(){
        try {
            Scanner sc = new Scanner(System.in);
            int k = sc.nextInt();
            System.out.println("Do you want to add Symbols ?...");
            System.out.println("Enter Y for Yes or N for No...");
            Scanner s = new Scanner(System.in);
            String c = s.nextLine();
            String pass = PasswordGenerator.getPassword(k, c);
            System.out.println(pass);
            PasswordGenerator.savePasswordToFile(pass);

        }
        catch (IllegalArgumentException g){
            System.out.println(g.getMessage());
        }
        catch (Exception e){
            System.out.println("Invalid Length...");
        }

    }

    public static void savePasswordToFile(String password){

        try {
            System.out.println("do you want to store the password in a file ?...");
            System.out.println("if yes then input Y , if no then input N");
            Scanner t = new Scanner(System.in);
            String j = t.nextLine();
            if (!j.equalsIgnoreCase("Y") && !j.equalsIgnoreCase("N")) {
                throw new IllegalArgumentException("Wrong input...");
            } else if (j.equalsIgnoreCase("Y")) {
                FileWriter writer = new FileWriter("output.txt",true);
                String timestamp= LocalDateTime.now().toString();

                writer.write("time:["+timestamp+"] ,password: "+password+"\n");
                writer.close();
                System.out.println("file created and text written successfully...");
            }
        }
        catch (IOException e){
            System.out.println("something went wrong please try again...");
        }

    }
    public static String getPassword(int length,String a){

        StringBuilder password= new StringBuilder();
        SecureRandom r= new SecureRandom();

            if(length<=0||length>100000){
                 throw new IllegalArgumentException("Length of password can not be <1 or >100000");
            }
            if((!a.equalsIgnoreCase("Y"))&&(!a.equalsIgnoreCase("N"))){
                throw new IllegalArgumentException("Invalid Input...");
            }
            if(a.equalsIgnoreCase("Y")) {
                for (int i = 0; i < length; i++) {
                    int decimal = r.nextInt(32, 127);
                    password.append((char) decimal);
                }
            }
            else{
                for(int i=0;i<length;i++){
                    int decimal =r.nextInt(0,PasswordGenerator.characterset.length());
                    password.append(PasswordGenerator.characterset.charAt(decimal));
                }
            }
        return password.toString();
    }

    public static void main(String[] args){


        System.out.println("Welcome to random password generator... ");
        System.out.println("Please enter the length of the password you want... ");
        PasswordGenerator.prompt();

    }
}

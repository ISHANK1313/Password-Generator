import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Scanner;

public class PasswordGenerator {

    private static final String characterset="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";


    public static void createAndDisplayGUI(){
        JFrame frame= new JFrame("Password generator");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);

        JLabel label = new JLabel("Enter Password length");
        JTextField field= new JTextField();
        label.setBounds(50,30,200,25);
        field.setBounds(50,60,100,25);
        frame.add(field);
        frame.add(label);

        JCheckBox checkBox=new JCheckBox("Include Symbols");
        checkBox.setBounds(50,100,200,25);
        frame.add(checkBox);

        JButton button = new JButton("Generate Password");
        button.setBounds(50,140,200,30);
        frame.add(button);

        JTextField passwordField=new JTextField();
        passwordField.setBounds(40,190,300,25);
        passwordField.setEditable(false);
        frame.add(passwordField);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             try {
                 String p = field.getText();
                 int j = Integer.parseInt(p);
                 String isCheck=checkBox.isSelected()?"Y":"N";
                 String password=getPassword(j,isCheck);
                 passwordField.setText(password);
                 copyToClipboard(password);
                 JOptionPane.showMessageDialog(frame, "Password copied to clipboard!");
             }
             catch (NumberFormatException ex){
                 JOptionPane.showMessageDialog(frame,"Input a valid number...","Error",JOptionPane.ERROR_MESSAGE);
             }
             catch (IllegalArgumentException g){
                 JOptionPane.showMessageDialog(frame,g.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
             }
             catch (Exception t){
                 JOptionPane.showMessageDialog(frame,"Invalid Length...","Error",JOptionPane.ERROR_MESSAGE);
             }
            }
        });


    }
    public static void copyToClipboard(String text){
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            StringSelection selection = new StringSelection(text);
            clipboard.setContents(selection, null);
            System.out.println("password copied to clipboard...");
        }
        catch (Exception e){
            System.out.println("Clipboard not available ...");
        }
    }
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
            PasswordGenerator.copyToClipboard(pass);
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

      // for CLI--->
       /* System.out.println("Welcome to random password generator... ");
        System.out.println("Please enter the length of the password you want... ");
        PasswordGenerator.prompt();
        */


        // for GUI--->
        createAndDisplayGUI();

    }
}

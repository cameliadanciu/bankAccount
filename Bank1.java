package bank1;

import java.util.Scanner;
import java.util.Random;

public class Bank1 {

    public static void main(String[] args) {
        String ANSI_RESET = "\u001B[0m";                //color reset code
        String ANSI_RED = "\u001B[31m";                 //red color code
        String ANSI_GREEN = "\u001B[32m";               //green color code
       
        Scanner input = new Scanner(System.in);             
        Account account = null;                         //at te start account is 0
        while(true){  
           
            System.out.println("    *** Welcome to Bank of UWL ***"
                            +"\nPlease enter the number of the selected option:"
                            +"\n      1. Create Account"
                            +"\n      2. Access Account"
                            +"\n      3. Exit");
           
            int selectOption = input.nextInt();
            
            if(selectOption == 1){                                              //option 1 = Create account
                System.out.println("Please enter your First name");
                String FirstName = input.next();
                System.out.println("Please enter your Last name");
                String LastName = input.next();                
                String name = FirstName+ " " + LastName;
                
                System.out.println("Please enter your age");
                int age = input.nextInt();
                Random generator = new Random();                        //create random object
                            //append string creates 6 numbers each between 0 and 9
                String accountIdGen = Integer.toString(generator.nextInt(10)) + Integer.toString(generator.nextInt(10)) + Integer.toString(generator.nextInt(10)) + Integer.toString(generator.nextInt(10)) + Integer.toString(generator.nextInt(10)) + Integer.toString(generator.nextInt(10));
                
                int id = Integer.parseInt(accountIdGen);          //parse string to int - generates random account number  
                            //append string creates 4 numbers each between 0 and 9
                String pinGen = Integer.toString(generator.nextInt(10)) + Integer.toString(generator.nextInt(10)) + Integer.toString(generator.nextInt(10)) + Integer.toString(generator.nextInt(10));
                int pin = Integer.parseInt(pinGen);              //parse string to int - generates random pin number
                 
                System.out.println("The curent account balance is 0. Please make a deposit to finish creating the account: ");
                double deposit = input.nextInt();               //enter first deposit when creating account
                if(deposit < 1){                                //deposit cannot be less than 1
                    System.out.println(ANSI_RED+"Amount entered is invalid. Please create new account."+ANSI_RESET);
                    continue;                                   
                }  
                account = new Account(name, age, id, pin, deposit);     //create the account
                account.setAnnualInterestRate(4.5);                     //set anual interest
               
                System.out.println("*** THANK YOU FOR CHOOSING UWL BANK ***"
                                + ANSI_GREEN+"\n   Your Account has been created \u2713    "+ANSI_RESET);
                
                System.out.println("Your ID number is: " + id + ". Please make note of this number!");
                System.out.println("Your PIN number is: "+ pin +". Please make note of this number!");
                System.out.println("Date created: "+account.getDateCreated());
                System.out.println(); 
                System.out.println("Would you like to access your account?"
                                  + "\n       1. YES"
                                  + "\n       2. NO");
                int YesNo = input.nextInt();
                if(YesNo == 1){
                    selectOption = 2;   //sends to option 2 = Access account
                }
                else{
                    break;
                }
        }  
            if(selectOption == 2){
                System.out.println("Please enter your user account number:");
                int id = input.nextInt();

                System.out.println("Please enter your pin number:");
                int pin = input.nextInt();  
                
                if(account.getId()== id && account.getPin() == pin){ //if id and pin corespond to the ones in account it opens the account menu
                    while(true){
                        System.out.println("          Hello, "+account.getName());
                        System.out.println("      *** Please select one of the options: *** "
                           + "\n             1. Withdraw"
                           + "\n             2. Deposit"
                           + "\n             3. View Montly Interest Rate"
                           + "\n             4. View balance"
                           + "\n             5. Main Menu"
                           + "\n             6. Exit");
                        int option = input.nextInt();  //waits for input from the menu
                    
                        switch(option){
                            case 1: 
                                System.out.println("Current ballance is "+account.getBalance());
                                System.out.println("Please enter the amount to withdraw.");
                            
                                double withdrawAmount = input.nextDouble(); 
                                        //withdraw amount cannot be less than 0 and cannot be more that the balance
                                if(withdrawAmount < 0 || withdrawAmount > account.getBalance()){  
                                    System.out.println(ANSI_RED+"Amount entered is invalid. Please try again!"+ANSI_RESET);
                                }
                                else{
                                    account.withdraw(withdrawAmount);   //call withdraw method
                                    System.out.println("The new balance is "+ account.getBalance());
                                    System.out.println();
                                }
                                break;
                            case 2:
                                System.out.println("Please enter the amount to deposit.");
                                double depositAmount = input.nextDouble();
                                if(depositAmount < 0){              //amount deposited cannot be a negative number
                                    System.out.println(ANSI_RED+"Amount entered is invalid. Please try again!"+ANSI_RESET);
                                }
                                else{
                                    account.deposit(depositAmount);  // call deposit method
                                    System.out.println("The new balance is "+ account.getBalance());
                                    System.out.println();
                                }
                                break;
                            case 3:
                                System.out.println("The Montly interest rate is "+account.getMonthlyInterestRate()); //call monthly interest method
                                System.out.println();
                                break;
                            case 4:
                                System.out.println("The current balance is "+account.getBalance());
                                System.out.println();
                                break;
                            case 5:
                                selectOption = 0;   //main menu
                                break;                                
                            case 6:
                                selectOption = 3;  //exit
                                break;  
                        }
                        if( option == 5 || option ==6){   //exit loop
                            break;
                        }
                    } 
                }
                else{
                    System.out.println(ANSI_RED+"   WRONG ID/PASSWORD. ACCESS DENIED"
                            +"\n    PLEASE TRY AGAIN "+ANSI_RESET);
                }     
            }
            if (selectOption == 3){                                                              //loop exit
                System.out.println("Thank you for choosing Bank of UWL! Have a good day!");                
                break;
            }
            if(selectOption > 3){                                                               //only if entered 3 it will exit
                System.out.println(ANSI_RED+"INVALID REQUEST PLEASE START AGAIN!"+ ANSI_RESET);
            }
        }
    }
}
class Account {      // atributes of the account class
    private String Name;
    private int age;
    private int id;
    private int pin;
    private double balance;
    private double annualInterestRate;
    private java.util.Date dateCreated;
   
    public Account(){         //default constructor
        //this.id = 0;
        //this.age = 0;
        //this.pin = 0;
        //this.balance = 0;
        //this.annualInterestRate = 0;
        this.dateCreated = new java.util.Date();
    }
    public Account (String name,int age, int id, int pin, double balance){   //constructor parametrized
        this();
        this.Name = name;
        this.id = id;
        this.age = age;
        this.pin = pin;
        this.balance = balance;
    }
//accessor methods
    public String getName(){
        return this.Name;
    }
    public int getAge(){
        return this.age;
    }
    public int getId(){
        return this.id;
    }
    public int getPin(){
        return this.pin;
    }
    public double getBalance(){
        return this.balance;
    }
    public double getAnnualInterestRate(){
        return this.annualInterestRate;
    }
    public String getDateCreated(){
        return this.dateCreated.toString();
    }
//mutator methods   
    public void setName(){
        this.Name = Name;
    }
    public void setAge(){
        this.age = age;
    }
    public void setId(){
        this.id = id;                
    }
    public void setPin(){
        this.pin = pin;
    }
    public void setBalance(){
        this.balance = balance;
    }
    public void setAnnualInterestRate(double annualInterestRate){
        this.annualInterestRate = annualInterestRate;
    }
    public double getMonthlyInterestRate(){
        return annualInterestRate/12;
    }
    public double applyMonthlyInterest(){
        return balance * getMonthlyInterestRate();
    }
    public void withdraw (double amount){
        balance -= amount;
}
    public void deposit (double amount){
        balance += amount;
    }
}
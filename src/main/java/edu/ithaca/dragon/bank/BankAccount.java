package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount < 0){
            throw new InsufficientFundsException("Amount is negative");
        }
        else if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        
        if (email.indexOf('@') == -1){
            return false;
        }
        if (email.chars().filter(ch -> ch == "@".charAt(0)).count() !=1){
            return false;
        }
        else if (email.indexOf('.') == -1){
            return false;
        }

        String[] invalidChar = {"!","#","$","&"};
        int count = 0;
        for (int i = 0; i < invalidChar.length; i++){
            String current = invalidChar[i];
            count += email.chars().filter(ch -> ch == current.charAt(0)).count();
            if (count > 0){
                return false;
            }
        }

        String[] parts = email.split("@", 2);
        
        

        System.out.println("");
        System.out.println("Starting");
        boolean specialMatch = false;
        for (int i = 0; i < parts[0].length(); i++){
            if (specialMatch == false){
                if (parts[0].charAt(i) == '-' || parts[0].charAt(i) =='_' || parts[0].charAt(i) =='.'){
                    specialMatch = true;
                }
                else{
                    specialMatch = false;
                }
            }
            else if (specialMatch == true){
                if (parts[0].charAt(i) == '-' || parts[0].charAt(i) == '_' || parts[0].charAt(i) == '.'){
                    return false;
                }
                else{
                    specialMatch = false;
                }
            }
            System.out.print(specialMatch);
        }

        


        if (parts[0].indexOf('.') == 0){
            return false;
        }
        else if (parts[0].indexOf('-') == 0){
            return false;
        }
        else if (parts[0].indexOf('_') == 0){
            return false;
        }
        else if (parts[0].indexOf('.') == parts[0].length()-1){
            return false;
        }
        else if (parts[0].indexOf('-') == parts[0].length()-1){
            return false;
        }
        else if (parts[0].indexOf('_') == parts[0].length()-1){
            return false;
        }
        
        if (parts[1].chars().filter(ch -> ch == '.').count() != 1){
            return false;
        }
        String[] subparts = parts[1].split(".", 2);
        if (subparts[1].length() <= 2){
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * @return true if the amount is positive and has two decimal points or less, false otherwise
     */
    public static boolean isAmountValid(double amount){
        return false;        
    }
}

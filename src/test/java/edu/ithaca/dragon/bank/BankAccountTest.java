package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());

        BankAccount newBankAccount = new BankAccount("a@b.com", 0);
        assertEquals(0, newBankAccount.getBalance());

    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance()); //It's equivalence case is that it's balance must be less than the account
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));

        BankAccount bankAccount2 = new BankAccount("a@c.com", 500);
        bankAccount2.withdraw(100);
        assertEquals(400, bankAccount2.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount2.withdraw(-100)); //The border case is minimum, where the amount can't be in the negatives
        bankAccount2.withdraw(0); //This border case is nominal value or just above the minimum where the amount can be 0 because it's less than the amount but also not in the negatives
        assertEquals(400, bankAccount2.getBalance());
        bankAccount2.withdraw(400);
        assertEquals(0, bankAccount2.getBalance());
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com")); //boundary, username and domain name must be at least one character
        assertTrue(BankAccount.isEmailValid("2@c.com")); // same as above
        assertTrue(BankAccount.isEmailValid("2@c.edu")); // ...
        assertFalse(BankAccount.isEmailValid("")); // boundary, needs @ ., empty case
        assertFalse(BankAccount.isEmailValid("a.com")); // boundary, missing @
        assertFalse(BankAccount.isEmailValid("a@b")); // boundary, missing .
        assertFalse(BankAccount.isEmailValid("abc")); //needs @ and . 

        assertTrue(BankAccount.isEmailValid("a_b@c.com")); // boundary, _-. must be surrounded by letter or number
        assertTrue(BankAccount.isEmailValid("a-b@c.com")); // same as above
        assertTrue(BankAccount.isEmailValid("ab.de@c.com")); //equivalence, now . surrounded by several other characters
        assertFalse(BankAccount.isEmailValid("ab-@c.com")); //equivalence, any username ending with - not allowed
        assertFalse(BankAccount.isEmailValid("a..b@c.com")); // equivalence, no .-_ should be next to one another
        assertFalse(BankAccount.isEmailValid("ab#c@c.com")); // equivalence, no special characters other than .-_ needed
        assertFalse(BankAccount.isEmailValid(".ab@c.com")); //equivalence, .-_ now allowed at beginning of username

        assertTrue(BankAccount.isEmailValid("ab.d@c.cc")); //boundary case 2 characters or more after period
        assertTrue(BankAccount.isEmailValid("ab.d@c-e.com")); //equivalence case, - . _ are allowed so long as they are in between two letters or numbers
        assertFalse(BankAccount.isEmailValid("ab.d@c.c")); //boundary case for same reason as previous boundary case
        assertFalse(BankAccount.isEmailValid("ab.d@c#dance.com")); //equivalence case, no special characters other than periods, underscores, dashes allowed
        assertFalse(BankAccount.isEmailValid("ab@c..com"));   //equivalence case, no special characters next to each other

        //It's boundary case should allow it to multiple of the same special characters before the @ symbol
        assertTrue(BankAccount.isEmailValid("a-b-c@c.cc"));
        //It must have characters between special characters as a boundary case
        assertFalse(BankAccount.isEmailValid("a--b@-c.cc"));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    @Test
    void isAmountValidTest(){
        assertTrue(BankAccount.isAmountValid(200.50)); //It's equivalence class must be bigger than zero and must have 2 decimal place
        assertTrue(BankAccount.isAmountValid(200.00)); //It's equivalence class cent can be zero as it's not in the negative.
        assertFalse(BankAccount.isAmountValid(200)); //It's border case is just below the maximum, where it's missing cents
        assertFalse(BankAccount.isAmountValid(-200.50)); //It's border case is just above the minimum, where the amount needs to be greater than 0
        assertFalse(BankAccount.isAmountValid(-200)); //It's border case is minimum because it's missing cents. 

        assertTrue(BankAccount.isAmountValid(0.00)); //It's border case is nominal, where it's equivalence class allows the amount to be zero since it's not negative and have cents
        assertFalse(BankAccount.isAmountValid(0)); //It's border case is just above the minimum because it's missing cents though the amount is not negative
        assertTrue(BankAccount.isAmountValid(0.1)); //It's border case is just above the minimum where it's equivalence class allows the cent to be 1 decimal since the cent is no more than 3 decimal place
        assertTrue(BankAccount.isAmountValid(0.01)); //It's border case is nominal because the cents is not greater than 2 decimal 
        assertFalse(BankAccount.isAmountValid(0.001)); //It's border case is maximum because cent is greater than 2 deminal cents
        assertFalse(BankAccount.isAmountValid(-0.01)); //It's border case is just aboe the minimum because the amount can't be negative



    }



}
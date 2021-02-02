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



}
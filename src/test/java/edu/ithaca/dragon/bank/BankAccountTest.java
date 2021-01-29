package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com")); //boundary, username and domain name must be at least one character
        assertTrue(BankAccount.isEmailValid("2@c.com")); // same as above
        assertTrue(BankAccount.isEmailValid("2@c.edu")); // ...
        assertFalse(BankAccount.isEmailValid("")); // boundary, needs @ .
        assertFalse(BankAccount.isEmailValid("a.com")); // boundary, missing @
        assertFalse(BankAccount.isEmailValid("a@b")); // boundary, missing .
        assertFalse(BankAccount.isEmailValid("abc"));

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
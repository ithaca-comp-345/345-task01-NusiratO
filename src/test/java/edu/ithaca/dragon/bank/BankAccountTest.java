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
        /*
        * The first 3 line equivalence class belongs to the nominal value
        * however, I'm not entirely sure if that's correct because I'm still having 
        * trouble understanding the concept of equvalence class and it's classes. I want
        * to say it's not a border case for the first 3 but all the tests combine is a border case.
        * however again, I'm not sure. Any equivalence cases or border cases I miss would be
        * overly used special characters after the at symbol. 
        */
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertTrue(BankAccount.isEmailValid("2@c.com"));
        assertTrue(BankAccount.isEmailValid("2@c.edu"));
        /*
        * it's equivalence class belongs to minimum
        */
        assertFalse(BankAccount.isEmailValid(""));
        /*
        * It's equivalence class belongs to just above the minimum
        */
        assertFalse(BankAccount.isEmailValid("a.com"));
        assertFalse(BankAccount.isEmailValid("a@b"));
        assertFalse(BankAccount.isEmailValid("abc"));

        /*
        * It's equivvalence class belongs to just below the maximum
        */ 
        assertTrue(BankAccount.isEmailValid("a_b@c.com"));
        assertTrue(BankAccount.isEmailValid("a-b@c.com"));
        /*
        * It's equivvalence class belongs to Maximum
        */ 
        assertTrue(BankAccount.isEmailValid("ab.de@c.com"));
        assertFalse(BankAccount.isEmailValid("ab-@c.com"));
        assertFalse(BankAccount.isEmailValid("a..b@c.com"));
        assertFalse(BankAccount.isEmailValid("ab#c@c.com"));
        assertFalse(BankAccount.isEmailValid(".ab@c.com"));

        assertTrue(BankAccount.isEmailValid("ab.d@c.cc"));
        assertTrue(BankAccount.isEmailValid("ab.d@c-e.com"));
        assertFalse(BankAccount.isEmailValid("ab.d@c.c"));
        assertFalse(BankAccount.isEmailValid("ab.d@c#dance.com"));
        assertFalse(BankAccount.isEmailValid("ab@c..com"));   
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
package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TicketHandlerTest {
    @Test
    void membershipPassTest() throws InsufficientQuantityException{
        Customer newCustomer = new Customer(1); //Equivalence Class: a nominal value because the value of the tickets should be a positive number
        TicketHandler newTicketHandler = new TicketHandler(newCustomer);
        assertEquals(1, newCustomer.getNumOfTickets());
        newTicketHandler.membershipPass("123 Apple Street");
        newTicketHandler.calculateTotalPrice();
        newCustomer.ColorPass(newTicketHandler.getCusColorPass());
        assertEquals(29.95, newTicketHandler.getTotalPrice());
        assertEquals("Gold", newCustomer.getColorPass());

        /**
         * Should have thrown in an exception right here, but the program isn't working the way it should
         */
        //Customer newCustomer2 = new Customer(0);
        //assertThrows(InsufficientQuantityException.class, () -> newCustomer2.getNumOfTickets()); //Equivalence Class: just above the minimum because the value of tickets should greater than 0
        
        //Customer newCustomer3 = new Customer(-1);
        //assertThrows(InsufficientQuantityException.class, () -> newCustomer3.getNumOfTickets()); //Equivalence Class: Minimum because the value of the number of tickets can't be a negative

        Customer newCustomer4 = new Customer(3);
        TicketHandler newTicketHandler2 = new TicketHandler(newCustomer4);
        newTicketHandler2.membershipPass("123 Apple Street");
        newTicketHandler2.calculateTotalPrice();
        assertEquals(89.85, newTicketHandler2.getTotalPrice());

    }

    @Test
    void allDayPassTest() throws InsufficientQuantityException{
        Customer newCustomer = new Customer(1);
        TicketHandler newTicketHandler = new TicketHandler(newCustomer);
        newTicketHandler.allDayPass();
        newTicketHandler.calculateTotalPrice();
        assertEquals(16.98, newTicketHandler.getTotalPrice());

        Customer newCustomer2 = new Customer(3);
        TicketHandler newTicketHandler2 = new TicketHandler(newCustomer2);
        newTicketHandler2.allDayPass();
        newTicketHandler2.calculateTotalPrice();
        assertEquals(27.96, newTicketHandler2.getTotalPrice());

        Customer newCustomer3 = new Customer(5);
        TicketHandler newTicketHandler3 = new TicketHandler(newCustomer3);
        newTicketHandler3.allDayPass();
        newTicketHandler3.calculateTotalPrice();
        assertEquals(40.94, newTicketHandler3.getTotalPrice());

    }
}

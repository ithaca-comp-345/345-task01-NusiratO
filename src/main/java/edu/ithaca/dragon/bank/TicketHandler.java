package edu.ithaca.dragon.bank;

public class TicketHandler {
    private Customer quantity;
    private Customer colorPass;
    private Customer braceletID;
    private double totalPrice;

    public TicketHandler(Customer cusNumTickets){
        quantity = cusNumTickets;
    }

    public void membershipPass(String billingAddress){

    }

    public void allDayPass(){

    }

    public void halfTheDayPass(){

    }

    public void ExtendedStay(Customer braceletID, float hours){

    }

    public void groupTrip(){

    }

    public void calculateTotalPrice(){

    }

    public double getTotalPrice() {
        return totalPrice;
    }
}

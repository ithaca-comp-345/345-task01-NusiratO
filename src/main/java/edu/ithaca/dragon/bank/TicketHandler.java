package edu.ithaca.dragon.bank;

public class TicketHandler {
    private Customer quantity;
    private String cusColorPass;
    private Customer braceletID;
    private double totalPrice;

    public TicketHandler(Customer cusNumTickets){
        quantity = cusNumTickets;
    }

    public void membershipPass(String billingAddress){
        totalPrice = (5.99 + 5.99 * (4.00)) * (quantity.getNumOfTickets());
        totalPrice = Math.round(totalPrice * 100.0) / 100.0;
        cusColorPass = "Gold";
    
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

    public String getCusColorPass(){
        return cusColorPass;
    }
}

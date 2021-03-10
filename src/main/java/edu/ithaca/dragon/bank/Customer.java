package edu.ithaca.dragon.bank;

public class Customer {
    private int numOfTickets;
    private String colorPass;
    private int bracletID;
    private float bill;

    public Customer(int quantity) throws InsufficientQuantityException{
        if(quantity < 1){
            throw new InsufficientQuantityException("incorrect number of tickets");
        }
        else{
            numOfTickets = quantity;
        }   
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public void ColorPass(String passColor){
        if(passColor.equals("Gold")){
            colorPass = "Gold";
        }
    }

    public String getColorPass(){
        return colorPass;
    }

    

    
}

package com.example.resto;

public class Basket {
    static double total=0.0d;

    public static double getTotal() {
        return total;
    }

    public static void setTotal(double total) {
        Basket.total = total;
    }


    public static void addtotal(double price){

        total +=price;
    }

    public void delete(double price){
        total -=price;
    }
}



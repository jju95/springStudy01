package hello.core.SingletonTest;

public class StatefulService {

    private int price; // 무상태로 지정해야함

    public int order(String name, int price){
        System.out.println("name = " + name + " price = "+price);
        //this.price = price;
        return price;
    }

}

package helper;

import java.util.Random;

public class Medicine {
    String name;
    Integer schedule;
    Integer quantity;

    public Medicine(String name) {
        this.name = name;
        
        Random rand1 = new Random();
        Random rand2 = new Random();
        
        this.schedule = rand1.nextInt(4) + 1;
        this.quantity = rand2.nextInt(4) + 1;
    }
    
    public String getName() {
        return name;
    }

    public Integer getSchedule() {
        return schedule;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

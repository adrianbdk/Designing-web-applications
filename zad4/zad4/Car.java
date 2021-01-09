package zad4;
public class Car implements Comparable<Car>{
    String brand;
    String model;
    int horsePower;
    int productionYear;

    public Car(String brand, String model, int horsePower, int productionYear){
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
        this.productionYear = productionYear;
    }

    @Override
    public int compareTo(Car obj) {
        if(this.horsePower != obj.horsePower)
            return this.horsePower - obj.horsePower;
        else return this.productionYear - obj.productionYear;
    }

    public String toString(){
        return "Car{" +
                "brand = '" + brand + '\'' +
                ", model = '" +  model + '\'' +
                ", horsePower = " + horsePower + '\'' +
                ", productionYear=" +  productionYear +
                '}';
    }
}

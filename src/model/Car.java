package model;

public class Car {
    private Integer id = 0;
    private String make;
    private String model;
    private int year;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    private String condition;
    private Double price;

    public Car() {

    }
    public Car(String make, String model, int year, String condition, Double price) {
        id++;
        this.make = make;
        this.model = model;
        this.year = year;
        this.condition = condition;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", condition='" + condition + '\'' +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }



    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

package ru.bgbrakhi.carseller.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_city")
    private City city;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carmodel")
    private CarModel carmodel;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carbody")
    private CarBody carbody;

    @Column(name = "year")
    @NotNull
    private Integer year;

    @Column(name = "price")
    @NotNull
    private Integer price;

    @Column(name = "filename")
    private String filename;

    @Column(name = "inactive", columnDefinition = "boolean default false")
    @NotNull
    private Boolean inactive;

    @Column(name = "timestamp")
    private Long timestamp;

    public Car() {
        timestamp = System.currentTimeMillis();
    }

    public Car(User user, City city, CarModel carmodel, CarBody carbody, @NotNull Integer year,
               @NotNull Integer price, String filename, @NotNull Boolean inactive, Long timestamp) {
        this.user = user;
        this.city = city;
        this.carmodel = carmodel;
        this.carbody = carbody;
        this.year = year;
        this.price = price;
        this.filename = filename;
        this.inactive = inactive;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public CarModel getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(CarModel carmodel) {
        this.carmodel = carmodel;
    }

    public CarBody getCarbody() {
        return carbody;
    }

    public void setCarbody(CarBody carbody) {
        this.carbody = carbody;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFilename() {
        return filename == null ? "" : filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Boolean getInactive() {
        return inactive == null ? false : inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public String getTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return dateFormat.format(new Date(this.timestamp));
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && Objects.equals(carmodel, car.carmodel)
                && Objects.equals(carbody, car.carbody)
                && Objects.equals(year, car.year);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, carmodel, carbody, year);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", carmodel=" + carmodel
                + ", carbody=" + carbody
                + ", year=" + year
                + '}';
    }

    public static void main(String[] args) {
        Date d = new Date(1567591940658L);
        int a = 0;
    }
}

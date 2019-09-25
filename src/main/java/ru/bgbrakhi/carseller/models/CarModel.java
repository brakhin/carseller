package ru.bgbrakhi.carseller.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "ref_carmodel")
public class CarModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cartype")
    private CarType cartype;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carmark")
    private CarMark carmark;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    public CarModel() {
    }

    public CarModel(String name) {
        this.name = name;
    }

    public CarModel(CarType cartype, CarMark carmark, @NotNull String name) {
        this.cartype = cartype;
        this.carmark = carmark;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CarType getCartype() {
        return cartype;
    }

    public void setCartype(CarType cartype) {
        this.cartype = cartype;
    }

    public CarMark getCarmark() {
        return carmark;
    }

    public void setCarmark(CarMark carmark) {
        this.carmark = carmark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarModel{"
                + "id=" + id
                + ", cartype=" + cartype
                + ", carmark=" + carmark
                + ", name='" + name
                + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CarModel carModel = (CarModel) o;
        return id == carModel.id
                && Objects.equals(cartype, carModel.cartype)
                && Objects.equals(carmark, carModel.carmark)
                && Objects.equals(name, carModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartype, carmark, name);
    }
}

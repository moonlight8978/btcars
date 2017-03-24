package com.btcars.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Orderlist.
 */
@Entity
@Table(name = "orderlist")
public class Orderlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ho", nullable = false)
    private String ho;

    @NotNull
    @Column(name = "ten", nullable = false)
    private String ten;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "total", nullable = false)
    private Long total;

    @ManyToMany
    @JoinTable(name = "orderlist_car",
               joinColumns = @JoinColumn(name="orderlists_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="cars_id", referencedColumnName="id"))
    private Set<Car> cars = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHo() {
        return ho;
    }

    public Orderlist ho(String ho) {
        this.ho = ho;
        return this;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public Orderlist ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getAddress() {
        return address;
    }

    public Orderlist address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTotal() {
        return total;
    }

    public Orderlist total(Long total) {
        this.total = total;
        return this;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public Orderlist cars(Set<Car> cars) {
        this.cars = cars;
        return this;
    }

    public Orderlist addCar(Car car) {
        this.cars.add(car);
        car.getOrderlists().add(this);
        return this;
    }

    public Orderlist removeCar(Car car) {
        this.cars.remove(car);
        car.getOrderlists().remove(this);
        return this;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Orderlist orderlist = (Orderlist) o;
        if (orderlist.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, orderlist.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Orderlist{" +
            "id=" + id +
            ", ho='" + ho + "'" +
            ", ten='" + ten + "'" +
            ", address='" + address + "'" +
            ", total='" + total + "'" +
            '}';
    }
}

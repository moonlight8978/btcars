package com.btcars.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "customer_cart",
               joinColumns = @JoinColumn(name="customers_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="carts_id", referencedColumnName="id"))
    private Set<Car> carts = new HashSet<>();

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Car> getCarts() {
        return carts;
    }

    public Customer carts(Set<Car> cars) {
        this.carts = cars;
        return this;
    }

    public Customer addCart(Car car) {
        this.carts.add(car);
        car.getCarts().add(this);
        return this;
    }

    public Customer removeCart(Car car) {
        this.carts.remove(car);
        car.getCarts().remove(this);
        return this;
    }

    public void setCarts(Set<Car> cars) {
        this.carts = cars;
    }

    public User getUser() {
        return user;
    }

    public Customer user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        if (customer.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            '}';
    }
}

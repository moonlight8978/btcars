package com.btcars.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Recommend.
 */
@Entity
@Table(name = "recommend")
public class Recommend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private Car car;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public Recommend car(Car car) {
        this.car = car;
        return this;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Recommend recommend = (Recommend) o;
        if (recommend.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, recommend.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Recommend{" +
            "id=" + id +
            '}';
    }
}

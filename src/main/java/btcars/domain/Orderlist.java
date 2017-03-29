package btcars.domain;

import org.springframework.data.elasticsearch.annotations.Document;

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
@Document(indexName = "orderlist")
public class Orderlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @NotNull
    @Column(name = "lastname", nullable = false)
    private String lastname;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull
    @Column(name = "phonenumber", nullable = false)
    private Integer phonenumber;

    @NotNull
    @Column(name = "total", nullable = false)
    private Integer total;

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

    public String getFirstname() {
        return firstname;
    }

    public Orderlist firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Orderlist lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public Integer getPhonenumber() {
        return phonenumber;
    }

    public Orderlist phonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }

    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getTotal() {
        return total;
    }

    public Orderlist total(Integer total) {
        this.total = total;
        return this;
    }

    public void setTotal(Integer total) {
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
            ", firstname='" + firstname + "'" +
            ", lastname='" + lastname + "'" +
            ", address='" + address + "'" +
            ", phonenumber='" + phonenumber + "'" +
            ", total='" + total + "'" +
            '}';
    }
}

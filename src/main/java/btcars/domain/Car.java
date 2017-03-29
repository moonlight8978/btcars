package btcars.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Car.
 */
@Entity
@Table(name = "car")
@Document(indexName = "car")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "make", nullable = false)
    private String make;

    @NotNull
    @Column(name = "model", nullable = false)
    private String model;

    @NotNull
    @Column(name = "year", nullable = false)
    private Integer year;

    @NotNull
    @Column(name = "trim", nullable = false)
    private String trim;

    @NotNull
    @Column(name = "country", nullable = false)
    private String country;

    @NotNull
    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "ed")
    private Integer ed;

    @Column(name = "ev")
    private Integer ev;

    @Column(name = "emp")
    private Integer emp;

    @Column(name = "w")
    private Integer w;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @NotNull
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull
    @Column(name = "sold", nullable = false)
    private Integer sold;

    @ManyToMany(mappedBy = "cars")
    @JsonIgnore
    private Set<Customer> customers = new HashSet<>();

    @ManyToMany(mappedBy = "cars")
    @JsonIgnore
    private Set<Orderlist> orderlists = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public Car make(String make) {
        this.make = make;
        return this;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public Car model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public Car year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTrim() {
        return trim;
    }

    public Car trim(String trim) {
        this.trim = trim;
        return this;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getCountry() {
        return country;
    }

    public Car country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPrice() {
        return price;
    }

    public Car price(Integer price) {
        this.price = price;
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getEd() {
        return ed;
    }

    public Car ed(Integer ed) {
        this.ed = ed;
        return this;
    }

    public void setEd(Integer ed) {
        this.ed = ed;
    }

    public Integer getEv() {
        return ev;
    }

    public Car ev(Integer ev) {
        this.ev = ev;
        return this;
    }

    public void setEv(Integer ev) {
        this.ev = ev;
    }

    public Integer getEmp() {
        return emp;
    }

    public Car emp(Integer emp) {
        this.emp = emp;
        return this;
    }

    public void setEmp(Integer emp) {
        this.emp = emp;
    }

    public Integer getW() {
        return w;
    }

    public Car w(Integer w) {
        this.w = w;
        return this;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getStatus() {
        return status;
    }

    public Car status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Car quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSold() {
        return sold;
    }

    public Car sold(Integer sold) {
        this.sold = sold;
        return this;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public Car customers(Set<Customer> customers) {
        this.customers = customers;
        return this;
    }

    public Car addCustomer(Customer customer) {
        this.customers.add(customer);
        customer.getCars().add(this);
        return this;
    }

    public Car removeCustomer(Customer customer) {
        this.customers.remove(customer);
        customer.getCars().remove(this);
        return this;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Orderlist> getOrderlists() {
        return orderlists;
    }

    public Car orderlists(Set<Orderlist> orderlists) {
        this.orderlists = orderlists;
        return this;
    }

    public Car addOrderlist(Orderlist orderlist) {
        this.orderlists.add(orderlist);
        orderlist.getCars().add(this);
        return this;
    }

    public Car removeOrderlist(Orderlist orderlist) {
        this.orderlists.remove(orderlist);
        orderlist.getCars().remove(this);
        return this;
    }

    public void setOrderlists(Set<Orderlist> orderlists) {
        this.orderlists = orderlists;
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
        if (car.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + id +
            ", make='" + make + "'" +
            ", model='" + model + "'" +
            ", year='" + year + "'" +
            ", trim='" + trim + "'" +
            ", country='" + country + "'" +
            ", price='" + price + "'" +
            ", ed='" + ed + "'" +
            ", ev='" + ev + "'" +
            ", emp='" + emp + "'" +
            ", w='" + w + "'" +
            ", status='" + status + "'" +
            ", quantity='" + quantity + "'" +
            ", sold='" + sold + "'" +
            '}';
    }
}

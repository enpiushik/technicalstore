package technicalstore.technics.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "Technic")
@Table(name = "technics")
public class TechnicEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Device should not be empty!")
    @Size(max = 200)
    @Column(name = "device", length = 200, nullable = false)
    private String device;

    @NotEmpty(message = "ISBN should not be empty!")
    @Size(min = 10, max = 50)
    @Column(name = "isbn", length = 50, unique = true, nullable = false)
    private String isbn;

    @NotEmpty(message = "Company should not be empty!")
    @Size(max = 200)
    @Column(name = "company", length = 200, nullable = false)
    private String company;

    @Min(value = 1)
    @NotNull
    @Column(name = "year", nullable = false)
    private int year;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

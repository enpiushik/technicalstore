package technicalstore.technics.model;

import technicalstore.auth.model.UserEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity (name = "Reservation")
@Table (name = "reservations")
public class ReservationEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private TechnicEntity technic;
    @ManyToOne
    private UserEntity user;
    @Column
    @Enumerated (EnumType.STRING)
    private ReservationStatus status;
    @Column
    private LocalDateTime created;

    @PrePersist
    public void onCreate() {
        created = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TechnicEntity getTechnic() {
        return technic;
    }

    public void setTechnic(TechnicEntity technic) {
        this.technic = technic;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}

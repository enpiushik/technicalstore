package technicalstore.technics.boundary;

import technicalstore.auth.boundary.CurrentUser;
import technicalstore.technics.model.ReservationEntity;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ViewScoped
@Named
public class MyTechnicsBean implements Serializable {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private CurrentUser currentUser;
    private List<ReservationEntity> availableResult;
    private List<ReservationEntity> inQueueResult;
    private List<ReservationEntity> takenResult;
    private List<ReservationEntity> closedResult;

    public void prepare(){
        availableResult = new ArrayList<>();
        inQueueResult = new ArrayList<>();
        closedResult = new ArrayList<>();
        List<ReservationEntity> userReservations = em.createQuery("select r from Reservation r where r.user = :user and r.status = 'ACTIVE'", ReservationEntity.class)
                .setParameter("user", currentUser.getUser())
                .getResultList();

        for (ReservationEntity r : userReservations) {
            Long reservationId = r.getId();
            Optional<ReservationEntity> firstReservation = em.createQuery("select r from Reservation r where r.technic = :technic and r.status <> 'CLOSED' order by r.created", ReservationEntity.class)
                    .setParameter("technic", r.getTechnic())
                    .getResultStream()
                    .findFirst();

            if (firstReservation.isEmpty() || firstReservation.get().getId().equals(reservationId)){
                availableResult.add(r);
            } else {
                inQueueResult.add(r);
            }
        }
        takenResult = em.createQuery("select r from Reservation r where r.user = :user and r.status = 'TAKEN'", ReservationEntity.class)
                .setParameter("user", currentUser.getUser())
                .getResultList();

        closedResult = em.createQuery("select distinct r from Reservation r where r.user = :user and r.status = 'CLOSED'", ReservationEntity.class)
                .setParameter("user", currentUser.getUser())
                .getResultList();
    }

    public List<ReservationEntity> getAvailableTechnics() {
        return availableResult;
    }

    public List<ReservationEntity> getInQueueTechnics() {
        return inQueueResult;
    }

    public List<ReservationEntity> getTakenTechnics() {
        return takenResult;
    }

    public List<ReservationEntity> getClosedResult() {
        return closedResult;
    }
}

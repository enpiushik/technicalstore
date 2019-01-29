package technicalstore.technics.boundary;

import technicalstore.technics.model.ReservationEntity;
import technicalstore.technics.model.ReservationStatus;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
@ViewScoped
public class ManageTechnicBean implements Serializable {
    @PersistenceContext
    private EntityManager em;
    private List<ReservationEntity> availableResult;
    private List<ReservationEntity> takenResult;
    private List<ReservationEntity> closedResult;

    public void prepare(){
        availableResult = new ArrayList<>();
        closedResult = new ArrayList<>();
        List<ReservationEntity> userReservations = em.createQuery("select r from Reservation r where r.status = 'ACTIVE'", ReservationEntity.class)
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
                availableResult.add(r);
            }
        }
        takenResult = em.createQuery("select r from Reservation r where r.status = 'TAKEN'", ReservationEntity.class)
                .getResultList();

        closedResult = em.createQuery("select r from Reservation r where r.status = 'CLOSED'", ReservationEntity.class)
                .getResultList();
    }

    @Transactional
    public void giveTechnic(ReservationEntity reservation) {
        ReservationEntity r = em.merge(reservation);
        r.setStatus(ReservationStatus.TAKEN);
        prepare();
    }

    @Transactional
    public void takeTechnic(ReservationEntity reservation) {
        ReservationEntity r = em.merge(reservation);
        r.setStatus(ReservationStatus.CLOSED);
        prepare();
    }

    public List<ReservationEntity> getAvailableTechnics() {
        return availableResult;
    }

    public List<ReservationEntity> getTakenTechnics() {
        return takenResult;
    }

    public List<ReservationEntity> getClosedResult() {
        return closedResult;
    }
}

package technicalstore.technics.boundary;

import technicalstore.auth.boundary.CurrentUser;
import technicalstore.technics.model.ReservationEntity;
import technicalstore.technics.model.ReservationStatus;
import technicalstore.technics.model.TechnicEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Named
public class TechnicReservationBean {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private CurrentUser currentUser;

    public void reserve(Long id){
        System.out.println("Trying to reserve a technic " + id + "for user " + currentUser.getUser().getId());

        TechnicEntity technic = em.find(TechnicEntity.class, id);
        ReservationEntity reservation = new ReservationEntity();
        reservation.setTechnic(technic);
        reservation.setUser(currentUser.getUser());
        reservation.setStatus(ReservationStatus.ACTIVE);
        em.persist(reservation);
    }
    public List<ReservationEntity> getReservation(){
        return em.createQuery("select r from Reservation r", ReservationEntity.class).getResultList();
    }
}

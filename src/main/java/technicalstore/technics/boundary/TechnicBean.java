package technicalstore.technics.boundary;

import technicalstore.technics.model.TechnicEntity;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;

@ViewScoped
@Named
public class TechnicBean implements Serializable {
    @PersistenceContext
    private EntityManager em;
    private Long id;
    private TechnicEntity technic;
    private boolean updated = false;

    public void openTechnic() {
        System.out.println("Opening technic " + id);
        technic = em.find(TechnicEntity.class, id);
    }

    @Transactional
    public String updateTechnics() {
        em.merge(technic);
        updated = true;
        return null;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public boolean isUpdated() {
        return updated;
    }

    public TechnicEntity getTechnic() {
        return technic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

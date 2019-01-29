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
public class NewTechnicBean implements Serializable {
    @PersistenceContext
    private EntityManager em;
    private TechnicEntity technic = new TechnicEntity();
    private boolean created = false;

    @Transactional
    public String createTechnic(){
        em.persist(technic);
        technic = new TechnicEntity();
        created = true;
        return null;
    }

    public boolean isCreated() {
        return created;
    }

    public TechnicEntity getTechnic() {
        return technic;
    }

    public void setTechnic(TechnicEntity technic) {
        this.technic = technic;
    }
}

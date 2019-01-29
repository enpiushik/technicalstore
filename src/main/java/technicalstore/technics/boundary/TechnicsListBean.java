package technicalstore.technics.boundary;

import technicalstore.technics.model.TechnicEntity;

import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class TechnicsListBean implements Serializable {
    @PersistenceContext
    private EntityManager em;
    private String term;
    private Long id;

    private boolean deleted = false;

    public List<TechnicEntity> getTechnics(){
        if (term == null) {
            return em.createQuery("select t from Technic t", TechnicEntity.class).getResultList();
        } else {
            return em.createQuery("select t from Technic t where lower(t.device) like :term", TechnicEntity.class)
                    .setParameter("term", "%" + term.toLowerCase() + "%")
                    .getResultList();
        }
    }

    @Transactional
    public String deleteTechnics() {
        TechnicEntity technic;
        technic = em.find(TechnicEntity.class, id);
        em.remove(technic);
        deleted = true;
        return null;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void doSearch() {
        System.out.println("SEARCH!!!");
    }
}

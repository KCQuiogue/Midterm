package edu.benilde.appintr.midterm.service;

import edu.benilde.appintr.midterm.model.TermGrade;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class TermGradeService {
    @PersistenceContext
    private EntityManager entityManager;
    public void create(TermGrade termGrade) {
        entityManager.persist(termGrade);
    }
    public List<TermGrade> list() {
        return entityManager
                .createQuery("SELECT g from TermGrade g", TermGrade.class)
                .getResultList();
    }

    public Double getTotalGP() {
        return entityManager
                .createQuery("SELECT SUM(gradePoint) FROM TermGrade", Double.class)
                .getSingleResult();
    }

    public Double getTotalUnits() {
        return entityManager
                .createQuery("SELECT SUM(units) FROM TermGrade WHERE grade > 0", Double.class)
                .getSingleResult();
    }

}

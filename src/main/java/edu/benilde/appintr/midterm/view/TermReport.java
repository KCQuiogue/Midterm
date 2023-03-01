package edu.benilde.appintr.midterm.view;

import edu.benilde.appintr.midterm.model.TermGrade;
import edu.benilde.appintr.midterm.service.TermGradeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named @RequestScoped
public class TermReport {
    private final TermGrade termGrade = new TermGrade();
    private List<TermGrade> termGrades;
    private Double totalUnits;
    private Double totalGP;
    private Double termGPA;

    @Inject
    private TermGradeService termGradeService;
    @PostConstruct
    public void init() {
        termGrades = termGradeService.list();
    }
    public void add() {
        termGradeService.create(termGrade);
        termGrades.add(termGrade);
    }
    public TermGrade getTermGrade() { return termGrade; }
    public List<TermGrade> getTermGrades() { return termGrades; }

    public void calc() {
        totalGP = termGradeService.getTotalGP();
        totalUnits = termGradeService.getTotalUnits();
        termGPA = totalGP / totalUnits;
    }

    public Double getTotalUnits() { return totalUnits; }

    public Double getTotalGP() { return totalGP; }

    public Double getTermGPA() { return termGPA; }
}

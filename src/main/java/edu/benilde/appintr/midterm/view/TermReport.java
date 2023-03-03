package edu.benilde.appintr.midterm.view;

import edu.benilde.appintr.midterm.model.TermGrade;
import edu.benilde.appintr.midterm.service.TermGradeService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.Size;

import java.util.List;

@Named @RequestScoped
public class TermReport {
    private final TermGrade termGrade = new TermGrade();
    @Size( max = 7 )
    private List<TermGrade> termGrades;
    private Double totalUnits;
    private Double totalGP;
    private Double termGPA;
    private Long count;

    public TermReport() {
        count = 0L;
    }

    @Inject
    private TermGradeService termGradeService;
    @PostConstruct
    public void init() {
        termGrades = termGradeService.list();
    }

    public void check() {
        count = termGradeService.countEntries();
    }
    public void add() {
        if ( count < 8L) {
            termGradeService.create(termGrade);
            termGrades.add(termGrade);
        }
    }

    public void compute() {
        totalGP = termGradeService.getTotalGP();
        totalUnits = termGradeService.getTotalUnits();
        termGPA = totalGP / totalUnits;
    }

    public TermGrade getTermGrade() { return termGrade; }
    public List<TermGrade> getTermGrades() { return termGrades; }

    public Double getTotalUnits() { return totalUnits; }

    public Double getTotalGP() { return totalGP; }

    public Double getTermGPA() { return termGPA; }

    public void setTotalUnits(Double totalUnits) { this.totalUnits = totalUnits; }

    public void setTotalGP(Double totalGP) { this.totalGP = totalGP; }

    public void setTermGPA(Double termGPA) { this.termGPA = termGPA; }
}

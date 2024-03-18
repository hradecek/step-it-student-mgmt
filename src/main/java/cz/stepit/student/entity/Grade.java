package cz.stepit.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import static cz.stepit.student.entity.Grade.TABLE_NAME;

/**
 * Represents a {@link Student}'s grade for specific {@link Subject}.
 */
@Entity
@Table(name = TABLE_NAME)
public class Grade {

    public static final String TABLE_NAME = "grades";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int grade;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    /**
     * Constructor.
     */
    protected Grade() {
        // No-arg constructor required by JPA
    }

    /**
     * Constructor.
     *
     * @param subject subject
     * @param grade grade
     */
    public Grade(Subject subject, int grade) {
        this(0L, subject, grade);
    }

    /**
     * Constructor.
     *
     * @param id ID
     * @param subject subject
     * @param grade grade
     */
    public Grade(long id, Subject subject, int grade) {
        this.id = id;
        this.subject = subject;
        this.grade = grade;
    }

    /**
     * Get ID.
     *
     * @return ID
     */
    public long getId() {
        return id;
    }

    /**
     * Get student.
     *
     * @return student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Set the student.
     *
     * @param student student
     */
    protected void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Get subject.
     *
     * @return subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Get grade.
     *
     * @return grade
     */
    public int getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Grade{subject=" + subject + ", grade=" + grade + '}';
    }
}

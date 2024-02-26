package cz.stepit.student.entity;

/**
 * Represents a {@link Student}'s grade for specific {@link Subject}.
 */
public class Grade {

    private final long id;
    private final Subject subject;
    private final int grade;

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

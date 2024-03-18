package cz.stepit.student.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import static cz.stepit.student.entity.Subject.TABLE_NAME;

/**
 * Represents a school subject.
 */
@Entity
@Table(name = TABLE_NAME)
public class Subject {

    public static final String TABLE_NAME = "subjects";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String name;

    /**
     * Constructor.
     */
    protected Subject() {
        // No-arg constructor required by JPA
    }

    /**
     * Constructor.
     *
     * @param name subject's name
     */
    public Subject(String name) {
        this(0L, name);
    }

    /**
     * Constructor.
     *
     * @param id subject's ID
     * @param name subject's name
     */
    public Subject(long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get subject's ID.
     *
     * @return ID
     */
    public long getId() {
        return id;
    }

    /**
     * Get subject's name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Subject{name='" + name + '}';
    }
}

package cz.stepit.student.entity;

/**
 * Represents a school subject.
 */
public class Subject {

    private final long id;
    private final String name;

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

package cz.stepit.student.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represents a student.
 */
public class Student {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final List<Grade> grades = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param firstName student's firstname
     * @param lastName student's lastname
     */
    public Student(String firstName, String lastName) {
        this(0L, firstName, lastName);
    }

    /**
     * Constructor.
     *
     * @param id ID
     * @param firstName firstname
     * @param lastName lastname
     */
    public Student(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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
     * Get student's firstname.
     *
     * @return firstname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get student's lastname.
     *
     * @return lastname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get all student's grades.
     *
     * @return get grades
     */
    public List<Grade> getGrades() {
        return grades;
    }

    /**
     * Get averages per subject.
     *
     * @return average grades per subject
     */
    public Map<String, Float> getAverages() {
        final var sumGrades = new HashMap<String, Integer>();
        final var countGrades = new HashMap<String, Integer>();

        for (var grade : grades) {
            final var subjectName = grade.getSubject().getName();
            final int gradeValue = grade.getGrade();

            sumGrades.put(subjectName, sumGrades.getOrDefault(subjectName, 0) + gradeValue);
            countGrades.put(subjectName, countGrades.getOrDefault(subjectName, 0) + 1);
        }

        final var averageGrades = new HashMap<String, Float>();
        for (var entry : sumGrades.entrySet()) {
            final var subjectName = entry.getKey();
            final int sum = entry.getValue();
            final int count = countGrades.get(subjectName);
            final float average = (float) sum / count;
            averageGrades.put(subjectName, average);
        }

        return averageGrades;
    }

    /**
     * Get average for all subjects.
     *
     * @return average
     */
    public double getAverage() {
        final var maybeAverage = grades.stream().mapToInt(Grade::getGrade).average();
        return maybeAverage.orElseGet(() -> 0.0d);
    }

    /**
     * Adds new {@code grade}.
     *
     * @param grade grade
     */
    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    /**
     * Get set of subjects students attends.
     *
     * @return set of subjects
     */
    public Set<Subject> getSubjects() {
        return grades.stream().map(Grade::getSubject).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", grades=" + grades +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final var otherStudent = (Student) object;

        return id == otherStudent.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

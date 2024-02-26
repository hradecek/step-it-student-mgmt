package cz.stepit.student.repository;

import cz.stepit.student.entity.Grade;
import cz.stepit.student.entity.Student;
import cz.stepit.student.entity.Subject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class StudentRepository {

    private static final String SQL_CREATE =
            """
            INSERT INTO students (firstname, lastname)
            VALUES (?, ?)
            """;

    private static final String SQL_UPDATE =
            """
            UPDATE students SET firstname = ?, lastname = ?
            WHERE id = ?
            """;

     private static final String SQL_FIND_BY_ID =
             """
             SELECT
               s.id AS studentId, 
               s.firstname AS firstname,
               s.lastname AS lastname, 
               g.id AS gradeId, 
               g.grade AS grade,
               subj.id AS subjectId,
               subj.name AS subjectName
             FROM students s
             LEFT JOIN grades g ON g.student_id = s.id
             LEFT JOIN subjects subj ON subj.id = g.subject_id
             WHERE s.id = ?
             """;

    private static final String SQL_FIND_ALL =
            """
            SELECT
              s.id AS studentId, 
              s.firstname AS firstname,
              s.lastname AS lastname, 
              g.id AS gradeId, 
              g.grade AS grade,
              subj.id AS subjectId,
              subj.name AS subjectName
            FROM students s
            LEFT JOIN grades g ON g.student_id = s.id
            LEFT JOIN subjects subj ON subj.id = g.subject_id
            """;

    private static final String SQL_DELETE =
            """
            DELETE FROM students WHERE id = ?
            """;

    private static final String SQL_INSERT_GRADE =
            """
            INSERT INTO grades (grade, student_id, subject_id)
            VALUES (?, ?, ?)
            """;

    private final Connection connection;

    public StudentRepository(Connection connection) {
        this.connection = connection;
    }

    public Student create(Student student) {
        try {
            final var insert = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
            insert.setString(1, student.getFirstName());
            insert.setString(2, student.getLastName());
            insert.execute();

            return new Student(getGeneratedId(insert), student.getFirstName(), student.getLastName());
        } catch (SQLException e) {
            throw new DBException("Cannot create student");
        }
    }

    private long getGeneratedId(Statement statement) throws SQLException {
        final var generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        throw new DBException("Cannot create student");
    }

    public void update(Student student) {
        try {
            connection.setAutoCommit(false);

            final var update = connection.prepareStatement(SQL_UPDATE);
            update.setString(1, student.getFirstName());
            update.setString(2, student.getLastName());
            update.setLong(3, student.getId());

            insertNewGrades(student);

            update.executeUpdate();

            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException rex) {
                throw new DBException("Cannot update student", rex);
            }
            throw new DBException("Cannot update student", ex);
        }
    }

    protected void insertNewGrades(Student student) throws SQLException {
        for (Grade grade : student.getGrades()) {
            if (grade.getId() == 0L) {
                insertNewGrade(grade.getGrade(), student.getId(), grade.getSubject().getId());
            }
        }
    }

    protected void insertNewGrade(int grade, long studentId, long subjectId) throws SQLException {
        final var insert = connection.prepareStatement(SQL_INSERT_GRADE);
        insert.setInt(1, grade);
        insert.setLong(2, studentId);
        insert.setLong(3, subjectId);

        insert.executeUpdate();
    }

    public Optional<Student> findById(long id) {
        try {
            final var select = connection.prepareStatement(SQL_FIND_BY_ID);
            select.setLong(1, id);

            final var resultSet = select.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }

            final var student = resultSetToStudent(resultSet);
            do {
                if (resultSet.getLong("gradeId") != 0) {
                    student.addGrade(resultSetToGrade(resultSet));
                }
            } while (resultSet.next());
            return Optional.of(student);
        } catch (SQLException ex) {
            throw new DBException("Cannot find student", ex);
        }
    }

    public List<Student> findAll() {
        try {
            final var select = connection.prepareStatement(SQL_FIND_ALL);
            final var resultSet = select.executeQuery();

            final var students = new HashMap<Long, Student>();
            while (resultSet.next()) {
                final var studentId = resultSet.getLong("studentId");
                students.putIfAbsent(studentId, resultSetToStudent(resultSet));
                if (resultSet.getLong("gradeId") != 0) {
                    students.get(studentId).addGrade(resultSetToGrade(resultSet));
                }
            }

            return new ArrayList<>(students.values());
        } catch (SQLException ex) {
            throw new DBException("Cannot get all students", ex);
        }
    }

    protected Student resultSetToStudent(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getLong("studentId"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname")
        );
    }

    protected Grade resultSetToGrade(ResultSet resultSet) throws SQLException {
        return new Grade(
                resultSet.getLong("gradeId"),
                new Subject(resultSet.getLong("subjectId"), resultSet.getString("subjectName")),
                resultSet.getInt("grade")
        );
    }

    public void delete(long id) {
        try {
            final var delete = connection.prepareStatement(SQL_DELETE);
            delete.setLong(1, id);
            delete.executeUpdate();
        } catch (SQLException ex) {
            throw new DBException("Cannot delete student", ex);
        }
    }
}

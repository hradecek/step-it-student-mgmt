package cz.stepit.student.repository;

import cz.stepit.student.entity.Subject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectRepository {

    private static final String SQL_CREATE =
            """
            INSERT INTO subjects (name)
            VALUES (?)
            """;

    private static final String SQL_FIND_ALL =
            """
            SELECT id, name
            FROM subjects
            ORDER BY id
            """;

    private static final String SQL_FIND_BY_NAME =
            """
            SELECT id, name
            FROM subjects
            WHERE name = ?
            """;

    private final Connection connection;

    public SubjectRepository(Connection connection) {
        this.connection = connection;
    }

    public void create(Subject subject) {
        try {
            final var insert = connection.prepareStatement(SQL_CREATE);
            insert.setString(1, subject.getName());
            insert.execute();
        } catch (SQLException ex) {
            throw new DBException("Cannot create subject", ex);
        }
    }

    public List<Subject> findAll() {
        try {
            final var select = connection.prepareStatement(SQL_FIND_ALL);
            final var resultSet = select.executeQuery();
            final var subjects = new ArrayList<Subject>();
            while (resultSet.next()) {
                subjects.add(resultSetToSubject(resultSet));
            }
            return subjects;
        } catch (SQLException ex) {
            throw new DBException("Cannot find subjects", ex);
        }
    }

    public Optional<Subject> findByName(String name) {
        try {
            final var select = connection.prepareStatement(SQL_FIND_BY_NAME);
            select.setString(1, name);

            final var resultSet = select.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSetToSubject(resultSet));
            }
            return Optional.empty();
        } catch (SQLException ex) {
            throw new DBException("Cannot create subject", ex);
        }
    }

    protected Subject resultSetToSubject(ResultSet resultSet) throws SQLException {
        return new Subject(resultSet.getLong("id"), resultSet.getString("name"));
    }
}

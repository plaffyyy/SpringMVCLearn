package ru.zimin.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.zimin.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_CNT = 0;

    //these parameters should be in properties file
    private static final String URL = "jdbc:postgresql://localhost:5432/learn_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "Zimin2005228007";

    private static final Connection connection;
    static {
        //этот первый блок обязателен, хотя кажется что должно прикручиваться само
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT * FROM Person"
            );
            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);

            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {
//        return people.stream()
//                .filter(person -> person.getId() == id)
//                .findAny()
//                .orElse(null);

        Person person = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Person WHERE id=?"
            );
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {
//        person.setId(++PEOPLE_CNT);
//        people.add(person);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                 "INSERT INTO Person VALUES(1, ?, ?, ?)"
            );

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(int id, Person person) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Person SET name=?, age=?, email=? WHERE id=?"
            );

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Person WHERE id=?"
            );
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

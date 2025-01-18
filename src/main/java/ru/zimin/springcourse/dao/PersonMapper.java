package ru.zimin.springcourse.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.zimin.springcourse.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setName(rs.getString("name"));
        person.setAge(rs.getInt("age"));
        person.setEmail(rs.getString("email"));
        person.setAddress(rs.getString("address"));
        person.setAdmin(rs.getBoolean("is_admin"));

        return person;
    }
}

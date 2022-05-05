package com.hitema.dao;


import com.hitema.jdbc.Channel;
import com.hitema.modeles.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class StudentJdbcDaoImpl extends Channel implements StudentService{

    private static final Logger log = LoggerFactory.getLogger(StudentJdbcDaoImpl.class);

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where type ='S' ");
            while( resultSet.next() ){
                students.add(createStudent(resultSet));
            }
        } catch (SQLException e) {
            log.error("ERROR While execute select all from users :"+e.getLocalizedMessage());
        }
        return students;
    }

    private Student createStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setGenre(rs.getString("genre"));
        student.setEmail(rs.getString("email"));
        student.setCursus(rs.getString("cursus"));
        student.setBirthDate(rs.getDate("birth_date").toLocalDate());
        return student;
    }

    @Override
    public Student create(Student student) {
        PreparedStatement statement ;
        try {
            statement = getPreparedStatement(student,false);
            Integer insert = statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();

            Integer generatedId =-1 ;
            if (keys.next()) {
                generatedId = keys.getInt(1); //id returned after insert execution
            }
            student.setId(generatedId);
        } catch (SQLException e) {
            log.error("ERROR While insert Student :"+e.getLocalizedMessage());
        }
        return student;
    }

    private PreparedStatement getPreparedStatement(Student student,boolean update) throws SQLException {
        PreparedStatement statement = null;
        if ( update ) {
            statement = getConnection().prepareStatement("UPDATE users set type = 'S', first_name=?, last_name=?, birth_date=?, genre=?, email= ?, cursus=? where id=? ", Statement.RETURN_GENERATED_KEYS);
            statement.setObject(7, student.getId());
        }else
            statement = getConnection().prepareStatement("INSERT users values(null, 'S', ?, ?, ?, ?, ?, null,  ?)", Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, student.getFirstName());
        statement.setString(2, student.getLastName());
        statement.setObject(3, student.getBirthDate());
        statement.setString(4, student.getGenre());
        statement.setString(5, student.getEmail());
        statement.setString(6, student.getCursus());

        return statement;
    }

    @Override
    public Student read(Integer id) {
        Student s = null;
        log.debug("read dao id :{}", id);
        try {
            PreparedStatement statement  = getConnection().prepareStatement("select * from users where id=?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                s = createStudent(resultSet);
            }
        } catch (SQLException e) {
            log.error("ERROR while READ Student :"+e.getLocalizedMessage());
        }
        return s;
    }

    @Override
    public Student update(Student student) {
        try {
            PreparedStatement statement = getPreparedStatement(student,true);
            int nbr = statement.executeUpdate();
            log.trace("Upade OK :"+nbr);
        } catch (SQLException e) {
            log.error("ERROR while UPDATE Student :"+e.getLocalizedMessage());
        }
        return student;
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            PreparedStatement statement  = getConnection().prepareStatement("delete from users where id=?");
            statement.setInt(1, id);
            int nbr = statement.executeUpdate();
            log.trace("Delete OK : {} , nbr deleted : {} ", (nbr==1), nbr);
            return (nbr==1);
        } catch (SQLException e) {
            log.error("ERROR while UPDATE Student :"+e.getLocalizedMessage());
            return  false;
        }
    }
}

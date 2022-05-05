package com.hitema.dao;

import com.hitema.modeles.Student;
import com.hitema.tests.JdbcTester;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;


class StudentServiceTest {

    private static final Logger log = LoggerFactory.getLogger(JdbcTester.class);
    private StudentService service;

    @BeforeAll
    void beforeAll(){
        service = new StudentJdbcDaoImpl();
    }

    @BeforeEach
    void setUp() {
        //service = new StudentJdbcDaoImpl();
    }

    @Test
    void getAll() {
        log.info("TEST GET ALL START");
        service.getAll().forEach(s->log.trace("{}",s));
        log.info("TEST GET ALL END");
    }

    @Test
    void create() {
        Student student = new Student();
        student.setBirthDate(LocalDate.now());
        student.setCursus("JUnit");
        student.setEmail("demo@gmail.com");
        student.setGenre("M");
        student.setFirstName("Démo");
        student.setLastName("LA DEMO");
        log.info("TEST CREATE START");
        var created = service.create(student) ;

        if ( created.getId() != null )
            log.debug("Creation utilisateur OK :"+student);
        log.info("TEST CREATE END");
    }

    @Test
    void read() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
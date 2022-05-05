package com.hitema.tests;

import com.hitema.dao.StudentJdbcDaoImpl;
import com.hitema.dao.StudentService;
import com.hitema.modeles.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class JdbcTester {

    private static final String line = "=".repeat(150);
    static StudentService service ;

    private static final Logger log = LoggerFactory.getLogger(JdbcTester.class);


    public static void main(String[] args) {
        // On instancie un service DAO
        service = new StudentJdbcDaoImpl();
        // Liste tous les Student AVANT
        listAll(service);
        // Création du Student de TEST
        Student student = new Student();
        student.setBirthDate(LocalDate.now());
        student.setCursus("Java JEE");
        student.setEmail("demo@gmail.com");
        student.setGenre("M");
        student.setFirstName("Démo");
        student.setLastName("LA DEMO");
        // Début des CRUD
        // Test Create
        var created = create(student) ;
        if ( created.getId() != null )
            log.debug("Creation utilisateur OK :"+student);
        listAll(service);
        // Test Read
        var read = read(created);
        if ( read.getId().equals(created.getId()))
            log.debug("Read utilisateur OK :"+read);
        // Test Update
        read.setLastName("<<<<<<<"+read.getLastName()+">>>>>>>>MODIFIED");
        update(read);
        listAll(service);
        // Test Delete
        delete(read);
        listAll(service);
    }

    private static void listAll(StudentService service) {
        log.debug(line);
        service.getAll().forEach(s ->log.trace("{}",s));
        log.debug(line);
    }

    public static Student create(Student student){
        log.info("<<<<START CREATE>>>>");
        var s = service.create(student);
        log.info("<<<<END   CREATE>>>>");
        return s;
    }

    public static Student read(Student student){
        log.info("<<<<START READ>>>>");
        var s = service.read(student.getId());
        log.info("<<<<END   READ>>>>");
        return s;
    }

    public static void update(Student student){
        log.info("<<<<START UPDATE>>>>");
        service.update(student);
        log.info("<<<<END   UPDATE>>>>");
    }
    public static void delete(Student student){
        log.info("<<<<START DELETE>>>>");
        service.delete(student.getId());
        log.info("<<<<END   DELETE>>>>");
    }
}

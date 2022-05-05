package com.hitema.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public abstract class Channel {

    private static final Logger log = LoggerFactory.getLogger(Channel.class);


    private static Connection connection;

    public Connection getConnection() {
        if ( connection == null ){
            ResourceBundle bundle = ResourceBundle.getBundle("db");
            try {
                connection = DriverManager.getConnection(
                         bundle.getString("db.url")
                        ,bundle.getString("db.user")
                        ,bundle.getString("db.pass")) ;
                var meta = connection.getMetaData();
                log.info("Connection CREATED for : {} - {} ", meta.getDatabaseProductName(), meta.getDatabaseProductVersion());
            } catch (SQLException e) {
                log.error("Error while connecting to the SERVER :{}"+e.getLocalizedMessage());
            }
        }
        return connection;
    }
}

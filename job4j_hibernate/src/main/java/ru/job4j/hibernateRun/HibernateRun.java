package ru.job4j.hibernateRun;

import org.hibernate.SessionFactory;

import javax.security.auth.login.Configuration;

public class HibernateRun {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

}

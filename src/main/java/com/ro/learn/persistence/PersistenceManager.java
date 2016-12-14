package com.ro.learn.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rohanw on 10/10/2015.
 */
public class PersistenceManager {
    private static EntityManagerFactory emf;

    private PersistenceManager() {
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    public static void initialize(Map database) {
        Map properties = new HashMap(4);
        properties.put("javax.persistence.jdbc.driver", database.get("driver"));
        properties.put("javax.persistence.jdbc.url", database.get("url"));
        properties.put("javax.persistence.jdbc.user", database.get("user"));
        properties.put("javax.persistence.jdbc.password", database.get("password"));
        emf = Persistence.createEntityManagerFactory("KUPPI_PU", properties);

        emf.createEntityManager();// to generate schema at startup.
    }
}

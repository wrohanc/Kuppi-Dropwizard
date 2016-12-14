package com.ro.learn.services;

import com.ro.learn.api.SearchRequestAPI;
import com.ro.learn.api.SearchResponseAPI;
import com.ro.learn.model.Order;
import com.ro.learn.persistence.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Date;

/**
 * Created by rohan on 2016-12-13.
 */
public class OrderService {
    public Order placeOrder(Order order) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        order.setStatus((short) 1);
        order.setCreatedDate(new Date());
        EntityTransaction txn = em.getTransaction();
        txn.begin();
        try {
            em.persist(order);
            txn.commit();
            return order;
        } catch (Exception e) {
            if (txn.isActive()) {
                txn.rollback();
            }
            em.clear();
            e.printStackTrace();
            return null;
        }
    }

    public Order amendOrder(Order order) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        order.setStatus((short) 5);
        order.setCreatedDate(new Date());
        EntityTransaction txn = em.getTransaction();
        txn.begin();
        try {
            Order old = em.find(Order.class, order.getOrderId());
            if (old == null) {
                return null;
            }
            old.setQuantity(order.getQuantity());
            old.setPrice(order.getPrice());
            em.merge(old);
            txn.commit();
            return order;
        } catch (Exception e) {
            if (txn.isActive()) {
                txn.rollback();
            }
            em.clear();
            e.printStackTrace();
            return null;
        }
    }

    public SearchResponseAPI findByProduct(SearchRequestAPI api) {
        EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        SearchResponseAPI searchResponse = new SearchResponseAPI();

        Query query = em.createNamedQuery("Order.findByProduct", Order.class);
        query.setParameter("productCode", api.getProductCode());
        query.setFirstResult(api.getOffset());
        query.setMaxResults(api.getLimit());

        searchResponse.setOrder(query.getResultList());
        query = em.createNamedQuery("Order.findByProductCount", Order.class);
        query.setParameter("productCode", api.getProductCode());
        long count = (Long) query.getSingleResult();
        searchResponse.setRecordCount(count);

        return searchResponse;
    }
}

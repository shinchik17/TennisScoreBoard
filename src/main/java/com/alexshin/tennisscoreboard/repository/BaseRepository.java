package com.alexshin.tennisscoreboard.repository;

import com.alexshin.tennisscoreboard.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class BaseRepository<E> implements Repository<E> {
    protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public E save(E entity) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            handleException(e);
        }
        return entity;
    }


    public void delete(E entity) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            handleException(e);
        }
    }


    public void update(E entity) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            handleException(e);
        }
    }


    abstract void handleException(Exception e);
}

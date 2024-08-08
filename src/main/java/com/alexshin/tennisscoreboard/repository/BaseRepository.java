package com.alexshin.tennisscoreboard.repository;

import com.alexshin.tennisscoreboard.util.HibernateUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseRepository<E> implements Repository<E> {
    protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final Class<E> clazz;


    public E save(E entity) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            rethrowException(e);
        }
        return entity;
    }


    public void delete(E entity) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            rethrowException(e);
        }
    }


    public void update(E entity, Integer id) {
        Optional<E> optionalEntity = findById(id);
        if (optionalEntity.isEmpty()) {
            save(entity);
            return;
        }

        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            rethrowException(e);
        }
    }

    public Optional<E> findById(Integer id) {
        Optional<E> result;
        try (var session = sessionFactory.openSession()) {
            result = Optional.of(session.find(clazz, id));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    abstract void rethrowException(Exception e);


}

package com.alexshin.tennisscoreboard.repository;


import com.alexshin.tennisscoreboard.model.entity.Player;
import com.alexshin.tennisscoreboard.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;


public class PlayersRepository implements Repository<Player> {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Player save(Player entity) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public void delete(Player entity) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Player entity) {
        Transaction transaction = null;
        try (var session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Player> findById(Integer id) {
        Optional<Player> result;
        try (var session = sessionFactory.openSession()) {
            result = Optional.of(session.find(Player.class, id));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public Optional<Player> findByName(Player entity) {
        Optional<Player> result;
        try (var session = sessionFactory.openSession()) {
            Query<Player> query = session.createQuery("FROM Player WHERE name = :name", Player.class);
            query.setParameter("name", entity.getName());
            query.uniqueResultOptional();
            result = query.uniqueResultOptional();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public Player saveOrGet(Player entity) {
        var optEntity = findByName(entity);
        return optEntity.orElseGet(() -> save(entity));
    }


}

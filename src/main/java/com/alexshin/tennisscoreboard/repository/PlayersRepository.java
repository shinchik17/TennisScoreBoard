package com.alexshin.tennisscoreboard.repository;



import com.alexshin.tennisscoreboard.model.entity.Player;
import com.alexshin.tennisscoreboard.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.Optional;


public class PlayersRepository implements Repository<Player>{
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Player save(Player entity) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(Player entity) {
        @Cleanup var session = sessionFactory.openSession();
        session.remove(entity);
        session.flush();
    }

    @Override
    public void update(Player entity) {
        @Cleanup var session = sessionFactory.openSession();
        session.merge(entity);
    }

    @Override
    public Optional<Player> findById(Integer id) {
        @Cleanup var session = sessionFactory.openSession();
        return Optional.of(session.find(Player.class, id));
    }

    public Optional<Player> findByName(Player entity) {
        @Cleanup var session = sessionFactory.openSession();
        Query<Player> query = session.createQuery("FROM Player WHERE name = :name", Player.class);
        query.setParameter("name", entity.getName());
        query.uniqueResultOptional();
        return query.uniqueResultOptional();
    }


    public Player saveOrGet(Player entity){
        var optEntity = findByName(entity);
        return optEntity.orElseGet(() -> save(entity));

    }


}

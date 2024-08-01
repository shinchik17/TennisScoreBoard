package com.alexshin.tennisscoreboard.repository;


import com.alexshin.tennisscoreboard.model.entity.Match;
import com.alexshin.tennisscoreboard.util.HibernateUtil;
import lombok.Cleanup;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class MatchesRepository implements Repository<Match> {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Match save(Match entity) {
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(Match entity) {
        @Cleanup var session = sessionFactory.openSession();
        session.remove(entity);
        session.flush();
    }

    @Override
    public void update(Match entity) {
        @Cleanup var session = sessionFactory.openSession();
        session.merge(entity);
    }

    @Override
    public Optional<Match> findById(Integer id) {
        @Cleanup var session = sessionFactory.openSession();
        return Optional.of(session.find(Match.class, id));
    }

    public List<Match> findMatchesByPlayerName(int start, int amount, String playerName) {
        @Cleanup var session = sessionFactory.openSession();
        Query<Match> query = session.createQuery("FROM Match WHERE player1.name = :name OR player2.name = :name ORDER BY id DESC", Match.class);
        query.setParameter("name", playerName);
        query.setFirstResult(start);
        query.setMaxResults(amount);
        return query.getResultList();
    }

    public List<Match> findMatches(int start, int amount) {
        @Cleanup var session = sessionFactory.openSession();
        Query<Match> query = session.createQuery("FROM Match ORDER BY id DESC", Match.class);
        query.setFirstResult(start);
        query.setMaxResults(amount);
        return query.getResultList();
    }


}

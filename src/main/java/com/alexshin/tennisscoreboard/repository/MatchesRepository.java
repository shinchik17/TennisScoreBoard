package com.alexshin.tennisscoreboard.repository;


import com.alexshin.tennisscoreboard.exception.repository.MatchesRepositoryException;
import com.alexshin.tennisscoreboard.model.entity.Match;
import org.hibernate.query.Query;

import java.util.List;

public class MatchesRepository extends BaseRepository<Match> {

    public List<Match> findMatchesByPlayerName(int start, int amount, String playerName) {
        try (var session = sessionFactory.openSession()) {
            String queryString = """
                    FROM Match
                    WHERE LOWER(player1.name) LIKE :name
                    OR LOWER(player2.name) LIKE :name
                    ORDER BY id DESC
                    """;
            Query<Match> query = session.createQuery(queryString, Match.class);
            query.setParameter("name", "%" + playerName.toLowerCase() + "%");
            query.setFirstResult(start);
            query.setMaxResults(amount);
            return query.getResultList();

        } catch (Exception e) {
            handleException(e);
        }
        return List.of();
    }


    public List<Match> findMatches(int start, int amount) {
        try (var session = sessionFactory.openSession()) {
            String queryString = "FROM Match ORDER BY id DESC";
            Query<Match> query = session.createQuery(queryString, Match.class);
            query.setFirstResult(start);
            query.setMaxResults(amount);
            return query.getResultList();

        } catch (Exception e) {
            handleException(e);
        }
        return List.of();
    }


    @Override
    void handleException(Exception e) {
        throw new MatchesRepositoryException(e);
    }
}

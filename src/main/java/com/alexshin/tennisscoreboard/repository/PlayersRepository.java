package com.alexshin.tennisscoreboard.repository;


import com.alexshin.tennisscoreboard.exception.repository.PlayersRepositoryException;
import com.alexshin.tennisscoreboard.model.entity.Player;
import org.hibernate.query.Query;

import java.util.Optional;


public class PlayersRepository extends BaseRepository<Player> {

    public PlayersRepository() {
        super(Player.class);
    }

    public Optional<Player> findByName(Player entity) {
        Optional<Player> result = Optional.empty();
        try (var session = sessionFactory.openSession()) {
            Query<Player> query = session.createQuery("FROM Player WHERE name = :name", Player.class);
            query.setParameter("name", entity.getName());
            query.uniqueResultOptional();
            result = query.uniqueResultOptional();
        } catch (Exception e) {
            rethrowException(e);
        }
        return result;
    }


    public Player saveOrGet(Player entity) {
        var optEntity = findByName(entity);
        return optEntity.orElseGet(() -> save(entity));
    }


    @Override
    void rethrowException(Exception e) {
        throw new PlayersRepositoryException(e);
    }
}

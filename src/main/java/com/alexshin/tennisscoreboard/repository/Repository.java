package com.alexshin.tennisscoreboard.repository;


import java.util.Optional;

public interface Repository<E> {

    E save(E entity);

    void delete(E entity);

    void update(E entity, Integer id);

    Optional<E> findById(Integer id);

}

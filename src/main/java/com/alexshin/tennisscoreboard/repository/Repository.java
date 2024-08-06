package com.alexshin.tennisscoreboard.repository;

@SuppressWarnings("unused")
public interface Repository<E> {

    E save(E entity);

    void delete(E entity);

    void update(E entity);

}

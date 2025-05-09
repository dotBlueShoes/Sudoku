package pl.comp.dao;

import pl.comp.dao.exceptions.DaoInOuException;

public interface Dao<T> extends AutoCloseable {

    T read() throws DaoInOuException;

    void write(T obj) throws DaoInOuException;

}
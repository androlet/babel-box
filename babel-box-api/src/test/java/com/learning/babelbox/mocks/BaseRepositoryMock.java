package com.learning.babelbox.mocks;

import com.learning.babelbox.domain.EntityCore;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.util.ReflectionTestUtils;

import java.lang.reflect.Field;
import java.util.*;

public class BaseRepositoryMock<T extends EntityCore> implements JpaRepository<T, Long> {

    private Class<T> type;
    private static Long autoIncrementer = 1L;
    private static Map<Long, EntityCore> data;

    public BaseRepositoryMock(Class<T> type){
        this.type = type;
        reset();
    }

    public void reset() {
        data = new LinkedHashMap<>();
    }

    protected Map<Long, T> getRepositoryData() {
        Map<Long, T> results = new LinkedHashMap<>();
        data.values().stream()
            .filter(entity -> type.isInstance(entity))
            .forEach(entity -> results.put(entity.getId(), (T)entity));
        return results;
    }

    protected Map<Long, EntityCore> getRepositoriesData() {
        return data;
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(getRepositoryData().values());
    }

    @Override
    public List<T> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<T> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        getRepositoryData().remove(aLong);
    }

    @Override
    public void delete(T t) {

    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends T> S save(S s) {
        Long id = s.getId();
        for(Field field : s.getClass().getDeclaredFields()) {
            if (EntityCore.class.isInstance(field)) {
                try {
                    save((S) field.get(s));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        if (id != null && getRepositoryData().get(id) != null) {
            getRepositoryData().put(id, s);
        } else {
            id = autoIncrementer++;
            ReflectionTestUtils.setField(s, "id", id);
            getRepositoriesData().put(id, s);
        }
        return s;
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> iterable) {
        iterable.forEach(this::save);
        return (List<S>) iterable;
    }

    @Override
    public Optional<T> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends T> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<T> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public T getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends T> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends T> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends T> boolean exists(Example<S> example) {
        return false;
    }
}

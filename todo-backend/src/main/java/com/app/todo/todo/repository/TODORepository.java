package com.app.todo.todo.repository;

import com.app.todo.todo.repository.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TODORepository extends CrudRepository<TaskEntity,String> {
    TaskEntity findByName(String name);
    Iterable<TaskEntity> findByStatus(String name);
    @Transactional
    void deleteByName(String name);
}

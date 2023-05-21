package com.walecode.activitytrackerapp.repositories;

import com.walecode.activitytrackerapp.models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

}

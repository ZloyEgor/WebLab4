package com.web.lab4.repository;

import com.web.lab4.entity.Dot;
import com.web.lab4.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DotRepository extends CrudRepository<Dot, Long> {

    List<Dot> findByUser(User user);

    @Transactional
    void deleteAllByUser(User user);
}

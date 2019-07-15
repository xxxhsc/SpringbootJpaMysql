package com.hsc.dao;

import com.hsc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;



/**
 * 继承JpaRepository来完成对数据库的操作
 */
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findByUsernameAndPassword(String name,String password);
    User findByUsername(String username);
    String  findUsernameById(Integer id);
    @Query(value = "select t from User t where t.username like %?1%  or t.id like %?1% ")
    List<User> findByInputLike(String input);
}

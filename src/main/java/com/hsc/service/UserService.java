package com.hsc.service;

import com.hsc.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 判断登录
     * @param user
     * @return
     */
  boolean verifyLogin(User user);

  /**
   *通过username查找userid
   * @param username
   * @return
   */
  User findByUsername(String username);

  String findUsernameByID(Integer id);

  List<User> findByInputLike(String input);

}

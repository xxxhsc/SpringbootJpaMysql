package com.hsc.serviceimpl;

import com.hsc.dao.UserRepository;
import com.hsc.entity.User;
import com.hsc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: UserServiceimpl
 * @Author: 93799
 * @Description: ${description}
 * @Date: 2019/7/5 15:35
 */
@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserRepository UserDao;
    @Override
    public boolean verifyLogin(User user){
        List<User> userList = UserDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return userList.size()>0;
    }

    @Override
    public User findByUsername(String username) {

        return UserDao.findByUsername(username);
    }

    /**
     * 通过userid查找到username
     * @param id
     * @return
     */
    @Override
    public String findUsernameByID(Integer id) {
        User user = UserDao.findById(id).get();
        return user.getUsername();
    }

    @Override
    public List<User> findByInputLike(String input){
        List<User> userList=UserDao.findByInputLike(input);
        return userList;
    }
}

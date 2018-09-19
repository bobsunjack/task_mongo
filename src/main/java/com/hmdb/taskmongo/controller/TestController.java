package com.hmdb.taskmongo.controller;

import com.hmdb.taskmongo.entity.User;
import com.hmdb.taskmongo.mysql.dao.UserDao;
import com.hmdb.taskmongo.mysql.dao.UserMapper;
import com.hmdb.taskmongo.repository.UserRepository;
import com.hmdb.taskmongo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/test")
public class TestController {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/create")
    public User addNewUsers(@RequestBody User user) {
        LOG.info("Saving user.");
        userDao.getUserById("admin");
        return userRepository.save(user);
    }
    @RequestMapping(value = "/test")
    public Object addNewUsers() {
        LOG.info("Saving user.");
        userService.getAllUsers();
        userMapper.listAllParentMenu();
       return userDao.getUserById("admin");
    }
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID: {}.", userId);
        User user=new User();
     //   user.setUserId("1");
        user.setName("测试");
        userRepository.save(user);
        return userRepository.findById(userId).orElse(null);
    }
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        LOG.info("Getting all users.");
        return userRepository.findAll();
    }
    @RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
    public Object getAllUserSettings(@PathVariable String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getUserSettings();
        } else {
            return "User not found.";
        }
    }

    @RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
    public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            return user.getUserSettings().get(key);
        } else {
            return "User not found.";
        }
    }

    @RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
    public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.getUserSettings().put(key, value);
            User save = userRepository.save(user);
            return "Key added";
        } else {
            return "User not found.";
        }
    }

}

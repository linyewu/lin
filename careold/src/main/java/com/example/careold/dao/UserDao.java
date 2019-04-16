package com.example.careold.dao;

import com.example.careold.domain.Users;

public interface UserDao extends CrudRepository<Users,String>{

    Users getUsersByPhone(String phone);
}

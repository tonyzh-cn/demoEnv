package com.example.demo;

import java.util.List;

public interface UserMapper {
    User getById(int id);

    List<User> getAll();
}

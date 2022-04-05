package ru.roombooking.admin.service;

import java.util.List;

public interface RoomService<T, ID> {
    T save(T model);

    List<T> findAll();
}
package com.example.eng;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    public void addUser(User user);

    @Query("select *from users")
    public List<User> getUsers();

    @Query("select * from users where id = :td LIMIT 1 ")
    public User loadUserById(int td);

    @Query("select * from users where kategoria = :cat")
    public List<User> loadUserByKategoria(String cat);
//
//    @Query("select kategoria from users LIMIT 1 ")
//    public List<User> loadKategorie();

    @Delete
  public void deleteUsers (User user);



}

package com.example.eng;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    void addUser(User user);

    @Query("select *from users")
    List<User> getUsers();

    @Query("select * from users order by kategoria ASC")
    List<User> loadUserOrderByKategoria();

    @Query("select * from users where kategoria = :cat")
    List<User> loadUserByKategoria(String cat);

    @Query("select * from users ORDER BY RANDOM() LIMIT 1")
    List<User> loadLos();

    @Query("select * from users where nazwisko = 'meldojthgsbxgslwojrfidyvsnrownxossaa' and kategoria = 'hdshjaiasaslokasjdjasadkjjdiayucxzpw'")
    List<User> loadAllCategory();

    //meldojthgsbxgslwojrfidyvsnrownxossaa
    //hdshjaiasaslokasjdjasadkjjdiayucxzpw
    @Delete
    void deleteUsers(User user);

    @Delete
    void deleteAllUsers(List<User> users);



}

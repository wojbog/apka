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

    @Query("select * from users where id = :td LIMIT 1 ")
    User loadUserById(int td);
//
//    @Query("select * from users where kategoria = :cat")
//    List<User> loadUserByKategoria(String cat);
//
//    @Query("select kategoria from users LIMIT 1 ")
//    public List<User> loadKategorie();

    @Query("select * from users where nazwisko = 'meldojthgsbxgslwojrfidyvsnrownxossaa' and kategoria = 'hdshjaiasaslokasjdjasadkjjdiayucxzpw'")
    List<User> loadAllCategory();

    //meldojthgsbxgslwojrfidyvsnrownxossaa
    //hdshjaiasaslokasjdjasadkjjdiayucxzpw
    @Delete
    void deleteUsers(User user);



}

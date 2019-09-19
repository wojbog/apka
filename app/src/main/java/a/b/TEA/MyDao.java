package a.b.TEA;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    void addUser(User user);

    @Insert
    void addUsers(List<User> users);

    @Update
    void updateUser(User user);

    @Update
    void updateUsers(List<User> users);

    @Query("select * from users")
    List<User> getUsers();

    @Query("select * from users order by kategoria,imie ASC ")
    List<User> loadUserOrderByKategoria();

    @Query("select * from users where kategoria = :cat")
    List<User> loadUserByKategoria(String cat);

    @Query("select * from users ORDER BY RANDOM() LIMIT 1")
    User loadLos();

    @Query("select * from users where zoladki = :zol")
    List<User> loadUsersByZolodek(String zol);

    @Query("select * from users where nazwisko = 'meldojthgsbxgslwojrfidyvsnrownxossaa' and kategoria = 'hdshjaiasaslokasjdjasadkjjdiayucxzpw'")
    List<User> loadAllCategory();

    @Query("select * from users where kategoria = 'Data'")
    User loadData();

    //meldojthgsbxgslwojrfidyvsnrownxossaa
    //hdshjaiasaslokasjdjasadkjjdiayucxzpw
    @Delete
    void deleteUsers(User user);

    @Delete
    void deleteAllUsers(List<User> users);



}

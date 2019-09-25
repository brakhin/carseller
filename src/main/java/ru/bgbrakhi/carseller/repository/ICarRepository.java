package ru.bgbrakhi.carseller.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.bgbrakhi.carseller.models.Car;

import java.util.List;

public interface ICarRepository extends CrudRepository<Car, Integer> {

    @Query(value = "select c.*  from cars c \n"
    + "inner join ref_carmodel cm on c.id_carmodel = cm.id\n"
    + "inner join ref_carmark ck on cm.id_carmark = ck.id\n"
    + "where coalesce(c.inactive, false) = :inactive \n"
    + "and (not :filter_mark = true or ck.name = :mark) \n"
    + "and (not :filter_today = true or c.timestamp >= extract(epoch from date_trunc('day', localtimestamp))*1000)\n"
    + "and (not :filter_photo = true or coalesce(c.filename, '') <> '')",
    nativeQuery = true)
    List<Car> findWithFilter(
            @Param("inactive")Boolean inactive,
            @Param("filter_mark")Boolean filterMark,
            @Param("mark")String mark,
            @Param("filter_today")Boolean filterToday,
            @Param("filter_photo")Boolean filterPhoto);

    @Query("select c from Car c where c.user.login = :login")
    List<Car> findForUser(@Param("login")String login);

    @Transactional
    @Modifying
    @Query(value = "update cars set inactive = not coalesce(inactive, false) \n"
            + "where id=:id and id_user in (select u.id from users u where u.login=:login)",
            nativeQuery = true)
    void swapInactive(@Param("id")Long id, @Param("login") String login);
}



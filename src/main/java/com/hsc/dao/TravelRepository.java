package com.hsc.dao;

import com.hsc.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: TravelRepository
 * @Author: 93799
 * @Description: ${description}
 * @Date: 2019/7/4 17:07
 */
@Repository
public interface TravelRepository extends JpaRepository<Travel,Integer> {
    List<Travel>  findAllTravelByuserid(Integer userid);

    boolean deleteTravelById(Integer userid);
    @Query(value = "select t from Travel t where t.addressname like %?1%  or t.userid like %?1%  or t.id like %?1% ")
    List<Travel> findByInputLike(String input);
}

package com.hsc.service;

import com.hsc.entity.Travel;

import java.util.List;

/**
 * @ClassName: TravelService
 * @Author: 93799
 * @Description: ${description}
 * @Date: 2019/7/5 16:56
 */
public interface TravelService {


    List<Travel> findAllTravelByuserid(Integer userid);

    boolean deleteTravelById(Integer userid);

    List<Travel> findByInputLike(String input1);

}

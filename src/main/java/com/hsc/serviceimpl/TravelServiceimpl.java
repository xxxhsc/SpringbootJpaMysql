package com.hsc.serviceimpl;

import com.hsc.dao.TravelRepository;
import com.hsc.dao.UserRepository;
import com.hsc.entity.Travel;
import com.hsc.entity.User;
import com.hsc.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: TravelServiceimpl
 * @Author: 93799
 * @Description: ${description}
 * @Date: 2019/7/5 16:56
 */
@Service
public class TravelServiceimpl implements TravelService {
    @Autowired
    private TravelRepository TravelDao;
    @Autowired
    private UserRepository UserDao;
    @Override
    public List<Travel> findAllTravelByuserid(Integer userid) {return TravelDao.findAllTravelByuserid(userid);  }
    @Override
    public boolean deleteTravelById(Integer id){TravelDao.deleteById(id);return true;}
    @Override
    public  List<Travel> findByInputLike(String input){
        List<Travel> travelList= TravelDao.findByInputLike(input);
        return travelList;
    }

}

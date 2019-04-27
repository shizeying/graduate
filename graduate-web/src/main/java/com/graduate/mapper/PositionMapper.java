package com.graduate.mapper;

import com.graduate.bean.PositionBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-01-10  19:26
 * @Description：
 */
@Component
public interface PositionMapper {

    int addPos(@Param("pos") PositionBean pos);

    PositionBean getPosByName(String name);

    List<PositionBean> getAllPos();

    int deletePosById(@Param("pids") int[] pids);

    int updatePosById( PositionBean positionBean);
}

package com.graduate.service;

import com.graduate.bean.PositionBean;
import com.graduate.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  18:56
 * @Description：<描述>
 */
@Service
@Transactional
public class PositionService {
    @Autowired
    PositionMapper positionMapper;

    public int addPos(PositionBean pos) {
        if (positionMapper.getPosByName(pos.getName()) != null) {
            return -1;
        }
        Timestamp time2 = new Timestamp(new Date().getTime());
        pos.setCreateDate(time2);
        return positionMapper.addPos(pos);
    }

    public List<PositionBean> getAllPos() {
        return positionMapper.getAllPos();
    }

    public boolean deletePosById(String pids) {
        String[] split = pids.split(",");
        int[] ints = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i] = Integer.parseInt(split[i]);

        }
        return positionMapper.deletePosById(ints) == split.length;
    }

    public int updatePosById(PositionBean positionBean) {
        positionBean.setEnabled(true);
        return positionMapper.updatePosById(positionBean);
    }

}


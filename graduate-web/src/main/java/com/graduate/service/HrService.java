package com.graduate.service;

import com.graduate.bean.HrBean;
import com.graduate.common.HrUtils;
import com.graduate.common.PinyinTool;
import com.graduate.mapper.HrMapper;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-14  18:54
 * @Description：<描述>
 */
@Service
@Transactional
public class HrService implements UserDetailsService {
    @Autowired
    HrMapper hrMapper;

    public int insertHr(String name, String address, String phone) throws BadHanyuPinyinOutputFormatCombination {
        PinyinTool pinyinTool = new PinyinTool();
        String s = pinyinTool.toPinYin(name, "", PinyinTool.Type.LOWERCASE);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(phone);
        HrBean hrBean = hrMapper.loadUserByUsername(pinyinTool.toPinYin(name, "", PinyinTool.Type.LOWERCASE));
        if (hrBean == null) {
            return hrMapper.insertHr(name, address, phone, s, encode);
        }
        return -1;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        HrBean hrBean = hrMapper.loadUserByUsername(s);
        if (hrBean == null) {
            throw new UsernameNotFoundException("用户名不对");
        }
        return hrBean;
    }

    public int hrReg(String username, String password) {
        //如果用户名存在，返回错误
        if (hrMapper.loadUserByUsername(username) != null) {
            return -1;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        return hrMapper.hrReg(username, encode);
    }

    public List<HrBean> getHrsByKeywords(String keywords) {
        if (keywords.equals("all")) {
            return hrMapper.getHrsByKeywords(keywords);
        }
        String nameLike = "%" + keywords + "%";
        return hrMapper.getHrsByKeywords(nameLike);
    }

    public int updateHr(HrBean hrBean) {
        if (hrBean.getPassword() != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encode = encoder.encode(hrBean.getPassword());
            hrBean.setPassword(encode);
            hrBean.setEnabled(true);
            return hrMapper.updateHr(hrBean);
        }
        if (hrBean.stringEnabled().length() == 0) {
            hrBean.setEnabled(true);
        }
        return hrMapper.updateHr(hrBean);
    }

    public boolean updateHrRoles(Long hrId, Long[] rids) {
        int i = hrMapper.deleteRoleByHrId(hrId);
        for (long rid : rids) {
            hrMapper.addRolesForHr(hrId, rid);
        }
        return true;
    }

    public HrBean getHrById(Long hrId) {
        return hrMapper.getHrById(hrId);
    }

    public int deleteHr(Long hrId) {
        return hrMapper.deleteHr(hrId);
    }

    public List<HrBean> getAllHrExceptAdmin() {
        return hrMapper.getAllHr(HrUtils.getCurrentHr().getId());
    }

    public List<HrBean> getAllHr() {
        return hrMapper.getAllHr(null);
    }
}

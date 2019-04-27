package com.graduate.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-10  10:12
 * @Description：<描述>
 */
@JsonIgnoreProperties(value = {"handler"})
@Component
public class HrBean implements UserDetails {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String phone;
    @Getter
    @Setter
    private String telephone;
    @Getter
    @Setter
    private String address;
    @Setter
    private boolean enabled;

    @Setter
    private String username;
    @Setter
    private String password;
    @Getter
    @Setter
    private String remark;
    @Getter
    @Setter
    private List<RoleBean> roles;
    @Getter
    @Setter
    private String userface;

    /**
     * 判断用户帐号是否已启用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String stringEnabled(){
        return String.valueOf(enabled);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /**
         * 所有的认证实现类都保存了一个的GrantedAuthority 列表,其表示用户所具有的权限,判断用户是否具有访问对应资源的权限。
         */
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleBean roleBean : roles) {
            authorities.add(new SimpleGrantedAuthority(roleBean.getName()));
        }
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}

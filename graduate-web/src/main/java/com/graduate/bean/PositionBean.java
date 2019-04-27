package com.graduate.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-10  11:40
 * @Description：<描述>
 */
@Component
@NoArgsConstructor
public class PositionBean {
    @Setter
    @Getter
    private Long id;
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private Timestamp createDate;
    @Setter
    @Getter
    private boolean enabled;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        PositionBean position = (PositionBean) object;

        return name != null ? name.equals(position.name) : position.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public PositionBean(String name) {
        this.name = name;
    }


}

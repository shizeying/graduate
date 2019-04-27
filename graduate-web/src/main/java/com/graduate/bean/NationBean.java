package com.graduate.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-10  11:12
 * @Description：<描述>
 */
@Component
@NoArgsConstructor
public class NationBean {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;

    public NationBean(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        NationBean nation = (NationBean) object;

        return name != null ? name.equals(nation.name) : nation.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}

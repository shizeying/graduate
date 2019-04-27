package com.graduate.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @Author：史泽颖
 * @Date： Create in  2019-02-10  11:31
 * @Description：<描述>
 */
@Component
@NoArgsConstructor
public class PoliticsStatusBean {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }

        PoliticsStatusBean that = (PoliticsStatusBean) object;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public PoliticsStatusBean(String name) {
        this.name = name;
    }

}

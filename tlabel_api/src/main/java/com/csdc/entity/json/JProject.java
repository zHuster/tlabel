package com.csdc.entity.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangzhi
 * @since <pre>2019/3/25</pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JProject {
    private String id;
    private String name;
 //   private Integer year;

  /*  public JProject(String id,String name){
        this.id=id;
        this.name=name;
    }*/
}

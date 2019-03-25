package com.csdc.entity;

import com.csdc.entity.json.JProject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangzhi
 * @since <pre>2019.3.18</pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    private String id;
    private String name;

    public static JProject makeNewJProject(Project project){
        return new JProject(project.getId(),project.getName());
    }

}

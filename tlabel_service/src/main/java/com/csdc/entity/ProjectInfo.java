package com.csdc.entity;

import com.csdc.entity.json.JProjectInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangzhi
 * @since <pre>2019/3/26</pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInfo {
    private String id;
    private String name;
    private Integer year;
    private String productType;
    private String discipline;
    private String applicant;
    private String agency;
    private String province;

    public static JProjectInfo makeNewJProjectInfo(ProjectInfo projectInfo){
        JProjectInfo jProjectInfo=new JProjectInfo(projectInfo.id,
                projectInfo.name,projectInfo.year,projectInfo.productType,
                projectInfo.discipline,projectInfo.applicant,projectInfo.agency,
                projectInfo.province);
        return jProjectInfo;
    }

}

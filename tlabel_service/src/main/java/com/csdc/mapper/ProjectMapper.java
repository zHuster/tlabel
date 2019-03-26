package com.csdc.mapper;

import com.csdc.entity.Project;
import com.csdc.entity.ProjectInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMapper {

    List<Project> findProjectByIds(@Param("ids") List<String> ids);

    ProjectInfo findProjectInfoById(@Param("id") String id);

}

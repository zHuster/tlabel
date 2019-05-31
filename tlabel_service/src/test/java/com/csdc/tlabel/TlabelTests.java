package com.csdc.tlabel;

import com.alibaba.dubbo.config.annotation.Reference;
import com.csdc.mapper.ProjectMapper;
import csdc.info.lda_common.service.TopicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TlabelTests {

    @Autowired
    DataSource dataSource;
    @Reference
    TopicService topicService;

    @Autowired
    ProjectMapper projectMapper;


    @Test
    public void contextLoads() throws SQLException {
     //System.out.println(projectMapper.findProjectById("4028d89a49402ac70149402f7ca837fb"));
    }
    /**
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
     * 并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
     */
    public int FirstNotRepeatingChar(String str) {
        if(str==null||str.isEmpty()) return -1;
        LinkedHashMap<Character,Integer> map=new LinkedHashMap<>();
        char[] cs=str.toCharArray();
        for(char e:cs){
            if(map.get(e)==null)
                map.put(e,1);
            else {
                map.put(e,map.get(e)+1);
            }
        }
        int index=0;
        boolean flag=false;
        for (Character e:map.keySet()){
            if(map.get(e)==1){
                flag=true;
                break;
            }
            ++index;
        }
        return flag?index:-1;
    }

}

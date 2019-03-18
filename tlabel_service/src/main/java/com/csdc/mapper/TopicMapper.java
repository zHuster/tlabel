package com.csdc.mapper;

import com.csdc.entity.Topic;
import org.springframework.stereotype.Repository;
import java.util.*;

/**
 * @author zhangzhi
 * @since <pre>2019.3.18</pre>
 */
@Repository
public interface TopicMapper {
    void insertTopics(List<Topic> topics);
}

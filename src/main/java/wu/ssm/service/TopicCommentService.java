package wu.ssm.service;


import wu.ssm.Utility;
import wu.ssm.mapper.TopicCommentMapper;
import wu.ssm.mapper.TopicMapper;
import wu.ssm.model.TopicCommentModel;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TopicCommentService {
    private TopicCommentMapper mapper;
    private TopicMapper topicMapper;

    public TopicCommentService(TopicCommentMapper mapper, TopicMapper topicMapper) {
        this.mapper = mapper;
        this.topicMapper = topicMapper;
    }

    //    时间格式化
    public static String formattedTime(Long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        return dateString;
    }

    public TopicCommentModel comment_add(Integer userId, Integer topicid, String content) {
        Long unixTime = System.currentTimeMillis() / 1000L;

        TopicCommentModel m = new TopicCommentModel();
        m.setTopicid(topicid);
        m.setUserId(userId);
        m.setContent(content);
//        m.setCreatedTime(unixTime);
        m.setCreatedTime(formattedTime(unixTime));
        m.setUpdatedTime(formattedTime(unixTime));
//        m.setUpdatedTime(unixTime);
        Utility.log("!!!!!!!!!!!!!!!UId(%s), content(%s), TID(%s)", userId,content, topicid);
        mapper.insert_comment(m);
        return m;
    }







}

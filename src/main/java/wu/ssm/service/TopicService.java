package wu.ssm.service;


import wu.ssm.Utility;
import wu.ssm.mapper.TopicCommentMapper;
import wu.ssm.mapper.TopicMapper;
import wu.ssm.model.TopicCommentModel;
import wu.ssm.model.TopicModel;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TopicService {
    private TopicMapper mapper;
    private TopicCommentMapper commentMapper;
    // Spring 会注入 mapper
    public TopicService(TopicMapper mapper, TopicCommentMapper commentMapper) {
        this.mapper = mapper;
        this.commentMapper = commentMapper;
    }


    //    时间格式化
    public static String formattedTime(Long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        return dateString;
    }

    public List<TopicModel> findTitleByUserId(Integer Userid){
        List<TopicModel> title = new ArrayList<>();
        List<TopicCommentModel> comment = commentMapper.findCommentByUserId(Userid);    //1.1根据用户id找comment
    Utility.log("!!!!!!!!!!!!!!!comment(%s)", comment);
        Utility.log("!!!!!!!!!!!!!!comment.size(%s)",  comment.size());
        for (TopicCommentModel c:      //遍历topiccomment
                comment
             ) {
            Utility.log("!!!!!!!!循环里topicID (%s)", c.getTopicid());
            TopicModel topic = mapper.selectOneWithId(c.getTopicid()); //根据comment 找TopicId 再找Topic
            Utility.log("!!!!!!!!循环里topic (%s)", topic);
            title.add(topic);      //title 添加 根据topicId找到的title
            Utility.log("!!!!里!!!!!!!!!!title(%s)", title);
        }
        Utility.log("!!!!!!!!!!!!!!!title外(%s)", title);
        return title;
    }

    //查评论帖子
    public List<TopicModel> selectAllfindById(Integer id){
        return mapper.selectAllByUserId(id);
    }

    public TopicModel add(Integer userId, String title, String content, Integer boardId) {
        Long unixTime = System.currentTimeMillis() / 1000L;

        TopicModel m = new TopicModel();
        m.setTitle(title);
        m.setContent(content);
        m.setUserId(userId);
        m.setCreatedTime(formattedTime(unixTime));
        m.setUpdatedTime(formattedTime(unixTime));
        m.setBoardId(boardId);

        mapper.insert(m);
        return m;
    }



    public TopicModel update(Integer id, String title, String content) {
        Long unixTime = System.currentTimeMillis() / 1000L;

        TopicModel m = new TopicModel();
        m.setId(id);
        m.setContent(content);
        m.setTitle(title);
        m.setUpdatedTime(formattedTime(unixTime));
        mapper.update(m);
        return m;
    }


    public void deleteById(Integer id) {
        mapper.delete(id);
    }


    public  TopicModel findById(Integer id) {
        return mapper.selectOne(id);
    }


    public  List<TopicModel> all() {
        return mapper.selectAll();
    }

    public  List<TopicModel> findTopicByBoardId(Integer id) {
        return mapper.selectTopicByBoardId(id);
    }

    public TopicModel findByIdWithCommeentsAndUser(Integer id) {
        TopicModel topic = mapper.selectOneWithCommentsAndUser(id);
        return topic;
    }



}

package wu.ssm.mapper;

import wu.ssm.model.TopicCommentModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TopicCommentMapper {

    void insert_comment(TopicCommentModel todo);

    List<TopicCommentModel> cselectAllByTopicId(Integer id);


//    根据UID找commentfind
    List<TopicCommentModel> findCommentByUserId(Integer id);

}

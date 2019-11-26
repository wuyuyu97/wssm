package wu.ssm.mapper;

import wu.ssm.model.TopicCommentModel;
import wu.ssm.model.TopicModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TopicMapper {
    void insert(TopicModel todo);

    void insertcomment(TopicCommentModel todo);

    List<TopicModel> selectAll();

    TopicModel selectOne(Integer id);

    TopicModel selectOneWithId(Integer id);

    void update(TopicModel todo);

    void delete(Integer id);

    List<TopicModel> selectAllByUserId(Integer id);



    TopicModel findtitleByTopicId(TopicCommentModel id);

    List<TopicModel> selectTopicByBoardId(Integer id);

    TopicModel selectOneWithCommentsAndUser(Integer id);


}

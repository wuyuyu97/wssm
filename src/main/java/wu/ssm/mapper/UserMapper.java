package wu.ssm.mapper;

import wu.ssm.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    void insert(UserModel todo);

    List<UserModel> selectAll();
    UserModel SelectUsernameById(Integer id);

    UserModel selectOne(Integer id);

    UserModel selectOneByUsername(String username);

    void update(UserModel todo);

    void delete(Integer id);


}

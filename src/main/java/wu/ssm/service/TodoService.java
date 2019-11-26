package wu.ssm.service;


import wu.ssm.mapper.TodoMapper;
import wu.ssm.model.TodoModel;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {
    private TodoMapper mapper;

//依赖注入
    public TodoService(TodoMapper todoMapper) {
        this.mapper = todoMapper;
    }

//    时间格式化
    public static String formattedTime(Long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        return dateString;
    }

    public TodoModel add(String content) {


        TodoModel m = new TodoModel();
        m.setContent(content);

        mapper.insertTodo(m);
        return m;
    }


    public TodoModel update(Integer id, String content) {
        TodoModel m = new TodoModel();
        m.setId(id);
        m.setContent(content);
        mapper.updateTodo(m);
        return m;
    }


    public void deleteById(Integer id) {
        mapper.deleteTodo(id);
    }


    public  TodoModel findById(Integer id) {
        return mapper.selectTodo(id);
    }


    public  List<TodoModel> all() {
        return mapper.selectAllTodo();
    }

}

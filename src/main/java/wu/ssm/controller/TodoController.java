package wu.ssm.controller;
import wu.ssm.Utility;
import wu.ssm.model.TodoModel;
import wu.ssm.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todo/add")
    public ModelAndView add(String content) {
        TodoModel todo = todoService.add(content);
        Utility.log("todo add id %s", todo.getId());
        return new ModelAndView("redirect:/todo");
    }

    @GetMapping("/todo/delete")
    public String deleteMapper(Integer id) {
        todoService.deleteById(id);
        return "redirect:/todo";
    }


    @GetMapping("/todo/edit")
    public ModelAndView edit(Integer id) {
        TodoModel todo = todoService.findById(id);

        ModelAndView m = new ModelAndView("todo_edit");
        m.addObject("todo", todo);
        return m;
    }

    @PostMapping("/todo/update")
    public String updateMapper(Integer id, String content) {
        todoService.update(id, content);
        return "redirect:/todo";
    }

    @GetMapping("/todo")
    public ModelAndView index() {
        Utility.log("todo 主页");
        List<TodoModel> todos = todoService.all();
        ModelAndView m = new ModelAndView("todo_index");
        m.addObject("todos", todos);
        return m;
    }
}

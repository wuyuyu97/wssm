package wu.ssm.controller;

import wu.ssm.Utility;
import wu.ssm.model.TopicCommentModel;
import wu.ssm.model.TopicModel;
import wu.ssm.model.UserModel;
import wu.ssm.service.TopicCommentService;
import wu.ssm.service.TopicService;
import wu.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TopicController {

    private TopicService topicService;
    private TopicCommentService topicCommentService;
    private UserService UserService;

    public TopicController(TopicService topicService, TopicCommentService topicCommentService, UserService UserService) {
        this.topicService = topicService;
        this.topicCommentService = topicCommentService;
        this.UserService = UserService;
    }
//    不分板块1/或2的topic主页
    @GetMapping("/topic")
    public ModelAndView index(HttpSession session) {
        Utility.log("!!!!!!!!!!!!!!!!调用index!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Integer uid = (Integer) session.getAttribute("user_id");
        UserModel user = UserService.FindUsernameById(uid);
        List<TopicModel> topics = topicService.all();
        ModelAndView m = new ModelAndView("topic/topic_index");
        m.addObject("boardId", "1");
        m.addObject("topics", topics);
        m.addObject("user",user);
        return m;
    }
//    分板块的主页
    @GetMapping("/topic/board/{id}")
    public ModelAndView boardindex(@PathVariable Integer id, HttpSession session) {
        Utility.log("!!!!!!!!!!!!!!!!调用boardindex!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Integer uid = (Integer) session.getAttribute("user_id");
        UserModel user = UserService.FindUsernameById(uid);
        List<TopicModel> topics = topicService.findTopicByBoardId(id);
        Utility.log("!!!!!!!!!!!!!!!!!!!!id(%s)",id);
        Utility.log("!!!!!!!!!!!!!!!!!!!!topics(%s)",topics);
        ModelAndView m = new ModelAndView("topic/topic_index");
        m.addObject("boardId", id);
        m.addObject("topics", topics);
        m.addObject("user",user);
        return m;
    }

    //用户个人主页
    @GetMapping("/topic/user_index/{id}")
    public ModelAndView UserIndex(@PathVariable Integer id) {
        UserModel current = UserService.findById(id);
        List<TopicModel> title = topicService.findTitleByUserId(id);
        Utility.log("title!!!!!!!%s",title);
        List<TopicModel> topics = topicService.selectAllfindById(id);
        Utility.log("size%s",topics.size());

        ModelAndView m = new ModelAndView("topic/topic_user_index");
        m.addObject("topics", topics);
        m.addObject("user", current);
        m.addObject("title", title);

        return m;
    }

    //topic 评论页面
    @GetMapping("/topic/commentedit")
    public ModelAndView commentedit(Integer id) {
        ModelAndView m = new ModelAndView("topic/topiccomment_edit");
        m.addObject("topicid", id);
        return m;
    }

    //增加评论
    @PostMapping("/topic/commentadd")
    public ModelAndView commentupdateMapper(Integer id, String content, HttpSession session) {
        Integer uid = (Integer) session.getAttribute("user_id");
        Utility.log("!!!!!!!!!!!!!!!Id(%s), content(%s), UID(%s)", id,content, uid);

        topicCommentService.comment_add(uid, id, content);
        return new ModelAndView("redirect:/topic");
    }


    @GetMapping("/topic/detail/{id}")
    public ModelAndView detail(@PathVariable Integer id) {
        UserModel user = UserService.findUserByTopicId(id);
        TopicModel topic = topicService.findByIdWithCommeentsAndUser(id);
        for (TopicCommentModel comment:
                topic.getCommentList()) {
            Utility.log("comment user %s", comment.getUser());
            Utility.log("comment username %s", comment.getUser().getUsername());
        }
        ModelAndView m = new ModelAndView("topic/topic_detail");
        m.addObject("topic", topic);
        m.addObject("user", user);
        return m;
    }

    @PostMapping("/topic/add")
    public ModelAndView add(Integer boardId, String title, String content, HttpSession session) {
                Utility.log("!!!!!!!!!!!!!!!!ADD Boardid(%s)", boardId);
        Integer uid = (Integer) session.getAttribute("user_id");
        TopicModel topic = topicService.add(uid, title, content, boardId);
        Utility.log("topic add id %s", topic.getId());
        return new ModelAndView("redirect:/topic");
    }

    @GetMapping("/topic/delete")
    public ModelAndView deleteMapper(HttpServletRequest request) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        topicService.deleteById(id);
        return new ModelAndView("redirect:/topic");
    }


    @GetMapping("/topic/edit")
    public ModelAndView edit(Integer id) {
        TopicModel topic = topicService.findById(id);

        ModelAndView m = new ModelAndView("topic/topic_edit");
        m.addObject("topic", topic);
        return m;
    }

    @PostMapping("/topic/update")
    public ModelAndView updateMapper(Integer id, String title, String content) {
        topicService.update(id, title, content);
        return new ModelAndView("redirect:/topic");
    }
}

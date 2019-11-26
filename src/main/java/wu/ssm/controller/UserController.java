package wu.ssm.controller;

import wu.ssm.Utility;
import wu.ssm.model.UserModel;
import wu.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.UUID;

@Controller
public class UserController {

    private HashMap<String, String> tokenMap = new HashMap<>();

    private UserService userService;
    private MailController mailController;
    public UserController(UserService userService, MailController mailController) {
        this.userService = userService;
        this.mailController = mailController;
    }


    @GetMapping("/pdf")
    public ModelAndView pdf() {
        return new ModelAndView("user/wupdf");
    }

//找回密码页面
    @GetMapping("/reset")
    public ModelAndView ResetindexView() {
        ModelAndView m = new ModelAndView("user/reset");
        return m;
    }


//发送邮件
    @PostMapping("/rest/send")
    public ModelAndView ResetSend(HttpServletRequest request){
        String username = request.getParameter("username");
        UserModel user = userService.findByUsername(username);
        String mail = user.getMail();
        String token = String.valueOf(UUID.randomUUID());
        Integer userid = user.getId();
        tokenMap.put(token, String.valueOf(userid));
        String url = String.format(" http://localhost:8080/reset/view?token=" + token);
        mailController.sendMail(mail, "修改您的密码", String.format("点击下边的链接 %s 重置密码", url));
        return new ModelAndView("redirect:/index");
    }

//邮箱链接跳转, 如果token存在, 跳转到更改密码界面, 如果不存在, 404
    @GetMapping("/reset/view")
    public ModelAndView ResetView( HttpServletRequest request){
        String token = request.getParameter("token");
        Utility.log("ResetView的token!!!(%s)",token);
        if(tokenMap.containsKey(token)){
            return new ModelAndView(String.format("redirect:/reset/passwordIndex?token=%s", token));
        }
        else{
            Utility.log("token不对");
            return new ModelAndView("redirect:/404");
        }
    }
//更改密码界面
    @GetMapping("/reset/passwordIndex")
    public ModelAndView ResetPasswordView(HttpServletRequest request) {
        ModelAndView m = new ModelAndView("user/ResetPassword");
        String token = request.getParameter("token");
        m.addObject("token", token);
        return m;
    }
//更改密码
    @PostMapping("/reset/passwordupdate")
    public ModelAndView Reset_Password(HttpServletRequest request){

        String token = request.getParameter("token");
        Integer userid = Integer.valueOf(tokenMap.get(token));
        UserModel user = userService.findById(userid);
        String username = user.getUsername();
        String password = request.getParameter("password");
        userService.update(userid,username,password);

        tokenMap.remove(token);

        ModelAndView m = new ModelAndView("redirect:/login");
        return m;
    }


    @GetMapping("/index")
    public ModelAndView indexView(HttpServletRequest request) {
        UserModel current = userService.currentUser(request);
        ModelAndView m = new ModelAndView("index");
        m.addObject("currentUser", current);
        return m;
    }



    @PostMapping("/user/login")
    public ModelAndView login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (userService.validateLogin(username, password)) {
            UserModel current = userService.findByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("user_id", current.getId());
            return new ModelAndView("redirect:/index");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @PostMapping("/user/register")
    public ModelAndView register(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail");
        userService.add(username, password, mail);
        ModelAndView m = new ModelAndView("redirect:/login");
        return m;
    }
}

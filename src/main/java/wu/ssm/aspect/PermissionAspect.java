package wu.ssm.aspect;


import wu.ssm.Utility;
import wu.ssm.model.TopicModel;
import wu.ssm.model.UserModel;
import wu.ssm.service.TopicService;
import wu.ssm.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class PermissionAspect {
    private UserService userService;
    private TopicService topicService;
    private HttpServletRequest request;

    public PermissionAspect(UserService service, TopicService topicService, HttpServletRequest request) {
        Utility.log("PermissionAspect 注入需要的依赖", request, service);
        this.request = request;
        this.userService = service;
        this.topicService = topicService;
    }


    @Around("execution(* wu.ssm.controller.TodoController.index(..)) || execution(* wu.ssm.controller.TodoController.add(..))")
    public ModelAndView matchSingle(ProceedingJoinPoint joint) throws Throwable {
        Utility.log("路由函数之前执行 %s", request.getRequestURI());

        // 这一句就是在执行路由函数
        ModelAndView result = (ModelAndView) joint.proceed();

        Utility.log("路由函数之后执行 %s", request.getRequestURI());
        return result;
    }

    @Around("execution(* wu.ssm.controller.TopicController.*(..))")
    public ModelAndView loginRequired(ProceedingJoinPoint joint) throws Throwable {

        Utility.log("loginRequired 正在访问的 url %s", request.getRequestURI());
        Utility.log("loginRequired 正在执行的方法 %s %s", joint.getSignature(), joint.getArgs());
        Integer userID = (Integer) request.getSession().getAttribute("user_id");
        if (userID == null) {
            // 跳转登陆页面
            Utility.log("loginRequired 没有 session");
            return new ModelAndView("redirect:/login");
        } else {
            UserModel u = userService.findById(userID);
            if (u == null) {
                // 跳转登陆页面
                Utility.log("loginRequired 用户不存在 %s", userID);
                return new ModelAndView("redirect:/login");
            } else {
                // 执行被插入的方法
                return (ModelAndView) joint.proceed();
            }
        }
    }


    @Around("execution(* wu.ssm.controller.TopicController.edit(..)) || execution(* wu.ssm.controller.TopicController.deleteMapper(..))")
    public ModelAndView owerRequired(ProceedingJoinPoint joint) throws Throwable {
        Utility.log("owerRequired 正在访问的 url %s", request.getRequestURI());
        Utility.log("owerRequired 正在执行的方法 %s %s", joint.getSignature(), joint.getArgs());
        Integer userID = (Integer) request.getSession().getAttribute("user_id");
        String topicIdParameter = request.getParameter("id");
        Utility.log("currentUser(%s), topic Owner(%s)", userID, topicIdParameter);

        if (userID == null || topicIdParameter == null) {
            return new ModelAndView("redirect:/login");
        } else {
            UserModel currentUser = userService.findById(userID);
            TopicModel topicAccessed = topicService.findById(Integer.valueOf(topicIdParameter));
            if (currentUser == null || topicAccessed == null) {
                return new ModelAndView("redirect:/login");
            } else {
                if (currentUser.getId().equals(topicAccessed.getUserId())) {
                    // 执行被插入的方法
                    return (ModelAndView) joint.proceed();
                } else {
                    Utility.log("警告页面");
                    return new ModelAndView("redirect:/alert");
                }
            }
        }
    }


}

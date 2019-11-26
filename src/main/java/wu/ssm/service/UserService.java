package wu.ssm.service;


import wu.ssm.Utility;
import wu.ssm.mapper.TopicMapper;
import wu.ssm.mapper.UserMapper;
import wu.ssm.model.UserModel;
import wu.ssm.model.UserRole;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private UserMapper mapper;
    private TopicMapper Topicmapper;


    public UserService(UserMapper mapper, TopicMapper Topicmapper) {
        this.mapper = mapper;
        this.Topicmapper = Topicmapper;
    }

//    生成4位uuid
    public  String getUUID4(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[1];
    }

    //    加盐
    public static String hexFromBytes(byte[] array) {
        String hex = new BigInteger(1, array).toString(16);
        int zeroLength = array.length * 2 - hex.length();
        for (int i = 0; i < zeroLength; i++) {
            hex = "0" + hex;
        }
        return hex;
    }
    //密码加盐
    public static String saltedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("md5");
        String message = password;
        md.update(message.getBytes());
        byte[] result = md.digest();
        Utility.log("md5 result %s", result.length);
        String hex = hexFromBytes(result);
        Utility.log("md5 result hex (%s) length(%s)", hex, hex.length());
        return hex;
    }

    //    时间格式化
    public static String formattedTime(Long unixTime) {
        Date date = new Date(unixTime * 1000);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateString = dateFormat.format(date);
        return dateString;
    }

    public UserModel add(String username, String password, String mail) {
        Long unixTime = System.currentTimeMillis() / 1000L;

        UserModel m = new UserModel();
        m.setUsername(username);
        try {
            m.setPassword(saltedPassword(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.setRole(UserRole.normal);
        m.setAvatar("/x.jpg");
        m.setCreatedTime(formattedTime(unixTime));
        m.setUpdatedTime(formattedTime(unixTime));
        m.setMail(mail);

        mapper.insert(m);
        return m;
    }

    public UserModel findUserByTopicId(Integer id){
        Integer uid = Topicmapper.selectOne(id).getUserId();
        return mapper.selectOne(uid);
    }



    public UserModel update(Integer id, String uesrname, String password) {
        Long unixTime = System.currentTimeMillis() / 1000L;

        UserModel m = new UserModel();
        m.setId(id);
        m.setUsername(uesrname);
        try {
            m.setPassword(saltedPassword(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.setUpdatedTime(formattedTime(unixTime));

        mapper.update(m);
        return m;
    }




    public void deleteById(Integer id) {
        mapper.delete(id);
    }


    public  UserModel findById(Integer id) {
        return mapper.selectOne(id);
    }

    public  UserModel findByUsername(String username) {
        return mapper.selectOneByUsername(username);
    }

    public UserModel FindUsernameById(Integer id){
        return  mapper.SelectUsernameById(id);
    }

    public  List<UserModel> all() {
        return mapper.selectAll();
    }


    public boolean validateLogin(String username, String password) {
        UserModel user = mapper.selectOneByUsername(username);

        try {
            String inputpassword = saltedPassword(password);
            if (user != null && user.getPassword().equals(inputpassword)) {
                return true;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
            return false;
    }

    public UserModel guest() {
        UserModel guest = new UserModel();
        guest.setId(-1);
        guest.setUsername("游客");
        guest.setPassword("");
        guest.setRole(UserRole.guest);
        return guest;
    }

    public UserModel currentUser(HttpServletRequest request) {
         HttpSession session = request.getSession();
        Integer uid = (Integer) session.getAttribute("user_id");
        if (uid == null) {
            return this.guest();
        } else {
            UserModel current = mapper.selectOne(uid);
            if (current == null) {
                return this.guest();
            } else {
                return current;
            }
        }
    }
}

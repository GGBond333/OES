package com.oes.gbloes;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oes.gbloes.domain.User;
import com.oes.gbloes.service.serviceImpl.UserImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class userTest {
    @Autowired
    UserImpl userimpl;

    @Test
    void isUserInfo(){
        IPage<User> iPage = userimpl.getUserPage("",3,1,10);
        List<User> userList = iPage.getRecords();
        System.out.println("count:" + iPage.getTotal());
        System.out.println("pages:" + iPage.getPages());

        for (User u:
             userList) {
            System.out.println(u);
        }
        //System.out.println(user.getUserPage(null,3,1,10));
    }

    @Test
    void modifyUser(){
        User user = new User();
        user.setId(4);
        user.setUserName("Tom");
        user.setPassword("123456");
        user.setRealName("汤姆");
        user.setRole(1);
        System.out.println(userimpl.modifyUser(user));
    }

    @Test
    void deleteUser(){
        System.out.println(userimpl.deleteUser(4));
    }

    @Test
    void changeStatus() {
        System.out.println(userimpl.changeUserStatus(4));

    }

    @Test
    void addUser(){
        User user = new User();
        user.setUserName("Daming");
        user.setRealName("大明");
        user.setPassword("123456");
        user.setAge(16);
        user.setSex(1);
        user.setStatus(1);
        System.out.println(userimpl.addUser(user));


    }
}

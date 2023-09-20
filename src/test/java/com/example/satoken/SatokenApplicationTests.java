package com.example.satoken;

import com.example.satoken.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class SatokenApplicationTests {

    @Test
    void contextLoads() {

        List<String> templateNameSet = Arrays.asList(new String[]{"asad", "afl", "adfsfd"});
        String errMsg = String.join("、", templateNameSet);
        System.out.println(errMsg);
        templateNameSet.forEach(item -> System.out.print(item));
    }

    @Test
    void stream01() {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.add(6);
//        list.add(7);
//        list.stream()
//                .filter(x -> x % 2 == 0)
//                .forEach(System.out::println);
//        List<User> userList = new ArrayList<>();
//        userList.add(new User("a",1));
//        userList.add(new User("b",2));
//        userList.add(new User("c",3));
//        userList.add(new User("d",4));
//        userList.add(new User("e",5));
//
//        Stream<String> stringStream = userList.stream()
//                .map(User.getAge(),x -> x.getName());
//
//        Object[] objects = stringStream.toArray();
//        System.out.println(stringStream);


    }

}

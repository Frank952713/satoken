package com.example.satoken;

import cn.dev33.satoken.stp.StpUtil;
import com.example.satoken.model.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {


    @Test
    void Main02() throws IllegalAccessException {
        User user = new User("zhangsan", 13);
        for (Field field:user.getClass().getDeclaredFields()){
            //允许访问私有字段
            field.setAccessible(true);
            System.out.print(field.getName()+": "+field.get(user));
            System.out.println();
        }
    }
    @Test
     void Main01(){
        // 假设 componentList 是一个包含 EtsAdditionalComponent 对象的列表
        List<EtsAdditionalComponent> componentList = new ArrayList<>();
        componentList.add(new EtsAdditionalComponent("Component1", 3));
        componentList.add(new EtsAdditionalComponent("Component2", 1));
        componentList.add(new EtsAdditionalComponent("Component3", 2));

        // 使用 sorted() 和 Comparator 进行排序
        List<EtsAdditionalComponent> sortedList = componentList.stream()
                .sorted(Comparator.comparingInt(EtsAdditionalComponent::getOrderBy))
                .collect(Collectors.toList());

        // 输出排序后的列表
        sortedList.forEach(component -> System.out.println(component.getName()));
    }

    static class EtsAdditionalComponent {
        private String name;
        private int orderBy;

        public EtsAdditionalComponent(String name, int orderBy) {
            this.name = name;
            this.orderBy = orderBy;
        }

        public String getName() {
            return name;
        }

        public int getOrderBy() {
            return orderBy;
        }
    }
}





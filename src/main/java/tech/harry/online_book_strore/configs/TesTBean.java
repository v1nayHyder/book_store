package tech.harry.online_book_strore.configs;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class TesTBean {
    public TesTBean(){
        System.out.println("Test bean......");
    }

    @PostConstruct
    public void init(){
        System.out.println("Init-------");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy-------");
    }
}

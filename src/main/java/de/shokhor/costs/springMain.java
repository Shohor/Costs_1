package de.shokhor.costs;

import de.shokhor.costs.repository.Jpa.JpaCostRepositoryImpl;
import de.shokhor.costs.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;


public class springMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext bf = new ClassPathXmlApplicationContext("spring/spring-db.xml");
        System.out.println(Arrays.toString(bf.getBeanDefinitionNames()));

    }
}

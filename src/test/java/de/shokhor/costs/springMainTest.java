package de.shokhor.costs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by user on 14.07.2017.
 */
public class springMainTest {
    public static void main(String[] args) {
        /*ConfigurableApplicationContext actx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println(Arrays.toString(actx.getBeanDefinitionNames()));*/
        GenericXmlApplicationContext appCtx = new GenericXmlApplicationContext();
        appCtx.load("spring/spring-app.xml", "spring/spring-db.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

        appCtx.refresh();
    }
}

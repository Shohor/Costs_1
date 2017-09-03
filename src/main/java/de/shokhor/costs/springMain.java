package de.shokhor.costs;

import de.shokhor.costs.repository.Jpa.JpaCostRepositoryImpl;
import de.shokhor.costs.util.PasswordUtil;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;


public class springMain {
    public static void main(String[] args) {
        System.out.println(PasswordUtil.encode("Russia1984"));
        System.out.println(PasswordUtil.isMatch("Shish1983","$2a$10$nLAIv6MfFCp2g1Y9iW.z0ubvrMzI2L0Sd.zimVj/REx2kQe1/A2xK"));

    }
}

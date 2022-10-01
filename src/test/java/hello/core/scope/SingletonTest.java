package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

       SingletonBean singletonBeans1 = ac.getBean(SingletonBean.class);
       SingletonBean singletonBeans2 = ac.getBean(SingletonBean.class);

       System.out.println("singletonBeans1 = " + singletonBeans1);
       System.out.println("singletonBeans2 = " + singletonBeans2);

       Assertions.assertThat(singletonBeans1).isEqualTo(singletonBeans2);

       ac.close();
    }

    @Scope("singleton")
    static class SingletonBean{

        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }

    }

}

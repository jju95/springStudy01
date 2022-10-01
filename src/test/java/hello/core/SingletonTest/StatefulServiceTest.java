package hello.core.SingletonTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("SINGLETON TEST")
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : 사용자A가 10000원을 주문
        int userAprice = statefulService1.order("userA",10000 ); // 지역변수 설계
        // 공유 필드는 절대 nonooo....!!

        // ThreadB : 사용자B가 20000원을 주문
        int userBprice = statefulService2.order("userB",20000 );

        // 왜 why..?!
        // 똑같은 객체를 사용하는 입장에서 사용자 A 주문 이후, 사용자 B가 주문을 하여
        // 결과론적으로 후에 계산한 사용자B의 주문에 맞게 객체안의 가격이 20000원으로 수정되었음...

        Assertions.assertThat(userAprice).isEqualTo(10000);

    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}
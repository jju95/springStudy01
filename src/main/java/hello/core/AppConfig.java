package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig { // 중복 제거 하고 역할과 구현 나뉨 ( 수정에 용의함, 구성 파악이 쉬움 )

    // 상상속 call 순서
    /*
    call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.memberRepository
    call AppConfig.orderService
    call AppConfig.memberRepository
    */

    // 실제로 call 순서
    /*
    call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.orderService
    */

    @Bean
    public MemberService memberService(){ // 빈 이름
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 빈 객체
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

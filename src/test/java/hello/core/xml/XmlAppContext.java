package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlAppContext {

    @Test
    @DisplayName("xml test")
    void xmlAppContext(){
       ApplicationContext ac  = new GenericXmlApplicationContext("appConfig.xml");
       MemberService memberService = ac.getBean("memberService", MemberService.class);
       assertThat(memberService).isInstanceOf(MemberService.class);
    }
}

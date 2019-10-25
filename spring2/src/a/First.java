package a;

import javax.annotation.PostConstruct;
import javax.jws.soap.InitParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class First {
	private String msg;

	@Autowired
//	@Autowired(required = false) Second객체 없어서 자동주입 실패시 null로 유지하려면 false로 설정
	@Qualifier("s1")
	private Second second; //생성자, setter를 통한 주입이 아닌 어노테이션(@Autowired: 자동 주입) 이용
	//Second객체가 없어서 자동주입 실패시 NoSuchBeanDefinitionException 발생
	//Second객체가 여러개면 주동주입 실패시 NoUniqueBeanDefinitionException 발생
	private boolean flag;
	
	@PostConstruct
	public void init() { //First타입 객체 생성시, 자동 호출되길 바람 -> 
		flag = true;
		System.out.println("init()호출됨");
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String toString() {
		return "msg = " + msg + ", second.info = " + second.info();
	}
	
}

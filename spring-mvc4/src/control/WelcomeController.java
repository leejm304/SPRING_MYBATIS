package control;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController implements ApplicationContextAware{ // messageSource 사용하기위해
	
	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}
	
	@GetMapping("/welcome")
	@ResponseBody
	public String welcome(Locale locale) {
		System.out.println(locale); // ko_KR
		System.out.println(locale.getLanguage()); // ko

		String greeting;
		String code = "greeting";
		Object[]args = null;
		// 1)messageSource에 해당하는 properties 파일찾기
		// 순서 - locale용 properties 파일찾기(message_ko_KR.properties)
		// 		locale없는 properties 파일찾기
		greeting = applicationContext.getMessage(code,args, locale);
		
//		switch(locale.getLanguage()) {
//		case "ko": greeting = "환영합니다";
//		break;
//		default : greeting = "WELCOME";
//		break;
//	}
		return greeting;
  }

}

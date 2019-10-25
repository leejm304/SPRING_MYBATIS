import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import a.First;

@Configuration // @Component의 하위 어노테이션
public class Beans {
//	@Bean
//	public Second second() {
//		//return new Second1();
//		//return new Second2();
//	} 
	
	@Bean
	public First first() {
		//return new First();
		First f = new First();
		f.setMsg("금요일입니다");
//		First  f = new First(Second()); //생성자를 통한 의존성 주입
//		f.setMsg("금요일입니다"); //setter를 이용한 주입
		return f;
	}
}

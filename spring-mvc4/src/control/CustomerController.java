package control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.service.CustomerService;
import com.my.vo.Customer;
import com.my.vo.Post;

@Controller
public class CustomerController {
	@Autowired
	private CustomerService service;

	@RequestMapping("/join")
	@ResponseBody
	public Map<String, Object> join(@ModelAttribute("c") Customer c, 
							String buildingno,
							@RequestParam(required = false, defaultValue = "0") int age // (required = false) : 선택전달(필수X)
							// 모든 요청전달 데이터는 string 타입으로 받아오려함 : string으로 전달된것이 없음
							// -> null : defaultValue = "0" 빈 문자열로 설정해야함
							, @RequestParam("addr2") String addr) {

		Post post = new Post();
		post.setBuildingno(buildingno);
		c.setPost(post);
		c.setAddr(addr);
		
		Map<String, Object> map = service.join(c);
		
		return map;
	}

	@PostMapping("/dupchk")
	@ResponseBody
	public Map<String, Object> dupchk(String id) {
		Map<String, Object> map = service.dupchk(id);
		return map;
	}

	@RequestMapping("/login")
	@ResponseBody //응답 내용을 JSON 문자열로 자동 반환(내부에서 Jackson라이브러리가 일함.)
	public Map<String, Object> login(String id, String pwd, HttpSession session) { 
	//return의 형태가 String 이면 return하는것을 viewer의 이름으로인식한다.
		session.removeAttribute("loginInfo");

		Map<String, Object> map = service.login(id, pwd);
		if((Integer)map.get("status")==1) {
			session.setAttribute("loginInfo",id);
		}	
		return map;
	}
	
//	@RequestMapping("/login")
//	public ModelAndView login(String id, String pwd, HttpSession session) {
//		session.removeAttribute("loginInfo");
//
//		String str = service.login(id, pwd);
//		JSONParser parser = new JSONParser();
//		try {
//			Object obj = parser.parse(str);
//			JSONObject jsonObj = (JSONObject) obj;
//			if ((Long) jsonObj.get("status") == 1) {// 로그인 성공
//				session.setAttribute("loginInfo", id);
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//		// request.setAttribute("result", str);
//		// String path = "/result.jsp";
//		// return path;
//		ModelAndView mnv = new ModelAndView();
//		mnv.addObject("result", str);
//		mnv.setViewName("/result.jsp");
//		return mnv;
//
//	}


	@RequestMapping("/logout")
	@ResponseBody
	public void logout(HttpSession session) {
		// HttpSession session = request.getSession();
		session.invalidate();

	}
}

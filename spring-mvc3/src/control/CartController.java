package control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.vo.Product;

@Controller
public class CartController {

	@PostMapping("/addcart")
	public ModelAndView addCart(Product p, //커맨드객체
								int quantity, 
								HttpSession session) { 
		ModelAndView mnv = new ModelAndView();
		
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");
		if(cart == null) {//장바구니가 없으면
			cart = new HashMap<>();//장바구니 만들어서
			session.setAttribute("cart", cart);//세션에 추가
		}
		Integer oldQuantity = cart.get(p);//상품에 해당 수량 찾기
		if(oldQuantity != null) {
			quantity += oldQuantity;
		}
		cart.put(p, quantity);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", 1);
		mnv.addObject("result", jsonObj.toString());
		mnv.setViewName("/result.jsp");
		return mnv;
	}
	
	//session에 저장되어있기때문에 jsp에서도 sessionScope의 attribute에 저장된 값을 가져올 수 있음
	//컨트롤러에서는 화면 이동만 해주면 되므로 Stirng을 return
	@GetMapping("/cartlist")
	//public ModelAndView cartList(HttpSession session) {
	public String cartList() {
		//ModelAndView mnv = new ModelAndView();
		//mnv.addObject("cart", session.getAttribute("cart"));
		//mnv.setViewName("/result.jsp");
		//return mnv;
		return "/cartlistresult.jsp";
	}
	
}

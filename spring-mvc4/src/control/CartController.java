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
								Integer quantity, 
								HttpSession session){
		System.out.println("p의 정보" + p.getProd_no()+" "+p.getProd_name()+" "+ p.getProd_price()+" "+quantity);
		Map<Product, Integer> cart = (Map)session.getAttribute("cart");
		if(cart==null) {
			cart = new HashMap<>();
			session.setAttribute("cart", cart);
		};
		
		Integer oldQuantity = cart.get(p);
		if(oldQuantity!=null) {
			quantity += oldQuantity;
		}
		
		cart.put(p, quantity);

		JSONObject jsonObj = new JSONObject();
	
		
		session.setAttribute("cart", cart);
		ModelAndView mnv = new ModelAndView();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		mnv.addObject("result", jsonObject.toString());
		mnv.setViewName("/result.jsp");
		return mnv;
	}
	
	@GetMapping("/cartlist")
	public String cartList() {
		return "/cartlist.jsp";
	}
}

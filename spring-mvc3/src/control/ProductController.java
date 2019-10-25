package control;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.my.exception.NotFoundException;
import com.my.service.ProductService;
import com.my.vo.Product;

@Controller
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("/productlist")
	public ModelAndView list() {
		ModelAndView mnv = new ModelAndView();
		List<Product> list;
		try {
			list = service.productList();
			mnv.addObject("list", list);
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      mnv.setViewName("/productlist.jsp");
	      return mnv;
	}
	
//	@GetMapping("/productdetail")
//	@ResponseBody
//	public Map<String, Object> productDetail(String prod_no) throws ParseException {
//		Map<String, Object> map = service.productDetail(prod_no);
//		return map;
//	}
	
	@GetMapping("/productdetail")
	public ModelAndView productDetail(String prod_no) throws ParseException {
		ModelAndView mnv = new ModelAndView();
		String str = service.productDetail(prod_no);
		mnv.addObject("result", str);
		mnv.setViewName("/productdetailresult.jsp");
		return mnv;
	}
	
}


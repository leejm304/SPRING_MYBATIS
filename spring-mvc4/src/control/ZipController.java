package control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.service.ZipService;

@Controller
public class ZipController {
	
	@Autowired
	private ZipService service;

	@PostMapping("/searchzip")
	public ModelAndView searchZip(String doro){
		String str = service.searchZip(doro);
		ModelAndView mnv = new ModelAndView();
		mnv.addObject("result", str);
		mnv.setViewName("/result.jsp");
		return mnv;

	}
}

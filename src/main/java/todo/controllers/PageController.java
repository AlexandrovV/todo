package todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/todo")
public class PageController {
	@RequestMapping(value = "")
	public String index(){
		return "index";
	}
}

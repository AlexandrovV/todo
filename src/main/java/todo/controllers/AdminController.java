package todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/todo")
public class AdminController {
	@RequestMapping(value = "/") 
	public ModelAndView admin(){
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("admin", "admin v");
		return mv;
	}
}

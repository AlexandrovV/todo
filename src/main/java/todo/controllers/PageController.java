package todo.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import todo.models.Day;
import todo.models.TodoLine;
import todo.models.dao.TodoLineDAO;

@Controller
@RequestMapping("/todo")
public class PageController {
	
	@Autowired
	TodoLineDAO todoLineDao;
	
	@RequestMapping(value = "")
	public ModelAndView index(){
		
		ModelAndView mv = new ModelAndView("index");
		
		String currentWeek[] = getCurrentWeek();

		List<Day> week = new ArrayList<Day>();
		List<TodoLine> todos;
		for(String date : currentWeek){
			todos = todoLineDao.findByDate(date);
			week.add(new Day(date, todos));
		}
		mv.addObject("week", week);
		
		return mv;
		
	}
	
	@RequestMapping(value = "/showSelectedWeek")
	public ModelAndView showSelectedWeek(@RequestParam(name="fullWeek") String fullWeek){
		ModelAndView mv = new ModelAndView("index");
		String currentWeek[] = fullWeek.split("-");
		
		List<Day> week = new ArrayList<Day>();
		List<TodoLine> todos;
		for(String date : currentWeek){
			todos = todoLineDao.findByDate(date);
			week.add(new Day(date, todos));
		}
		
		mv.addObject("week", week);
		return mv;
	}
	
	@RequestMapping(value = "/addNote")
	public String addNote(@ModelAttribute TodoLine todoLine){		
		todoLineDao.save(todoLine);
	    return "redirect:/todo";
	}
	
	@RequestMapping(value = "/do/{id}")
	public String doTodo(@PathVariable("id") int id){
		TodoLine todoLine = todoLineDao.findById(id);
		todoLine.setDone(true);
		todoLineDao.save(todoLine);
	    return "redirect:/todo";
	}
	@RequestMapping(value = "/undo/{id}")
	public String undoTodo(@PathVariable("id") int id){
		TodoLine todoLine = todoLineDao.findById(id);
		todoLine.setDone(false);
		todoLineDao.save(todoLine);
	    return "redirect:/todo";
	}
	
	@ModelAttribute("todoline")
	public TodoLine getCategory(){
		return new TodoLine();
	}
	
	
	public String[] getCurrentWeek() {
		String[] week = {getDOW(Calendar.MONDAY), getDOW(Calendar.TUESDAY), getDOW(Calendar.WEDNESDAY), getDOW(Calendar.THURSDAY), getDOW(Calendar.FRIDAY), getDOW(Calendar.SATURDAY), getDOW(Calendar.SUNDAY)};
		return week;
	}
	public String getDOW(int dow) {
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Calendar date = Calendar.getInstance();
		int today = date.get(Calendar.DAY_OF_WEEK);
		if(today<dow){
			
		}
        int diff = dow - date.get(Calendar.DAY_OF_WEEK);
        if (diff < 0) 
            diff += 7;
        date.add(Calendar.DAY_OF_MONTH, diff);
        return df.format(date.getTime());
	}
}

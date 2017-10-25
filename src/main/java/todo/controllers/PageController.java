package todo.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import todo.models.Day;
import todo.models.TodoLine;
import todo.models.User;
import todo.models.dao.TodoLineDAO;
import todo.models.dao.UserDAO;

@Controller
@RequestMapping("/todo")
public class PageController {

	@Autowired
	HttpSession session;
	
	@Autowired
	TodoLineDAO todoLineDao;
	
	@Autowired
	UserDAO userDao;

	@RequestMapping(value = "")
	public ModelAndView index(){
		
		ModelAndView mv = new ModelAndView("index");
		
		User u = (User) session.getAttribute("user");
		System.out.println(u);
		if(SecurityContextHolder.getContext().getAuthentication()==null) {
			mv.setViewName("login");
			return mv;
		}
		String currentWeek[] = getCurrentWeek();
		
		List<Day> week = new ArrayList<Day>();
		List<TodoLine> todos;
		for(String date : currentWeek){
			todos = todoLineDao.findByDateAndUserId(date, u.getId());
			week.add(new Day(date, todos));
		}
		mv.addObject("week", week);
		
		return mv;
		
	}
	
//	@RequestMapping(value = "/showSelectedWeek")
//	public String redirect(@RequestParam(name="newsdate") String weekRange){
//		User u = (User) session.getAttribute("user");
//		System.out.println(u);
//		if(SecurityContextHolder.getContext().getAuthentication()==null) {
//			return "redirect:/todo/login";
//		}
//		
//		return "redirect:/todo/showSelectedWeek/"+weekRange;
//		
//	}
	
	@RequestMapping(value = "/showSelectedWeek/{week}")
	public ModelAndView showSelectedWeek2(@PathVariable("week") String weekRange){
		
		ModelAndView mv = new ModelAndView("index");
		User u = (User) session.getAttribute("user");
		String selectedWeek[] = formatWeek(weekRange);
		
		List<Day> week = new ArrayList<Day>();
		List<TodoLine> todos;
		for(String date : selectedWeek){
			todos = todoLineDao.findByDateAndUserId(date, u.getId());
			week.add(new Day(date, todos));
		}
		
		mv.addObject("week", week);
		
		return mv;
	}
		
	@RequestMapping(value = "/addNote")
	public String addNote(@ModelAttribute TodoLine todoLine, @RequestParam(name="link") String link){		
		User u = (User) session.getAttribute("user");
		todoLine.setUserId(u.getId());
		todoLineDao.save(todoLine);
	    return "redirect:"+link;
	}
	
	@RequestMapping(value = "/do/{id}")
	public String doTodo(@PathVariable("id") int id, @RequestParam(name="link") String link){
		TodoLine todoLine = todoLineDao.findById(id);
		todoLine.setDone(true);
		todoLineDao.save(todoLine);
	    return "redirect:"+link;
	}
	@RequestMapping(value = "/undo/{id}")
	public String undoTodo(@PathVariable("id") int id, @RequestParam(name="link") String link){
		TodoLine todoLine = todoLineDao.findById(id);
		todoLine.setDone(false);
		todoLineDao.save(todoLine);
	    return "redirect:"+link;
	}
	@RequestMapping(value = "/delete/{id}")
	public String deleteTodo(@PathVariable("id") int id, @RequestParam(name="link") String link){
		todoLineDao.deleteById(id);
	    return "redirect:"+link;
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name="error", required = false) String error, @RequestParam(name="logout", required = false) String logout) {
		
		ModelAndView mv = new ModelAndView("login");
		
		if(error!=null) {
			mv.addObject("message", "Invalid Username and Password!");
		}
		if(logout!=null) {
			mv.addObject("logout", "User has successfully logout!");
		}
		

		return mv;

	}
	@RequestMapping(value = "/register")
	public String registration(){
		return "registration";
	}
	@RequestMapping(value = "/registerUser")
	public String registerUser(@Valid @ModelAttribute User user, BindingResult results){
		userDao.save(user);
		return "redirect:/todo/login";
	}
	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		// first we are going to fetch the authentication
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth!=null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
				
		return "redirect:/todo/login?logout";
	}
	
	@RequestMapping(value = "/403")
	public String accessDenied() {
		return "403";
	}
	
	@ModelAttribute("todoline")
	public TodoLine getCategory(){
		return new TodoLine();
	}
	@ModelAttribute("user")
	public User getUser(){
		return new User();
	}
	
	public String[] getCurrentWeek() {
		Calendar now = Calendar.getInstance();

	    SimpleDateFormat format = new SimpleDateFormat("d.M.yyyy");

	    String[] days = new String[7];
	    int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 2; //add 2 if your week start on monday
	    now.add(Calendar.DAY_OF_MONTH, delta );
	    for (int i = 0; i < 7; i++)
	    {
	        days[i] = format.format(now.getTime());
	        now.add(Calendar.DAY_OF_MONTH, 1);
	    }
	    return days;
	}
	public String[] formatWeek(String weekRange) {
		String start = weekRange.split("-")[0];
		
		String startDate[] = start.split("\\.");

		int day, month, year;
		day = Integer.parseInt(startDate[0]);
		month = Integer.parseInt(startDate[1]);
		year = Integer.parseInt(startDate[2]);
		
		String selectedWeek[] = new String[7];
		selectedWeek[0] = day+"."+month+"."+year;
		for(int i=1; i<7; i++){
			if( month==1 || month==3 || month==5 || month==7 || month==8 || month==10 ){
				if(day>30){
					day=1;
					month++;
				}
				else
					day++;
			}
			else if( month==4 || month==6 || month==9 || month==11 )
				if(day>29) {
					day=1;
					month++;
				} 
				else
					day++;
			else if( month==2 ){
				if(day>27) {
					day=1;
					month++;
				}
				else
					day++;
			}
			else if( month==12 ){
				if(day>30){
					day=1;
					month=1;
					year++;
				}
				else
					day++;
			}
			selectedWeek[i] = day+"."+month+"."+year;
		}
		return selectedWeek;
	}	
}

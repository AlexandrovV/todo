package todo.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
	public String redirect(@RequestParam(name="newsdate") String weekRange){
		return "redirect:/todo/showSelectedWeek/"+weekRange;
		
	}
	
	@RequestMapping(value = "/showSelectedWeek/{week}")
	public ModelAndView showSelectedWeek2(@PathVariable("week") String weekRange){
		
		ModelAndView mv = new ModelAndView("index");
		
		String selectedWeek[] = formatWeek(weekRange);
		
		List<Day> week = new ArrayList<Day>();
		List<TodoLine> todos;
		for(String date : selectedWeek){
			todos = todoLineDao.findByDate(date);
			week.add(new Day(date, todos));
		}
		
		mv.addObject("week", week);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/addNote")
	public String addNote(@ModelAttribute TodoLine todoLine, @RequestParam(name="link") String link){		
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
	
	@ModelAttribute("todoline")
	public TodoLine getCategory(){
		return new TodoLine();
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

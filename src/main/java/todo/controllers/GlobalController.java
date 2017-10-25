package todo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import todo.models.User;
import todo.models.dao.UserDAO;

@ControllerAdvice
public class GlobalController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	UserDAO userDAO;
	
	@ModelAttribute("globalUser")
	public User getGlobalUser() {
		if(session.getAttribute("user")==null) {
			// add the user model
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = userDAO.findByLogin(authentication.getName());
		
			//set the userModel in the session
			if(user!=null)
				session.setAttribute("user", user);
			return user;
		}
		return (User) session.getAttribute("user");
	}
}

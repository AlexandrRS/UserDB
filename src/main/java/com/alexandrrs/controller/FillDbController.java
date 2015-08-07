package com.alexandrrs.controller;

import com.alexandrrs.model.User;
import com.alexandrrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FillDbController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/filling-db")
	public ModelAndView addFakeUsersPage()
	{
		ModelAndView modelAndView = new ModelAndView("filling-db");
		return modelAndView;
	}

	@RequestMapping(value="/fill")
	public ModelAndView addFakeUsers() {
		ModelAndView modelAndView = new ModelAndView("home");
		String[][] fakeUsers = {
				{"Elvis Presley", "80", "false"},
				{"Ornella Muti", "60", "false"},
                {"Adriano Chelentano", "60", "false"},
                {"Jacky Chan", "60", "false"},
                {"Chuck Norris", "60", "true"},
                {"Monica Belucci", "50", "false"}
		};

		for (int i = 0; i < fakeUsers.length; i++) {
			User user = new User();
			user.setName(fakeUsers[i][0]);
			user.setAge(Integer.parseInt(fakeUsers[i][1]));
			user.setIsAdmin(Boolean.parseBoolean(fakeUsers[i][2]));
			userService.addUser(user);
		}

		String message = "DB felt";
		modelAndView.addObject("message", message);

		return modelAndView;
	}
}

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
		return new ModelAndView("filling-db");
	}

	@RequestMapping(value="/fill")
	public ModelAndView addFakeUsers() {
		ModelAndView modelAndView = new ModelAndView("home");
		String[][] fakeUsers = {
				{"Элвис Пресли", "80", "false"},
				{"Орнелла Мути", "60", "false"},
                {"Адриано Челентано", "77", "false"},
                {"Джеки Чан", "61", "false"},
                {"Чак Норрис", "75", "true"},
                {"Моника Белуччи", "50", "false"},
                {"Энтони Хопкинс", "77", "false"},
                {"Венсан Кассель", "48", "true"},
                {"Киану Ривз", "50", "false"},
                {"Мел Гибсон", "59", "true"},
                {"Эмир Кустурица", "60", "false"},
                {"Арнольд Шварценеггер", "68", "true"},
                {"Пенелопа Крус", "41", "false"},
                {"Сальма Хайек", "48", "true"},
                {"Скарлетт Йоханссон", "30", "false"},
                {"Кира Найтли", "30", "true"},
                {"Николь Кидман", "48", "false"},
                {"Анджелина Джоли", "40", "true"},
                {"Том Круз", "53", "false"},
                {"Антонио Бандерас", "54", "true"},
                {"Кэтрин Зета-Джонс", "45", "false"},
                {"Майкл Дуглас", "70", "true"},
                {"Шон Коннери", "84", "false"},
                {"Пирс Броснан", "62", "true"},
                {"Харрисон Форд", "73", "false"},
                {"Шон Пенн", "54", "true"},
                {"Майкл Кейн", "82", "false"},
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

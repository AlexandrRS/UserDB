package com.alexandrrs.controller;

import java.util.ArrayList;
import java.util.List;

import com.alexandrrs.model.Filter;
import com.alexandrrs.model.User;
import com.alexandrrs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addUserPage() {
		ModelAndView modelAndView = new ModelAndView("add-user-form");
		modelAndView.addObject("filter", new Filter());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingUser(@ModelAttribute Filter filter) {

        filter.validateData();

        String message = "User: " + filter.getName() +" added.";
        if (filter.getAge().matches("^[0-9]{1}$|^[1-9]{1}[0-9]{1}$|^1[0-4]{1}[0-9]{1}$"))
        {
            User user = filter.generateUser();
            userService.addUser(user);
        }
        else
        {
            message = "User: " + filter.getName() +" not added. Age is incorrect. Go back and edit.";
        }
		ModelAndView modelAndView = new ModelAndView("info-user-form");
		modelAndView.addObject("message", message);
        modelAndView.addObject("filt", filter.getName());
		return modelAndView;
	}

	@RequestMapping(value="/list/page{id}.html&filter={filt}", method=RequestMethod.GET)
	public ModelAndView listOfUsers(@PathVariable Integer id, @PathVariable String filt) {

        Filter filter = new Filter();
        filter.setName(filt);

        String message = "Here you can see all users, delete and edit them";
        if (!filt.equals(""))
        {
            message = "users filtered by name, filter is: " + filter.getName();
        }

		return UserController.createListOfUsersModelAndView(userService.getUsers(), filter, 5, id, message);
	}

	@RequestMapping(value="/list/page{id}.html&filter={filt}", method=RequestMethod.POST)
	public ModelAndView filteredListOfUsers(@PathVariable Integer id, @PathVariable String filt, @ModelAttribute Filter filter)
    {
        filter.validateData();

		return UserController.createListOfUsersModelAndView(userService.getUsers(), filter, 5, id, "users filtered by name, filter is: " + filter.getName());
	}

	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editUserPage(@PathVariable Integer id)
    {
		ModelAndView modelAndView = new ModelAndView("edit-user-form");
		User user = userService.getUser(id);
        Filter filter = new Filter(user);
		modelAndView.addObject("filter", filter);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingUser(@ModelAttribute Filter filter, @PathVariable Integer id) {

        filter.validateData();

        ModelAndView modelAndView = new ModelAndView("info-user-form");

        String message = "User: " + filter.getName() +" was successfully edited.";
        if (filter.getAge().matches("^[0-9]{1}$|^[1-9]{1}[0-9]{1}$|^1[0-4]{1}[0-9]{1}$"))
        {

            User user = new User();
            filter.updateUser(user);
            userService.updateUser(user);
        }
        else
        {
            message = "User: " + filter.getName() +" not edited. Age is incorrect. Go back and edit.";
        }

		modelAndView.addObject("message", message);
        modelAndView.addObject("filt", filter.getName());
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteTeam(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("info-user-form");

        User user = userService.getUser(id);
        userService.deleteUser(id);
		String message = "User: " + user.getName() +" was successfully deleted.";
		modelAndView.addObject("message", message);
        modelAndView.addObject("filt", user.getName());
		return modelAndView;
	}

    private static ModelAndView createListOfUsersModelAndView(
            List<User> sourceDb,
            Filter filter,
            int pagesPerList,
            int currentPage,
            String message
    )
    {
        ModelAndView modelAndView = new ModelAndView("list-of-users");

        List<User> users = new ArrayList<>();

        for (User userFromDB : sourceDb)
        {
            String name = userFromDB.getName();

            if (name.toLowerCase().contains(filter.getName().toLowerCase()))
            {
                users.add(userFromDB);
            }
        }

        PagedListHolder<User> pagedListHolder = new PagedListHolder<>(users);
        pagedListHolder.setPageSize(pagesPerList);
        pagedListHolder.setPage(currentPage - 1);

        modelAndView.addObject("message", message);
        modelAndView.addObject("users", pagedListHolder.getPageList());
        modelAndView.addObject("pagecount", pagedListHolder.getPageCount());
        modelAndView.addObject("currentpage", currentPage);
        modelAndView.addObject("filter", filter);

        return modelAndView;
    }

}

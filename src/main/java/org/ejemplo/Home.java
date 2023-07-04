package org.ejemplo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Home {
    @GetMapping("/")
	public ModelAndView index() {
		return new ModelAndView("redirect:" + "/index.html");
	}
}

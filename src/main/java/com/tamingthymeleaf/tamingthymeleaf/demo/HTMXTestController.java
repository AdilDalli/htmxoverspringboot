package com.tamingthymeleaf.tamingthymeleaf.demo;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/public/htmx-test-view")
public class HTMXTestController {
	
	
    @GetMapping
    public String start(Model model) {
        model.addAttribute("now", new Date().toInstant());
        return "htmx-test-view";
    }
    

	final String html = """
			<div id="parent-div">
			Clicked %s time(s)
				<button hx-post="/public/htmx-test-view/clicked" hx-trigger="click"
					hx-target="#parent-div" hx-swap="outerHTML">Click Me!</button>
			</div>

					""";
	
	final String clicked="Clicked %s time(s)";
	int nm = 0;

	@PostMapping("/clicked")
	@ResponseBody
	public String updateContact(Model model) {
		nm += 1;

	//	return html.formatted(nm);
		
		return clicked.formatted(nm);
	}
}
package de.woock.infra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import de.woock.infra.service.AnfragenService;
import lombok.AllArgsConstructor;

@CrossOrigin
@AllArgsConstructor
@Controller
public class AnfragenMVCController {
	
	private AnfragenService anfragenService;

	@GetMapping({"/", "/index"})
    public ModelAndView home() {
    	ModelAndView model = new ModelAndView("index");
    	model.addObject("anfragen", anfragenService.alleAnfragen());
        return model;
    }
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/anfragen")
	public ModelAndView  alleAnfragen() {
		ModelAndView model = new ModelAndView("anfragen");
		model.addObject("anfragen", anfragenService.alleAnfragen());
		return model;
	}
}
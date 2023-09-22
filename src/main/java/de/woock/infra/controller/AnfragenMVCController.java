package de.woock.infra.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import de.woock.infra.converter.dozer.Converter;
import de.woock.infra.repository.AnfrageDTO;
import de.woock.infra.service.AnfragenService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RequiredArgsConstructor
@Controller
public class AnfragenMVCController {
	
	private final AnfragenService anfragenService;
	private final Converter       converter;

	@GetMapping({"/", "/index"})
    public ModelAndView home() {
    	ModelAndView model = new ModelAndView("index");
    	
    	model.addObject("anfragen", anfragen());
        return model;
    }

	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/anfragen")
	public ModelAndView  alleAnfragen() {
		ModelAndView model = new ModelAndView("anfragen");
		model.addObject("anfragen", anfragen());
		return model;
	}
	
	private List<AnfrageDTO> anfragen() {
		return  anfragenService.alle()
                               .stream()
                               .map(anfrage -> converter.toDto(anfrage))
                               .collect(Collectors.toList());
	}

}
package projekti.tulkkaukset.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import projekti.tulkkaukset.domain.Tulkkaus;
import projekti.tulkkaukset.domain.TulkkausRepository;
import projekti.tulkkaukset.domain.TulkkauskieliRepository;
import projekti.tulkkaukset.domain.TulkkaustyyppiRepository;

@Controller
public class TulkkausController {

	
	@Autowired
	private TulkkausRepository repository;

	@Autowired
	private TulkkauskieliRepository krepository;

	@Autowired
	private TulkkaustyyppiRepository trepository;
	
	@Autowired
	private SendEmailServiceImpl sendEmailService;

	@GetMapping(value = { "/", "/tulkkauslista" })
	public String index(Model model) {
		model.addAttribute("tulkkaukset", repository.findAll());
		return "tulkkauslista";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	// RESTful service to get all tulkkaukset
	@RequestMapping(value = "/tulkkaukset", method = RequestMethod.GET)
	public @ResponseBody List<Tulkkaus> tulkkausListRest() {
		return (List<Tulkkaus>) repository.findAll();
	}

	// RESTful service to get tulkkaus by id
	@RequestMapping(value = "/tulkkaus/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Tulkkaus> findTulkkausRest(@PathVariable("id") Long tulkkausId) {
		return repository.findById(tulkkausId);
	}

	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("tulkkaus", new Tulkkaus());
		model.addAttribute("tulkkaustyypit", trepository.findAll());
		model.addAttribute("tulkkauskielet", krepository.findAll());
		
		return "addtulkkaus";
	}

	@PostMapping("/save")
	public String saveTulkkaus(@Valid Tulkkaus tulkkaus, Errors errors, Model model) {
		if (errors.hasErrors()) {
			model.addAttribute("tulkkaustyypit", trepository.findAll());
			model.addAttribute("tulkkauskielet", krepository.findAll());
			return "addtulkkaus";
		}
		
		repository.save(tulkkaus);
		sendEmailService.sendEmail("janica.fagerblom@gmail.com", "Sinulle on varattu uusi tulkkaus", 
				"Tulkkausksen tiedot: \n" + 
				"Aika: " + tulkkaus.getPvm() + "\n" + 
				"Kieli: " + tulkkaus.getTulkkauskieli().getTulkkauskielennimi() + "\n" +
				"Tulkkauksen tyyppi: " + tulkkaus.getTulkkaustyyppi().getTulkkaustyypinnimi() + "\n" +
				"Tulkkauksen aihe: " + tulkkaus.getAihe() + "\n" +
				"Tilaaja: " + tulkkaus.getTilaaja() + "\n" +
				"Tulkkauspaikan osoite: " + tulkkaus.getOsoite() + "\n");
		return "redirect:/";
	}

	@GetMapping("/cancel/{id}")
	public String cancelTulkkaus(@PathVariable("id") Long tulkkausId, Model model) {
		List<Tulkkaus> tulkkaus = new ArrayList<>();
		tulkkaus.addAll(repository.findAll());
		int index = 0;
		for(int i = 0; i<tulkkaus.size();i++) {
			if(tulkkaus.get(i).getId()==tulkkausId) {
				index = i;
			}
		}
		
		sendEmailService.sendEmail("janica.fagerblom@gmail.com", "Seuraava tulkkaus on PERUTTU", 
				"PERUTETUN tulkkausksen tiedot: \n" + 
				"Aika: " + tulkkaus.get(index).getPvm() + "\n" + 
				"Kieli: " + tulkkaus.get(index).getTulkkauskieli().getTulkkauskielennimi() + "\n" +
				"Tulkkauksen tyyppi: " + tulkkaus.get(index).getTulkkaustyyppi().getTulkkaustyypinnimi() + "\n" +
				"Tulkkauksen aihe: " + tulkkaus.get(index).getAihe() + "\n" +
				"Tilaaja: " + tulkkaus.get(index).getTilaaja() + "\n" +
				"Tulkkauspaikan osoite: " + tulkkaus.get(index).getOsoite() + "\n");
		repository.deleteById(tulkkausId);
		return "redirect:../";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTulkkaus(@PathVariable("id") Long tulkkausId, Model model) {
		repository.deleteById(tulkkausId);
		return "redirect:../";
	}

	@GetMapping("/edit/{id}")
	public String editTulkkaus(@PathVariable("id") Long tulkkausId, Model model) {
		model.addAttribute("tulkkaus", repository.findById(tulkkausId));
		model.addAttribute("tulkkaustyypit", trepository.findAll());
		model.addAttribute("tulkkauskielet", krepository.findAll());
		return "edittulkkaus";
	}
	
	@PostMapping("/editvalidation")
	public String editValidation(Model model, @Valid Tulkkaus muutettuTulkkaus, Errors errors) {
			if (errors.hasErrors()) {
				model.addAttribute("tulkkaustyypit", trepository.findAll());
				model.addAttribute("tulkkauskielet", krepository.findAll());
				return "edittulkkaus";
			}
			
			List<Tulkkaus> tulkkaukset = new ArrayList<>();
			tulkkaukset.addAll(repository.findAll());
			int index = 0;
			for(int i = 0; i<tulkkaukset.size();i++) {
				if(tulkkaukset.get(i).getId()==muutettuTulkkaus.getId()) {
					index = i;
				}
			}
			sendEmailService.sendEmail("janica.fagerblom@gmail.com", "Seuraavan tulkkauksen tietoja on MUUTETTU", 
					"Muutetun tulkkausksen VANHAT tiedot: \n" + 
					"Aika: " + tulkkaukset.get(index).getPvm() + "\n" + 
					"Kieli: " + tulkkaukset.get(index).getTulkkauskieli().getTulkkauskielennimi() + "\n" +
					"Tulkkauksen tyyppi: " + tulkkaukset.get(index).getTulkkaustyyppi().getTulkkaustyypinnimi() + "\n" +
					"Tulkkauksen aihe: " + tulkkaukset.get(index).getAihe() + "\n" +
					"Tilaaja: " + tulkkaukset.get(index).getTilaaja() + "\n" +
					"Tulkkauspaikan osoite: " + tulkkaukset.get(index).getOsoite() + ".\n\n" +
					"Muutetun tulkkausksen UUDET tiedot: \n" + 
					"Aika: " + muutettuTulkkaus.getPvm() + "\n" + 
					"Kieli: " + muutettuTulkkaus.getTulkkauskieli().getTulkkauskielennimi() + "\n" +
					"Tulkkauksen tyyppi: " + muutettuTulkkaus.getTulkkaustyyppi().getTulkkaustyypinnimi() + "\n" +
					"Tulkkauksen aihe: " + muutettuTulkkaus.getAihe() + "\n" +
					"Tilaaja: " + muutettuTulkkaus.getTilaaja() + "\n" +
					"Tulkkauspaikan osoite: " + muutettuTulkkaus.getOsoite() + "\n");
			repository.save(muutettuTulkkaus);
			
			return "redirect:/";
		}

}

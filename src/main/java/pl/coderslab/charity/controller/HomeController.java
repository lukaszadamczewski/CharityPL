package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


@Controller
public class HomeController {
    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }


    @RequestMapping("/")
    public String homeAction(Model model){
        List<Donation> donations = donationRepository.findAll();
        int bags = 0;
        for(Donation donation:donations) {
            bags += donation.getQuantity();
        }
        model.addAttribute("bags", bags);
        return "index";
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions(){
        return institutionRepository.findAll();
    }

    @ModelAttribute("donations")
    public List<Donation> donations(){
        return donationRepository.findAll();
    }

}

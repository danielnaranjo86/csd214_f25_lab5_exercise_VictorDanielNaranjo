package ca.saultcollege.csd214.lab5.controllers;

import ca.saultcollege.csd214.lab5.entities.DiscMagEntity;
import ca.saultcollege.csd214.lab5.repositories.DiscMagEntityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/discmags")
public class DiscMagController {

    private final DiscMagEntityRepository discMagRepository;

    public DiscMagController(DiscMagEntityRepository discMagRepository) {
        this.discMagRepository = discMagRepository;
    }

    @GetMapping
    public String listDiscMags(Model model) {
        List<DiscMagEntity> discMags = discMagRepository.findAll();
        model.addAttribute("discMags", discMags);
        return "discMagList";
    }
}
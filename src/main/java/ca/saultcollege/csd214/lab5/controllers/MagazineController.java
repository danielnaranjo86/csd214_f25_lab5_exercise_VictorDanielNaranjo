package ca.saultcollege.csd214.lab5.controllers;

import ca.saultcollege.csd214.lab5.entities.MagazineEntity;
import ca.saultcollege.csd214.lab5.repositories.MagazineEntityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/magazines")
public class MagazineController {

    private final MagazineEntityRepository magazineRepository;

    public MagazineController(MagazineEntityRepository magazineRepository) {
        this.magazineRepository = magazineRepository;
    }

    @GetMapping
    public String listMagazines(Model model) {
        List<MagazineEntity> magazines = magazineRepository.findAll();
        model.addAttribute("magazines", magazines);
        return "magazineList";
    }
}

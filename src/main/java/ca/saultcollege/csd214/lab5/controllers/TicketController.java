package ca.saultcollege.csd214.lab5.controllers;

import ca.saultcollege.csd214.lab5.entities.TicketEntity;
import ca.saultcollege.csd214.lab5.repositories.TicketEntityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketEntityRepository ticketRepository;

    public TicketController(TicketEntityRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    public String listTickets(Model model) {
        List<TicketEntity> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        return "ticketList";
    }
}

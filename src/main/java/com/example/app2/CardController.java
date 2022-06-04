package com.example.app2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/getall")
    public List<Card> getAllCards(){
        return cardService.getAllCards();
    }

    @GetMapping("/get/name/equals/{name}")
    public List<Card> getCardsByNameEqual(@PathVariable final String name){
        return cardService.getCardsByNameEqual(name);
    }

    @GetMapping("/get/name/contains/{name}")
    public List<Card> getCardsByNameContaining(@PathVariable final String name){
        return cardService.getCardsByNameContaining(name);
    }

    @GetMapping("/get/surname/equals/{surname}")
    public List<Card> getCardsBySurnameEqual(@PathVariable final String surname){
        return cardService.getCardsBySurnameEqual(surname);
    }

    @GetMapping("/get/surname/contains/{surname}")
    public List<Card> getCardsBySurnameContaining(@PathVariable final String surname){
        return cardService.getCardsBySurnameContaining(surname);
    }

    @GetMapping("/get/phone/equals/{phone}")
    public List<Card> getCardsByPhoneEqual(@PathVariable final String phone){
        return cardService.getCardsByPhoneEqual(phone);
    }

    @GetMapping("/get/phone/contains/{phone}")
    public List<Card> getCardsByPhoneContaining(@PathVariable final String phone){
        return cardService.getCardsByPhoneContaining(phone);
    }
}

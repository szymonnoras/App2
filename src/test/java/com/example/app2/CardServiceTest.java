package com.example.app2;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CardServiceTest {

    @Autowired
     CardService cardService;

     static final Logger logger = Logger.getLogger(CardServiceTest.class.getName());
     private String validData;
     private String invalidData;

    @BeforeEach
    void init(){
        logger.info("startup");

        validData = "src/test/java/resources/validData.csv";
        invalidData = "src/test/java/resources/invalidData.csv";
        cardService=new CardService();
    }

    @AfterEach
    void teardown(){
        logger.info("teardown\n");
        validData=null;
        invalidData=null;
        cardService=null;
    }

    @Test
    void getDataFromCVSReturnsNotNullValue(){
        logger.info("executing test");
        assertFalse(cardService.getDataFromCSV(validData).isEmpty());
    }

    @Test
    void validateDataReturnsNotNullValueFromValidFile(){
        logger.info("executing test");
        assertFalse(cardService.validateData(validData).isEmpty());
    }

    @Test
    void validateDataThrowsExceptionForInvalidFile(){
        logger.info("executing test");
        assertThrows(IllegalArgumentException.class, () -> cardService.validateData(invalidData));
    }

    @Test
    void parseDataToCardReturnsListOfCards(){
        logger.info("executing test");
        assertTrue(cardService.parseDataToCard(validData).get(0) instanceof Card);
    }

    @Test
    void getCardsByNameEqualIsNotNull(){
        logger.info("executing test");
        cardService.setPath("src/test/java/resources/validData.csv");
        assertFalse(cardService.getCardsByNameEqual("Gordon").isEmpty());
    }

    @Test
    void getCardsByNameEqualNamesEqualsParameter(){
        logger.info("executing test");
        cardService.setPath("src/test/java/resources/validData.csv");
        String name="Gordon";
        List<Card> cards=cardService.getCardsByNameEqual(name);
        boolean valid=true;
        for (Card card : cards) {
            if (!card.getName().equals(name)) {
                valid = false;
                break;
            }
        }
        assertTrue(valid);
    }

    @Test
    void getCardsByNameContainsNamesContainsParameter(){
        logger.info("executing test");
        cardService.setPath("src/test/java/resources/validData.csv");
        String name="Gor";
        List<Card> cards=cardService.getCardsByNameEqual(name);
        boolean valid=true;
        for (Card card : cards) {
            if (!card.getName().contains(name)) {
                valid = false;
                break;
            }
        }
        assertTrue(valid);
    }

    @Test
    void getCardsBySurnameEqualSurnamesEqualsParameter(){
        logger.info("executing test");
        cardService.setPath("src/test/java/resources/validData.csv");
        String surname="Collins";
        List<Card> cards=cardService.getCardsBySurnameEqual(surname);
        boolean valid=true;
        for (Card card : cards) {
            if (!card.getSurname().equals(surname)) {
                valid = false;
                break;
            }
        }
        assertTrue(valid);
    }

    @Test
    void getCardsBySurnameContainsSurnamesContainsParameter(){
        logger.info("executing test");
        cardService.setPath("src/test/java/resources/validData.csv");
        String surname="ll";
        List<Card> cards=cardService.getCardsBySurnameEqual(surname);
        boolean valid=true;
        for (Card card : cards) {
            if (!card.getSurname().contains(surname)) {
                valid = false;
                break;
            }
        }
        assertTrue(valid);
    }

    @Test
    void getCardsByPhoneEqualPhoneEqualsParameter(){
        logger.info("executing test");
        cardService.setPath("src/test/java/resources/validData.csv");
        String phone="876315751796";
        List<Card> cards=cardService.getCardsByPhoneEqual(phone);
        boolean valid=true;
        for (Card card : cards) {
            if (!card.getPhone().equals(phone)) {
                valid = false;
                break;
            }
        }
        assertTrue(valid);
    }

    @Test
    void getCardsByPhoneContainsPhoneContainsParameter(){
        logger.info("executing test");
        cardService.setPath("src/test/java/resources/validData.csv");
        String phone="123";
        List<Card> cards=cardService.getCardsByPhoneEqual(phone);
        boolean valid=true;
        for (Card card : cards) {
            if (!card.getPhone().contains(phone)) {
                valid = false;
                break;
            }
        }
        assertTrue(valid);
    }
}
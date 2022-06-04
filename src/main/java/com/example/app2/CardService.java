package com.example.app2;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final String path = "src/main/resources/static/data.csv";

    public List<String[]> getDataFromCSV() {
        List<String[]> allData = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            CSVReader csvReader = new CSVReader(fileReader);
            allData = csvReader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allData;
    }

    public List<String[]> validateData() {
        List<String[]> dataToValidate = getDataFromCSV();
        for (int i = 0; i < dataToValidate.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (dataToValidate.get(i)[j].isEmpty()) {
                    throw new IllegalArgumentException("Found no data in column: " + dataToValidate.get(0)[j] + ", row: " + (i + 1));
                }
            }
        }
        return dataToValidate;
    }

    public List<Card> parseDataToCard() {
        List<String[]> dataToParse=validateData();
        List<Card> parsedData = new ArrayList<>();
        for (String[] row : dataToParse) {
            parsedData.add(new Card(row[0], row[1], row[2]));
        }
        return parsedData;
    }

    public List<Card> getAllCards() {
        return parseDataToCard();
    }

    public List<Card> getCardsByNameEqual(String name) {
        List<Card> cards=getAllCards();
        cards.removeIf(c -> !c.getName().equals(name));
        return cards;
    }

    public List<Card> getCardsByNameContaining(String name) {
        List<Card> cards=getAllCards();
        cards.removeIf(c -> !c.getName().contains(name));
        return cards;
    }

    public List<Card> getCardsBySurnameEqual(String surname) {
        List<Card> cards=getAllCards();
        cards.removeIf(c -> !c.getSurname().equals(surname));
        return cards;
    }

    public List<Card> getCardsBySurnameContaining(String surname) {
        List<Card> cards=getAllCards();
        cards.removeIf(c -> !c.getSurname().contains(surname));
        return cards;
    }

    public List<Card> getCardsByPhoneEqual(String phone) {
        List<Card> cards=getAllCards();
        cards.removeIf(c -> !c.getPhone().equals(phone));
        return cards;
    }

    public List<Card> getCardsByPhoneContaining(String phone) {
        List<Card> cards=getAllCards();
        cards.removeIf(c -> !c.getPhone().contains(phone));
        return cards;
    }
}

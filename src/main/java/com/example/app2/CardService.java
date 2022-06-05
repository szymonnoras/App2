package com.example.app2;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private String path = "src/main/resources/static/data.csv";

    public void setPath(String path) {
        this.path = path;
    }


    /**
     * Extracts data from CSV file without first row which contains headers.
     *
     * @param path path of file to get data from
     * @return data extracted from CSV file
     */
    public List<String[]> getDataFromCSV(String path) {
        List<String[]> allData = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            CSVReader csvReader = new CSVReader(fileReader);
            allData = csvReader.readAll();
            allData.remove(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allData;
    }

    /**
     * Method validates if data from file is in proper form (each row of the list contains not empty 3 strings)
     * Returns empty list if data is not valid.
     * Returns data if it is valid.
     *
     * @param path path of file to get data from
     * @return valid data or empty list
     */
    public List<String[]> validateData(String path) {
        List<String[]> dataToValidate = getDataFromCSV(path);
        for (int i = 0; i < dataToValidate.size(); i++) {
            for (int j = 0; j < 3; j++) {
                if (dataToValidate.get(i)[j].isEmpty()) {
                    throw new IllegalArgumentException("Found no data in column: " + dataToValidate.get(0)[j] + ", row: " + (i + 1));
                }
            }
        }
        return dataToValidate;
    }

    /**
     * Parses valid List of arrays of strings to List of Cards
     *
     * @param path path of file to get data from
     * @return parsed list of Cards
     */
    public List<Card> parseDataToCard(String path) {
        List<String[]> dataToParse=validateData(path);
        List<Card> parsedData = new ArrayList<>();
        for (String[] row : dataToParse) {
            parsedData.add(new Card(row[0], row[1], row[2]));
        }
        return parsedData;
    }

    public List<Card> getAllCards() {
        return parseDataToCard(path);
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

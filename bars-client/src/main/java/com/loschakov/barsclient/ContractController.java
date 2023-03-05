package com.loschakov.barsclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContractController {

    private final ObservableList<Contract> contractData = FXCollections.observableArrayList();

    @FXML
    private TableView<Contract> tableContract;
    @FXML
    private TableColumn<Contract, String> isRelevanceColumn;
    @FXML
    private TableColumn<Contract, String> nameColumn;
    @FXML
    private TableColumn<Contract, String> updateDateColumn;
    @FXML
    private TableColumn<Contract, String> createDateColumn;

    @FXML
    private void initialize() {
        initData();
        isRelevanceColumn.setCellValueFactory(new PropertyValueFactory<Contract, String>("isRelevance"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Contract, String>("name"));
        updateDateColumn.setCellValueFactory(new PropertyValueFactory<Contract, String>("updateDate"));
        createDateColumn.setCellValueFactory(new PropertyValueFactory<Contract, String>("createDate"));

        tableContract.setItems(contractData);
    }

    private long checkRelevance(LocalDate nowDate, String updDate) {
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate fUpdDate = LocalDate.parse(updDate, formatter);

        return ChronoUnit.DAYS.between(fUpdDate,nowDate);
    }

    private void initData() {
        try {
            URL url = new URL("http://localhost:8080/api/v1/contract");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            StringBuilder contractResponse = new StringBuilder();
            ObjectMapper mapper = new ObjectMapper();

            while (scanner.hasNext()) {
                contractResponse.append(scanner.nextLine());
            }

            scanner.close();
            List<Contract> contractList = Arrays.asList(mapper.readValue(String.valueOf(contractResponse), Contract[].class));
/*
            List<Contract> contractList = mapper.readValue(String.valueOf(contractResponse), new TypeReference<List<Contract>>() {});
            contractData.addAll(contractList);
 */
            contractList.stream().forEach(e -> e.setIsRelevance(String.valueOf(checkRelevance(LocalDate.now(), e.getUpdateDate()) > 60 ? "Не актуален" : "Актуален")));
            contractData.addAll(contractList);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
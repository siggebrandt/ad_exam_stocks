package com.ad.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.ad.communication.NetworkRetriever;
import com.ad.interfaces.IControllerWithLifeCycle;
import com.ad.interfaces.IObserver;
import com.ad.models.Stock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sw.IStock;

public class TableController implements Initializable, IControllerWithLifeCycle, IObserver {

    @FXML
    private TableView<IStock> tbData;
    @FXML
    public TableColumn<IStock, String> name_cell;
    @FXML
    public TableColumn<IStock, Integer> price_cell;

    private final ObservableList<IStock> stockData = FXCollections
            .observableArrayList(new Stock("No data retrieved yet!", 0.0));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTableColumnLink();
        retreiver.subscribe(this);
    }
    private NetworkRetriever retreiver = NetworkRetriever.getNetworkRetriever();

    private void setupTableColumnLink() {
        name_cell.setCellValueFactory(new PropertyValueFactory<>("Name"));
        price_cell.setCellValueFactory(new PropertyValueFactory<>("Price"));


        tbData.setItems(stockData);
    }

    // can be called to clear all data in table data source
    private void clearTable() {
        stockData.remove(0, stockData.size()); // quick and dirty
    }

    // can be called to populate table with data from a Stock array
    private void populateTableFromArray(List<IStock> stocks) {
        stockData.addAll(stocks);
    }

    @Override
    public void willUnmount() {
        retreiver.unsubscribe(this);
        System.out.println("Table Controller Will unmount!!");
    }

    @Override
    public void update(List<IStock> stocks) {
        clearTable();
        populateTableFromArray(stocks);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooringmaster.DAOs;

import com.mycompany.flooringmaster.DTOs.Order;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public final class OrderDao {

    final static String DELIMETER = ",";
    private boolean testMode;
    EncodeDecode ed = new EncodeDecode();
    private List<Order> orderList = new ArrayList<>();

    public OrderDao(boolean testMode) {
        setup(testMode);
    }

    public void setup(boolean testMode) {
        orderList = ed.decode();
        this.testMode = testMode;
    }

    public List<Order> getFileOrderList(String fileName) {
        List<Order> fileOrderList = new ArrayList<>();
        for (Order current : orderList) {
            if (current.getFileName().equals(fileName)) {
                fileOrderList.add(current);
            }
        }
        return fileOrderList;
    }

    public void createOrder(Order currentOrder) {
        currentOrder.setOrderNum(getNextId(currentOrder.getFileName()));
        orderList.add(currentOrder);
        ed.encode();
    }

    public Integer getNextId(String fileName) {
        int fileNextId = 1;

        for (Order current : getFileOrderList(fileName)) {
            if (current.getOrderNum() >= fileNextId) {
                fileNextId = current.getOrderNum() + 1;
            }
        }
        return fileNextId;
    }

    public void deleteOrder(Order currentOrder) {
        for (Order current : orderList) {
            if (current.getFileName().equals(currentOrder.getFileName()) && Objects.equals(current.getOrderNum(), currentOrder.getOrderNum())) {
                orderList.remove(current);
                break;
            }
        }
        ed.encode();
        deleteFile();
    }

    public void updateOrder(Order currentOrder, double laborCostSqFt, double costSqFt, double tax) {
        currentOrder.setLaborCostSqFt(laborCostSqFt);
        currentOrder.setCostSqFt(costSqFt);
        currentOrder.setTaxRate(tax);

        currentOrder.setMaterialCost(currentOrder.getCostSqFt() * currentOrder.getArea());
        currentOrder.setLaborCost(currentOrder.getLaborCostSqFt() * currentOrder.getArea());
        currentOrder.setTaxCost((currentOrder.getTaxRate() / 100.0) * (currentOrder.getLaborCost() + currentOrder.getMaterialCost()));
        currentOrder.setTotalCost(currentOrder.getLaborCost() + currentOrder.getMaterialCost() + currentOrder.getTaxCost());
        ed.encode();
        deleteFile();

    }

    public Order getOrder(Integer orderNum, String fileName) {
        for (Order current : orderList) {
            if (current.getFileName().equals(fileName) && current.getOrderNum() == orderNum) {
                return current;
            }
        }
        return null;
    }

    public String getFileName(Order currentOrder) {
        return currentOrder.getFileName();
    }

    public String getDate(String fileName) {
        return fileName.substring(7, 9) + "/" + fileName.substring(9, 11) + "/" + fileName.substring(11, 15);
    }

    public String[] getStringOrders(String fileName) {
        String[] orders = new String[getFileOrderList(fileName).size()];
        int i = 0;
        for (Order x : getFileOrderList(fileName)) {
            orders[i] = "" + x.getOrderNum();
            i++;
        }
        return orders;
    }

    private void deleteFile() {
        File dir = new File(System.getProperty("user.dir"));
        File[] foundFiles = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("Orders_");
            }
        });
        for (File file : foundFiles) {
            String fileName = file.toString().substring(file.toString().length() - 19);

            if (!getActiveFiles().contains(fileName)) {
                try {
                    Files.delete(file.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private List<String> getActiveFiles() {
        List<String> fileList = new ArrayList<>();
        for (Order x : orderList) {
            if (!fileList.contains(x.getFileName())) {
                fileList.add(x.getFileName());
            }
        }
        return fileList;
    }
    
    private class EncodeDecode implements FileIO {
        @Override
        public List< Order> decode() {
            List<Order> orderList = new ArrayList<>();

            File dir = new File(System.getProperty("user.dir"));
            File[] foundFiles = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.startsWith("Orders_");
                }
            });

            for (File file : foundFiles) {
                try {
                    CSVReader reader = new CSVReader(new FileReader(file), ',', '"', '\0');
                    String[] values;
                    reader.readNext();

                    while ((values = reader.readNext()) != null) {
                        orderList.add(new Order(Integer.parseInt(values[0]), values[1], values[2], Double.parseDouble(values[3]), values[4], Double.parseDouble(values[5]), Double.parseDouble(values[6]), Double.parseDouble(values[7]), Double.parseDouble(values[8]), Double.parseDouble(values[9]), Double.parseDouble(values[10]), Double.parseDouble(values[11]), file.toString().substring(file.toString().length() - 19)));
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return orderList;
        }

        @Override
        public void encode() {
            if (!testMode) {
                try {
                    for (Order current : orderList) {
                        CSVWriter writer = new CSVWriter(new FileWriter(current.getFileName()), ',');
                        writer.writeNext(new String[]{"OrderNumber", "CustomerName", "State", "TaxRate", "ProductType", "Area", "CostPerSquareFoot", "LaborCostPerSquareFoot", "MaterialCost", "LaborCost", "Tax", "Total"});

                        for (Order currentOrder : getFileOrderList(current.getFileName())) {
                            String[] line = new String[]{
                                currentOrder.getOrderNum().toString(),
                                currentOrder.getName(),
                                currentOrder.getState(),
                                "" + currentOrder.getTaxRate(),
                                currentOrder.getProductType(),
                                "" + currentOrder.getArea(),
                                "" + currentOrder.getCostSqFt(),
                                "" + currentOrder.getLaborCostSqFt(),
                                "" + currentOrder.getMaterialCost(),
                                "" + currentOrder.getLaborCost(),
                                "" + currentOrder.getTaxCost(),
                                "" + currentOrder.getTotalCost()};
                            writer.writeNext(line);
                            writer.flush();
                        }
                        writer.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(OrderDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.flooringmvc.controllers;


import com.thesoftwarequild.flooringmvc.dao.OrderDao;
import com.thesoftwarequild.flooringmvc.models.Order;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.inject.Inject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {

    private OrderDao orderDao;

    @Inject
    public HomeController(OrderDao dao) {
        
        this.orderDao = dao;
    }

    public String generateNoSlashDate(String date) {
        String noSlash = date.replaceAll("/", "");

        return noSlash;
    }

//    private List<Order> decode(String fileName) {
//        List<Order> orders = new ArrayList<>();
//
//        CSVReader reader;
//
//        try {
//            reader = new CSVReader(new FileReader(fileName), ',');
//
//            List<Order> orderItems = new ArrayList<>();
//
//            List<String[]> lines = reader.readAll();
//            Iterator<String[]> iterator = lines.iterator();
//
//            iterator.next();
//
//            while (iterator.hasNext()) {
//                String[] values = iterator.next();
//
//                Order order = new Order();
//
//                order.setOrder_id(Integer.parseInt(values[0]));
//                order.setCustomer_name(values[1]);
//                order.setState(values[2]);
//                order.setTaxRate(Double.parseDouble(values[3]));
//                order.setMaterial(values[4]);
//                order.setArea(Double.parseDouble(values[5]));
//                order.setCostPerSquareFoot(Double.parseDouble(values[6]));
//                order.setLaborCostPerSquareFoot(Double.parseDouble(values[7]));
//                order.setTotal_material_cost(Double.parseDouble(values[8]));
//                order.setTotal_labor_cost(Double.parseDouble(values[9]));
//                order.setTotal_tax_cost(Double.parseDouble(values[10]));
//                order.setGrand_total(Double.parseDouble(values[11]));
//                order.setDate(values[12]);
//
//                orders.add(order);
//            }
//
//        } catch (FileNotFoundException ex) {
//
//        } catch (IOException e) {
//
//        }
//
//        return orders;
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model, @RequestParam(value = "date", required = false) String date) {

        if (date == null) {

            LocalDate dates = LocalDate.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

            date = dates.format(formatter);

        }

        date = generateNoSlashDate(date);

        List<Order> order = orderDao.list();

        model.addAttribute("createDate", date);

        model.addAttribute("orderList", order);

        return "index";
    }


}

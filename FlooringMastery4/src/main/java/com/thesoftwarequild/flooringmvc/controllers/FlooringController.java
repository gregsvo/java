/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwarequild.flooringmvc.controllers;

import com.thesoftwarequild.flooringmvc.commands.AddOrderCommand;
import com.thesoftwarequild.flooringmvc.dao.MaterialDao;
import com.thesoftwarequild.flooringmvc.dao.OrderDao;
import com.thesoftwarequild.flooringmvc.dao.StateDao;
import com.thesoftwarequild.flooringmvc.models.Material;
import com.thesoftwarequild.flooringmvc.models.Order;
import com.thesoftwarequild.flooringmvc.models.State;
import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/flooring")
public class FlooringController {

    private OrderDao orderDao;
    private StateDao stateDao;
    private MaterialDao materialDao;

    @Inject
    public FlooringController(OrderDao dao, StateDao stateDao, MaterialDao materialDao) {
        this.orderDao = dao;
        this.stateDao = stateDao;
        this.materialDao = materialDao;
    }

    public String generateFileName(String date) {
        String noSlash = date.replaceAll("/", "");

        return "Order_" + noSlash + ".csv";
    }

    public String generateNoSlashDate(String date) {
        String noSlash = date.replaceAll("/", "");

        return noSlash;
    }

    //CREATE ORDER
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Order createOrder(@RequestBody AddOrderCommand order) {

//        String date1 = generateNoSlashDate(order.getDate());
//        
//        String fileName = generateFileName(order.getDate());
        List<State> stateList = stateDao.searchAll(order.getState());
        List<Material> materialList = materialDao.searchAll(order.getProductType());

        Order orderToAdd = new Order();

        orderToAdd.setArea(order.getArea());
        orderToAdd.setCustomer_name(order.getCustomerName());
        orderToAdd.setDate(order.getDate());

        for (State state1 : stateList) {
            if (state1.getState_name().equalsIgnoreCase(order.getState())) {
                orderToAdd.setState(state1);
            }
        }

        for (Material material1 : materialList) {
            if (material1.getMaterial_name().equalsIgnoreCase(order.getProductType())) {
                orderToAdd.setMaterial(material1);
            }

        }

        orderToAdd.setTotal_material_cost(order.getArea() * orderToAdd.getMaterial().getMaterial_cpsf());
        orderToAdd.setTotal_labor_cost(order.getArea() * orderToAdd.getMaterial().getLabor_cpsf());
        Double subTotal = (orderToAdd.getArea() * (orderToAdd.getMaterial().getMaterial_cpsf() + orderToAdd.getMaterial().getLabor_cpsf()));
        orderToAdd.setTotal_tax_cost(subTotal * orderToAdd.getState().getTax_rate() / 100);
        orderToAdd.setGrand_total(orderToAdd.getTotal_tax_cost() + subTotal);

        Order orderReturned = orderDao.add(orderToAdd);

        return orderReturned;
    }

    @RequestMapping(value = "/{id}/{date}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer orderId, @PathVariable("date") String date) {

        orderDao.remove(orderId);

    }

    @RequestMapping(value = "/{id}/{date}", method = RequestMethod.GET)
    @ResponseBody
    public Order show(@PathVariable("id") Integer orderId, @PathVariable("date") String date) {

        Order order = orderDao.get(orderId);

        return order;

    }

    @RequestMapping(value = "/showEdit/{id}/{date}")
    public String showEdit(@PathVariable("id") Integer orderId, @PathVariable("date") String date, Model model) {

        Order order = orderDao.get(orderId);

        model.addAttribute("order", order);

        return "showEdit";

    }

    @RequestMapping(value = "/{id}/{date}", method = RequestMethod.PUT)
    @ResponseBody
    public Order edit(@RequestBody AddOrderCommand order, @PathVariable("id") Integer orderId, @PathVariable("date") String date) {
//            @ModelAttribute Order order, BindingResult bindingResult, @PathVariable("id") Integer orderId, @PathVariable("date") String date, Model model){

//        String fileName = generateFileName(date);
//
//        List<Order> order1 = orderDao.retrieveOrderDate(date, fileName);
//
//        Order order2 = orderDao.editOrder(orderDao.getOrder(date, orderId), order.getCustomerName(), order.getState(), order.getProductType(), order.getArea(), date, fileName);
//
//        return order2;
        
        List<State> stateList = stateDao.searchAll(order.getState());
        List<Material> materialList = materialDao.searchAll(order.getProductType());

        Order orderToAdd = orderDao.get(orderId);

        orderToAdd.setArea(order.getArea());
        orderToAdd.setCustomer_name(order.getCustomerName());
        orderToAdd.setDate(order.getDate());

        for (State state1 : stateList) {
            if (state1.getState_name().equalsIgnoreCase(order.getState())) {
                orderToAdd.setState(state1);
            }
        }

        for (Material material1 : materialList) {
            if (material1.getMaterial_name().equalsIgnoreCase(order.getProductType())) {
                orderToAdd.setMaterial(material1);
            }

        }

        orderToAdd.setTotal_material_cost(order.getArea() * orderToAdd.getMaterial().getMaterial_cpsf());
        orderToAdd.setTotal_labor_cost(order.getArea() * orderToAdd.getMaterial().getLabor_cpsf());
        Double subTotal = (orderToAdd.getArea() * (orderToAdd.getMaterial().getMaterial_cpsf() + orderToAdd.getMaterial().getLabor_cpsf()));
        orderToAdd.setTotal_tax_cost(subTotal * orderToAdd.getState().getTax_rate() / 100);
        orderToAdd.setGrand_total(orderToAdd.getTotal_tax_cost() + subTotal);

        orderDao.update(orderToAdd);

        return orderToAdd;

    }

}

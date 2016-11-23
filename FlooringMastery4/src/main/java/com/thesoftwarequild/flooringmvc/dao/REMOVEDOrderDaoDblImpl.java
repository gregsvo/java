package com.thesoftwarequild.flooringmvc.dao;

/**
 *
 * @author apprentice
 */
public class REMOVEDOrderDaoDblImpl {
    
//import com.thesoftwarequild.flooringmvc.models.Order;
//import com.thesoftwarequild.flooringmvc.models.Material;
//import com.thesoftwarequild.flooringmvc.models.State;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;


//public class OrderDaoDbImpl implements OrderDao {

//    private String FILENAME; // put in actual file name
//    private String DELIMETER = ",";
//    private String date;
//    private Order order;
//    private State taxes;
//    private StateDaoDbImpl taxesDao;
//    private Material products;
//    private Integer nextId = 1;
////    private List<Order> orderList = new ArrayList<>();
//    private Map<String, List<Order>> orderMap;

    // fill in constructor to automatically build order file based on the date.
//    public OrderDaoDbImpl(StateDaoDbImpl taxesDao, MaterialDaoDbImpl productsDao) {

//        this.taxesDao = taxesDao;
//        this.productsDao = productsDao;
//        orderMap = new HashMap<>();
//
//    }
//
//    @Override
//    public List<Order> list() {
//
//        return new ArrayList(orderMap.values());
//    }
//
//    @Override
//    public Order createOrder(String fileName, String date, String customerName, String state, String productType, double area) {
//
//        nextId = 1;
//
//        List<Order> orders = decode(fileName);
//
//        orderMap.put(date, orders);
//
//        for (Order thisOrder : orders) {
//            if (thisOrder.getOrder_id() >= nextId) {
//                nextId = (thisOrder.getOrder_id() + 1);
//            }
//        }
//
//        Order newOrder = new Order();
//        List<Order> orderList = new ArrayList<>();
//
//        newOrder.setOrder_id(nextId);
//        nextId++;
//        newOrder.setCustomer_name(customerName);
//        newOrder.setState(state);
//        newOrder.setTaxRate(taxesDao.retrieveTaxRate(state));
//        newOrder.setMaterial(productType);
//        newOrder.setArea(area);
//        newOrder.setCostPerSquareFoot(roundTwoDecimals(productsDao.retrieveCostPerSquareFoot(productType)));
//        newOrder.setLaborCostPerSquareFoot(roundTwoDecimals(productsDao.retrieveLaborCostPerSquareFoot(productType)));
//        newOrder.setTotal_material_cost(roundTwoDecimals(productsDao.calculateMaterialCost(productType, area)));
//        newOrder.setTotal_labor_cost(roundTwoDecimals(productsDao.calculateLaborCost(productType, area)));
//        newOrder.setTotal_tax_cost(roundTwoDecimals(taxesDao.calculateTax(productsDao.calculateSubtotal(productType, area), state)));
//        newOrder.setGrand_total(roundTwoDecimals(newOrder.getTotal_material_cost() + newOrder.getTotal_labor_cost() + newOrder.getTotal_tax_cost()));
//        newOrder.setDate(date);
//
//        //if the key (date) already exists, then add the order to the array list associated
//        //otherwise, create a new entry on the map with an array list, then add the order to the array list associated
//        if (orderMap.containsKey(date)) {
//            orderMap.get(date).add(newOrder);
//        } else {
//            orderMap.put(date, orderList);
//            orderMap.get(date).add(newOrder);
//        }
//
//        encode(date, fileName);
//        return newOrder;
//    }
//
//    @Override
//    public void removeOrder(String date, Integer orderNumber, String fileName) {
//
//        //look at the map, to find the key, to look inside the value, to find the order number.
//        for (int i = 0; i < orderMap.get(date).size(); i++) {
//            if (orderMap.get(date).get(i).getOrder_id().equals(orderNumber)) {
//                orderMap.get(date).remove(i);
//            }
//        }
//
//        encode(date, fileName);
//
//    }
//
//    @Override
//    public double roundTwoDecimals(double value) {
//        BigDecimal bigDecimal = new BigDecimal(value);
//
//        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
//
//        return bigDecimal.doubleValue();
//
//    }
//
//    @Override
//    public Order editOrder(Order order, String customerName, String state, String productType, Double area, String date, String fileName) {
//        order.setCustomer_name(customerName);
//        order.setState(state);
//        order.setMaterial(productType);
//        order.setArea(area);
//
//        order.setTaxRate(taxesDao.retrieveTaxRate(state));
//        order.setCostPerSquareFoot(roundTwoDecimals(productsDao.retrieveCostPerSquareFoot(productType)));
//        order.setLaborCostPerSquareFoot(roundTwoDecimals(productsDao.retrieveLaborCostPerSquareFoot(productType)));
//        order.setTotal_material_cost(roundTwoDecimals(productsDao.calculateMaterialCost(productType, area)));
//        order.setTotal_labor_cost(roundTwoDecimals(productsDao.calculateLaborCost(productType, area)));
//        order.setTotal_tax_cost(roundTwoDecimals(taxesDao.calculateTax(productsDao.calculateSubtotal(productType, area), state)));
//        order.setGrand_total(roundTwoDecimals(order.getTotal_material_cost() + order.getTotal_labor_cost() + order.getTotal_tax_cost()));
//
//        removeOrder(date, order.getOrder_id(), fileName);
//
//        orderMap.get(date).add(order);
//
//        encode(date, fileName);
//        
//        return order;
//    }
//
//    @Override
//    public void changeDate(Order order, String date, String fileName) {
//        List<Order> orderList = new ArrayList<>();
//
//        orderList = (orderMap.get(date));
//        Integer nextID = 1;
//
//        if (orderList == null) {
//            orderList = new ArrayList<>();
//            nextID = 1;
//        } else if (orderList.isEmpty()) {
//            nextID = 1;
//        } else {
//
//            for (Order nextOrder : orderList) {
//                if (nextOrder.getOrder_id() >= nextID) {
//                    nextID = (nextOrder.getOrder_id() + 1);
//                }
//            }
//        }
//
//        order.setOrder_id(nextID);
//
//        if (orderMap.containsKey(date)) {
//            orderMap.get(date).add(order);
//        } else {
//            orderMap.put(date, orderList);
//            orderMap.get(date).add(order);
//        }
//
//        encode(date, fileName);
//    }
//
//    @Override
//    public Order getOrder(String date, Integer orderNumber) {
//
//        List<Order> orders = decode("Order_" + date + ".csv");
//
//        for (int t = 0; t < orders.size(); t++) {
//            orderMap.put(date, orders);
//        }
//
//        List<Order> result = orderMap.get(date);
//        for (int i = 0; i < orderMap.get(date).size(); i++) {
//            if (orderMap.get(date).get(i).getOrder_id().equals(orderNumber)) {
//                return orderMap.get(date).get(i);
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public List<Order> retrieveOrderDate(String date, String fileName) {
//
//        List<Order> orderByDate = decode(fileName);
//
//        orderMap.put(date, orderByDate);
//
//        if (orderMap.containsKey(date)) {
//            return orderMap.get(date);
//        }
//        return null;
//    }
//
//    @Override
//    public boolean retrieveOrderID(Integer orderNumber, String date, String fileName) {
//
//        Integer id = null;
//        boolean test = false;
//
//        List<Order> orderByID = decode(fileName);
//
//        orderMap.put(date, orderByID);
//
//        if (orderMap.containsKey(date)) {
//            for (Order order : orderByID) {
//                if (order.getOrder_id() == orderNumber) {
//                    test = true;
//                }
//            }
//        }
//        return test;
//
//    }
//
//    private void encode(String date, String fileName) {
//
//        if (!test) {
//            try {
//
//                CSVWriter writer = new CSVWriter(new FileWriter(fileName));
//
//                List<Order> orderList = orderMap.get(date);
//
//                List<Order> storage = orderMap.get(date);
//
//                String[] line = ("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total").split(DELIMETER);
//                writer.writeNext(line, false);
//                for (Order thisOrder : storage) {
//
//                    String[] newLine = {
//                        thisOrder.getOrder_id().toString(),
//                        thisOrder.getCustomer_name(),
//                        thisOrder.getState(),
//                        String.valueOf(thisOrder.getTaxRate()),
//                        thisOrder.getMaterial_cost(),
//                        String.valueOf(thisOrder.getArea()),
//                        String.valueOf(thisOrder.getCostPerSquareFoot()),
//                        String.valueOf(thisOrder.getLaborCostPerSquareFoot()),
//                        String.valueOf(thisOrder.getTotal_labor_cost()),
//                        String.valueOf(thisOrder.getTotal_material_cost()),
//                        String.valueOf(thisOrder.getTotal_tax_cost()),
//                        String.valueOf(thisOrder.getGrand_total()),
//                        String.valueOf(thisOrder.getDate())
//
//                    };
//
//                    writer.writeNext(newLine, false);
//
//                }
//                writer.close();
//
//            } catch (IOException e) {
//
//            }
//        }
//
//    }
//
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
//
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
//
//    @Override
//    public List<Order> searchAll(String search) {
//
//        List<Order> test = new ArrayList<>();
//
//        File[] classRoomFiles = new File(System.getProperty("user.dir")).listFiles();
//        List<File> tests = Arrays.asList(classRoomFiles);
//        List<File> result = tests
//                .stream()
//                .filter(sample -> sample.getName().contains("Order"))
//                .collect(Collectors.toList());
//
//        for (File file : result) {
//            List<Order> orders = decode(file.getName());
//                String date1 = orders.get(0).getDate();
//                orderMap.put(date1, orders);
//
//        }
//        
//        for (List<Order> orders : orderMap.values()) {
//            test.addAll(orders
//                    .stream()
//                    .filter(contact -> contact.getCustomer_name().toLowerCase().contains(search.toLowerCase()) ||
//                            contact.getState().toLowerCase().contains(search.toLowerCase()) ||
//                            contact.getMaterial_cost().toLowerCase().contains(search.toLowerCase()))
//                    .collect(Collectors.toList()));
//
//        }
//
//        return test;
//
//    }

}
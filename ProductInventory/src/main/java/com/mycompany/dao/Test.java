/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dao;

import com.mycompany.dto.Phone;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author apprentice
 */
public class Test {

    public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InterruptedException {
        List<Phone> phoneInventory = new ArrayList<>();
        Phone phoneInMem = new Phone();
        phoneInMem.setProductName("Foo");
        phoneInventory.add(phoneInMem);

        String[] inputs = new String[2];
        inputs[0] = "Foo";
        inputs[1] = "setPrice";

        Phone phoneToUpdate = phoneInventory.stream().filter(phone -> phone.getProductName().equals(inputs[0])).findFirst().get();

        Method[] availMethods = phoneToUpdate.getClass().getMethods();
        Method methodToUpd = Arrays.stream(availMethods).filter(method -> method.getName().equals(inputs[1])).findFirst().get();
        methodToUpd.invoke(phoneInMem, 12);
        Thread.sleep(1222);
    }
}

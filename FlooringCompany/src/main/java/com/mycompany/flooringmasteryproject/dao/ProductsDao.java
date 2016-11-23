package com.mycompany.flooringmasteryproject.dao;

import com.mycompany.flooringmasteryproject.dto.ProductsDto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductsDao {

    private List<ProductsDto> productsList = new ArrayList<>();
    final String DELIMETER = ",";
    final String FILENAME = "products.txt";
    private Integer nextId = 1;

    public ProductsDao() {

        decode();

    }
    
 // DECODE     
    public List<ProductsDto> decode() {

        ////ArrayList<ProductsDto> productsList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));
            String currentLineReading = sc.nextLine();
            while (sc.hasNextLine()) {

                currentLineReading = sc.nextLine();
                String[] textFileContents = currentLineReading.split(DELIMETER);

                ProductsDto products = new ProductsDto();
                products.setProductType(textFileContents[0]);
                products.setMaterialCostPerSqFoot(Double.parseDouble(textFileContents[1]));
                products.setLaborCostPerSqFoot(Double.parseDouble(textFileContents[2]));

                productsList.add(products);

            }

        } catch (FileNotFoundException ex) {

        }
            return productsList;
    }
    
    public List<ProductsDto> getProductsList() {
        

        return this.productsList;
    }

}//END Class TaxesDao

package com.mycompany.dao;

import com.mycompany.dto.Tablet;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public final class TabletDAO implements iDAO {
     boolean testMode = false;

    public TabletDAO() {
        decode();
    }

    public TabletDAO(boolean isTestMode) {
        if (isTestMode) {
            this.testMode = true;
        }
    }

    private final Set<Tablet> tabletInventory = new HashSet<>();
    private final String FILENAME = "tablet.txt";

    @Override
    public void create(String... args) {
        try {
            if (args.length == 8) {
                Tablet tabletToBe = new Tablet(args);
                encode();
                tabletInventory.add(tabletToBe);
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (Exception ex) {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    @Override
    public boolean update(String... args) {
        Tablet tabletToUpdate = tabletInventory
                .stream()
                .filter(tablet -> tablet.getProductName().equals(args[0]))
                .findFirst()
                .get();

        Method[] availMethods = tabletToUpdate.getClass().getMethods();
        Method methodToUpd = Arrays.stream(availMethods).filter(method -> method.getName().equals(args[1])).findFirst().get();

        try {
            methodToUpd.invoke(tabletToUpdate, args[2]);
            encode();
            return true;
        } catch (IllegalArgumentException ex) {

            if (args[1].contains("Price") || args[1].contains("ScreenSize")) {

                try {
                    methodToUpd.invoke(tabletToUpdate, Double.parseDouble(args[2]));
                    encode();
                    return true;
                } catch (Exception exc) {
                    return false;
                }

            } else if (args[1].contains("Stock")) {

                try {
                    methodToUpd.invoke(tabletToUpdate, Integer.parseInt(args[2]));
                    encode();
                    return true;
                } catch (Exception exce) {
                    return false;
                }

            } else {

                return false;
            }
        } catch (Exception ex) {
            return false;
        }

    }

    @Override
    public Tablet get(String... args) {
//        Tablet tabletToBe = new Tablet();
        return tabletInventory.stream()
                .filter(tablet -> tablet.getProductName().equals(args[0]))
                .findFirst()
                .get();
    }

    @Override
    public void delete(String... args) {
        Tablet tabletToBe = tabletInventory.stream()
                .filter(tablet -> tablet.getProductName().equals(args[0]))
                .findFirst()
                .get();
        tabletInventory.remove(tabletToBe);
        encode();
    }

    @Override
    public void encode() {
        if (!testMode) {
            try {
                CSVWriter writer = new CSVWriter(new FileWriter(FILENAME));

                tabletInventory.stream().forEach(currentTablet -> {
                    String[] line = new String[8];
                    line[0] = currentTablet.getProductName();
                    line[1] = String.valueOf(currentTablet.getPrice());
                    line[2] = String.valueOf(currentTablet.getMaxStock());
                    line[3] = String.valueOf(currentTablet.getEndangeredStockNumber());
                    line[4] = String.valueOf(currentTablet.getCurrentStock());

                    line[5] = String.valueOf(currentTablet.getScreenSize());
                    line[6] = String.valueOf(currentTablet.isHasStylus());
                    line[7] = String.valueOf(currentTablet.getScreenSize());
                    writer.writeNext(line);

                    try {
                        writer.flush();
                    } catch (IOException ex) {
                    }

                });

            } catch (IOException ex) {

            }
        }
    }

    @Override
    public void decode() {
        if (!testMode) {
            try {
                CSVReader reader = new CSVReader(new FileReader(FILENAME), ',', '\"');

                String[] nextLine;

                while ((nextLine = reader.readNext()) != null) {
                    Tablet currentTablet = new Tablet(nextLine[0], nextLine[1], nextLine[2],
                            nextLine[3], nextLine[4], nextLine[5], nextLine[6], nextLine[7]);

                    tabletInventory.add(currentTablet);

                }
//            }

            } catch (Exception x) {

            }
        }
    }

    @Override
    public String getName() {
        return "Tablet";
    }

    @Override
    public String[] getExistingProducts() {

        List<String> existingList
                = tabletInventory.stream()
                .map(p -> p.getProductName())
                .collect(Collectors.toList());
        String[] garbage = new String[existingList.size()];
        return existingList.stream().collect(Collectors.toList()).toArray(garbage);

    }

    @Override
    public String[] getMethods() {
        return Arrays.stream(Tablet.class.getMethods())
                .map(meth -> meth.getName())
                .filter(name -> name.contains("set"))
                .collect(Collectors.toList())
                .toArray(new String[8]);
    }
}


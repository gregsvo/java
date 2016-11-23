package com.mycompany.dao;

import com.mycompany.dto.Computer;
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
public final class ComputerDAO implements iDAO {

    boolean testMode = false;

    public ComputerDAO() {
        decode();
    }

    public ComputerDAO(boolean isTestMode) {
        if (isTestMode) {
            this.testMode = true;
        }
    }

    private final Set<Computer> computerInventory = new HashSet<>();
    private final String FILENAME = "computer.txt";

    @Override
    public void create(String... args) {
        try {
            if (args.length == 8) {
                Computer computerToBe = new Computer(args);
                encode();
                computerInventory.add(computerToBe);
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (Exception ex) {
            throw new ArrayIndexOutOfBoundsException();
        }

    }

    @Override
    public boolean update(String... args) {
        Computer computerToUpdate = computerInventory
                .stream()
                .filter(computer -> computer.getProductName().equals(args[0]))
                .findFirst()
                .get();

        Method[] availMethods = computerToUpdate.getClass().getMethods();
        Method methodToUpd = Arrays.stream(availMethods).filter(method -> method.getName().equals(args[1])).findFirst().get();

        try {
            methodToUpd.invoke(computerToUpdate, args[2]);
            encode();
            return true;
        } catch (IllegalArgumentException ex) {

            if (args[1].contains("Price") || args[1].contains("ScreenSize")) {

                try {
                    methodToUpd.invoke(computerToUpdate, Double.parseDouble(args[2]));
                    encode();
                    return true;
                } catch (Exception exc) {
                    return false;
                }

            } else if (args[1].contains("Stock")) {

                try {
                    methodToUpd.invoke(computerToUpdate, Integer.parseInt(args[2]));
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
    public Computer get(String... args) {
//        Computer computerToBe = new Computer();
        return computerInventory.stream()
                .filter(computer -> computer.getProductName().equals(args[0]))
                .findFirst()
                .get();
    }

    @Override
    public void delete(String... args) {
        Computer computerToBe = computerInventory.stream()
                .filter(computer -> computer.getProductName().equals(args[0]))
                .findFirst()
                .get();
        computerInventory.remove(computerToBe);
        encode();
    }

    @Override
    public void encode() {
        if (!testMode) {
            try {
                CSVWriter writer = new CSVWriter(new FileWriter(FILENAME));

                computerInventory.stream().forEach(currentComputer -> {
                    String[] line = new String[8];
                    line[0] = currentComputer.getProductName();
                    line[1] = String.valueOf(currentComputer.getPrice());
                    line[2] = String.valueOf(currentComputer.getMaxStock());
                    line[3] = String.valueOf(currentComputer.getEndangeredStockNumber());
                    line[4] = String.valueOf(currentComputer.getCurrentStock());

                    line[5] = String.valueOf(currentComputer.getNumProcessors());
                    line[6] = String.valueOf(currentComputer.isIsGaming());
                    line[7] = String.valueOf(currentComputer.isIsLiquidCooled());
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
                    Computer currentComputer = new Computer(nextLine[0], nextLine[1], nextLine[2],
                            nextLine[3], nextLine[4], nextLine[5], nextLine[6], nextLine[7]);

                    computerInventory.add(currentComputer);

                }
//            }

            } catch (Exception x) {

            }
        }
    }

    @Override
    public String getName() {
        return "Computer";
    }

    @Override
    public String[] getExistingProducts() {

        List<String> existingList
                = computerInventory.stream()
                .map(p -> p.getProductName())
                .collect(Collectors.toList());
        String[] garbage = new String[existingList.size()];
        return existingList.stream().collect(Collectors.toList()).toArray(garbage);

    }

    @Override
    public String[] getMethods() {
        return Arrays.stream(Computer.class.getMethods())
                .map(meth -> meth.getName())
                .filter(name -> name.contains("set"))
                .collect(Collectors.toList())
                .toArray(new String[8]);
    }
}

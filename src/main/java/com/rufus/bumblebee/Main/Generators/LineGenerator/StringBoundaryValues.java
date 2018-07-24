package com.rufus.bumblebee.Main.Generators.LineGenerator;

import com.rufus.bumblebee.Main.Columns.Column;
import com.rufus.bumblebee.Main.Datatype.BaseDatatype;
import com.rufus.bumblebee.Main.Exeptions.GeneratorExceptionInputOptions;
import com.rufus.bumblebee.Main.Exeptions.TransferException;

import com.rufus.bumblebee.Main.Datatype.TypeTestData;
import com.rufus.bumblebee.Main.Generators.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
/**
 * Class : Генерирует строки от 1 до Len+INCREASE_QUANTITY
 * @version : 0.0.1
 * @author : Baldin Timur
 */
public class StringBoundaryValues implements Rule {
    private final int MIN_ID_CAPITALS = 65;
    private final int MAX_ID_CAPITALS = 90;
    private final int MIN_ID_LOWERCASE = 97;
    private final int MAX_ID_LOWERCASE = 122;
    private final String TYPE = "STRING";
    private Integer len;
    private Boolean Low;
    private Boolean Cap;
    private Boolean NullValue;
    private Integer INCREASE_QUANTITY;
    private Column column;
    private List<TypeTestData> values = new ArrayList<TypeTestData>();

    public StringBoundaryValues(Integer Len, Integer INCREASE_QUANTITY, Boolean Low, Boolean Cap, Boolean NullValue, Column column) {
        this.len = Len;
        this.Low = Low;
        this.Cap = Cap;
        this.INCREASE_QUANTITY = INCREASE_QUANTITY;
        this.NullValue = NullValue;
        this.column = column;
    }

    @Override
    public void construct() throws GeneratorExceptionInputOptions,TransferException {
        if (checkIn()) {
            String paremeters=len.toString()+Low.toString()+Cap.toString()+INCREASE_QUANTITY.toString()+NullValue.toString();
            throw new GeneratorExceptionInputOptions("Your choice is not right.",paremeters);
        }else {
            if (NullValue) {
                values.add(new BaseDatatype(new StringNull().returnValue(), TYPE));
            }
            if (Low && Cap) {
                stringBuildLowCap();
                transfer();
                return;
            } else {
                if (Low && (!Cap)) {
                    stringBuildLow();
                    transfer();
                } else {
                    stringBuildCap();
                    transfer();
                }
            }
        }
    }

    @Override
    public void transfer() throws TransferException {
        if (checkOut()) {
            throw new TransferException("Please create test data");
        } else {
            column.setValues(this.values);
            this.values.clear();
        }
    }

    private void stringBuildLowCap() {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for (Integer j = 1; j <= len + INCREASE_QUANTITY; j++) {
            for (Integer word = 1; word <= j; word++) {
                if (word % 2 == 0) {
                    id = ThreadLocalRandom.current().nextInt(MIN_ID_CAPITALS, MAX_ID_CAPITALS + 1);
                } else {
                    id = ThreadLocalRandom.current().nextInt(MIN_ID_LOWERCASE, MAX_ID_LOWERCASE + 1);
                }
                Character symbol = (char) id;
                bufer.append(symbol);
            }
            values.add(new BaseDatatype(bufer.toString(), TYPE));
            bufer.delete(0, j);
        }

    }

    private void stringBuildLow() {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for (Integer j = 1; j <= len + INCREASE_QUANTITY; j++) {

            for (Integer word = 1; word <= j; word++) {
                id = ThreadLocalRandom.current().nextInt(MIN_ID_LOWERCASE, MAX_ID_LOWERCASE + 1);
                Character symbol = (char) id;
                bufer.append(symbol);
            }
            values.add(new BaseDatatype(bufer.toString(), TYPE));
            bufer.delete(0, j);
        }

    }

    private void stringBuildCap() {
        int id = 0;
        StringBuilder bufer = new StringBuilder();
        for (Integer j = 1; j <= len + INCREASE_QUANTITY; j++) {
            for (Integer word = 1; word <= j; word++) {
                id = ThreadLocalRandom.current().nextInt(MIN_ID_CAPITALS, MAX_ID_CAPITALS + 1);
                Character symbol = (char) id;
                bufer.append(symbol);

            }
            values.add(new BaseDatatype(bufer.toString(), TYPE));
            bufer.delete(0, j);
        }

    }

    private boolean checkIn() {
        if ((!Low && !Cap) || len <= 0 || INCREASE_QUANTITY <0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkOut() {
        if ((values.size() == 0) || (column == null)) {
            return true;
        } else {
            return false;
        }
    }
}
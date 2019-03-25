package com.risk.helperInterfaces;

import java.io.InputStream;

public class ConstantCard {

    public static InputStream loadCardsInputStream() {
        return ConstantCard.class.getResourceAsStream("ConstantCard.json");
    }

}
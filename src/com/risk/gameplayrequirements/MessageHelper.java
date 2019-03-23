package com.risk.gameplayrequirements;

/**
 * @author gursimransingh
 */
public class MessageHelper {


    /**
     * The message.
     */
    private String message;

    /**
     * Instantiates a new message util.
     *
     * @param message the message
     */
    public MessageHelper(String message) {
        this.message = message;
    }

    /**
     * prints Message.
     *
     * @return String
     */
    public String printMessage() {
        System.out.println(message);
        return message;
    }

    /**
     * Salutation Message.
     *
     * @return String
     */
    public String salutationMessage() {
        message = "Hi!" + message;
        System.out.println(message);
        return message;
    }
}


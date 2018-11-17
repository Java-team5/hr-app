package com.team5.controller.enums;

/**
 * This enum type mean result of controller operation.
 * it can be expanded by adding new items.
 */
public enum ActionResult {
    /**
     * The success result of action in controllers.
     */
    SUCCESS("Success"),

    /**
     * The failed result of action in controllers.
     */
    ERROR("Error");

    /**
     * String representation of the enumeration item.
     */
    private String type;

    /**
     * private enum constructor.
     * @param typeValue String representation of enum value
     */
    ActionResult(final String typeValue) {
        this.type = typeValue;
    }

    @Override
    public String toString() {
        return type;
    }
}

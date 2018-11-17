package com.team5.utils;

import java.util.HashMap;
import java.util.Iterator;

/**
 * This is class that helps to build sql filter and sort part of SQL query.
 * The value of filter stored as key - name of column and value - filter flag.
 * That why it is inhereted HashMap.
 */
public class SqlFilter extends HashMap<String, String> {

    /**
     * A constant service word that is added to the sql query part.
     */
    //language=sql
    private final String WHEREPART = " WHERE ";

    /**
     * A constant service word that is added to the sql query part.
     */
    //language=sql
    private final String LIKEPART = " LIKE ";

    /**
     * A constant service word that is added to the sql query part.
     */
    //language=sql
    private final String AND = " AND ";

    /**
     * A constant service word that is added to the sql query part.
     */
    //language=sql
    private final String ORDERBYPART = " ORDER BY ";

    /**
     * A constant that is added to the sql query part.
     */
    private final String OPENBRACKET = "(";

    /**
     * A constant that is added to the sql query part.
     */
    private final String CLOSEBRACKET = ")";

    /**
     * Sorting flag.
     */
    private String sortingCriterion;

    /**
     * Get sortingСriterion param.
     * @return name of column that mean sorting flag.
     */
    public String getSortingCriterion() {
        return sortingCriterion;
    }

    /**
     * Sets sorting flag.
     * @param sortingCriterionValue the name of column in real Database table.
     */
    public void setSortingCriterion(final String sortingCriterionValue) {
        this.sortingCriterion = sortingCriterionValue;
    }

    /** Build a part of sql query and return this as a String
     *  The result contain where part with sorting.
     * @return sql part query in String representation.
     */
    public String getEmbeddedLine() {
        StringBuffer resultFilter = new StringBuffer();
        if (this.isEmpty()) {
            addSort(resultFilter);
            return resultFilter.toString();
        }

        resultFilter.append(this.WHEREPART);
        Iterator iterator = this.entrySet().iterator();

        if (this.size() == 1) {
            addCondition(resultFilter, (Entry<String, String>) iterator.next());
            addSort(resultFilter);
            return resultFilter.toString();
        }

        resultFilter.append(this.OPENBRACKET);
        while (true) {
            addCondition(resultFilter, (Entry<String, String>) iterator.next());
            if (iterator.hasNext()) {
                resultFilter.append(this.AND);
            } else {
                break;
            }
        }
        resultFilter.append(this.CLOSEBRACKET);
        addSort(resultFilter);

        return resultFilter.toString();
    }


    /** Add part of sql request by template for filtering.
     * Example: "column" LIKE '%"filter"%'.
     * @param resultFilter changeable string
     *        representing the sql part of the query.
     * @param entry consists of two values "column"
     *        the name of column in table of Database
     *        and "filter" the part of full filter string.
     */
    private void addCondition(
            final StringBuffer resultFilter,
            final Entry<String, String> entry) {
        resultFilter.append(entry.getKey());
        resultFilter.append(this.LIKEPART);
        resultFilter.append("'%" + entry.getValue() + "%'");
    }

    /** Add part of sql request by template for sort.
     * Example: ORDER BY "column".
     * @param resultFilter changeable string
     *        representing the sql part of the query.
     */
    private void addSort(final StringBuffer resultFilter) {
        if (this.sortingCriterion != null
            && !this.sortingCriterion.isEmpty()) {
            resultFilter.append(this.ORDERBYPART);
            resultFilter.append(this.sortingCriterion);
        }
    }
}

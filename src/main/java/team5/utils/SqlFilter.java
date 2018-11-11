package team5.utils;

import java.util.HashMap;
import java.util.Iterator;

public class SqlFilter extends HashMap<String, String> {
    //language=sql
    private final String WHEREPART = " WHERE ";
    //language=sql
    private final String LIKEPART = " LIKE ";
    //language=sql
    private final String AND = " AND ";
    //language=sql
    private final String ORDERBYPART = " ORDER BY ";
    private final String OPENBRACKET = "(";
    private final String CLOSEBRACKET = ")";

    private String sortingСriterion;

    public String getSortingСriterion() {
        return sortingСriterion;
    }

    public void setSortingСriterion(final String sortingСriterion) {
        this.sortingСriterion = sortingСriterion;
    }

    /** Build a part of sql query and return this as a String
     *  The result contain where part with sorting.
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


    /** Add part of sql request by template.
     * Example: "column" LIKE '%"filter"%'
     * @param resultFilter changeable string
     *        representing the sql part of the query
     * @param entry consists of two values "column"
     *        the name of column in table of Database
     *        and "filter" the part of full filter string
     */
    private void addCondition(
            final StringBuffer resultFilter,
            final Entry<String, String> entry) {
        resultFilter.append(entry.getKey());
        resultFilter.append(this.LIKEPART);
        resultFilter.append("'%" + entry.getValue() + "%'");
    }

    private void addSort(final StringBuffer resultFilter) {
        if (this.sortingСriterion != null
            && !this.sortingСriterion.isEmpty()) {
            resultFilter.append(this.ORDERBYPART);
            resultFilter.append(this.sortingСriterion);
        }
    }
}

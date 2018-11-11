package team5.filter;

import org.junit.Assert;
import org.junit.Test;
import team5.utils.SqlFilter;

import java.util.ArrayList;

public class SqlFilterTest {

    @Test
    public void getEmbeddedLine() {
        ArrayList<String> querylist = new ArrayList<>();
        querylist.add(" WHERE position LIKE '%Java%'");
        querylist.add(" WHERE (position LIKE '%Java%' AND vacancyState LIKE '%Active%')");
        querylist.add(" WHERE (position LIKE '%Java%' AND vacancyState LIKE '%Active%') ORDER BY salaryTo");
        querylist.add(" WHERE vacancyState LIKE '%Active%' ORDER BY salaryTo");
        querylist.add(" ORDER BY salaryTo");

        SqlFilter filter = new SqlFilter();
        filter.put("position","Java");
        Assert.assertEquals(querylist.get(0),filter.getEmbeddedLine());
        filter.put("vacancyState","Active");
        Assert.assertEquals(querylist.get(1),filter.getEmbeddedLine());
        filter.setSortingСriterion("salaryTo");
        Assert.assertEquals(querylist.get(2),filter.getEmbeddedLine());

        filter.remove("position");
        Assert.assertEquals(querylist.get(3),filter.getEmbeddedLine());
        filter.remove("vacancyState");
        Assert.assertEquals(querylist.get(4),filter.getEmbeddedLine());
    }
}

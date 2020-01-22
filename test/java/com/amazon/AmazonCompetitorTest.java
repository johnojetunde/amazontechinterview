package com.amazon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AmazonCompetitorTest {

    private AmazonCompetitor amazon;
    private static Integer numCompetitors = 6;
    private static Integer topNCompetitors = 2;
    private static List<String> competitors = asList("newshop", "shownow", "afshion", "fashionbeats", "mymarket", "tcellular");
    private static Integer numReviews = 6;
    private static List<String> reviews = asList("newshop is providing good services in the city; everyone should use newshop", "best services by newshop",
            "fashionbeats has great services in the city", "I am proud to have fashionbeats", "mymarket has awesome services", "Thanks Newshop for the quick delivery");

    @BeforeEach
    void setUp() {
        amazon = new AmazonCompetitor();
    }

    @Test
    public void resultShouldNotGreaterThan__topNCompetitors() {
        List<String> topCompetitors = amazon.topNCompetitors(numCompetitors, topNCompetitors, competitors, numReviews, reviews);
        assertFalse(topCompetitors.size() > topNCompetitors);
    }

    @Test
    public void shouldGroupByCompetitorsCorrectly() {
        Map<String, Long> topCompetitors = amazon.reviewFrequency(competitors, reviews);
        Map<String, Long> expected = new HashMap<>();
        expected.put("newshop", 3L);
        expected.put("fashionbeats", 2L);
        expected.put("mymarket", 1L);

        assertEquals(expected, topCompetitors);
    }

    @Test
    public void shouldSortGroupedCompetitorsCorrectly() {
        Map<String, Long> data = new HashMap<>();
        data.put("newshop", 3L);
        data.put("fashionbeats", 3L);
        data.put("mymarket", 1L);

        List<String> topCompetitors = amazon.sortReviews(data);
        assertEquals(asList("fashionbeats", "newshop", "mymarket"), topCompetitors);
    }

    @Test
    public void shouldGet_topNCompetitors() {
        System.out.println("Start---> "+System.currentTimeMillis());
        List<String> topCompetitors = amazon.topNCompetitors(numCompetitors, topNCompetitors, competitors, numReviews, reviews);
        assertEquals(asList("newshop", "fashionbeats"), topCompetitors);
        System.out.println("End---> "+System.currentTimeMillis());
    }

    @Test
    public void shouldGet_allCompetitorsInReviews__whentopNCompetitor_is_greater_than_totalCompetitors() {
        topNCompetitors = 8;
        List<String> topCompetitors = amazon.topNCompetitors(numCompetitors, topNCompetitors, competitors, numReviews, reviews);
        assertEquals(asList("newshop", "fashionbeats","mymarket"), topCompetitors);
    }
}
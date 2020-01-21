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
        Map<String, Integer> topCompetitors = amazon.reviewFrequency(competitors, reviews);
        Map<String, Integer> expected = new HashMap<>();
        expected.put("newshop", 3);
        expected.put("fashionbeats", 2);
        expected.put("mymarket", 1);

        assertEquals(expected, topCompetitors);
    }

    @Test
    public void shouldSortGroupedCompetitorsCorrectly() {
        Map<String, Integer> data = new HashMap<>();
        data.put("newshop", 3);
        data.put("fashionbeats", 3);
        data.put("mymarket", 1);

        List<String> topCompetitors = amazon.sortReviews(data);
        assertEquals(asList("fashionbeats", "newshop", "mymarket"), topCompetitors);
    }

    @Test
    public void shouldGet_topNCompetitors() {
        List<String> topCompetitors = amazon.topNCompetitors(numCompetitors, topNCompetitors, competitors, numReviews, reviews);
        assertEquals(asList("newshop", "fashionbeats"), topCompetitors);
    }

}
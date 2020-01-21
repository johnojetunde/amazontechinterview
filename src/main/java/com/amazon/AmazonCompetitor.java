package com.amazon;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AmazonCompetitor {
    public List<String> topNCompetitors(Integer numCompetitors, Integer topNCompetitors, List<String> competitors, Integer numReviews, List<String> reviews) {
        List<String> totalCompetitors = sortReviews(
                reviewFrequency(competitors, reviews)
        );

        return totalCompetitors.subList(0, topNCompetitors);
    }

    public List<String> sortReviews(Map<String, Integer> competitors) {
        Map<String, Integer> sorted = competitors.entrySet().parallelStream()
                .sorted(Map.Entry.comparingByKey())
                .sorted((value1, value2) -> Long.compare(value2.getValue(), value1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return new ArrayList<>(sorted.keySet());
    }

    public Map<String, Integer> reviewFrequency(List<String> competitors, List<String> reviews) {
        Map<String, Integer> reviewFreq = new HashMap<>();
        reviews.stream()
                .map(review -> competitorsFound(review, competitors))
                .forEach(competitorsFound -> updateCompetitorFrequency(competitorsFound, reviewFreq));

        return reviewFreq;
    }

    private Stream<String> competitorsFound(String review, List<String> competitors) {
        return competitors.stream()
                .filter(s -> review.toLowerCase().contains(s.toLowerCase()));
    }

    private void updateCompetitorFrequency(Stream<String> competitors, Map<String, Integer> reviewFreq) {
        competitors.forEach(s -> {
            Integer currentCount = reviewFreq.getOrDefault(s, 0);
            reviewFreq.put(s, ++currentCount);
        });
    }
}

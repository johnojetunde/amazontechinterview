package com.amazon;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class AmazonCompetitor {

    public List<String> topNCompetitors(Integer numCompetitors, Integer topNCompetitors, List<String> competitors, Integer numReviews, List<String> reviews) {
        List<String> totalCompetitors = sortReviews(
                reviewFrequency(competitors, reviews)
        );
        //total complexity here us O(4n2) + 2(O(n))

        return topNCompetitors > totalCompetitors.size()
                ? totalCompetitors
                : totalCompetitors.subList(0, topNCompetitors);
    }

    List<String> sortReviews(Map<String, Long> competitors) {
        Map<String, Long> sorted = competitors.entrySet().stream() // O(n)
                .sorted(Map.Entry.comparingByKey()) //O(1)
                .sorted((value1, value2) -> Long.compare(value2.getValue(), value1.getValue())) //O(1)
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return new ArrayList<>(sorted.keySet()); //O(n)
    }

    Map<String, Long> reviewFrequency(List<String> competitors, List<String> reviews) {
        AtomicReference<String> currentReview = new AtomicReference<>("");

        return reviews.stream()
                .map(String::toLowerCase) //O(n)
                .flatMap(s -> assignReviewToAtomicInteger(currentReview, s, competitors)) //O(1)
                .map(String::toLowerCase)//O(n)
                .filter(s -> isFound(currentReview.get(), s)) //O(n)
                .collect(groupingBy(identity(), counting())); //O(n)

        //taking the O(n) complexity of the stream into consideration, we now have
        //O(n2)+ O(n2)+ O(1) + O(n2) + O(n2)
        //the total complexity here is  O(4n2) + O(1)
    }

    public boolean isFound(String review, String competitor) {
        Map<String, String> reviewParts = extractReviewParts(review); //O(n)
        return reviewParts.get(competitor) != null; //O(1)
    }

    //this takes lesser time
    Map<String, Long> reviewFrequencyVersion2WithoutStream(List<String> competitors, List<String> reviews) {
        Map<String, Long> reviewFreq = new HashMap<>();

        for (String review : reviews) { // O(n)
            review = review.toLowerCase(); //O(n)
            String[] competitorsRetrieved = new String[competitors.size()];
            competitorsRetrieved = competitors.toArray(competitorsRetrieved); //O(n)

            //O(n)
            for (int i = 0; i < competitorsRetrieved.length; i++) {
                competitorsRetrieved[i] = competitorsRetrieved[i].toLowerCase();
            }

            Map<String, String> reviewParts = extractReviewParts(review); //O(n)

            for (String competitor : competitorsRetrieved) { //O(n)
                if (reviewParts.get(competitor) != null) {  //O(1)
                    Long currentCount = reviewFreq.getOrDefault(competitor, 0L); //O(1)
                    reviewFreq.put(competitor, ++currentCount); //O(1)
                }
            }

        }

        //taking the O(n) complexity of the stream into consideration, we now have
        //  O(n2)+ O(n2)+ O(n2) + O(n2)
        //the total complexity here is  O(4n2) + 2
        return reviewFreq;
    }

    private Map<String, String> extractReviewParts(String review) {
        String[] reviewParts = review.split(" ");
        Map<String, String> map = new HashMap<>();
        for (String part : reviewParts) {
            map.put(part, part);
        }
        return map;
    }

    private Stream<String> assignReviewToAtomicInteger(AtomicReference<String> review, String currentReview, List<String> competitor) {
        review.set(currentReview);
        return competitor.stream();
    }

    private void updateCompetitorFrequency(String competitor, Map<String, Integer> reviewFreq) {
        Integer currentCount = reviewFreq.getOrDefault(competitor, 0);
        reviewFreq.put(competitor, ++currentCount);
    }
}

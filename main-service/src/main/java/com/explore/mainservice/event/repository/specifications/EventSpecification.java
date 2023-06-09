package com.explore.mainservice.event.repository.specifications;

import com.explore.mainservice.category.model.Category;
import com.explore.mainservice.event.enums.StateEvent;
import com.explore.mainservice.event.model.Event;
import com.explore.mainservice.event.model.Event_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.explore.mainservice.event.repository.specifications.SpecificationUtils.*;
import static com.explore.mainservice.event.util.DateFormatConstant.DATE_FORMAT;


public class EventSpecification {


    public static Specification<Event> requestSpec(List<Long> users, List<StateEvent> states, List<Category> categories,
                                                   String rangeStart, String rangeEnd) {
        List<Specification<Event>> specifications = new ArrayList<>();


        if (!CollectionUtils.isEmpty(users)) {
            specifications.add(in(Event_.initiatorId, users));
        }
        if (!CollectionUtils.isEmpty(states)) {
            specifications.add(in(Event_.state, states));
        }

        if (!CollectionUtils.isEmpty(categories)) {
            specifications.add(in(Event_.category, categories));
        }

        if (rangeStart != null) {
            LocalDateTime start = LocalDateTime.parse(URLDecoder.decode(rangeStart, StandardCharsets.UTF_8),
                    DateTimeFormatter.ofPattern((DATE_FORMAT)));
            specifications.add(greaterThanOrEqualTo(Event_.eventDate, start));
        }

        if (rangeEnd != null) {
            LocalDateTime end = LocalDateTime.parse(URLDecoder.decode(rangeEnd, StandardCharsets.UTF_8),
                    DateTimeFormatter.ofPattern((DATE_FORMAT)));
            specifications.add(lessThanOrEqualTo(Event_.eventDate, end));
        }
        return and(specifications);
    }

    public static Specification<Event> requestSpec(String text, List<Category> categories, Boolean paid,
                                                   String rangeStart, String rangeEnd, Boolean onlyAvailable,
                                                   StateEvent state) {
        List<Specification<Event>> specifications = new ArrayList<>();
        if (!CollectionUtils.isEmpty(categories)) {

            specifications.add(in(Event_.category, categories));
        }
        if (rangeStart != null) {
            LocalDateTime start = LocalDateTime.parse(URLDecoder.decode(rangeStart, StandardCharsets.UTF_8),
                    DateTimeFormatter.ofPattern((DATE_FORMAT)));
            specifications.add(greaterThanOrEqualTo(Event_.eventDate, start));
        } else {
            specifications.add(greaterThanOrEqualTo(Event_.eventDate, LocalDateTime.now()));
        }

        if (rangeEnd != null) {
            LocalDateTime end = LocalDateTime.parse(URLDecoder.decode(rangeEnd, StandardCharsets.UTF_8),
                    DateTimeFormatter.ofPattern((DATE_FORMAT)));
            specifications.add(lessThanOrEqualTo(Event_.eventDate, end));
        }

        if (paid != null) {
            specifications.add(isEquals(Event_.paid, paid));
        }

        if (onlyAvailable != null && onlyAvailable) {
            specifications.add(lessThan(Event_.confirmedRequests, Event_.participantLimit));
        }

        if (!StringUtils.isEmpty(text)) {
            specifications.add(or(List.of(containsIgnoreCase(Event_.annotation, text),
                    containsIgnoreCase(Event_.description, text))));

        }

        if (state != null) {
            specifications.add(isEquals(Event_.state, state));
        }

        return and(specifications);
    }
}

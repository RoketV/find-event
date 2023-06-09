package com.explore.mainservice.event.dto;

import com.explore.mainservice.category.dto.CategoryDto;
import com.explore.mainservice.event.enums.StateEvent;
import com.explore.mainservice.location.dto.LocationDto;
import com.explore.mainservice.user.dto.UserShortDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class EventFullDto {

    @NotNull
    private String annotation;

    @NotNull
    private CategoryDto category;

    private Integer confirmedRequests;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;

    private String description;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    private Long id;

    @NotNull
    private UserShortDto initiator;

    @NotNull
    private LocationDto location;

    @NotNull
    private Boolean paid;

    private Integer participantLimit;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;

    private Boolean requestModeration;

    private StateEvent state;

    @NotNull
    private String title;

    private Long views;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventFullDto)) return false;
        EventFullDto that = (EventFullDto) o;
        return Objects.equals(annotation, that.annotation) && Objects.equals(category, that.category)
                && Objects.equals(confirmedRequests, that.confirmedRequests)
                && Objects.equals(createdOn, that.createdOn)
                && Objects.equals(description, that.description)
                && Objects.equals(eventDate, that.eventDate)
                && Objects.equals(id, that.id) && Objects.equals(initiator, that.initiator)
                && Objects.equals(location, that.location) && Objects.equals(paid, that.paid)
                && Objects.equals(participantLimit, that.participantLimit)
                && Objects.equals(publishedOn, that.publishedOn)
                && Objects.equals(requestModeration, that.requestModeration)
                && Objects.equals(state, that.state) && Objects.equals(title, that.title)
                && Objects.equals(views, that.views);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annotation, category, confirmedRequests, createdOn, description, eventDate, id, initiator,
                location, paid, participantLimit, publishedOn, requestModeration, state, title, views);
    }
}

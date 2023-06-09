package com.explore.mainservice.location.dto;

import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class LocationDto {

    private Long id;

    private Float lat;

    private Float lon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationDto)) return false;
        LocationDto that = (LocationDto) o;
        return Objects.equals(id, that.id) && Objects.equals(lat, that.lat) && Objects.equals(lon, that.lon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lat, lon);
    }
}

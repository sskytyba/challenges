package com.bus.routes.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DirectRouteAvailability {

    @JsonProperty("dep_sid")
    private final int departureId;

    @JsonProperty("arr_sid")
    private final int arrivalId;

    @JsonProperty("direct_bus_route")
    private final boolean hasDirectRoute;
}

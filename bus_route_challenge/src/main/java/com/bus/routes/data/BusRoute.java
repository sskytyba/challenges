package com.bus.routes.data;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public final class BusRoute {
    private final int routeId;
    private final Map<Integer, Station> stations;
}

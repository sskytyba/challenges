package com.bus.routes;

import com.bus.routes.data.Station;
import com.google.common.collect.ImmutableMap;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.Set;

@UtilityClass
public final class TestUtils {
    public static Map<Integer, Station> buildStationsMap(Set<Integer> ids) {
        return ids.stream().collect(ImmutableMap.toImmutableMap(id -> id, Station::new));
    }
}

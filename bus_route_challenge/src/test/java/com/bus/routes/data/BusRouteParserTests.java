package com.bus.routes.data;

import com.bus.routes.TestUtils;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BusRouteParserTests {

    @Test
    public void routeParseTest() throws IOException {
        BusRouteParser container = new BusRouteParser("src/test/resources/example");
        container.parse();
        assertEquals(
            ImmutableSet.of(
                BusRoute.builder()
                        .routeId(0)
                        .stations(TestUtils.buildStationsMap(ImmutableSet.of(0, 1, 2, 3, 4)))
                        .build(),
                BusRoute.builder()
                        .routeId(1)
                        .stations(TestUtils.buildStationsMap(ImmutableSet.of(3, 1, 6, 5)))
                        .build(),
                BusRoute.builder()
                        .routeId(2)
                        .stations(TestUtils.buildStationsMap(ImmutableSet.of(0, 6, 4)))
                        .build()),
            container.getBusRoutes());
    }
}

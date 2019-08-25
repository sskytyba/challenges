package com.bus.routes.service;

import com.bus.routes.TestUtils;
import com.bus.routes.data.BusRoute;
import com.bus.routes.data.BusRouteParser;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RouteServiceTests {

    @Test
    public void hasDirectRouteTest() {
        BusRouteParser holder = mock(BusRouteParser.class);
        when(holder.getBusRoutes()).thenReturn(ImmutableSet.of(
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
                    .build()));

        RouteService service = new RouteServiceImpl(holder);

        assertTrue(service.hasDirectRoute(0, 0));
        assertFalse(service.hasDirectRoute(7, 7));
        assertTrue(service.hasDirectRoute(1, 3));
        assertTrue(service.hasDirectRoute(3, 5));
        assertFalse(service.hasDirectRoute(3, 7));
    }
}

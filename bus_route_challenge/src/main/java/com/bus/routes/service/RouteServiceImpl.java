package com.bus.routes.service;

import com.bus.routes.data.BusRouteParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService {
    private BusRouteParser busRouteParser;

    RouteServiceImpl(BusRouteParser busRouteParser) {
        this.busRouteParser = busRouteParser;
    }

    @Override
    public boolean hasDirectRoute(int depSid, int arrSid) {
        return busRouteParser
            .getBusRoutes()
            .stream()
            .anyMatch(busRoute -> busRoute.getStations().containsKey(depSid) &&
                                  busRoute.getStations().containsKey(arrSid));
    }
}

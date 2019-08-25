package com.bus.routes.controller;

import com.bus.routes.service.RouteService;
import com.bus.routes.view.DirectRouteAvailability;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RouteController {

    @Autowired
    private RouteService routeService;

    /**
     * Checks availability of direct route between two stations
     * @param depSid - unique id of departure station
     * @param arrSid - unique id of arrival station
     * @return availability of direct route {@link DirectRouteAvailability}
     */
    @RequestMapping("/direct")
    public DirectRouteAvailability getDirectRouteAvailability(@RequestParam("dep_sid") int depSid,
                                                              @RequestParam("arr_sid") int arrSid) {
        log.info("Api /direct call with params: dep_sid = {}, arr_sid = {}", depSid, arrSid);

        boolean hasDirectRoute = routeService.hasDirectRoute(depSid, arrSid);
        return DirectRouteAvailability
            .builder()
            .departureId(depSid)
            .arrivalId(arrSid)
            .hasDirectRoute(hasDirectRoute)
            .build();
    }
}

package com.bus.routes.data;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
public class BusRouteParser {

    private final String busRoutesFilePath;

    @Getter
    private Set<BusRoute> busRoutes;

    BusRouteParser(@Value("${app.bus-routes}") String busRoutesFilePath) {
        this.busRoutesFilePath = busRoutesFilePath;
    }

    @PostConstruct
    void parse() throws IOException {
        log.info("Routes container initialization...");

        Path filePath = Paths.get(busRoutesFilePath);
        if (!Files.exists(filePath)) {
            log.error("Routes file not found: {}", busRoutesFilePath);
            throw new FileNotFoundException(busRoutesFilePath);
        }

        List<String> lines = Files.readAllLines(filePath);

        Preconditions.checkArgument(!lines.isEmpty(), "Empty routes file");
        Preconditions.checkArgument(Integer.parseInt(lines.get(0)) == lines.size() - 1, "Wrong number of routes");

        busRoutes = lines
            .stream()
            // skip size line
            .skip(1)
            .map(BusRouteParser::parseLine)
            .collect(ImmutableSet.toImmutableSet());
    }

    private static BusRoute parseLine(String line) {
        List<String> splittedLine = Arrays.asList(line.split(" "));
        Preconditions.checkArgument(splittedLine.size() > 2, "Expected more then 2 numbers in route line");

        int routeId = Integer.parseInt(splittedLine.get(0));
        Map<Integer, Station> stations = splittedLine
            .stream()
            // skip route id
            .skip(1)
            .map(Integer::parseInt)
            .collect(ImmutableMap.toImmutableMap(id -> id, Station::new));
        return BusRoute.builder().routeId(routeId).stations(stations).build();
    }
}

package com.bus.routes.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"app.bus-routes=src/test/resources/example"})
@AutoConfigureMockMvc
public class RouteControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertThat(mockMvc).isNotNull();
    }

    @Test
    public void hasDirectRouteValidRequest() throws Exception {
        this.mockMvc
            .perform(get("/direct").param("dep_sid", "3").param("arr_sid", "6"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(matchesJsonSchemaInClasspath("json-schema.json")));
    }

    @Test
    public void hasDirectRouteMissingParam() throws Exception {
        this.mockMvc
            .perform(get("/direct").param("arr_sid", "6"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void hasDirectRouteBadParam() throws Exception {
        this.mockMvc
            .perform(get("/direct").param("dep_sid", "6").param("arr_sid", "gfg"))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }
}

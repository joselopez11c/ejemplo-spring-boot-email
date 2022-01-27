package com.coderhouse;

import com.coderhouse.model.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmailMessageApplicationTests {

    private String url;
    @LocalServerPort
    private int port;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll - se ejecuta antes de todos los tests");
    }

    @BeforeEach
    void init() {
        url = String.format("http://localhost:%d/coder-house/", port);
        System.out.println("@BeforeEach - se ejecuta antes de la ejecución de cada test");
    }

    @Test
    public void getAllMessages() throws Exception {
        var uriTest = String.format("%s%s", url, "mensajes/all");

        var messageResult = this.restTemplate.getForObject(uriTest, List.class);

        Assert.notNull(messageResult, "Lista de mensajes no nula");
        Assert.notEmpty(messageResult, "Lista de mensajes con elementos");
        Assert.isTrue(messageResult.size() == 6, "Tamaño de la lista es de 5");

    }

    @Test
    public void getMessageById() {
        var uriTest = String.format("%s%s", url, "mensajes/1");
        var messageResult = this.restTemplate.getForObject(uriTest, Message.class);

        Assert.notNull(messageResult, "Mensaje no nula");
        Assert.isTrue(messageResult.getId() == 1, "ID del mensaje OK");
        Assert.isTrue(messageResult.getDescription().equals("Mensaje-ABCD"), "Descripción del mensaje OK");
    }

    @Test
    public void createMessage() {
        var uriTest = String.format("%s%s", url, "mensajes");
        var message = Message.builder().id(18L).description("Mensaje de ejemplo").build();

        var messageResult = this.restTemplate.postForObject(uriTest, message, Message.class);

        Assert.notNull(messageResult, "Mensaje no nula");
        Assert.isTrue(messageResult.getId() == 18L, "ID del mensaje OK");
        Assert.isTrue(messageResult.getDescription().equals("Mensaje de ejemplo"), "Descripción del mensaje OK");
    }


    @Test
    public void getAllMessagesHttpRequestStatus() throws IOException {
        var uriTest = String.format("%s%s", url, "mensajes/all");

        var request = new HttpGet(uriTest);
        var httpResponse = HttpClientBuilder.create().build().execute(request);

        Assert.isTrue(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK, "Response status OK");
    }

    @Test
    public void getAllMessagesHttpRequestHeader() throws IOException {
        var uriTest = String.format("%s%s", url, "mensajes/all");
        var headerAppJson = "application/json";

        var request = new HttpGet(uriTest);
        var httpResponse = HttpClientBuilder.create().build().execute(request);
        var mimeType = ContentType.getOrDefault(httpResponse.getEntity()).getMimeType();
        Assert.isTrue(headerAppJson.equals(mimeType), "Header application/json OK");
    }

    @Test
    public void getAllMessagesHttpRequestPayload() throws IOException {
        var uriTest = String.format("%s%s", url, "mensajes/all");

        var request = new HttpGet(uriTest);
        var httpResponse = HttpClientBuilder.create().build().execute(request);

        String content = EntityUtils.toString(httpResponse.getEntity());
        var messageResult = objectMapper.readValue(content, List.class);

        Assert.notNull(messageResult, "Lista de mensajes no nula");
        Assert.notEmpty(messageResult, "Lista de mensajes con elementos");
        Assert.isTrue(messageResult.size() == 6, "Tamaño de la lista es de 5");
    }

}

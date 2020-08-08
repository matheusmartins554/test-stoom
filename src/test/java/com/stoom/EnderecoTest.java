package com.stoom;

import com.stoom.model.Endereco;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StoomApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EnderecoTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() { }

    @Test
    public void testGetAllEndereco() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/endereco",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetEnderecoById() {
        Endereco endereco = restTemplate.getForObject(getRootUrl() + "/endereco/6", Endereco.class);
        System.out.println(endereco.getStreetName());
        assertNotNull(endereco);
    }

    @Test
    public void testCreateEndereco() {
        Endereco endereco = new Endereco();
        endereco.setStreetName("test");
        endereco.setCity("test");
        ResponseEntity<Endereco> postResponse = restTemplate.postForEntity(getRootUrl() + "/endereco", endereco, Endereco.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateEndereco() {
        int id = 1;
        Endereco endereco = restTemplate.getForObject(getRootUrl() + "/endereco/" + id, Endereco.class);

        endereco.setStreetName("test");
        endereco.setCity("test");

        restTemplate.put(getRootUrl() + "/endereco/" + id, endereco);
        Endereco updatedEndereco = restTemplate.getForObject(getRootUrl() + "/endereco/" + id, Endereco.class);
        assertNotNull(updatedEndereco);
    }

    @Test
    public void testDeleteEndereco() {
        int id = 4;
        Endereco endereco = restTemplate.getForObject(getRootUrl() + "/endereco/" + id, Endereco.class);
        assertNotNull(endereco);
        restTemplate.delete(getRootUrl() + "/endereco/" + id);
        try {
            endereco = restTemplate.getForObject(getRootUrl() + "/endereco/" + id, Endereco.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
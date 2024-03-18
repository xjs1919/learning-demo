package com.github.xjs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {

    @Test
    public void testGetById(){
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8888/webservice/userService?wsdl");
        final ObjectMapper mapper = new ObjectMapper();
        try {
            Object[] objects = client.invoke("getById", 123);
            System.out.println(mapper.writeValueAsString(objects[0]));
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }
}
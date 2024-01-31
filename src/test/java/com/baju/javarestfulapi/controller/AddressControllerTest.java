package com.baju.javarestfulapi.controller;

import com.baju.javarestfulapi.entity.Address;
import com.baju.javarestfulapi.entity.Contact;
import com.baju.javarestfulapi.entity.User;
import com.baju.javarestfulapi.model.AddressResponse;
import com.baju.javarestfulapi.model.CreateAddressRequest;
import com.baju.javarestfulapi.model.UpdateAddressRequest;
import com.baju.javarestfulapi.model.WebResponse;
import com.baju.javarestfulapi.repository.AddressRepository;
import com.baju.javarestfulapi.repository.ContactRepository;
import com.baju.javarestfulapi.repository.UserRepository;
import com.baju.javarestfulapi.security.BCrypt;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.ContentResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
        contactRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("aldi");
        user.setPassword(BCrypt.hashpw("aldi123", BCrypt.gensalt()));
        user.setName("Aldi");
        user.setToken("soraja");
        user.setTokenExpiredAt(System.currentTimeMillis() + 1000000000);
        userRepository.save(user);

        Contact contact = new Contact();
        contact.setId("aldi");
        contact.setUser(user);
        contact.setFirstName("Aldi");
        contact.setLastName("Soraja");
        contact.setEmail("aldisoraja1@gmail.com");
        contact.setPhone("082245489778");
        contactRepository.save(contact);
    }

    @Test
    void createAddressBadRequest() throws Exception{
        CreateAddressRequest request = new CreateAddressRequest();
        request.setCountry("");

        mockMvc.perform(
                post("/api/contacts/aldi/addresses")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "soraja")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void createAddressSuccess() throws Exception{
        CreateAddressRequest request = new CreateAddressRequest();
        request.setStreet("Banyu Urip Wetan");
        request.setCity("Surabaya");
        request.setProvince("Jawa Timur");
        request.setCountry("Indonesia");
        request.setPostalCode("60255");

        mockMvc.perform(
                post("/api/contacts/aldi/addresses")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "soraja")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(request.getStreet(), response.getData().getStreet());
            assertEquals(request.getCity(), response.getData().getCity());
            assertEquals(request.getProvince(), response.getData().getProvince());
            assertEquals(request.getCountry(), response.getData().getCountry());
            assertEquals(request.getPostalCode(), response.getData().getPostalCode());

            assertTrue(addressRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void getAddressNotFound() throws Exception{
        mockMvc.perform(
                get("/api/contacts/aldi/addresses/test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "soraja")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void getAddressSuccess() throws Exception{
        Contact contact = contactRepository.findById("aldi").orElseThrow();

        Address address = new Address();
        address.setId("1");
        address.setContact(contact);
        address.setStreet("Banyu Urip Wetan");
        address.setCity("Surabaya");
        address.setProvince("Jawa Timur");
        address.setCountry("Indonesia");
        address.setPostalCode("60255");
        addressRepository.save(address);

        mockMvc.perform(
                get("/api/contacts/aldi/addresses/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "soraja")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(address.getId(), response.getData().getId());
            assertEquals(address.getStreet(), response.getData().getStreet());
            assertEquals(address.getCity(), response.getData().getCity());
            assertEquals(address.getProvince(), response.getData().getProvince());
            assertEquals(address.getCountry(), response.getData().getCountry());
            assertEquals(address.getPostalCode(), response.getData().getPostalCode());
        });
    }

    @Test
    void updateAddressBadRequest() throws Exception{
        UpdateAddressRequest request = new UpdateAddressRequest();
        request.setCountry("");

        mockMvc.perform(
                put("/api/contacts/aldi/addresses/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "soraja")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void updateAddressSuccess() throws Exception{
        Contact contact = contactRepository.findById("aldi").orElseThrow();

        Address address = new Address();
        address.setId("1");
        address.setContact(contact);
        address.setStreet("Lama");
        address.setCity("Lama");
        address.setProvince("Lama");
        address.setCountry("Lama");
        address.setPostalCode("11111");
        addressRepository.save(address);

        UpdateAddressRequest request = new UpdateAddressRequest();
        request.setStreet("Banyu Urip Wetan");
        request.setCity("Surabaya");
        request.setProvince("Jawa Timur");
        request.setCountry("Indonesia");
        request.setPostalCode("60255");

        mockMvc.perform(
                put("/api/contacts/aldi/addresses/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN", "soraja")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(request.getStreet(), response.getData().getStreet());
            assertEquals(request.getCity(), response.getData().getCity());
            assertEquals(request.getProvince(), response.getData().getProvince());
            assertEquals(request.getCountry(), response.getData().getCountry());
            assertEquals(request.getPostalCode(), response.getData().getPostalCode());

            assertTrue(addressRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void deleteAddressNotFound() throws Exception{
        mockMvc.perform(
                delete("/api/contacts/aldi/addresses/test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "soraja")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void deleteAddressSuccess() throws Exception{
        Contact contact = contactRepository.findById("aldi").orElseThrow();

        Address address = new Address();
        address.setId("1");
        address.setContact(contact);
        address.setStreet("Banyu Urip Wetan");
        address.setCity("Surabaya");
        address.setProvince("Jawa Timur");
        address.setCountry("Indonesia");
        address.setPostalCode("60255");
        addressRepository.save(address);

        mockMvc.perform(
                delete("/api/contacts/aldi/addresses/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "soraja")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals("Success", response.getData());

            assertFalse(addressRepository.existsById("1"));
        });
    }

    @Test
    void listAddressNotFound() throws Exception{
        mockMvc.perform(
                get("/api/contacts/alga/addresses")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "soraja")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void listAddressSuccess() throws Exception{
        Contact contact = contactRepository.findById("aldi").orElseThrow();

        for (int i = 0; i < 5; i++) {
            Address address = new Address();
            address.setId("1" + i);
            address.setContact(contact);
            address.setStreet("Banyu Urip Wetan");
            address.setCity("Surabaya");
            address.setProvince("Jawa Timur");
            address.setCountry("Indonesia");
            address.setPostalCode("60255");
            addressRepository.save(address);
        }

        mockMvc.perform(
                get("/api/contacts/aldi/addresses")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "soraja")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<AddressResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(5, response.getData().size());
        });
    }
}
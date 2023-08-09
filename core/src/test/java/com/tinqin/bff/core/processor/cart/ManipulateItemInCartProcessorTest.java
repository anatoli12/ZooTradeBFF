//package com.tinqin.bff.core.processor.cart;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.tinqin.bff.api.operation.cart.addtocart.ManipulateItemCartInput;
//import com.tinqin.bff.api.operation.security.register.RegisterOperation;
//import com.tinqin.bff.api.operation.security.register.RegisterRequest;
//import com.tinqin.bff.persistence.model.User;
//import com.tinqin.bff.persistence.repository.CartItemRepository;
//import com.tinqin.bff.persistence.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//@WebMvcTest
//@AutoConfigureWebMvc
//class ManipulateItemInCartProcessorTest {
//
//    private MockMvc mockMvc;
//
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private RegisterOperation registerOperation;
//
//    @MockBean
//    private ManipulateItemInCartProcessor manipulateItemInCartProcessor;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @MockBean
//    private CartItemRepository cartItemRepository;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCorrectManipulateItemInCart() {
//        registerOperation.process(RegisterRequest.builder()
//                .firstName("example")
//                .lastName("example")
//                .password("example")
//                .email("example@example.com")
//                .build());
//
//        ManipulateItemCartInput.builder()
//                .userId()
//    }
//}
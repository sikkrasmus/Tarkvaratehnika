package com.portfolio.backend.controllers;

import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.helper.FormatHelper;
import com.portfolio.backend.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RegisterController.class, secure = false)
public class RegisterControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    public void testCreatingUser() throws Exception {
        UserDTO u = new UserDTO();
        u.setEmail("test@test.com");
        u.setName("test");
        u.setPassword("Pass123-rrAAA");
        Mockito.when(userService.isUserEmailExisting(any(UserDTO.class))).thenReturn(false);
        Mockito.when(userService.createAndSaveUser(any(UserDTO.class))).thenReturn(u);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/register").content(FormatHelper.asJsonString(u))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{\"email\":\"test@test.com\",\"password\":\"Pass123-rrAAA\",\"name\":\"test\"}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}

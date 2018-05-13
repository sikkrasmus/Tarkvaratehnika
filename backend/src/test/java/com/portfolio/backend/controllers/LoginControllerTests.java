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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpSession;

import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LoginController.class, secure = false)
public class LoginControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;



    @Test
    public void testLogin() throws Exception {
        UserDTO u = new UserDTO();
        u.setEmail("testEmail");
        MockHttpSession s = new MockHttpSession();
        Mockito.when(userService.validateUser(any(UserDTO.class))).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/login").content(FormatHelper.asJsonString(u))
                .contentType(MediaType.APPLICATION_JSON)
                .session(s)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "{\"sessionid\":\"1\",\"username\":\"testEmail\"}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}

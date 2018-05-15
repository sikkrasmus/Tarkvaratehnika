package com.portfolio.backend.controllers;


import com.portfolio.backend.DTO.CoinDTO;
import com.portfolio.backend.DTO.PortfolioDTO;
import com.portfolio.backend.DTO.UserDTO;
import com.portfolio.backend.DTO.UserPortfolioDTO;
import com.portfolio.backend.entities.*;
import com.portfolio.backend.helper.FormatHelper;
import com.portfolio.backend.repository.PortfolioRepository;
import com.portfolio.backend.service.CoinService;
import com.portfolio.backend.service.PortfolioService;
import com.portfolio.backend.service.RequestService;
import com.portfolio.backend.service.UserService;
import org.json.JSONException;
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

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PortfolioController.class, secure = false)
public class PortfolioControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CoinService coinService;

    @MockBean
    private PortfolioService portfolioService;

    @MockBean
    private UserService userService;

    @MockBean
    private RequestService requestService;

    @MockBean
    private PortfolioRepository portfolioRepository;


    @Test
    public void testAddingPortfolio() throws Exception {
        UserPortfolioDTO userPortfolioDTO = new UserPortfolioDTO();
        Portfolio portfolio = new Portfolio();
        portfolio.setName("testName");
        portfolio.setDescription("testDescription");
        User user = new User();
        user.setEmail("testEmail");

        Mockito.when(portfolioService.createAndSavePortfolio(any(PortfolioDTO.class), any(User.class)))
                .thenReturn(portfolio);
        Mockito.when(userService.findUser(Mockito.anyString())).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/addPortfolio").content(FormatHelper.asJsonString(userPortfolioDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"description\":\"testName\",\"portfolioName\":\"testDescription\",\"username\":\"testEmail\"}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void testGettingPortfolios() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@test.com");
        Map<Long, List<String>> testPortfolios = new HashMap<>();
        testPortfolios.put(1L, Arrays.asList("name1", "desc1"));
        testPortfolios.put(2L, Arrays.asList("name2", "desc2"));
        Mockito.when(portfolioService.getPortfoliosNamesByUsername(Mockito.anyString())).thenReturn(testPortfolios);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/getPortfolio").content(FormatHelper.asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"1\":[\"name1\",\"desc1\"],\"2\":[\"name2\",\"desc2\"]}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }



    @Test
    public void testAddingCoin() throws Exception {
        CoinDTO coinDTO = new CoinDTO();
        Coin testCoin = new Coin();
        coinDTO.setShortName("testShort");
        coinDTO.setLongName("testLong");
        Mockito.when(coinService.createAndSaveCoin(Mockito.mock(CoinDTO.class), Mockito.mock(Portfolio.class)))
                .thenReturn(testCoin);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/addCoin").content(FormatHelper.asJsonString(coinDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{'shortName':'testShort','longName':'testLong','exchange':null,'amount':0,'timeAdded':null," +
                "'priceBought':0.0,'portfolioId':0}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void testGettingPortfolioCoins() throws Exception {
        PortfolioDTO portfolioDTO = new PortfolioDTO();
        portfolioDTO.setPortfolioId(2L);
        Portfolio portfolio = new Portfolio();

        Mockito.when(portfolioService.getPortfolioById(Mockito.anyLong())).thenReturn(portfolio);

        Map<String, String[]> response = new HashMap<>();
        response.put("Litecoin",
                new String[]{"LTC",
                        "215", "189", "4%"});
        response.put("VeChain",
                new String[]{"VEN",
                        "4", "2", "25%"});
        Mockito.when(portfolioService.getAllCoins(any(Portfolio.class))).thenReturn(response);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/getPortfolioCoins").content(FormatHelper.asJsonString(portfolioDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"VeChain\":[\"VEN\",\"4\",\"2\",\"25%\"],\"Litecoin\":[\"LTC\",\"215\",\"189\",\"4%\"]}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }


    @Test
    public void testGettingGraphData() throws Exception {
        PortfolioDTO portfolioDTO = new PortfolioDTO();
        portfolioDTO.setPortfolioId(2L);
        Portfolio portfolio = new Portfolio();
        portfolio.setName("testName");
        Set<PortfolioHistory> histories = new HashSet<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse("23/09/2007");
        long time = date.getTime();
        Timestamp t = new Timestamp(time);
        PortfolioHistory h1 = new PortfolioHistory();
        h1.setPortfolio(portfolio);
        h1.setValue(55.5);
        h1.setTime(t);
        histories.add(h1);
        portfolio.setHistories(histories);
        Mockito.when(portfolioService.getPortfolioBy(Mockito.anyLong())).thenReturn(portfolio);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/getGraphData").content(FormatHelper.asJsonString(portfolioDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"2007-09-22T21:00:00.000+0000\":55.5}";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void testGettingAllCoins() throws Exception {
        List<CoinNames> names = new ArrayList<>();
        CoinNames c1 = new CoinNames();
        c1.setShortname("BTC");
        c1.setLongname("Bitcoin");
        CoinNames c2 = new CoinNames();
        c2.setShortname("LTC");
        c2.setLongname("Litecoin");
        names.add(c1);
        names.add(c2);
        Mockito.when(coinService.getAllCoins()).thenReturn(names);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/getAllCoins")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"id\":0,\"shortname\":\"BTC\",\"longname\":\"Bitcoin\"}," +
                "{\"id\":0,\"shortname\":\"LTC\",\"longname\":\"Litecoin\"}]";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void testCalculatingProfit() throws Exception {
        PortfolioDTO p = new PortfolioDTO();
        p.setPortfolioId(1L);
        Mockito.when(requestService.getProfitForPortfolio(Mockito.anyLong())).thenReturn(145.6);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/getProfit")
                .content(FormatHelper.asJsonString(p)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "145.6";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }

    @Test
    public void testCalculatingTotal() throws Exception {
        PortfolioDTO p = new PortfolioDTO();
        p.setPortfolioId(1L);
        Mockito.when(requestService.getTotalPriceForPortfolio(Mockito.anyLong())).thenReturn(4500.5);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/getTotal")
                .content(FormatHelper.asJsonString(p)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        String expected = "4500.5";
        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }
}

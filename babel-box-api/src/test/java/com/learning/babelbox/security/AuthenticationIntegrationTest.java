package com.learning.babelbox.security;

import com.learning.babelbox.mocks.BaseRepositoryMock;
import com.learning.babelbox.platform.UUIDProviderMock;
import com.learning.babelbox.security.builders.UserBuilder;
import com.learning.babelbox.mocks.UserRepositoryMock;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.Cookie;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SecurityTestConfiguration.class })
@WebAppConfiguration
@EnableWebMvc
public class AuthenticationIntegrationTest {

    private static final String API_LOGIN = "/api/login";
    private static final String API_AUTH_REQUIRED = "/api/languages";

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Autowired
    private UserRepositoryMock userRepositoryMock;
    @Autowired
    private UUIDProviderMock uuidProviderMock;

    private static final String EXPECTED_UUID = "753f99e4-cb2f-43c3-be97-23012ea6f920";
    private static final String EXPECTED_UNAUTHENTICATED_JSON = "{\"message\":\"Missing/Invalid token.\"}";

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
        uuidProviderMock.setUuid(EXPECTED_UUID);
        wac.getBeansOfType(BaseRepositoryMock.class)
                .values().forEach(BaseRepositoryMock::reset);
    }

    private MockHttpServletRequestBuilder requestBuilder(HttpMethod method, String url) {
        return MockMvcRequestBuilders.request(method, url)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

    @Test
    public void it_should_login_user_WHEN_user_is_loging_with_valid_credentials() throws Exception {

        //Given
        String email = "test@babelbox.com";
        String password = "password";
        userRepositoryMock.save(UserBuilder.buildActiveUserFrom(email, password));
        String requestPayload = new JSONObject().put("username", email).put("password", password).toString();
        MockHttpServletRequestBuilder requestBuilder = requestBuilder(HttpMethod.POST, API_LOGIN).content(requestPayload);

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(204);
        Assertions.assertThat(userRepositoryMock.findAll().get(0).getToken()).isEqualTo(EXPECTED_UUID);
        Cookie cookie = result.getResponse().getCookie("Bearer");
        Assertions.assertThat(cookie.getValue()).isEqualTo(EXPECTED_UUID);
    }

    @Test
    public void it_should_not_login_user_WHEN_username_is_wrong() throws Exception {

        //Given
        String email = "test@babelbox.com";
        String password = "password";
        userRepositoryMock.save(UserBuilder.buildActiveUserFrom(email, password));
        String requestPayload = new JSONObject().put("username", "wrong").toString();
        MockHttpServletRequestBuilder requestBuilder = requestBuilder(HttpMethod.POST, API_LOGIN).content(requestPayload);

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(400);
        Assertions.assertThat(userRepositoryMock.findAll().get(0).getToken()).isNull();
        Assertions.assertThat(result.getResponse().getCookie("Bearer")).isNull();
    }

    @Test
    public void it_should_not_login_user_WHEN_password_is_wrong() throws Exception {

        //Given
        String email = "test@babelbox.com";
        String password = "password";
        userRepositoryMock.save(UserBuilder.buildActiveUserFrom(email, password));
        String requestPayload = new JSONObject().put("username", email).put("password", "wrong").toString();
        MockHttpServletRequestBuilder requestBuilder = requestBuilder(HttpMethod.POST, API_LOGIN).content(requestPayload);

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(400);
        Assertions.assertThat(userRepositoryMock.findAll().get(0).getToken()).isNull();
        Assertions.assertThat(result.getResponse().getCookie("Bearer")).isNull();
    }

    @Test
    public void it_should_not_login_user_WHEN_user_is_not_active() throws Exception {

        //Given
        String email = "test@babelbox.com";
        String password = "password";
        userRepositoryMock.save(UserBuilder.buildDisabledUserFrom(email, password));
        String requestPayload = new JSONObject().put("username", email).put("password", "password").toString();
        MockHttpServletRequestBuilder requestBuilder = requestBuilder(HttpMethod.POST, API_LOGIN).content(requestPayload);

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(400);
        Assertions.assertThat(userRepositoryMock.findAll().get(0).getToken()).isNull();
        Assertions.assertThat(result.getResponse().getCookie("Bearer")).isNull();
    }

    @Test
    public void it_should_authenticate_user_WHEN_request_has_valid_token() throws Exception {

        //Given
        String email = "test@babelbox.com";
        String password = "password";
        String token = "token";
        userRepositoryMock.save(UserBuilder.buildActiveUserFrom(email, password, token));
        MockHttpServletRequestBuilder requestBuilder =
                requestBuilder(HttpMethod.GET, API_AUTH_REQUIRED).cookie(new Cookie("Bearer", token));

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    public void it_should_not_authenticate_user_WHEN_request_has_wrong_token() throws Exception {

        //Given
        String email = "test@babelbox.com";
        String password = "password";
        String token = "token";
        userRepositoryMock.save(UserBuilder.buildActiveUserFrom(email, password, token));
        MockHttpServletRequestBuilder requestBuilder =
                requestBuilder(HttpMethod.GET, API_AUTH_REQUIRED).cookie(new Cookie("Bearer", "wrong_token"));

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(401);
        Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(EXPECTED_UNAUTHENTICATED_JSON);
    }

    @Test
    public void it_should_not_authenticate_user_WHEN_request_has_not_token() throws Exception {

        //Given
        MockHttpServletRequestBuilder requestBuilder = requestBuilder(HttpMethod.GET, API_AUTH_REQUIRED);

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(401);
        Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(EXPECTED_UNAUTHENTICATED_JSON);
    }

    @Test
    public void it_should_not_authenticate_user_WHEN_user_is_disabled() throws Exception {

        //Given
        String email = "test@babelbox.com";
        String password = "password";
        String token = "token";
        userRepositoryMock.save(UserBuilder.buildDisabledUserFrom(email, password, token));
        MockHttpServletRequestBuilder requestBuilder =
                requestBuilder(HttpMethod.GET, API_AUTH_REQUIRED).cookie(new Cookie("Bearer", token));

        //When
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        //Then
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(401);
        Assertions.assertThat(result.getResponse().getContentAsString()).isEqualTo(EXPECTED_UNAUTHENTICATED_JSON);
    }
 }

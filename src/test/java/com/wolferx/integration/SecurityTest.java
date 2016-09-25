package com.weshare.integration;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {Application.class, SecurityTest.SecurityTestConfig.class})
//@WebAppConfiguration
//@IntegrationTest({"server.port:0", "management.port=0"})
public class SecurityTest {
    /*
    private static final String X_AUTH_USERNAME = "X-Auth-Username";
    private static final String X_AUTH_PASSWORD = "X-Auth-Password";
    private static final String X_AUTH_TOKEN = "X-Auth-Token";

    @Value("${local.server.port}")
    int port;

    @Value("${keystore.file}")
    String keystoreFile;

    @Value("${keystore.pass}")
    String keystorePass;

    @Autowired
    AuthService authService;

    @Configuration
    public static class SecurityTestConfig {
        @Bean
        public AuthService authService() {
            return mock(AuthService.class);
        }
    }

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        //RestAssured.keystore(keystoreFile, keystorePass);
        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        Mockito.reset(authService);
    }

    @Test
    public void healthEndpoint_isAvailableToEveryone() {
        when().get("/health").
            then().statusCode(HttpStatus.OK.value()).body("status", equalTo("UP"));
    }

    @Test
    public void metricsEndpoint_withoutBackendAdminCredentials_returnsUnauthorized() {
        when().get("/metrics").
            then().statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void metricsEndpoint_withInvalidBackendAdminCredentials_returnsUnauthorized() {
        String username = "test_user_2";
        String password = "InvalidPassword";
        given().header(X_AUTH_USERNAME, username).header(X_AUTH_PASSWORD, password).
            when().get("/metrics").
            then().statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void metricsEndpoint_withCorrectBackendAdminCredentials_returnsOk() {
        String username = "backend_admin";
        String password = "remember_to_change_me_by_external_property_on_deploy";
        given().header(X_AUTH_USERNAME, username).header(X_AUTH_PASSWORD, password).
            when().get("/metrics").
            then().statusCode(HttpStatus.OK.value());
    }

    @Test
    public void authenticate_withoutPassword_returnsUnauthorized() {
        given().header(X_AUTH_USERNAME, "SomeUser").
            when().post(RouteConfig.AUTH_URL).
            then().statusCode(HttpStatus.UNAUTHORIZED.value());

        BDDMockito.verifyNoMoreInteractions(authService);
    }

    @Test
    public void authenticate_withoutUsername_returnsUnauthorized() {
        given().header(X_AUTH_PASSWORD, "SomePassword").
            when().post(RouteConfig.AUTH_URL).
            then().statusCode(HttpStatus.UNAUTHORIZED.value());

        BDDMockito.verifyNoMoreInteractions(authService);
    }

    @Test
    public void authenticate_withoutUsernameAndPassword_returnsUnauthorized() {
        when().post(RouteConfig.AUTH_URL).
            then().statusCode(HttpStatus.UNAUTHORIZED.value());

        BDDMockito.verifyNoMoreInteractions(authService);
    }

    @Test
    public void authenticate_withInvalidUsernameOrPassword_returnsUnauthorized() {
        String username = "test_user_2";
        String password = "InvalidPassword";

        BDDMockito.when(authService.authByPassword(anyString(), anyString())).
            thenThrow(new BadCredentialsException("Invalid Credentials"));

        given().header(X_AUTH_USERNAME, username).header(X_AUTH_PASSWORD, password).
            when().post(RouteConfig.AUTH_URL).
            then().statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void gettingStuff_withoutToken_returnsUnauthorized() {
        when().get(RouteConfig.STUFF_URL).
            then().statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void gettingStuff_withInvalidToken_returnsUnathorized() {
        given().header(X_AUTH_TOKEN, "InvalidToken").
            when().get(RouteConfig.STUFF_URL).
            then().statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void gettingStuff_withValidToken_returnsData() {
        String generatedToken = authenticateByUsernameAndPasswordAndGetToken();

        given().header(X_AUTH_TOKEN, generatedToken).
            when().get(RouteConfig.STUFF_URL).
            then().statusCode(HttpStatus.OK.value());
    }

    private String authenticateByUsernameAndPasswordAndGetToken() {
        String username = "test_user_2";
        String password = "ValidPassword";

        authenticationWithToken.setToken("hello_token");

        BDDMockito.when(authService.authByPassword(eq(username), eq(password))).
            thenReturn(authenticationWithToken);

        ValidatableResponse validatableResponse = given().header(X_AUTH_USERNAME, username).
            header(X_AUTH_PASSWORD, password).
            when().post(RouteConfig.AUTH_URL).
            then().statusCode(HttpStatus.OK.value());
        String generatedToken = authenticationWithToken.getToken();
        validatableResponse.body("token", equalTo(generatedToken));

        return generatedToken;
    }
    */

}

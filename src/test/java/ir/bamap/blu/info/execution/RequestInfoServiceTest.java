package ir.bamap.blu.info.execution;

import ir.bamap.blu.info.execution.service.RequestInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(classes = RequestInfoServiceTest.class)
public class RequestInfoServiceTest {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RequestInfoServiceTest.class);
    private final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private final RequestInfoService service = new RequestInfoService(request);

    @BeforeEach
    public void setup() {
        Mockito.when(request.getHeader("Authorization"))
                .thenReturn(getDefaultAuthorizationToken());
        Mockito.when(request.getRequestURI())
                .thenReturn("/temp");
    }

    @Test
    void testGetIdOrNull() {
        log.info("Testing RequestInfoService.getId method ...");

        var requestId = UUID.randomUUID().toString();
        Mockito.when(request.getHeader("X-Request-ID")).thenReturn(requestId);

        assertEquals("RequestId test not passed", requestId, service.getId());
    }

    @Test
    void testGetCurrentUsernameOrNull() {
        log.info("Testing RequestInfoService.getUsername method ...");
        assertEquals("Incorrect username", "service-account-prepaid", service.getCurrentUsername());
    }

    @Test
    void testGetAuthorizationToken() {
        log.info("Testing RequestInfoService.getAuthorizationToken method ...");
        assertEquals("Incorrect authorizationToken", getDefaultAuthorizationToken(), service.getAuthorizationTokenOrNull());
    }

    private String getDefaultAuthorizationToken() {
        return "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIwbGdHblVzY1UwTmh3dGN5TFhDOEJmV1psaW5tRVJvRGtTX3praFJNeUlRIn0.eyJleHAiOjE3NDMzNzI5MTksImlhdCI6MTc0MzMzNjkyMCwianRpIjoiNjQ2ZGY5NjUtOTQ3Ny00MjFmLWIyZWUtMzI2YWM5MWU4Mjg2IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTgxL3JlYWxtcy9DYXJkIiwiYXVkIjpbInJlYWxtLW1hbmFnZW1lbnQiLCJhY2NvdW50Il0sInN1YiI6IjMwY2MzY2E1LTc1MzAtNDZmOC1hN2E5LTNmZmJkYzU4ZmQ5MyIsInR5cCI6IkJlYXJlciIsImF6cCI6InByZXBhaWQiLCJhY3IiOiIxIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vdWF0LnNkYjI0Ny5jb20iXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJkZWZhdWx0LXJvbGVzLWNhcmQiXX0sInJlc291cmNlX2FjY2VzcyI6eyJyZWFsbS1tYW5hZ2VtZW50Ijp7InJvbGVzIjpbIm1hbmFnZS11c2VycyIsInZpZXctdXNlcnMiLCJxdWVyeS1ncm91cHMiLCJxdWVyeS11c2VycyJdfSwicHJlcGFpZCI6eyJyb2xlcyI6WyJ1bWFfcHJvdGVjdGlvbiJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJlbWFpbCBwcm9maWxlIiwiY2xpZW50SG9zdCI6IjE3Mi4xOC4wLjEiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6InNlcnZpY2UtYWNjb3VudC1wcmVwYWlkIiwiY2xpZW50QWRkcmVzcyI6IjE3Mi4xOC4wLjEiLCJjbGllbnRfaWQiOiJwcmVwYWlkIn0.aJoyEY6DT2vp35XqparLgpNxRh1dN7axs9mu06p7xYF_loQ-qlBCjkphfIDa_YiPlVLBjhONqSHBGy34_wFWvf85Bf08Ej1POVGtEsToIheH8xbhEby96_mpw8iRZaiP5pefPMvlB-sKC7bbueLnAsJloeveQPSqMmhibiy1WxZwCB_QM0qYWWz736TW1WMBgoN_mM_g5WVg0auoeHVOr43IX0SzaKFXKxP_rxS54upNJbaZRZg13tNuk_09V66WnoHd85A8fMV14WW_3HmmEKNz8x320ATtRJ2hPciaeMpVEQj5fSv_-2sjCNNYRYZN_dakdtngn99iV2WaFOZfjw";
    }
}

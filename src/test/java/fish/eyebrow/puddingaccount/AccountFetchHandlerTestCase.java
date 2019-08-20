package fish.eyebrow.puddingaccount;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccountFetchHandlerTestCase {

    @Mock
    private RoutingContext mockContext;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private HttpServerResponse mockResponse;

    private AccountFetchHandler accountFetchHandler;


    @BeforeEach
    void setUp() {
        accountFetchHandler = new AccountFetchHandler();
        Mockito.when(mockContext.response()).thenReturn(mockResponse);
    }


    @Test
    void fetchingOpenSession() {
        accountFetchHandler.handle(mockContext);

        Mockito.verify(mockContext.response()).setStatusCode(200);
        Mockito.verify(mockContext.response()).end();
    }
}

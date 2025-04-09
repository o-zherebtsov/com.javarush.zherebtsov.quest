package quest.servlets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import quest.models.GameState;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class StartServletTest {
    private StartServlet startServlet;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private RequestDispatcher requestDispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startServlet = new StartServlet();

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    void testDoGetWithNewGameState() throws Exception {
        when(session.getAttribute("gameState")).thenReturn(null);

        startServlet.doGet(request, response);

        verify(session).setAttribute(eq("gameState"), any(GameState.class));
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoGetWithExistingGameState() throws Exception {
        GameState gameState = new GameState();
        when(session.getAttribute("gameState")).thenReturn(gameState);

        startServlet.doGet(request, response);

        verify(session, never()).setAttribute(eq("gameState"), any(GameState.class));
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPost() throws Exception {
        when(request.getParameter("playerName")).thenReturn("TestPlayer");
        GameState gameState = new GameState();
        when(session.getAttribute("gameState")).thenReturn(gameState);

        startServlet.doPost(request, response);

        assertEquals("TestPlayer", gameState.getPlayerName());
        assertEquals("scene1", gameState.getCurrentScene());
        verify(response).sendRedirect(anyString());
    }
}

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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameServletTest {
    private GameServlet gameServlet;

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
        gameServlet = new GameServlet();

        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    void testDoGetWithoutGameState() throws Exception {
        when(session.getAttribute("gameState")).thenReturn(null);

        gameServlet.doGet(request, response);

        verify(response).sendRedirect(anyString());
    }

    @Test
    void testDoGetWithGameState() throws Exception {
        GameState gameState = new GameState();
        when(session.getAttribute("gameState")).thenReturn(gameState);

        gameServlet.doGet(request, response);

        verify(request).setAttribute("gameState", gameState);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void testDoPostRestart() throws Exception {
        when(request.getParameter("action")).thenReturn("restart");
        GameState gameState = new GameState();
        when(session.getAttribute("gameState")).thenReturn(gameState);

        gameServlet.doPost(request, response);

        assertEquals("start", gameState.getCurrentScene());
        assertEquals(1, gameState.getGamesPlayed());
        verify(response).sendRedirect(anyString());
    }

    @Test
    void testDoPostScene1Left() throws Exception {
        GameState gameState = new GameState();
        gameState.setCurrentScene("scene1");
        when(session.getAttribute("gameState")).thenReturn(gameState);
        when(request.getParameter("action")).thenReturn("left");

        gameServlet.doPost(request, response);

        assertEquals("scene2", gameState.getCurrentScene());
        verify(response).sendRedirect(anyString());
    }

    @Test
    void testDoPostScene1Right() throws Exception {
        GameState gameState = new GameState();
        gameState.setCurrentScene("scene1");
        when(session.getAttribute("gameState")).thenReturn(gameState);
        when(request.getParameter("action")).thenReturn("right");

        gameServlet.doPost(request, response);

        assertEquals("scene3", gameState.getCurrentScene());
        verify(response).sendRedirect(anyString());
    }

    @Test
    void testDoPostScene2Fight() throws Exception {
        GameState gameState = new GameState();
        gameState.setCurrentScene("scene2");
        when(session.getAttribute("gameState")).thenReturn(gameState);
        when(request.getParameter("action")).thenReturn("fight");

        gameServlet.doPost(request, response);

        assertEquals("win", gameState.getCurrentScene());
        assertTrue(gameState.isGameWon());
        verify(response).sendRedirect(anyString());
    }

    @Test
    void testDoPostScene2Run() throws Exception {
        GameState gameState = new GameState();
        gameState.setCurrentScene("scene2");
        when(session.getAttribute("gameState")).thenReturn(gameState);
        when(request.getParameter("action")).thenReturn("run");

        gameServlet.doPost(request, response);

        assertEquals("lose", gameState.getCurrentScene());
        assertFalse(gameState.isGameWon());
        verify(response).sendRedirect(anyString());
    }

    @Test
    void testDoPostScene3Open() throws Exception {
        GameState gameState = new GameState();
        gameState.setCurrentScene("scene3");
        when(session.getAttribute("gameState")).thenReturn(gameState);
        when(request.getParameter("action")).thenReturn("open");

        gameServlet.doPost(request, response);

        assertEquals("win", gameState.getCurrentScene());
        assertTrue(gameState.isGameWon());
        verify(response).sendRedirect(anyString());
    }

    @Test
    void testDoPostScene3Ignore() throws Exception {
        GameState gameState = new GameState();
        gameState.setCurrentScene("scene3");
        when(session.getAttribute("gameState")).thenReturn(gameState);
        when(request.getParameter("action")).thenReturn("ignore");

        gameServlet.doPost(request, response);

        assertEquals("lose", gameState.getCurrentScene());
        assertFalse(gameState.isGameWon());
        verify(response).sendRedirect(anyString());
    }
}

package quest.servlets;

import quest.models.GameState;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        GameState gameState = (GameState) session.getAttribute("gameState");

        if (gameState == null) {
            resp.sendRedirect(req.getContextPath() + "/start");
            return;
        }

        req.setAttribute("gameState", gameState);
        req.getRequestDispatcher("/WEB-INF/views/game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        GameState gameState = (GameState) session.getAttribute("gameState");

        if ("restart".equals(action)) {
            gameState.setCurrentScene("start");
            gameState.incrementGamesPlayed();
            resp.sendRedirect(req.getContextPath() + "/start");
            return;
        }

        // Логика перехода между сценами
        switch (gameState.getCurrentScene()) {
            case "scene1":
                if ("left".equals(action)) {
                    gameState.setCurrentScene("scene2");
                } else if ("right".equals(action)) {
                    gameState.setCurrentScene("scene3");
                }
                break;
            case "scene2":
                if ("fight".equals(action)) {
                    gameState.setCurrentScene("win");
                    gameState.setGameWon(true);
                } else if ("run".equals(action)) {
                    gameState.setCurrentScene("lose");
                    gameState.setGameWon(false);
                }
                break;
            case "scene3":
                if ("open".equals(action)) {
                    gameState.setCurrentScene("win");
                    gameState.setGameWon(true);
                } else if ("ignore".equals(action)) {
                    gameState.setCurrentScene("lose");
                    gameState.setGameWon(false);
                }
                break;
        }

        resp.sendRedirect(req.getContextPath() + "/game");
    }
}

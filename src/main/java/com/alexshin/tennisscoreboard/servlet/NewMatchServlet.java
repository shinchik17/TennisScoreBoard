package com.alexshin.tennisscoreboard.servlet;

import com.alexshin.tennisscoreboard.model.MatchModel;
import com.alexshin.tennisscoreboard.service.OngoingMatchesService;
import com.alexshin.tennisscoreboard.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.alexshin.tennisscoreboard.util.ParseParams.*;


@WebServlet(name = "new-match-servlet", urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final Logger logger = LogManager.getLogger();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Received GET on /new-match. Forwarded to new-match.jsp");
        req.getRequestDispatcher(JspHelper.getPath("new-match")).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Received POST on /new-match");

        try {
            String player1Name = parsePlayerName(req.getParameter("player1-name"));
            String player2Name = parsePlayerName(req.getParameter("player2-name"));


            MatchModel match = ongoingMatchesService.createNewMatch(player1Name, player2Name);
            logger.info("Got match with uuid=" + match.getUuid());

            logger.info("Redirect to /match-score");
            String redirectURL = "%s/match-score?uuid=%s".formatted(req.getContextPath(), match.getUuid());
            resp.sendRedirect(redirectURL);

        } catch (Exception e) {
            logger.error(e.getMessage());
            req.setAttribute("error_info", e.getMessage());
            req.getRequestDispatcher(JspHelper.getPath("error")).forward(req, resp);
        }



    }
}

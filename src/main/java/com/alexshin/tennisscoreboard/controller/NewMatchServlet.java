package com.alexshin.tennisscoreboard.controller;

import com.alexshin.tennisscoreboard.model.dto.MatchDTO;
import com.alexshin.tennisscoreboard.model.entity.Match;
import com.alexshin.tennisscoreboard.service.OngoingMatchesService;
import com.alexshin.tennisscoreboard.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;

import java.io.IOException;

import static com.alexshin.tennisscoreboard.util.ParseParams.*;


@WebServlet(name = "new-match-servlet", urlPatterns = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final ModelMapper mapper = new ModelMapper();
    private final Logger logger = LogManager.getLogger();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(JspHelper.getPath("new-match")).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Received POST on /new-match");
        String player1Name = parsePlayerName(req.getParameter("player1Name"));
        String player2Name = parsePlayerName(req.getParameter("player2Name"));


        MatchDTO match = ongoingMatchesService.createNewMatch(player1Name, player2Name);
        logger.info("Got match with uuid=" + match.getUuid());

        String redirectURL = "/match-score?uuid=%s".formatted(match.getUuid());
        resp.sendRedirect(redirectURL);
        logger.info("Send redirect to /match-score");



    }
}

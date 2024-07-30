package com.alexshin.tennisscoreboard.controller;

import com.alexshin.tennisscoreboard.exception.NoSuchMatchException;
import com.alexshin.tennisscoreboard.mapper.MatchMapper;
import com.alexshin.tennisscoreboard.model.dto.MatchDTO;
import com.alexshin.tennisscoreboard.service.MatchScoreCalculationService;
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
import java.util.UUID;

import static com.alexshin.tennisscoreboard.util.ParseParams.parsePlayerNum;
import static com.alexshin.tennisscoreboard.util.ParseParams.parseUUID;

@WebServlet(name = "match-score-servlet", urlPatterns = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
    private final Logger logger = LogManager.getLogger();
    private final MatchMapper mapper = new MatchMapper();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        logger.info("Received GET on /match-score");
        try {
            UUID uuid = parseUUID(req.getParameter("uuid"));
            MatchDTO match = ongoingMatchesService.getCurrentMatch(uuid);

            req.setAttribute("matchScore", mapper.toScoreModel(match));
            req.getRequestDispatcher(JspHelper.getPath("match-score")).forward(req, resp);
            logger.info("Forwarded to match-score.jsp");

        } catch (NoSuchMatchException | IllegalArgumentException e) {
            logger.error(e);
            // TODO: error.jsp
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Received POST on /match-score");
        try {

            UUID uuid = parseUUID(req.getParameter("uuid"));
            int wonPointPlayerNum = parsePlayerNum(req.getParameter("playerWonNum"));
            MatchDTO match = ongoingMatchesService.getCurrentMatch(uuid);
            logger.info("Got match with uuid=" + match.getUuid());

            matchScoreCalculationService.updateMatchScore(match, wonPointPlayerNum);
            logger.info("Add point to player " + wonPointPlayerNum);

            if (MatchScoreCalculationService.isMatchFinished(match)) {
                ongoingMatchesService.saveMatch(match);

                req.setAttribute("matchScore", mapper.toScoreModel(match));

                req.getRequestDispatcher(JspHelper.getPath("match-end")).forward(req, resp);
                logger.info("Forwarded to match-end.jsp");
                return;
            }

            String redirectURL = "/match-score?uuid=%s".formatted(match.getUuid());
            resp.sendRedirect(req.getContextPath() + redirectURL);

            logger.info("Redirected to /match-score");

        } catch (NoSuchMatchException | IllegalArgumentException e) {
            // TODO: error.jsp
            throw new RuntimeException(e);
        }


    }
}

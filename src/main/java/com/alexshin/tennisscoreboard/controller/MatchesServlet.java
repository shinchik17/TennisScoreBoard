package com.alexshin.tennisscoreboard.controller;

import com.alexshin.tennisscoreboard.exception.NoSuchMatchException;
import com.alexshin.tennisscoreboard.mapper.MatchMapper;
import com.alexshin.tennisscoreboard.model.entity.Match;
import com.alexshin.tennisscoreboard.service.FinishedMatchesPersistenceService;
import com.alexshin.tennisscoreboard.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.alexshin.tennisscoreboard.util.ParseParams.*;

@WebServlet(name = "matches-servlet", urlPatterns = "/matches")
public class MatchesServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger();
    private final MatchMapper mapper = new MatchMapper();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Received GET on /matches");

        try {
            int pageNum = parsePageNum(req.getParameter("page"));
            Optional<String> optPlayerName = parsePlayerFilter(req.getParameter("filter_by_player"));

            logger.info("Page = %d, filter = '%s'".formatted(pageNum, optPlayerName.orElse("None")));

            List<Match> matches;
            if (optPlayerName.isPresent()) {
                matches = finishedMatchesPersistenceService.findMatches(pageNum, optPlayerName.get());
                req.setAttribute("filter", optPlayerName.get());
            } else {
                matches = finishedMatchesPersistenceService.findMatches(pageNum);
            }

            logger.info("Got matches list, size = %d".formatted(matches.size()));

            req.setAttribute("matches", matches);
            req.setAttribute("page", pageNum);
            req.setAttribute("maxPage", finishedMatchesPersistenceService.PAGE_SIZE);

            req.getRequestDispatcher(JspHelper.getPath("matches")).forward(req, resp);
            logger.info("Forwarded to match-score.jsp");

        } catch (NoSuchMatchException | IllegalArgumentException e) {
            // TODO: error.jsp
            throw new RuntimeException(e);
        }


    }
}

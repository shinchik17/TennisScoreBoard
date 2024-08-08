package com.alexshin.tennisscoreboard.servlet;

import com.alexshin.tennisscoreboard.exception.service.NoSuchMatchException;
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
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("Received GET on /matches");

        try {

            int pageNum = parsePageNum(req.getParameter("page"));
            Optional<String> optPlayerName = parsePlayerFilter(req.getParameter("filter_by_player_name"));

            // костыль, чтобы сбрасывать value в input filter by name
            if (req.getParameter("reset") != null) {
                optPlayerName = Optional.empty();
            }

            logger.info("Page = %d, filter = '%s'".formatted(pageNum, optPlayerName.orElse("None")));

            List<Match> matches;
            if (optPlayerName.isPresent()) {
                matches = finishedMatchesPersistenceService.findMatches(pageNum, optPlayerName.get());
                req.setAttribute("filter_by_player_name", optPlayerName.get());
            } else {
                matches = finishedMatchesPersistenceService.findMatches(pageNum);
            }

            logger.info("Got matches list, size = %d".formatted(matches.size()));

            req.setAttribute("matches", matches);
            req.setAttribute("page", pageNum);
            req.setAttribute("max_row_num", finishedMatchesPersistenceService.PAGE_SIZE);

            logger.info("Forward to matches.jsp");
            req.getRequestDispatcher(JspHelper.getPath("matches")).forward(req, resp);

        } catch (NoSuchMatchException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            req.setAttribute("error_info", e.getMessage());
            req.getRequestDispatcher(JspHelper.getPath("error")).forward(req, resp);
        }


    }
}

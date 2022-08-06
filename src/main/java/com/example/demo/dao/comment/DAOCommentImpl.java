package com.example.demo.dao.comment;

import com.example.demo.dao.PostgresFactory;
import com.example.demo.model.Comment;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCommentImpl implements DAOComment {
    private static final Logger LOGGER = Logger.getLogger(DAOCommentImpl.class);
    private static final String GET_COMMENT_LIST =
            "SELECT *" +
            " FROM lab3_ko_comments";

    private static final String GET_COMMENT =
            "SELECT *" +
            " FROM lab3_ko_comments" +
            " WHERE id = ?";

    private static final String INSERT_COMMENT =
            "INSERT INTO lab3_ko_comments" +
            " VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_COMMENT =
            "UPDATE lab3_ko_comments" +
            " SET id = ?, user_id = ?, title = ?," +
            " comment = ?, rating = ?" +
            " WHERE id = ?";

    private static final String DELETE_COMMENT =
            "DELETE FROM lab3_ko_comments" +
            " WHERE id = ?";
    
    @Override
    public List<Comment> getCommentList() {
        List<Comment> commentList = new ArrayList<>();
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(GET_COMMENT_LIST)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                commentList.add(parseComment(rs));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error("Could not get comment list.\n Message: " + e.getLocalizedMessage());
        }
        return commentList;
    }

    @Override
    public Comment getComment(int id) {
        Comment comment = null;
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(GET_COMMENT)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                comment = parseComment(rs);
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error("Could not get comment.\n Message: " + e.getLocalizedMessage());
        }
        return comment;
    }

    @Override
    public boolean insertComment(Comment comment) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(INSERT_COMMENT)) {

            ps.setInt(1, comment.getId());
            ps.setInt(2, comment.getUserId());
            ps.setString(3, comment.getTitle());
            ps.setString(4, comment.getComment());
            ps.setShort(5, comment.getRating());

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not insert comment.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean updateComment(Comment comment) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(UPDATE_COMMENT)) {

            ps.setInt(1, comment.getId());
            ps.setInt(2, comment.getUserId());
            ps.setString(3, comment.getTitle());
            ps.setString(4, comment.getComment());
            ps.setShort(5, comment.getRating());
            ps.setInt(6, comment.getId());

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not update comment.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public boolean deleteComment(int id) {
        try (PreparedStatement ps = PostgresFactory.connect()
                .prepareStatement(DELETE_COMMENT)) {

            ps.setInt(1, id);

            ps.execute();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Could not delete comment.\n Message: " + e.getLocalizedMessage());
            return false;
        }
    }

    private Comment parseComment(ResultSet rs) {
        Comment comment = null;
        try {
            int id = rs.getInt("id");
            int userId = rs.getInt("user_id");
            String title = rs.getString("title");
            String commentField = rs.getString("comment");
            short rating = rs.getShort("rating");
            comment = new Comment(id, userId, title, commentField, rating);
        } catch (SQLException e) {
            LOGGER.error("Could not parse comment.\n Message: " + e.getLocalizedMessage());
        }

        return comment;
    }
}

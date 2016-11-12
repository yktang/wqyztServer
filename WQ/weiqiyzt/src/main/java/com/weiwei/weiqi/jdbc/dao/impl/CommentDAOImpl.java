package com.weiwei.weiqi.jdbc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.weiwei.weiqi.jdbc.dao.ICommentDAO;
import com.weiwei.weiqi.jdbc.dbmodel.CommentTableJoinBean;

@Service
public class CommentDAOImpl implements ICommentDAO{
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Override
	public List<?> getCommentLimitedNumbersJoinQuery(int announce_id, int n) {
		String sql = "SELECT c1.*, COUNT(cl.id) AS countLike FROM "
				+ "(SELECT cm.*, ctemp.announce_id, ctemp.countComments, ctemp.userName, ctemp.commentGroup, ctemp.usernameGroup FROM "
				+ "(SELECT cs.announce_id, cmc.userName, MIN(cmc.comment_time) AS first_comment, COUNT(cmc.id) AS countComments, "
				+ "GROUP_CONCAT(cmc.comment_content ORDER BY comment_time SEPARATOR ';-;') AS commentGroup, GROUP_CONCAT(cmc.userName ORDER BY comment_time SEPARATOR ';-;') AS usernameGroup "
				+ "FROM dedecmsv57utf8sp1.comment_session cs "
				+ "JOIN (SELECT cm.*, cust.userName "
				+ "FROM dedecmsv57utf8sp1.comment cm JOIN dedecmsv57utf8sp1.customers cust ON cust.id = cm.customer_id ORDER BY session_id, comment_time DESC) cmc "
				+ "ON cs.id = cmc.session_id AND announce_id = ? "
				+ "GROUP BY cs.id) ctemp, dedecmsv57utf8sp1.comment cm "
				+ "WHERE ctemp.first_comment = cm.comment_time "
				+ "ORDER BY comment_time DESC LIMIT " + n +") c1 "
				+ "LEFT JOIN dedecmsv57utf8sp1.comment_like cl ON c1.session_id = cl.session_id AND cl.is_cancelled IS NULL "
				+ "GROUP BY c1.session_id ORDER BY comment_time DESC";
		return jdbcTemplate.query(sql, new Object[]{announce_id}, new BeanPropertyRowMapper<CommentTableJoinBean>());
	}

	@Override
	public List<?> getCommentLimitedNumbersJoinQuery(int n) {
		String sql = "SELECT c1.*, COUNT(cl.id) AS countLike FROM "
				+ "(SELECT cm.*, ctemp.announce_id, ctemp.countComments, ctemp.userName, ctemp.commentGroup, ctemp.usernameGroup FROM "
				+ "(SELECT cs.announce_id, cmc.userName, MIN(cmc.comment_time) AS first_comment, COUNT(cmc.id) AS countComments, "
				+ "GROUP_CONCAT(cmc.comment_content ORDER BY comment_time SEPARATOR ';-;') AS commentGroup, GROUP_CONCAT(cmc.userName ORDER BY comment_time SEPARATOR ';-;') AS usernameGroup "
				+ "FROM dedecmsv57utf8sp1.comment_session cs "
				+ "JOIN (SELECT cm.*, cust.userName "
				+ "FROM dedecmsv57utf8sp1.comment cm JOIN dedecmsv57utf8sp1.customers cust ON cust.id = cm.customer_id ORDER BY session_id, comment_time DESC) cmc "
				+ "ON cs.id = cmc.session_id "
				+ "GROUP BY cs.id) ctemp, dedecmsv57utf8sp1.comment cm "
				+ "WHERE ctemp.first_comment = cm.comment_time "
				+ "ORDER BY comment_time DESC LIMIT " + n +") c1 "
				+ "LEFT JOIN dedecmsv57utf8sp1.comment_like cl ON c1.session_id = cl.session_id AND cl.is_cancelled IS NULL "
				+ "GROUP BY c1.session_id ORDER BY comment_time DESC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<CommentTableJoinBean>());
	}

	@Override
	public List<?> getCommentLimitedNumbersFromStartIdJoinQuery(int announce_id, int start_id, int n) {
		String sql = "SELECT c1.*, COUNT(cl.id) AS countLike FROM "
				+ "(SELECT cm.*, ctemp.announce_id, ctemp.countComments, ctemp.userName, ctemp.commentGroup, ctemp.usernameGroup FROM "
				+ "(SELECT cs.announce_id, cmc.userName, MIN(cmc.comment_time) AS first_comment, COUNT(cmc.id) AS countComments, "
				+ "GROUP_CONCAT(cmc.comment_content ORDER BY comment_time SEPARATOR ';-;') AS commentGroup, GROUP_CONCAT(cmc.userName ORDER BY comment_time SEPARATOR ';-;') AS usernameGroup "
				+ "FROM dedecmsv57utf8sp1.comment_session cs "
				+ "JOIN (SELECT cm.*, cust.userName "
				+ "FROM dedecmsv57utf8sp1.comment cm JOIN dedecmsv57utf8sp1.customers cust ON cust.id = cm.customer_id ORDER BY session_id, comment_time DESC) cmc "
				+ "ON cs.id = cmc.session_id AND announce_id = ? "
				+ "GROUP BY cs.id) ctemp, dedecmsv57utf8sp1.comment cm "
				+ "WHERE ctemp.first_comment = cm.comment_time AND cm.session_id < ? "
				+ "ORDER BY comment_time DESC LIMIT " + n +") c1 "
				+ "LEFT JOIN dedecmsv57utf8sp1.comment_like cl ON c1.session_id = cl.session_id AND cl.is_cancelled IS NULL "
				+ "GROUP BY c1.session_id ORDER BY comment_time DESC";
		return jdbcTemplate.query(sql, new Object[]{announce_id, start_id}, new BeanPropertyRowMapper<CommentTableJoinBean>());
	}

	@Override
	public List<?> getCommentLimitedNumbersFromStartIdJoinQuery(int start_id, int n) {
		String sql = "SELECT c1.*, COUNT(cl.id) AS countLike FROM "
				+ "(SELECT cm.*, ctemp.announce_id, ctemp.countComments, ctemp.userName, ctemp.commentGroup, ctemp.usernameGroup FROM "
				+ "(SELECT cs.announce_id, cmc.userName, MIN(cmc.comment_time) AS first_comment, COUNT(cmc.id) AS countComments, "
				+ "GROUP_CONCAT(cmc.comment_content ORDER BY comment_time SEPARATOR ';-;') AS commentGroup, GROUP_CONCAT(cmc.userName ORDER BY comment_time SEPARATOR ';-;') AS usernameGroup "
				+ "FROM dedecmsv57utf8sp1.comment_session cs "
				+ "JOIN (SELECT cm.*, cust.userName "
				+ "FROM dedecmsv57utf8sp1.comment cm JOIN dedecmsv57utf8sp1.customers cust ON cust.id = cm.customer_id ORDER BY session_id, comment_time DESC) cmc "
				+ "ON cs.id = cmc.session_id "
				+ "GROUP BY cs.id) ctemp, dedecmsv57utf8sp1.comment cm "
				+ "WHERE ctemp.first_comment = cm.comment_time AND cm.session_id < ? "
				+ "ORDER BY comment_time DESC LIMIT " + n +") c1 "
				+ "LEFT JOIN dedecmsv57utf8sp1.comment_like cl ON c1.session_id = cl.session_id AND cl.is_cancelled IS NULL "
				+ "GROUP BY c1.session_id ORDER BY comment_time DESC";
		return jdbcTemplate.query(sql, new Object[]{start_id}, new BeanPropertyRowMapper<CommentTableJoinBean>());
	}
}

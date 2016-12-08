package com.weiwei;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.weiwei.weiqi.jdbc.dao.annoucement.CommentSessionDao;
import com.weiwei.weiqi.jdbc.dbmodel.annoucement.CommentSession;
import com.weiwei.weiqi.service.announcement.api.CommentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeiqiyztApplicationTests {

	@Autowired
	private CommentSessionDao comm;
	
	@Test
	@Transactional
	public void contextLoads() {
		
//		List<CommentSession> commentSessions = comm.findAll();
//		
//		System.out.println(commentSessions.get(0).getComments());
	}

}

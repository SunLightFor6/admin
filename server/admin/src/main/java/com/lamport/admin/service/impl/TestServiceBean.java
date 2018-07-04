package com.lamport.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lamport.admin.mapper.TestMapper;
import com.lamport.admin.po.Address;
import com.lamport.admin.po.Admin;
import com.lamport.admin.po.Enterprise;
import com.lamport.admin.po.FreeListen;
import com.lamport.admin.po.FreeListenBook;
import com.lamport.admin.po.Lesson;
import com.lamport.admin.po.Message;
import com.lamport.admin.po.MessageImg;
import com.lamport.admin.po.MessageLike;
import com.lamport.admin.po.MessageReply;
import com.lamport.admin.po.Refund;
import com.lamport.admin.po.Sorder;
import com.lamport.admin.po.Swiper;
import com.lamport.admin.po.Teacher;
import com.lamport.admin.po.User;
import com.lamport.admin.service.TestService;

@Service
public class TestServiceBean implements TestService {

	@Autowired
	private TestMapper testMapper;
	
	@Override
	public List<Address> selectAddressAll() throws Exception {
		System.out.println("........TestServiceBean........selectAddressAll().......");
		List<Address> list = testMapper.selectAddressAll();
		return list;
	}

	@Override
	public List<Admin> selectAdminAll() throws Exception {
		System.out.println("........TestServiceBean........selectAdminAll().......");
		List<Admin> list = testMapper.selectAdminAll();
		return list;
	}

	@Override
	public List<Enterprise> selectEnterpriseAll() throws Exception {
		System.out.println("........TestServiceBean........selectEnterpriseAll().......");
		List<Enterprise> list = testMapper.selectEnterpriseAll();
		return list;
	}

	@Override
	public List<FreeListen> selectFreeListenAll() throws Exception {
		System.out.println("........TestServiceBean........selectFreeListenAll().......");
		List<FreeListen> list = testMapper.selectFreeListenAll();
		return list;
	}

	@Override
	public List<FreeListenBook> selectFreeListenBookAll() throws Exception {
		System.out.println("........TestServiceBean........selectFreeListenBookAll().......");
		List<FreeListenBook> list = testMapper.selectFreeListenBookAll();
		return list;
	}

	@Override
	public List<Lesson> selectLessonAll() throws Exception {
		System.out.println("........TestServiceBean........selectLessonAll().......");
		List<Lesson> list = testMapper.selectLessonAll();
		return list;
	}

	@Override
	public List<Message> selectMessageAll() throws Exception {
		System.out.println("........TestServiceBean........selectMessageAll().......");
		List<Message> list = testMapper.selectMessageAll();
		return list;
	}

	@Override
	public List<MessageImg> selectMessageImgAll() throws Exception {
		System.out.println("........TestServiceBean........selectMessageImgAll().......");
		List<MessageImg> list = testMapper.selectMessageImgAll();
		return list;
	}

	@Override
	public List<MessageLike> selectMessageLikeAll() throws Exception {
		System.out.println("........TestServiceBean........selectMessageLikeAll().......");
		List<MessageLike> list = testMapper.selectMessageLikeAll();
		return list;
	}

	@Override
	public List<MessageReply> selectMessageReplyAll() throws Exception {
		System.out.println("........TestServiceBean........selectMessageReplyAll().......");
		List<MessageReply> list = testMapper.selectMessageReplyAll();
		return list;
	}

	@Override
	public List<Refund> selectRefundAll() throws Exception {
		System.out.println("........TestServiceBean........selectRefundAll().......");
		List<Refund> list = testMapper.selectRefundAll();
		return list;
	}

	@Override
	public List<Sorder> selectSorderAll() throws Exception {
		System.out.println("........TestServiceBean........selectSorderAll().......");
		List<Sorder> list = testMapper.selectSorderAll();
		return list;
	}
	
	@Override
	public List<Swiper> selectSwiperAll() throws Exception {
		System.out.println("........TestServiceBean........selectSwiperAll().......");
		List<Swiper> list = testMapper.selectSwiperAll();
		return list;
	}

	@Override
	public List<Teacher> selectTeacherAll() throws Exception {
		System.out.println("........TestServiceBean........selectTeacherAll().......");
		List<Teacher> list = testMapper.selectTeacherAll();
		return list;
	}

	@Override
	public List<User> selectUserAll() throws Exception {
		System.out.println("........TestServiceBean........selectUserAll().......");
		List<User> list = testMapper.selectUserAll();
		return list;
	}

}

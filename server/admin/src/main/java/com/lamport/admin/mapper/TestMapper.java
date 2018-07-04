package com.lamport.admin.mapper;

import java.util.List;

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

public interface TestMapper {
	public List<Address> selectAddressAll() throws Exception;
	public List<Admin> selectAdminAll() throws Exception;
	public List<Enterprise> selectEnterpriseAll() throws Exception;
	public List<FreeListen> selectFreeListenAll() throws Exception;
	public List<FreeListenBook> selectFreeListenBookAll() throws Exception;
	public List<Lesson> selectLessonAll() throws Exception;
	public List<Message> selectMessageAll() throws Exception;
	public List<MessageImg> selectMessageImgAll() throws Exception;
	public List<MessageLike> selectMessageLikeAll() throws Exception;
	public List<MessageReply> selectMessageReplyAll() throws Exception;
	public List<Refund> selectRefundAll() throws Exception;
	public List<Sorder> selectSorderAll() throws Exception;
	public List<Swiper> selectSwiperAll() throws Exception;
	public List<Teacher> selectTeacherAll() throws Exception;
	public List<User> selectUserAll() throws Exception;
}

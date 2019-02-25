package com.ljx.tutor_platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljx.tutor_platform.dao.PublishAndFeedBackDao;
import com.ljx.tutor_platform.entity.Contact;
import com.ljx.tutor_platform.entity.Publish;
import com.ljx.tutor_platform.service.PublishAndFeedBackService;

@Service
public class PublishAndFeedBackServiceImpl implements PublishAndFeedBackService{

	@Autowired
	private PublishAndFeedBackDao pfDao;
	@Override
	public boolean addPublish(Publish publish) {
		// TODO Auto-generated method stub
		return pfDao.addPublish(publish);
	}
	@Override
	public List<Publish> findPublishList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return pfDao.findPublishList(map);
	}
	@Override
	public Long getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return pfDao.getTotal(map);
	}
	@Override
	public boolean delPublishByid(String id) {
		// TODO Auto-generated method stub
		return pfDao.delPublishByid(id);
	}
	@Override
	public boolean updatePublishByid(Publish publish) {
		// TODO Auto-generated method stub
		return pfDao.updatePublishByid(publish);
	}
	@Override
	public boolean addFeedback(Contact contact) {
		// TODO Auto-generated method stub
		return pfDao.addFeedback(contact);
	}
	@Override
	public List<Contact> findFeedbacks(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return pfDao.findFeedbacks(map);
	}
	@Override
	public Long getContactTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return pfDao.getContactTotal(map);
	}
	@Override
	public boolean delFeedbackByid(String id) {
		// TODO Auto-generated method stub
		return  pfDao.delFeedbackByid(id);
	}
	@Override
	public List<Contact> showContacts() {
		// TODO Auto-generated method stub
		return pfDao.showContacts();
	}
	@Override
	public List<Publish> showPublish() {
		// TODO Auto-generated method stub
		return pfDao.showPublish();
	}

}

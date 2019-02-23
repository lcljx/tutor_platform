package com.ljx.tutor_platform.dao;

import java.util.List;
import java.util.Map;

import com.ljx.tutor_platform.entity.Contact;
import com.ljx.tutor_platform.entity.Publish;

public interface PublishAndFeedBackDao {

	boolean addPublish(Publish publish);

	List<Publish> findPublishList(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);
	
	Long getContactTotal(Map<String, Object> map);
	
	boolean delPublishByid(String id);

	boolean updatePublishByid(Publish publish);

	boolean addFeedback(Contact contact);

	List<Contact> findFeedbacks(Map<String, Object> map);

	boolean delFeedbackByid(String id);

	List<Contact> showContacts();
	
}

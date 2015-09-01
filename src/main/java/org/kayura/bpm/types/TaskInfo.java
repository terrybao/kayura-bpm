package org.kayura.bpm.types;

import java.util.Date;
import java.util.Properties;

public class TaskInfo {
	
	private Boolean isTodo;
	private String activityId;
	private String activityName;
	private String senderId;
	private String senderName;
	private Date createdTime;
	private Properties variables = new Properties();

}

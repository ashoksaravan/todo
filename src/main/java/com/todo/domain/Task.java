package com.todo.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vinodkumara
 * 
 */
@Document
public class Task implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * taskid.
	 */
	@Id
	private String taskid;

	/**
	 * username.
	 */
	private String username;
	/**
	 * taskname.
	 */
	private String taskname;
	/**
	 * taskstatus.
	 */
	private String taskstatus;
	/**
	 * taskdesc.
	 */
	private String taskdesc;
	/**
	 * priority.
	 */
	private String priority;
	/**
	 * createduser.
	 */
	private String createduser;
	/**
	 * cclist.
	 */
	private String cclist;
	/**
	 * editor.
	 */
	private String editor;
	/**
	 * projectId.
	 */
	private Integer projectId;
	/**
	 * @return the taskid
	 */
	public String getTaskid() {
		return taskid;
	}
	/**
	 * @param taskid the taskid to set
	 */
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the taskname
	 */
	public String getTaskname() {
		return taskname;
	}
	/**
	 * @param taskname the taskname to set
	 */
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	/**
	 * @return the taskstatus
	 */
	public String getTaskstatus() {
		return taskstatus;
	}
	/**
	 * @param taskstatus the taskstatus to set
	 */
	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}
	/**
	 * @return the taskdesc
	 */
	public String getTaskdesc() {
		return taskdesc;
	}
	/**
	 * @param taskdesc the taskdesc to set
	 */
	public void setTaskdesc(String taskdesc) {
		this.taskdesc = taskdesc;
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/**
	 * @return the createduser
	 */
	public String getCreateduser() {
		return createduser;
	}
	/**
	 * @param createduser the createduser to set
	 */
	public void setCreateduser(String createduser) {
		this.createduser = createduser;
	}
	/**
	 * @return the cclist
	 */
	public String getCclist() {
		return cclist;
	}
	/**
	 * @param cclist the cclist to set
	 */
	public void setCclist(String cclist) {
		this.cclist = cclist;
	}
	/**
	 * @return the editor
	 */
	public String getEditor() {
		return editor;
	}
	/**
	 * @param editor the editor to set
	 */
	public void setEditor(String editor) {
		this.editor = editor;
	}
	/**
	 * @return the projectId
	 */
	public Integer getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}

package com.todo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author vinodkumara
 * 
 */
@Document
public class TaskHistory {

	/**
	 * id.
	 */
	@Id
	private String id;
	/**
	 * taskid.
	 */
	private String taskid;
	/**
	 * version.
	 */
	private int version;
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
	 * editor.
	 */
	private String editor;
	
	/**
	 * lastUpdateDt.
	 */
	private String lastUpdateDt;
	/**
	 * @return the lastUpdateDt
	 */
	public String getLastUpdateDt() {
		return lastUpdateDt;
	}
	/**
	 * @param lastUpdateDt the lastUpdateDt to set
	 */
	public void setLastUpdateDt(String lastUpdateDt) {
		this.lastUpdateDt = lastUpdateDt;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
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
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
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

	
}

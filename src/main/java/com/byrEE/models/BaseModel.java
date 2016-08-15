package com.byrEE.models;

import java.util.Date;
import java.io.Serializable;
import javax.persistence.*;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import org.springframework.data.mongodb.core.mapping.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
/**
 * author byrEE
 */

public abstract class BaseModel implements Comparable<BaseModel>,Serializable{

//	@GeneratedValue(strategy=Generation.IDENTITY)
	@Id
	@Field("id")
	private Long id;

	@CreatedDate
	private Date createAt;

	@LastModifiedDate
	private Date updaetAt;

	@Override
	public int compareTo(BaseModel o){
		return this.getId().compareTo(o.getId());
	}

	public boolean equals(Object o){
		if(o==null||o.getClass()!=this.getClass())
			return false;
		return this.getId().equals(((BaseModel) o).getId());
	}

	public int hashCode(){
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public Long getId(){
		return id;
	}

	public void setId(Long _id){
		this.id=_id;
	}

	public Date getCreateAt(){
		return createAt;
	}

	public void setCreateAt(Date date){
		this.createAt=date;
	}

	public Date getUpdateAt(){
		return updaetAt;
	}

	public void setUpdateAt(Date date){
		this.updaetAt=date;
	}
}
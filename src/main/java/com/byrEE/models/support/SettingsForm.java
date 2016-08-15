package com.byrEE.models.support;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.NonNull;
import lombok.Data;

import javax.validation.constraints.NotNull;
/**
 * @author byrEE
 */
@Data
public class SettingsForm{
	
	@NonNull @NotNull
	private String siteName;

	public String getSiteName(){
		return siteName;
	}

	public void setSiteName(String siteName){
		this.siteName=siteName;
	}

	@NonNull
	private String siteSlogan;

	public String getSiteSlogan(){
		return siteSlogan;
	}

	public void setSiteSlogan(String siteSlogan){
		this.siteSlogan=siteSlogan;
	}

	@NonNull
	private Integer pageSize;

	public Integer getPageSize(){
		return pageSize;
	}

	public void setPageSize(Integer pageSize){
		this.pageSize=pageSize;
	}
}
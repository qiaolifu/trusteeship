package com.trusteeship.manage.service.bean.entity;

import com.core.page.BaseParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author Jo
 * @email 124185954@qq.com
 * @date 2020-03-30 17:08:39
 */
@Data
public class TDatabase extends BaseParam implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;  //
	private Integer belongs;  //
	private String user;  //
	private String url;  //
	private String password;  //
	private Date createTime;  //

}

package com.trusteeship.manage.service.bean.page;

import com.core.page.Page;
import lombok.Data;

import java.util.Date;

/**
 * 
 * 
 * @author Jo
 * @email 124185954@qq.com
 * @date 2020-03-30 17:08:39
 */
@Data
public class TDatabaseP extends Page {
	private static final long serialVersionUID = 1L;

	private Integer id; //
	private String url; //
	private String password; //
	private Date createTime; //

}

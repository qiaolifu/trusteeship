package com.trusteeship.manage.service.bean.page;

import java.util.Date;
import lombok.Data;
import com.core.page.Page;

/**
 * 
 * 
 * @author Jo
 * @email 124185954@qq.com
 * @date 2020-03-25 10:19:43
 */
@Data
public class TUserP extends Page {
	private static final long serialVersionUID = 1L;

	private Integer id; //
	private String database; //数据url
	private String user; //用户名
	private String password; //密码
	private String phone;  //联系电话
	private Date effectTime; //到期时间
	private Date vipTime; //VIP到期时间
	private Integer status; //0-失效；1-正常；2-VIP
	private Date createTime; //
	private Date updateTime; //

}

package com.trusteeship.manage.service.bean.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import com.core.page.BaseParam;

/**
 * 
 * 
 * @author Jo
 * @email 124185954@qq.com
 * @date 2020-03-25 10:19:43
 */
@Data
public class TUser extends BaseParam implements Serializable {
    public static final String ADMIN = "woshiqlf";
	public static final String KEY = "197346825";
	private static final long serialVersionUID = 1L;
	public static final Integer INVALID= 0;   //status 失效
	public static final Integer NORMAL = 1;//正常
	public static final Integer VIP= 2;   //status VIP

	private Integer id;  //
	private String user;  //用户名
	private String password;  //密码
	private String phone;  //联系电话
	private Date effectTime;  //VIP到期时间
	private Integer status;  //0-失效；1-正常；2-VIP
	private Date createTime;  //
	private Date updateTime;  //

}

package com.trusteeship.manage.manage.api.controller.game;

import com.core.exception.ApiException;
import com.core.page.R;
import com.trusteeship.manage.manage.api.controller.BaseController;
import com.trusteeship.manage.service.bean.entity.Account;
import com.trusteeship.manage.service.bean.entity.GameRole;
import com.trusteeship.manage.service.bean.entity.TDatabase;
import com.trusteeship.manage.service.common.constants.BizCode;
import com.trusteeship.manage.service.service.itf.TDatabaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.core.utils.StringUtil.encodeRoleName;

@RestController
@RequestMapping(value = "/api/account")
@Api(value = "")
public class AccountController extends BaseController {
    @Autowired
    private TDatabaseService tDatabaseService;


    @PostMapping(value = "/role/list")
    @ApiOperation(value = "获取账号角色")
    public R listRole(HttpServletRequest request, @RequestBody Account account) throws Exception {

        String u = checkToken(request);
        TDatabase database = tDatabaseService.selectByUsername(u);
        if (null == database) throw new ApiException(BizCode.DATABASE_NOT_BINDING);
        //查账号
        String str = "d_taiwan";
        Connection connAccount = ConnectToDatabase(database, str);
        if (null == connAccount) throw new ApiException(BizCode.DATABASE_NOT_EXIST);
        String uid = listAccount(connAccount, account);
        if (null == uid || 0 == uid.length()) throw new ApiException(BizCode.UNKNOWN_ERROR);
        //查角色
        str = "taiwan_cain";
        Connection connRole = ConnectToDatabase(database, str);
        List<GameRole> roles = roleList(connRole, uid);
        for (GameRole role : roles){
            role.setCharacterName(encodeRoleName(role.getCharacterName()));
        }
        return R.ok().put("roles", roles);
    }

    @PostMapping(value = "/role/recover")
    @ApiOperation(value = "账号角色恢复")
    public R insert(HttpServletRequest request, @RequestBody GameRole role) throws Exception {
        String u = checkToken(request);
        TDatabase database = tDatabaseService.selectByUsername(u);
        if (null == database) throw new ApiException(BizCode.DATABASE_NOT_BINDING);
        //查账号
        String str = "taiwan_cain";
        Connection connRole = ConnectToDatabase(database, str);
        Integer result = recoverRole(connRole, role);
        return R.ok();
    }

    private Integer recoverRole(Connection connRole, GameRole role) {

        String sql = "update taiwan_cain.charac_info t set t.delete_flag = 0 where t.charac_no = ?";
        try {
            PreparedStatement sta = connRole.prepareStatement(sql);
            sta.setString(1, role.getCharacterNo());
            return sta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<GameRole> roleList(Connection connRole, String uid) throws SQLException {
        //        查询
        String sql = "select t.m_id as uid," +
                "t.charac_no as characterNo," +
                "t.charac_name as characterName," +
                "t.delete_flag as deleteFlag" +
                "  from taiwan_cain.charac_info t where t.m_id = ?";
        ResultSet re = null;
        PreparedStatement sta = connRole.prepareStatement(sql);
        sta.setString(1, uid);
        List<GameRole> result = new ArrayList<>();
        try {
            re = sta.executeQuery();
            while (re.next()) {
                GameRole role = new GameRole();
                role.setUid(Integer.valueOf(re.getString(1)));
                role.setCharacterNo(re.getString(2));
                role.setCharacterName(re.getString(3));
                role.setDeleteFlag(Integer.valueOf(re.getString(4)));
                result.add(role);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            re.close();
            sta.close();
            connRole.close();
        }
        return null;

    }

    private String listAccount(Connection connAccount, Account account) throws SQLException {
        //        查询
        String sql = "select t.UID as uid  from d_taiwan.accounts t where t.accountname = ?";
        ResultSet re = null;

        PreparedStatement sta = connAccount.prepareStatement(sql);
        sta.setString(1, account.getName());
        String uid = null;
        try {
            re = sta.executeQuery();
            while (re.next()) {
                uid = re.getString("uid");
            }
            return uid;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            re.close();
            sta.close();
            connAccount.close();
        }
        return null;

    }

    public Connection ConnectToDatabase(TDatabase database, String str) {
        //连接url
        String url = "jdbc:mysql://" + database.getUrl() + ":3306" + "/" + str;
        //连接driver
        String driver = "com.mysql.jdbc.Driver";
        //用户名
        String name = database.getUser();
        //密码
        String pwd = database.getPassword();

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, name, pwd);
            System.out.println("connection success");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //增添
//  String sql ="insert into TEST(name,func) values(?,?)";
//  PreparedStatement statement = (PreparedStatement) connection.prepareStatement(sql);
//  statement.setObject(1,"高文斌");
//  statement.setObject(2,"准备找工作");
//  int result = statement.executeUpdate();
//  if (result == 1){
//      System.out.println("插入成功");
//  }
//  connection.commit();


//  //删除
//  String ddl = "delete from TEST where name = '高文斌'";
//  Statement sta = (Statement) connection.createStatement();
//  int eff = sta.executeUpdate(ddl);
//  System.out.println(eff);

    //更改
//    Statement s = (Statement) connection.createStatement();
//    int b = s.executeUpdate("update TEST set name='周文王' where name='李四'");
//        System.out.println(b);
//}catch(
//        Exception e)
//
//        {
//        System.out.println(e);
//        }

}



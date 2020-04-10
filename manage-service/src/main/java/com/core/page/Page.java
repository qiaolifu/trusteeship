package com.core.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Page<T> extends BaseParam implements Serializable {

    //每次获取数据最大条数 默认最大5000条数据
    public static Integer MAX_ROWS = 5000;

    public static String PAGE_KEY = "_page";
    //行记录总数
    private Integer _totalCount = 0;
    //总页数
    private Integer _totalPage = 0;
    //每页显示数据条数
    private Integer _pageSize = 20;
    //当前第几页
    private Integer _currPage = 0;
    //开始条数
    private Integer _start = 0;
    //分页数据
    private List<T> _records;//数据

    public void set_totalCount(Integer _totalCount) {
        this._totalCount = _totalCount;
        //设置页数
        this._totalPage = (this._totalCount <= 0) ?  0 : ((this._totalCount-1)/this._pageSize + 1);

        //修正当前页码编号
        if(this._currPage >= this._totalPage) this._currPage = this._totalPage;
        if((this._totalCount>0 && this._currPage <= 0)) this._currPage = 1;
        if(this._currPage <= 0) this._currPage = 1;
        //设置数据的拉取开始点
        this._start = this._currPage <= 0 ? 0 : (this._currPage-1) * this._pageSize;
    }

    public void set_totalPage(Integer _totalPage) {
        if(null == _totalPage) _totalPage = 0;
        this._totalPage = _totalPage;
    }

    public void set_pageSize(Integer _pageSize) {
        if(null == _pageSize) _pageSize = 0;
        this._pageSize = _pageSize > MAX_ROWS ? MAX_ROWS:_pageSize;

    }


}

package com.yinglan.scg.constant;

/**
 * 用于存放url常量的类
 * Created by ruitu ck on 2016/9/14.
 */

public class URLConstants {
    /**
     * 正式服务器地址URL
     */
    //  public static String SERVERURL = "http://user.api.shahaizhi.com/";
    public static String SERVERURL1 = "http://www.shahaizhi.com/";

    /**
     * 测试服务器地址URL
     */
    public static String SERVERURL = "http://192.168.1.247:8080/";
    //public static String SERVERURL = "http://api.shahaizi.keiousoft.com/";

    /**
     * 请求地址URL
     */
    public static String APIURL = SERVERURL + "api/guide/";

    /**
     * 获取七牛云key-ok
     */
    public static String QINIUKEY = SERVERURL + "api/public/key/qiniu.do";


    /**
     * 根据融云token获取头像性别昵称
     */
    public static String SYSRONGCLOUD = APIURL + "member/rongCloud.do";

    /**
     * 置换Token  get请求
     */
    public static String REFRESHTOKEN = APIURL + "m=Api&c=User&a=flashToken";

    /**
     * 登录
     */
    public static String USERLOGIN = APIURL + "member/login.do";

    /**
     * 获取会员登录状态
     */
    public static String ISLOGIN = APIURL + "member/islogin.do";

    /**
     * 退出登录
     */
    public static String LOGOUT = APIURL + "member/exit.do";

    /**
     * 第三方登录
     */
    public static String USERTHIRDLOGIN = APIURL + "member/third.do";

    /**
     * 获取第三方登录验证码
     */
    public static String THIRDCODE = APIURL + "member/thirdCode.do";

    /**
     * 短信验证码【手机号注册】
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    public static String SENDREGISTER = APIURL + "member/code.do";

    /**
     * 短信验证码【找回、修改密码】
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    public static String SENDFINFDCODE = APIURL + "member/find.do";

    /**
     * 用户注册
     */
    public static String REGISTER = APIURL + "member/regist.do";

    /**
     * 用户注册协议
     */
    public static String REGISTPROTOOL = SERVERURL1 + "dist/pages/registProtocol.html";

    /**
     * 更改密码【手机】
     */
    public static String USERRESTPWD = APIURL + "member/edit.do";

    /**
     * 获取接单信息列表
     */
    public static String GETGUIDEORDERPAGE = APIURL + "order/get_guide_order_page.do";

    /**
     * 获取用户错过的订单
     */
    public static String GETGUIDEMISSORDERPAGE = APIURL + "order/get_guide_miss_order_page.do";

    /**
     * 获取订单的详细信息
     */
    public static String GETTRAVELORDERDETAILS = APIURL + "order/get_travel_order_details.do";

    /**
     * 获取取消订单的原因列表
     */
    public static String CANCELREASONLIST = APIURL + "order/get_cancel_reason_list.do";

    /**
     * 快速接单
     */
    public static String GUIDESUBMITORDER = APIURL + "order/guide_submit_order.do";

    /**
     * 获取我的订单列表
     */
    public static String GETMYORDERPAGE = APIURL + "order/get_my_order_page.do";

    /**
     * 获取我的订单详情
     */
    public static String GETMYORDERDETAIL = APIURL + "order/get_my_order_details.do";

    /**
     * 获取系统消息首页
     */
    public static String NEWLISTBUYTITLE = APIURL + "news/listByTitle.do";

    /**
     * 获取消息列表
     */
    public static String NEWTITLE = APIURL + "news/title.do";

    /**
     * 选中某条消息并设为已读
     */
    public static String NEWSELECT = APIURL + "news/select.do";

    /**
     * 修改个人信息
     */
    public static String MEMBEREDIT = APIURL + "member/edit_guide.do";

    /**
     * 获取个人信息
     */
    public static String MEMBERINFO = APIURL + "member/get.do";

    /**
     * 资料信息 - 司导证件资料上传
     */
    public static String ADDCERTIFICATION = APIURL + "material/add_certification.do";

    /**
     * 资料信息 - 获取司导证件信息
     */
    public static String GETCERTIFICATIONDETAIL = APIURL + "material/get_certification_detail.do";

    /**
     * 资料信息 - 获取国家信息
     */
    public static String GETCOUNTRYLIST = APIURL + "model/get_country_list.do";

    /**
     * 资料信息 - 获取城市列表
     */
    public static String GETCITYLIST = APIURL + "model/get_city_list.do";

    /**
     * 资料信息 - 模糊查询城市信息
     */
    public static String GETCITYLISTBYNAME = APIURL + "model/get_city_list_by_name.do";

    /**
     * 获取用户车辆列表
     */
    public static String GETMODELLIST = APIURL + "material/get_model_list.do";

    /**
     * 设置默认车辆
     */
    public static String SETMODELDEFAULT = APIURL + "material/set_model_default.do";

    /**
     * 获取用户车辆详细信息
     */
    public static String GETMODELDETAIL = APIURL + "material/get_model_detail.do";

    /**
     * 司导上传车辆信息
     */
    public static String EIDTMODEL = APIURL + "material/eidt_model.do";

    /**
     * 获取车辆品牌列表
     */
    public static String GETMODELBRANDLIST = APIURL + "model/get_model_brand_list.do";

    /**
     * 获取车辆名称列表
     */
    public static String GETMODELNAMELIST = APIURL + "model/get_model_name_list.do";

    /**
     * 模糊查询车辆列表信息
     */
    public static String GETMODELLISTBYNAME = APIURL + "model/get_model_list_by_name.do";

    /**
     * 获取钱包余额
     */
    public static String PURSEGET = APIURL + "wallet/get.do";

    /**
     * 获取账户钱包明细
     */
    public static String PURSEDETAIL = APIURL + "wallet/detail.do";

    /**
     * 提现
     */
    public static String PURSECASH = APIURL + "wallet/cash.do";

    /**
     * 银行卡列表
     */
    public static String PURSELIST = APIURL + "wallet/list.do";

    /**
     * 银行卡列表
     */
    public static String PURSEBANK = APIURL + "wallet/banks.do";

    /**
     * 删除银行卡
     */
    public static String PURSEREMOVE = APIURL + "wallet/remove.do";

    /**
     * 设置默认银行卡
     */
    public static String PURSEDEFAULT = APIURL + "wallet/default.do";

    /**
     * 添加银行卡(可添加支付宝账号)
     */
    public static String PURSEADD = APIURL + "wallet/add.do";

    /**
     * 提交意见反馈
     */
    public static String ADVICEPOST = APIURL + "advice/post.do";

    /**
     * 关于我们
     */
    public static String ABOUTUS = SERVERURL1 + "dist/pages/about_us.html";

    /**
     * 帮助中心
     */
    public static String HELP = SERVERURL1 + "dist/pages/help.html";

    /**
     * 分享有礼
     */
    public static String SHARE = SERVERURL1 + "html/share.html?icode=";

    /**
     * 分享有礼分享网址
     */
    public static String REGISTERHTML = SERVERURL1 + "html/login.html?icode=";

    /**
     * 傻孩志学院
     */
    public static String COLLEGE = SERVERURL1 + "dist/pages/college.html";

}

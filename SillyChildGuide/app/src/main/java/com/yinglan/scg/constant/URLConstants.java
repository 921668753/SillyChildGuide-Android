package com.yinglan.scg.constant;

/**
 * 用于存放url常量的类
 * Created by ruitu ck on 2016/9/14.
 */

public class URLConstants {

    /**
     * 正式服务器地址URL
     */
    public static String SERVERURL = "http://user.api.shahaizhi.com/";

    /**
     * 测试服务器地址URL
     */
//    public static String SERVERURL = "http://192.168.1.247:8080/";

    public static String SERVERURL1 = "http://www.shahaizhi.tech/";

    /**
     * 请求地址URL
     */
    public static String APIURL = SERVERURL + "api/mobile/";

    /**
     * 获取七牛云key-ok
     */
    public static String QINIUKEY = SERVERURL + "api/public/key/qiniu.do";

    /**
     * 应用配置参数
     */
    public static String APPCONFIG = APIURL + "appConfig";

    /**
     * 根据融云token获取头像性别昵称
     */
    public static String SYSRONGCLOUD = APIURL + "member/rongCloud.do";

    /**
     * 百度定位
     */
    public static String BAIDUYUN = "http://api.map.baidu.com/geodata/v4/poi/create";
    public static String BAIDUUPDATE = "http://api.map.baidu.com/geodata/v4/poi/update";
    public static String BAIDUGETDATE = "http://api.map.baidu.com/geodata/v4/poi/list";

    /**
     * 获取首页信息
     */
    public static String HOMEPAGE = APIURL + "home/get_home_data.do";

    /**
     * 获取视频列表
     */
    public static String VIDEOLIST = APIURL + "video/get_video_list.do";

    /**
     * 获取视频详细信息
     */
    public static String VIDEODETAIL = APIURL + "video/get_video_detail.do";

    /**
     * 获取偏好列表
     */
    public static String CATEGORYLIST = APIURL + "travel/get_category_list.do";

    /**
     * 用户填写定制要求
     */
    public static String ADDCUSTOMIZED = APIURL + "travel/add_customized.do";

    /**
     * 商城
     */
    public static String MALLPAGE = APIURL + "page/home-list.do";

    /**
     * 首页  活动
     */
    public static String ACTIVITYGOOD = APIURL + "goods/activity-goods.do";

    /**
     * 获取分类广告
     */
    public static String ADVCAT = APIURL + "adv/adv-cat.do";

    /**
     * 首页----获取商品分类
     */
    public static String GOODSCATLIST = APIURL + "goodscat/list.do";

    /**
     * 首页---更多分类----商品列表
     */
    public static String GOODSLIST = APIURL + "goods/list.do";

    /**
     * 首页---更多分类----商品列表----店铺首页
     */
    public static String STOREIMAGE = APIURL + "store/image.do";
    public static String STOREINDEXGOODS = APIURL + "store/index-goods.do";

    /**
     * 首页---更多分类----商品列表----店铺商品
     */
    public static String STOREGOODSLIST = APIURL + "store/goods-list.do";

    /**
     * 首页---更多分类----商品列表----商品详情
     */
    public static String GOODDETAIL = APIURL + "goods/detail.do";

    public static String GOODSDETAIL = SERVERURL1 + "html/goods_detail.html?goodid=";

    /**
     * 首页---更多分类----商品列表----商品详情----获取评论列表
     */
    public static String COMMENTLIST = APIURL + "comment/list.do";

    /**
     * 首页---更多分类----商品列表----商品详情---收藏商品
     */
    public static String FAVORITADD = APIURL + "favorite/add.do";

    /**
     * 检测用户是否收藏店铺
     */
    public static String CHECKFAVORITED = APIURL + "goods/check_favorited.do";

    /**
     * 首页---更多分类----商品列表----商品详情----取消收藏商品
     */
    public static String UNFAVORIT = APIURL + "favorite/unfavorite.do";

    /**
     * 首页---更多分类----商品列表----商品详情----立即购买
     */
    public static String ORDERBUYNOW = APIURL + "order/buyNow.do";

    /**
     * 首页---更多分类----商品列表----商品详情----商品规格
     */
    public static String GOODSSPEC = APIURL + "goods/specs.do";

    /**
     * 首页---更多分类----商品列表----商品详情----商品规格----由规格数组获取货品的参数
     */
    public static String GOODSPRODUCTSPEC = APIURL + "goods/product-specs.do";

    /**
     * 首页---更多分类----商品列表----商品详情----商品规格----获取商品剩余规格
     */
    public static String GOODSPRODUCTSPECLEFT = APIURL + "goods/specs-left.do";


    /**
     * 城市与机场 - 获取国家列表
     */
    public static String AIRPOTCOUNTRYLIST = APIURL + "airport/get_country_list.do";

    /**
     * 城市与机场 - 通过国家编号获取城市与机场信息
     */
    public static String AIRPOTBYCOUNTRYID = APIURL + "airport/get_airport_by_countryId.do";

    /**
     * 包车服务 - 获取城市包车列表
     */
    public static String REGIONBYCOUNTRYID = APIURL + "airport/get_region.do";

    /**
     * 精品线路 - 获取精品线路城市列表
     */
    public static String ROUTEREGION = APIURL + "airport/get_route_region.do";

    /**
     * 精品线路 - 获取精品线路商品列表
     */
    public static String ROUTELIST = APIURL + "products/get_route_list.do";

    /**
     * 精品线路 - 获取精品线路商品列表
     */
    public static String PRODUCTLINEDETAILS = APIURL + "products/get_product_details.do";

    /**
     * 精品线路 - 线路详细信息
     */
    public static String ROUTEDETAIL = APIURL + "products/get_route_detail.do";

    /**
     * 城市与机场 - 通过国家编号获取城市与机场信息
     */
    public static String PRODUCTBYAIRPORTID = APIURL + "products/get_product_by_airport_id.do";

    /**
     * 搜索 - 获取某商品列表
     */
    public static String PRODUCTBYTYPE = APIURL + "products/get_product_by_type.do";

    /**
     * 包车服务 - 通过城市的编号来获取产品信息
     */
    public static String PRODUCTBYREGION = APIURL + "products/get_product_by_region.do";

    /**
     * 城市与机场 - 通过国家编号获取城市与机场信息
     */
    public static String PRODUCTDETAILS = APIURL + "products/get_product_details.do";

    /**
     * 接机产品 -  获取某商品的评价列表
     */
    public static String EVALUATIONPAGE = APIURL + "evaluation/get_evaluation_page.do";

    /**
     * 接机产品 - 用户填写接机预定信息
     */
    public static String ADDREQUIREMENTS = APIURL + "products/add_requirements.do";

    /**
     * 精品线路 - 用户填写线路需求
     */
    public static String ADDROUTEREQUIREMENTS = APIURL + "products/add_route_requirement.do";

    /**
     * 包车服务 - 用户填写包车需求
     */
    public static String ADDCARREQUIREMENTS = APIURL + "products/add_car_requirements.do";


    /**
     * 接机---支付订单
     */
    public static String TRAVELORDERDETAIL = APIURL + "products/get_travel_order_detail.do";


    /**
     * 支付订单 - 创建订单
     */
    public static String CREATETRAVEORDER = APIURL + "products/create_travel_order.do";


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


    public static String GETPRIVATEDETAIL1 = APIURL + "m=web&c=route&a=detail&air_id=";

    /**
     * 社区----分类信息列表
     */
    public static String CLASSIFITCATIONLIST = APIURL + "classification/get_classification_list.do";

    /**
     * 社区----发动态分类信息列表
     */
    public static String POSTCLASSIFITCATIONLIST = APIURL + "classification/get_post_classification_list.do";

    /**
     * 社区----帖子列表
     */
    public static String POSTLIST = APIURL + "post/get_post_list.do";

    /**
     * 社区----检索会员的信息
     */
    public static String MEMBERLIST = APIURL + "post/get_member_list.do";

    /**
     * 社区----获取帖子详情
     */
    public static String POSTDETAIL = APIURL + "post/get_post_detail.do";

    /**
     * 社区----关注或取消关注
     */
    public static String ADDCONCERN = APIURL + "concern/add_concern.do";

    /**
     * 社区----获取其他用户信息
     */
    public static String OTHERUSERINFO = APIURL + "post/get_other_user_info.do";

    /**
     * 社区----获取用户帖子列表
     */
    public static String OTHERUSERPOST = APIURL + "post/get_other_user_post.do";

    /**
     * 社区----点赞和取消
     */
    public static String ADDLIKE = APIURL + "like/add_like.do";

    /**
     * 社区----给评论点赞
     */
    public static String ADDCOMMRENTLIKE = APIURL + "comment/add_commentLike.do";

    /**
     * 社区----添加评论
     */
    public static String ADDCOMMENT = APIURL + "comment/add_comment.do";

    /**
     * 社区----举报用户帖子
     */
    public static String REPORT = APIURL + "post/report.do";

    /**
     * 社区----获取帖子评论列表
     */
    public static String POSTCOMMENT = APIURL + "comment/get_post_comment.do";

    /**
     * 社区----获取帖视频评论列表
     */
    public static String VIDEOCOMMENT = APIURL + "comment/get_video_comment.do";

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
    public static String LOGOUT = APIURL + "member/logout.do";

    /**
     * 第三方登录
     */
    public static String USERTHIRDLOGIN = APIURL + "member/thirdLogin.do";


    /**
     * 获取第三方登录验证码
     */
    public static String THIRDCODE = APIURL + "member/thirdCode.do";

    /**
     * 短信验证码【手机号注册】
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    public static String SENDREGISTER = APIURL + "member/send-register-code.do";

    /**
     * 短信验证码【找回、修改密码】
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    public static String SENDFINFDCODE = APIURL + "member/send-find-code.do";

    /**
     * 用户注册
     */
    public static String REGISTER = APIURL + "member/mobile-register.do";

    /**
     * 用户注册协议
     */
    public static String REGISTPROTOOL = SERVERURL1 + "dist/pages/registProtocol.html";

    /**
     * 更改密码【手机】
     */
    public static String USERRESTPWD = APIURL + "member/mobile-change-pass.do";

    /**
     * 消息-系统消息列表
     */
    public static String SYSTEMMESSAGELIST = APIURL + "m=Api&c=Message&a=getSystemMessage";

    /**
     * 获取用户信息
     */
    public static String USERINFO = APIURL + "member/info.do";

    /**
     * 会员资料保存
     */
    public static String SAVEINFO = APIURL + "member/save.do";

    /**
     * 获取收货地址列表
     */
    public static String ADDRESSLIST = APIURL + "address/list.do";

    /**
     * 设置默认收货地址
     */
    public static String DEFAULTADDRESS = APIURL + "address/set-default.do";

    /**
     * 删除收货地址
     */
    public static String DELETEADDRESS = APIURL + "address/delete.do";

    /**
     * 获取详细收货地址
     */
    public static String ADDRESS = APIURL + "address/get.do";

    /**
     * 根据父id获取地址列表
     */
    public static String REGIONLIST = APIURL + "address/region-list.do";

    /**
     * 根据parentid获取所有地区列表
     */
    public static String ADDRESSREGIONLIST = APIURL + "address/region-list-all.do";

    /**
     * 编辑收货地址
     */
    public static String EDITADDRESS = APIURL + "address/edit.do";

    /**
     * 添加认收货地址
     */
    public static String ADDADDRESS = APIURL + "address/add.do";

    /**
     * 获取钱包余额
     */
    public static String PURSEGET = APIURL + "purse/get.do";

    /**
     * 获取账户钱包明细
     */
    public static String PURSEDETAIL = APIURL + "purse/detail.do";

    /**
     * 优惠券列表
     */
    public static String COUPONS = APIURL + "member/bonus-main.do";

    /**
     * 优惠券使用说明
     */
    public static String INSTUCTIONS = SERVERURL1 + "dist/pages/instructions.html";

    /**
     * 提现
     */
    public static String PURSECASH = APIURL + "purse/cash.do";

    /**
     * 银行卡列表
     */
    public static String PURSELIST = APIURL + "purse/list.do";

    /**
     * 银行卡列表
     */
    public static String PURSEBANK = APIURL + "purse/banks.do";

    /**
     * 删除银行卡
     */
    public static String PURSEREMOVE = APIURL + "purse/remove.do";

    /**
     * 设置默认银行卡
     */
    public static String PURSEDEFAULT = APIURL + "purse/default.do";

    /**
     * 添加银行卡(可添加支付宝账号)
     */
    public static String PURSEADD = APIURL + "purse/add.do";


    /**
     * 用户充值信息接口
     */
    public static String ONLINEREC = APIURL + "online/rec.do";

    /**
     * 获取某一个评论的详细信息
     */
    public static String CINMENTDETAIL = APIURL + "comment/get_comment_detail.do";

    /**
     * 获取我关注的用户列表
     */
    public static String MYCONCERNLIST = APIURL + "concern/get_my_concern_list.do";

    /**
     * 获取收藏商品列表
     */
    public static String FAVORITEGOODLIST = APIURL + "favorite/list.do";

    /**
     * 取消收藏
     */
    public static String UNFAVORITEGOOD = APIURL + "favorite/unfavorite.do";

    /**
     * 添加到购物车
     */
    public static String AGGCARTGOOD = APIURL + "cart/add.do";

    /**
     * 获取我的粉丝列表
     */
    public static String MYFANSLIST = APIURL + "concern/get_my_fans_list.do";

    /**
     * 获取用户发布的帖子
     */
    public static String USERPOST = APIURL + "post/get_user_post.do";

    /**
     * 用户发布帖子
     */
    public static String ADDPOST = APIURL + "post/add_post.do";

    /**
     * 编辑帖子
     */
    public static String EDITPOST = APIURL + "post/edit_post.do";

    /**
     * 用户删除帖子
     */
    public static String DELETEPOST = APIURL + "post/delete_post.do";

    /**
     * 获取购物车商品列表
     */
    public static String CARTLIST = APIURL + "cart/list.do";

    /**
     * 删除购物车中的某项
     */
    public static String CARTDELETE = APIURL + "cart/delete.do";

    /**
     * 更新购物车某项商品数量
     */
    public static String CARTUPDATE = APIURL + "cart/update.do";

    /**
     * 确认订单
     */
    public static String CARTBALANCE = APIURL + "order/cartBalance.do";

    /**
     * 创建付款订单
     */
    public static String CREATEORDER = APIURL + "order/createOrder.do";

    /**
     * 订单支付信息接口
     */
    public static String ONLINEPAY = APIURL + "online/pay.do";

    /**
     * 获取订单简要信息
     */
    public static String ONLINESIMPLE = APIURL + "order/simple.do";

    /**
     * 获取订单信息列表
     */
    public static String ORDERLIST = APIURL + "order/list.do";

    /**
     * 取消订单
     */
    public static String ORDERCANCEL = APIURL + "order/cancel.do";

    /**
     * 确认收货
     */
    public static String ORDERCONFIRM = APIURL + "order/confirm.do";

    /**
     * 提醒发货
     */
    public static String ORDERREMIND = APIURL + "order/remind.do";

    /**
     * 获取订单详情
     */
    public static String ORDERDETAIL = APIURL + "order/detail.do";

    /**
     * 获取物流详情
     */
    public static String ORDERLOGISTICS = SERVERURL1 + "html/order_logistics.html?orderid=";

    /**
     * 发表评论
     */
    public static String COMMENTCREATE = APIURL + "comment/comment-create.do";

    /**
     * 获取售后信息
     */
    public static String ORDERREFUND = APIURL + "order/refund.do";

    /**
     * 退货类型,退货原因
     */
    public static String ORDERREFUNDLIST = APIURL + "order/refund-list.do";

    /**
     * 获取售后退款金额（由退货数目获取退款金额）
     */
    public static String ORDERREFUNDMONEY = APIURL + "order/refund-money.do";

    /**
     * 售后详情
     */
    public static String SELLBACKDETAIL = APIURL + "order/sell-back-detail.do";

    /**
     * 服务详情
     */
    public static String SELLBACKSERVICE = APIURL + "order/sell-back-service.do";


    /**
     * 包车服务 - 分页查询用户提交的订单
     */
    public static String CHARTORDERLIST = APIURL + "travelorder/get_member_order_list.do";

    /**
     * 包车服务 - 分页查询用户提交的订单
     */
    public static String CHARTERORDERDETAILS = APIURL + "travelorder/get_travel_order_detail.do";

    /**
     * 包车服务 - 获取私人定制单的详细信息
     */
    public static String CUSTOMIZEORDERDETAILS = APIURL + "travelorder/get_customize_order_detail.do";

    /**
     * 包车服务 - 获取要评价的商品信息
     */
    public static String REVIEWPRODUCT = APIURL + "travelorder/get_review_product.do";

    /**
     * 包车服务 - 添加商品评价
     */
    public static String ADDPRODUCTREVIEW = APIURL + "evaluation/add_product_review.do";

    /**
     * 提交意见反馈
     */
    public static String ADVICEPOST = SERVERURL + "api/member/advice/post.do";

    /**
     * 关于我们
     */
    public static String ABOUTUS = SERVERURL1 + "dist/pages/about_us.html";

    /**
     * 帮助中心
     */
    public static String HELP = SERVERURL1 + "dist/pages/help.html";

    /**
     * 帮助中心详情
     */
    public static String HELPDETAIL = SERVERURL1 + "dist/pages/helpDetal.html";

    /**
     * VIP救助电话
     */
    public static String VIPEMERGENCYCALL = SERVERURL1 + "pages/rescue_phone.html";

    /**
     * 分享有礼
     */
    public static String SHARE = SERVERURL1 + "html/share.html?icode=";

    /**
     * 分享有礼分享网址
     */
    public static String REGISTERHTML = SERVERURL1 + "html/login.html?icode=";

}

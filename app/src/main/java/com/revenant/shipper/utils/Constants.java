package com.revenant.shipper.utils;

import java.nio.charset.Charset;

import cn.hutool.core.util.CharsetUtil;

/**
 * Created by Administrator on 2018/8/21.
 */

public class Constants {
    /*加密*/

    public static final String TOKEN = "X-Token";
    public static final String DATA_KEY = "1m@N3B$v";
    public static final Charset CHARSET = CharsetUtil.CHARSET_UTF_8;
    public static final String ENCODE_ALLOW = "AES/CBC/PKCS5Padding";
    public static final String ENCODE = "AES";

    /*测试或者是生产
     * true 生成
     * false 测试
     * */

    public static final boolean isProOrTest = false;
    //    public static final boolean isProOrTest = true;
    public static final String ProUrl = "https://app.cltkj.cn/";
    public static final String TestUrl = "http://app.cltkj.net/";


    //        public static final String BaseUrl = "http://221.203.29.44:7511/";
//    public static final String BaseUrl = "http://app.cltkj.net/";
    public static final String BaseUrl = (isProOrTest) ? ProUrl : TestUrl;
    //    public static final String BaseUrl = "https://app.cltkj.cn/";
    public static final String Dev_BASE_URL_FunctionOne = BaseUrl;
    public static final String Dev_BASE_URL_FunctionTwo = BaseUrl;
    public static final String Dev_BASE_URL_FunctionThree = BaseUrl;
    public static final String Dev_BASE_URL_FunctionFour = BaseUrl;
    public static final String Dev_BASE_URL_FunctionFive = BaseUrl;

    /*1登录接口*/
    public static final String User_Login = Dev_BASE_URL_FunctionOne + "userLogin";


    /*2发送短信验证码接口*/
    public static final String User_Send_Verification_Code = Dev_BASE_URL_FunctionOne + "personal/sendSms";


    /*3手机验证码登入*/
    public static final String User_Login_Phone_Verification_ = Dev_BASE_URL_FunctionOne + "mobileLogin";

    /*4忘记密码*/
    public static final String User_Login_Forget_Pas = Dev_BASE_URL_FunctionOne + "forgetPass";


    /*5用户注册*/
    public static final String User_Login_Register = Dev_BASE_URL_FunctionOne + "register";


    /*6获取空车列表（未登入）*/
    public static final String List_Empty_No_Login = Dev_BASE_URL_FunctionTwo + "selectEmptyCarList";

    /*7上传图片
     */
    public static final String UploadFile = Dev_BASE_URL_FunctionFour + "file/upload";


    /*8货主端实名认证（身份证上传)
     */
    public static final String UploadIdCard = Dev_BASE_URL_FunctionOne + "clt-business-u/authentication/identityAuthentication";


    /*9货主上传营业执照（回显）
     */
    public static final String UploadEnterpriseAuthentication = Dev_BASE_URL_FunctionOne + "clt-business-u/authentication/enterpriseAuthentication";


    /*10货主上传营业执照保存数据库
     */
    public static final String SaveEnterpriseAuthentication = Dev_BASE_URL_FunctionOne + "clt-business-u/authentication/updateEnterprise";

    /*11获取空车列表（以实名）*/
    public static final String List_Empty_No_Authentication = Dev_BASE_URL_FunctionTwo + "selectEmptyCarList";


    /*12平台货源（已发布/已关闭）*/
    public static final String Goods_Publish_Or_closed = Dev_BASE_URL_FunctionTwo + "clt-business-c/release/goodsList";

    /*13获取空车详情*/
    public static final String Get_Empty_Details = Dev_BASE_URL_FunctionTwo + "clt-business-c/emptyCarList/selectOneEmptyCar";

    /*14详情平台货源（已发布/已关闭）*/
    public static final String Detail_Goods_Publish_Or_closed = Dev_BASE_URL_FunctionTwo + "clt-business-c/release/goodsOne";

    /*15获取个人信息）*/
    public static final String Get_personal_info = Dev_BASE_URL_FunctionOne + "clt-business-u/personal/personalQuery";


    /*16修改头像*/
    public static final String Update_personal_image = Dev_BASE_URL_FunctionOne + "clt-business-u/personal/updatePhoto";

    /*17编辑状态（发布->关闭）*/
    public static final String Update_state_goods = Dev_BASE_URL_FunctionTwo + "clt-business-c/release/updateStateGoods";

    /*18加入熟车*/
    public static final String Add_shu_car = Dev_BASE_URL_FunctionTwo + "clt-business-c/cooked/joinCookedCar";

    /*19发布货源*/
    public static final String Publish_Goods = Dev_BASE_URL_FunctionTwo + "clt-business-c/release/publishGoods";
    /*20查看熟车群*/
    public static final String Look_ShuChe_Group = Dev_BASE_URL_FunctionTwo + "clt-business-c/group/selectGroupList";

    /*21查看熟车单个熟车群*/
    public static final String Look_ShuChe_ZhiPai = Dev_BASE_URL_FunctionTwo + "coer/group/selectGroup";

    /*22查看熟车*/
    public static final String Look_ShuChe_Group_Member = Dev_BASE_URL_FunctionTwo + "clt-business-c/group/selectGroup";

    /*24保存熟车群*/
    public static final String Save_ShuChe = Dev_BASE_URL_FunctionTwo + "clt-business-c/group/saveGroup";

    /*24修改熟车群*/
    public static final String Update_ShuChe = Dev_BASE_URL_FunctionTwo + "clt-business-c/group/updateGroupTitle";

    /*25删除熟车群组*/
    public static final String Delete_ShuChe = Dev_BASE_URL_FunctionTwo + "clt-business-c/group/deleteGroup";

    /*26通过手机号查询Id*/
    public static final String Search_Phone = Dev_BASE_URL_FunctionTwo + "clt-business-u/personal/selectMobile";

    /*27 消息列表 */
//    public static final String News_list = Dev_BASE_URL_FunctionTwo + " ";

    /*27 消息列表 */
    public static final String News_list = Dev_BASE_URL_FunctionTwo + "clt-business-c/msg/selectList";

    /*28 消息详情 */
    public static final String News_Detail = Dev_BASE_URL_FunctionTwo + "clt-business-c/msg/findById";

    /*29 订单列表 发票订单*/
    public static final String Order_list = Dev_BASE_URL_FunctionTwo + "clt-business-c/order/selectAllOrderList";
    /*30 开出发票 */
    public static final String Fa_Piao_Write_list = Dev_BASE_URL_FunctionTwo + "clt-business-c/order/selectAllOrderList";
    /*31 开出发票详情 */
    public static final String Fa_Piao_Write_Detail = Dev_BASE_URL_FunctionTwo + "clt-business-c/invoice/selecInvoice";


    /*32 发票抬头列表 */
    public static final String Fa_Piao_TaiTou_List = Dev_BASE_URL_FunctionTwo + "clt-business-c/invoice/selectInvoiceTitleList";


    /*33 发票抬头详情 */
    public static final String Fa_Piao_TaiTou_Detail = Dev_BASE_URL_FunctionTwo + "clt-business-c/invoice/selecInvoiceTitle";

    /*34 编辑发票抬头详情 */
    public static final String Fa_Piao_TaiTou_EditDetail = Dev_BASE_URL_FunctionTwo + "clt-business-c/invoice/updateInvoiceTitle";
    /*35 删除票抬头 */
    public static final String Fa_Piao_TaiTou_Delete = Dev_BASE_URL_FunctionTwo + "clt-business-c/invoice/delInvoiceTitle";

    /*36 发票历史列表 */
    public static final String Fa_Piao_History_List = Dev_BASE_URL_FunctionTwo + "clt-business-c/invoice/selectInvoiceList";
    /*37 发票历史列表删除 */
    public static final String Fa_Piao_History_Delete = Dev_BASE_URL_FunctionTwo + "clt-business-c/invoice/delInvoice";
    /*38 发票历史列表详情 */
    public static final String Fa_Piao_History_Detail = Dev_BASE_URL_FunctionTwo + "clt-business-c/invoice/selecInvoice";

    /*39 上传磅单 */
    public static final String Upload_BangDan = Dev_BASE_URL_FunctionTwo + "clt-business-c/order/updateShipperInfo";

    /*40  支付页面 */
    public static final String Pay_Make_Deal = Dev_BASE_URL_FunctionTwo + "clt-business-c/order/updateAccount";


    /*41  熟车列表 */
    public static final String Shuche_List = Dev_BASE_URL_FunctionTwo + "clt-business-c/cooked/cookedCar";

    /*42  推送货源 */
    public static final String Push_Supply = Dev_BASE_URL_FunctionTwo + "clt-business-c/emptyCarList/pushSupply";

    /*43  查看磅单 */
    public static final String Look_BangDan = Dev_BASE_URL_FunctionTwo + "clt-business-c/order/getBillInfo";

    /*44  删除货源 */
    public static final String Delete_Goods = Dev_BASE_URL_FunctionTwo + "clt-business-c/release/delGoods";


    /*45  输入支付密码 */
    public static final String Pay_Input_Check = Dev_BASE_URL_FunctionThree + "clt-business-f/verifyPwd";

    /*46  支付页面显示 */
    public static final String Pay_Show = Dev_BASE_URL_FunctionThree + "clt-business-f/order";

    /*47  司机经纬度 */
    public static final String Driver_Location_Upload = Dev_BASE_URL_FunctionTwo + "clt-business-c/order/updateTrail";

    /*48  司机查看轨迹 */
    public static final String Driver_Track = Dev_BASE_URL_FunctionTwo + "clt-business-c/order/selectTrail";

    /*49  货主手动确认列表 */
    public static final String Que_RenSiJi_List = Dev_BASE_URL_FunctionTwo + "clt-business-c/release/manualValidationList";

    /*50  货主手动确认*/
    public static final String Que_RenSiJi_Confirm = Dev_BASE_URL_FunctionTwo + "clt-business-c/release/manualValidationDriver";

    /*51  编辑货源*/
    public static final String Edits_Goods = Dev_BASE_URL_FunctionTwo + "clt-business-c/release/updateGoods";

    /*52  查看货源*/
    public static final String Look_Goods = Dev_BASE_URL_FunctionTwo + "clt-business-c/release/goodsOne";

    /*72  订单详情*/
    public static final String Driver_JustOrder_Order_Detail = Dev_BASE_URL_FunctionTwo + "clt-business-c/order/selectOneOrder";

    /*73  添加发票*/
    public static final String Add_FaPiao = Dev_BASE_URL_FunctionTwo + "clt-business-c/invoice/saveInvoiceTitle";

    /*74  保存发票*/
    public static final String Save_FaPiao = Dev_BASE_URL_FunctionTwo + "clt-business-c/invoice/saveInvoice";

    /*75 编辑货源*/
    public static final String Publish_Good_Updates = Dev_BASE_URL_FunctionTwo + "clt-business-c/release/updateGoods";


    /*76 升级*/
    public static final String Update_Apk = Dev_BASE_URL_FunctionOne + "version/checkVersion";

    /*77找油网*/
    public static final String Find_Oil = Dev_BASE_URL_FunctionFive + "clt-business-t/login";


    /*79联网发布*/
    public static final String Save_Onlive = Dev_BASE_URL_FunctionTwo + "clt-business-c/goods/saveInfomaster";


    /*80钱包*/

    public static final String Money_Wallet_test = "http://web.cltkj.net/clt-wallet/#/index?accountId=";
    public static final String Money_Wallet_pro = "https://front.cltkj.cn/clt-wallet/#/index?accountId=";
    public static final String Money_Wallet = (isProOrTest) ? Money_Wallet_pro : Money_Wallet_test;
//

    /*多次token名称*/
    public static final String Name_header_token = "token";

    /*货主待审核状态*/
    public static final Integer Type_shipper_status = 9;
    /*货主待审核状态msg*/
    public static final String Type_shipper_message = "认证待审核";

    /*77 添加意见反馈*/
    public static final String Complaint_Insert = Dev_BASE_URL_FunctionFive + "clt-business-c/complaint/insert";

    /*78 意见反馈列表*/
    public static final String Complaint_SelectList = Dev_BASE_URL_FunctionFive + "clt-business-c/complaint/selectList";

    /*77 意见反馈详情*/
    public static final String Complaint_FindById = Dev_BASE_URL_FunctionFive + "clt-business-c/complaint/findById";


    /*77 评价*/
    public static final String Comment_Add = Dev_BASE_URL_FunctionFive + "clt-business-c/order/evaluate";

    /*77 评价*/
    public static final String Comment_PerSonal_Show = Dev_BASE_URL_FunctionFive + "clt-business-c/personal/introduction";
    public static final String APP_ID_WeChat = "1234567";
    public static final String APP_Secret_WeChat = "1234567";
    public static final String APP_Key_YouMeng = "5ea14e37dbc2ec07ad296221";
    public static final String Warning_Text = "本平台禁止发布危险品例如：易燃易爆、压缩气体、有毒气体、易燃固体、遇水自燃物资、有毒害物、腐蚀品。";

    public static final boolean isSecond = true;

    /*历史轨迹（中交）*/
    public static final String Track_ZhongJiao = Dev_BASE_URL_FunctionFive + "clt-business-c/selectZetTrail";

    /*轨迹实时查询 和之前的一样 目前是不用改*/
    public static final String Track_Real_Trail = Dev_BASE_URL_FunctionFive + "clt-business-c/order/selectRealTrail";

    /*查询邀请码*/
    public static final String SelectInvite = Dev_BASE_URL_FunctionFive + "clt-business-u/personal/selectInvite";

    /*修改邀请码*/
    public static final String UpdateInvite = Dev_BASE_URL_FunctionFive + "clt-business-u/personal/updateInvite";


}

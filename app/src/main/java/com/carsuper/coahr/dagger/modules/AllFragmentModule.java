package com.carsuper.coahr.dagger.modules;

import com.carsuper.coahr.dagger.components.BaseFragmentComponent;
import com.carsuper.coahr.dagger.modules.main.CityPickerDialogFragmentModule;
import com.carsuper.coahr.dagger.modules.main.FragmentPayPass_Module;
import com.carsuper.coahr.dagger.modules.main.Fragment_Exchange_Mall_Module;
import com.carsuper.coahr.dagger.modules.main.Fragment_Exchange_shopping_detail_Module;
import com.carsuper.coahr.dagger.modules.main.MainFragmentModule;
import com.carsuper.coahr.dagger.modules.maintenance.OrderToMaintenanceFragmentModule;
import com.carsuper.coahr.dagger.modules.myData.MyDataFragmentModule;
import com.carsuper.coahr.dagger.modules.shoppingMall.ShoppingMallFragmentModule;
import com.carsuper.coahr.dagger.modules.store.StoreFragmentModule;
import com.carsuper.coahr.mvp.view.Exchange_mall.FragmentPayPass;
import com.carsuper.coahr.mvp.view.Exchange_mall.Fragment_Exchange_mall;
import com.carsuper.coahr.mvp.view.Exchange_mall.Fragment_exchange_shopping_detail;
import com.carsuper.coahr.mvp.view.maintenance.CarMotorFragment;
import com.carsuper.coahr.mvp.view.maintenance.OrderFragmentSelectCouponFragment;
import com.carsuper.coahr.mvp.view.myData.InvitesCourtesy.Fragment_InvitesCourtesy;
import com.carsuper.coahr.mvp.view.myData.MyCoupon.CouponReceiveFragment;
import com.carsuper.coahr.mvp.view.myData.MyCoupon.CouponViewPagerFragment;
import com.carsuper.coahr.mvp.view.myData.MyCoupon.MyCouponMainFragment;
import com.carsuper.coahr.mvp.view.myData.ThankForEvaluateFragment;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.LogisticsFragment;
import com.carsuper.coahr.mvp.view.myData.integralCenter.IntegralCenterFragment;
import com.carsuper.coahr.mvp.view.myData.integralCenter.MyIntegralCenterSignFagment;
import com.carsuper.coahr.mvp.view.myData.integralCenter.PointFragment;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MHaveBeenCompleteFragment;
import com.carsuper.coahr.mvp.view.myData.returnorchange.ApplyReturnChangeFragment;
import com.carsuper.coahr.mvp.view.myData.returnorchange.GoodsStatusFragment;
import com.carsuper.coahr.mvp.view.myData.returnorchange.ReasonFragment;
import com.carsuper.coahr.mvp.view.myData.returnorchange.ReturnChangeSelectFragement;
import com.carsuper.coahr.mvp.view.myData.setting.AboutFragment;
import com.carsuper.coahr.mvp.view.SearchFragment;
import com.carsuper.coahr.mvp.view.main.CityPickerDialogFragment;
import com.carsuper.coahr.mvp.view.main.MainFragment;
import com.carsuper.coahr.mvp.view.maintenance.CarBrandFragment;
import com.carsuper.coahr.mvp.view.maintenance.CarSerialFragment;
import com.carsuper.coahr.mvp.view.maintenance.CarHorsePowerFragment;
import com.carsuper.coahr.mvp.view.maintenance.CommodityForMaintanceFragment;
import com.carsuper.coahr.mvp.view.maintenance.ConfirmMaintanceOrderFragment;
import com.carsuper.coahr.mvp.view.maintenance.OrderSuccessFragment;
import com.carsuper.coahr.mvp.view.maintenance.OrderToMaintenanceFragment;
import com.carsuper.coahr.mvp.view.myData.EditAddressFragment;
import com.carsuper.coahr.mvp.view.myData.MyAddressFragment;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.CommodityOrderDetailFragment;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.CommodityOrderFragment;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.MyCommodityOrderFragment;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.NeedToEvaluateFragment;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.NeedToPayFragment;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.NeedToRecieveFragment;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.NeedToSendFragment;
import com.carsuper.coahr.mvp.view.myData.commodityOrder.ReturnOrChangeFragment;
import com.carsuper.coahr.mvp.view.myData.EvaluateSuccessFragment;
import com.carsuper.coahr.mvp.view.myData.InvitationFragment;
import com.carsuper.coahr.mvp.view.myData.LoginFragment;
import com.carsuper.coahr.mvp.view.myData.MyDataFragment;
import com.carsuper.coahr.mvp.view.myData.MyLovelyCarFragment;
import com.carsuper.coahr.mvp.view.myData.OpinionFragment;
import com.carsuper.coahr.mvp.view.myData.ShoppingCartFragment;
import com.carsuper.coahr.mvp.view.myData.ToEvaluateFragment;
import com.carsuper.coahr.mvp.view.myData.VerifyMobilePhoneDialogFragment;
import com.carsuper.coahr.mvp.view.myData.VerifyPhoneDialogFragment;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MHaveBeenCanceledFragment;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MNeedToEvaluateFragment;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MNeedToPayFragment;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MNeedToServeFragment;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MaintanceOrderFragment;
import com.carsuper.coahr.mvp.view.myData.maintanceOrder.MyMaintanceOrderFragment;
import com.carsuper.coahr.mvp.view.myData.setting.UserInfoFragment;
import com.carsuper.coahr.mvp.view.myData.visitMaintance.MyMaintanceOrderDetailFragment;
import com.carsuper.coahr.mvp.view.myData.visitMaintance.MyVisitMaintanceFragment;
import com.carsuper.coahr.mvp.view.myData.myPoints.ExchangeCommoditySuccessFragment;
import com.carsuper.coahr.mvp.view.myData.myPoints.MyPointsFragment;
import com.carsuper.coahr.mvp.view.myData.myPoints.PointCenterFragment;
import com.carsuper.coahr.mvp.view.myData.setting.ClipHeaderImgFragment;
import com.carsuper.coahr.mvp.view.myData.setting.JoinUsFragment;
import com.carsuper.coahr.mvp.view.myData.setting.NameFragment;
import com.carsuper.coahr.mvp.view.myData.setting.PhoneNumberFragment;
import com.carsuper.coahr.mvp.view.myData.setting.SettingFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityDetailFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityEvaluateDetailFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityEvaluateFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.CommodityRecommendFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.ConfirmCommodityOrderFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.PaymentSuccessFragment;
import com.carsuper.coahr.mvp.view.shoppingMall.ShoppingMallFragment;
import com.carsuper.coahr.mvp.view.store.StoreDetailFragment;
import com.carsuper.coahr.mvp.view.store.StoreEvaluateDetailFragment;
import com.carsuper.coahr.mvp.view.store.StoreEvaluateFragment;
import com.carsuper.coahr.mvp.view.store.StoreFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Author： hengzwd on 2018/6/19.
 * Email：hengzwdhengzwd@qq.com
 */

@Module(subcomponents = {
        BaseFragmentComponent.class
})
public abstract class AllFragmentModule {

    //每个fragment都在这里声明,如果不需要再module中，通过provider方法提供对象，则不需要建立此fragment的相关module

    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    abstract MainFragment contributeMainFragmentInjector();

    @ContributesAndroidInjector(modules = MyDataFragmentModule.class)
    abstract MyDataFragment contributeMyDataFragmentInjector();

    @ContributesAndroidInjector(modules = StoreFragmentModule.class)
    abstract StoreFragment contributeStoreFragmentInjector();

    @ContributesAndroidInjector(modules = ShoppingMallFragmentModule.class)
    abstract ShoppingMallFragment contributeShoppingMallFragmentInjector();

    @ContributesAndroidInjector(modules = OrderToMaintenanceFragmentModule.class)
    abstract OrderToMaintenanceFragment contributeOrderToMaintenanceFragmentInjector();


    @ContributesAndroidInjector()
    abstract CarBrandFragment contributeCarSelectFragmentInjector();


    @ContributesAndroidInjector()
    abstract CommodityForMaintanceFragment contributeCommodityForMaintanceFragmentInjector();


    @ContributesAndroidInjector()
    abstract ConfirmMaintanceOrderFragment contributeConfirmMaintanceOrderFragmentInjector();


    @ContributesAndroidInjector()
    abstract OrderSuccessFragment contributeOrderSuccessFragmentInjector();


    @ContributesAndroidInjector()
    abstract CommodityOrderDetailFragment contributeCommodityOrderDetailFragmentInjector();


    @ContributesAndroidInjector()
    abstract CommodityOrderFragment contributeCommodityOrderFragmentInjector();


    @ContributesAndroidInjector()
    abstract ExchangeCommoditySuccessFragment contributeExchangeCommoditySuccessFragmentInjector();


    @ContributesAndroidInjector()
    abstract MyPointsFragment contributeMyPointsFragmentInjector();


    @ContributesAndroidInjector()
    abstract PointCenterFragment contributePointCenterFragmentInjector();


    @ContributesAndroidInjector()
    abstract ClipHeaderImgFragment contributeClipHeaderImgFragmentInjector();


    @ContributesAndroidInjector()
    abstract JoinUsFragment contributeJoinUsFragmentInjector();


    @ContributesAndroidInjector()
    abstract NameFragment contributeNameFragmentInjector();


    @ContributesAndroidInjector()
    abstract PhoneNumberFragment contributePhoneNumberFragmentInjector();


    @ContributesAndroidInjector()
    abstract SettingFragment contributeSettingFragmentInjector();


    @ContributesAndroidInjector()
    abstract MyMaintanceOrderDetailFragment contributeMyMaintanceOrderDetailFragmentInjector();

    @ContributesAndroidInjector()
    abstract MyVisitMaintanceFragment contributeMyVisitMaintanceFragmentInjector();

    @ContributesAndroidInjector()
    abstract EvaluateSuccessFragment contributeEvaluateSuccessFragmentInjector();

    @ContributesAndroidInjector()
    abstract InvitationFragment contributeInvitationFragmentInjector();


    @ContributesAndroidInjector()
    abstract MyLovelyCarFragment contributeMyLovelyCarFragmentInjector();

    @ContributesAndroidInjector()
    abstract OpinionFragment contributeOpinionFragmentInjector();

    @ContributesAndroidInjector()
    abstract ShoppingCartFragment contributeShoppingCartFragmentInjector();

    @ContributesAndroidInjector()
    abstract ToEvaluateFragment contributeToEvaluateFragmentInjector();

    @ContributesAndroidInjector()
    abstract CommodityDetailFragment contributeCommodityDetailFragmentInjector();

    @ContributesAndroidInjector()
    abstract CommodityEvaluateDetailFragment contributeCommodityEvaluateDetailFragmentInjector();

    @ContributesAndroidInjector()
    abstract CommodityEvaluateFragment contributeCommodityEvaluateFragmentInjector();

    @ContributesAndroidInjector()
    abstract CommodityRecommendFragment contributeCommodityRecommendFragmentInjector();

    @ContributesAndroidInjector()
    abstract ConfirmCommodityOrderFragment contributeConfirmOrderFragmentInjector();

    @ContributesAndroidInjector()
    abstract StoreDetailFragment contributeStoreDetailFragmentInjector();

    @ContributesAndroidInjector()
    abstract StoreEvaluateDetailFragment contributeStoreEvaluateDetailFragmentInjector();

    @ContributesAndroidInjector()
    abstract StoreEvaluateFragment contributeStoreEvaluateFragmentInjector();


    @ContributesAndroidInjector()
    abstract LoginFragment contributeLoginFragmentInjector();

    @ContributesAndroidInjector(modules = CityPickerDialogFragmentModule.class)
    abstract CityPickerDialogFragment CityPickerDialogFragmentInjector();


    @ContributesAndroidInjector()
    abstract SearchFragment SearchFragmentInjector();



    @ContributesAndroidInjector()
    abstract VerifyMobilePhoneDialogFragment VerifyMobilePhoneDialogFragmentInjector();



    @ContributesAndroidInjector()
    abstract VerifyPhoneDialogFragment VerifyPhoneDialogFragmentInjector();



    @ContributesAndroidInjector()
    abstract CarSerialFragment CarSerialFragmentInjector();


    @ContributesAndroidInjector()
    abstract CarMotorFragment CarDisplacementFragmentInjector();


    @ContributesAndroidInjector()
    abstract CarHorsePowerFragment CarTypeFragmentInjector();


    @ContributesAndroidInjector()
    abstract MyCommodityOrderFragment MyCommodityOrderFragmentInjector();


    @ContributesAndroidInjector()
    abstract NeedToEvaluateFragment NeedToEvaluateFragmentInjector();

    @ContributesAndroidInjector()
    abstract NeedToPayFragment NeedToPayFragmentInjector();

    @ContributesAndroidInjector()
    abstract NeedToSendFragment NeedToSendFragmentInjector();

    @ContributesAndroidInjector()
    abstract NeedToRecieveFragment NeedToRecieveFragmentInjector();

    @ContributesAndroidInjector()
    abstract ReturnOrChangeFragment ReturnOrChangeFragmentInjector();

    @ContributesAndroidInjector()
    abstract MyMaintanceOrderFragment MyMaintanceOrderFragmentInjector();


    //我的预约保养订单
    @ContributesAndroidInjector()
    abstract MaintanceOrderFragment MaintanceOrderFragmentInjector();
    @ContributesAndroidInjector()
    abstract MNeedToEvaluateFragment MNeedToEvaluateFragmentInjector();
    @ContributesAndroidInjector()
    abstract MNeedToPayFragment MNeedToPayFragmentInjector();
    @ContributesAndroidInjector()
    abstract MHaveBeenCompleteFragment MHaveBeenCompleteFragmentInjector();
    @ContributesAndroidInjector()
    abstract MNeedToServeFragment MNeedToServeFragmentInjector();
    @ContributesAndroidInjector()
    abstract MHaveBeenCanceledFragment MHaveBeenCanceledFragmentInjector();

    @ContributesAndroidInjector()
    abstract MyAddressFragment MyAddressFragmentInjector();


    @ContributesAndroidInjector()
    abstract EditAddressFragment EditAddressFragmentInjector();


    @ContributesAndroidInjector()
    abstract UserInfoFragment UserInfoFragmentInjector();

    @ContributesAndroidInjector()
    abstract AboutFragment AboutFragmentInjector();

    @ContributesAndroidInjector()
    abstract LogisticsFragment LogisticsFragmentInjector();


    @ContributesAndroidInjector()
    abstract IntegralCenterFragment IntegralCenterFragmentInjector();

    @ContributesAndroidInjector()
    abstract PointFragment PointFragmentInjector();

    @ContributesAndroidInjector()
    abstract ThankForEvaluateFragment ThankForEvaluateFragmentInjector();

    @ContributesAndroidInjector()
    abstract ReturnChangeSelectFragement ReturnChangeSelectFragementInjector();

    @ContributesAndroidInjector()
    abstract ApplyReturnChangeFragment ApplyReturnChangeFragmentInjector();

    @ContributesAndroidInjector()
    abstract ReasonFragment ReasonFragmentInjector();
    @ContributesAndroidInjector()
    abstract GoodsStatusFragment GoodsStatusFragmentInjector();

    @ContributesAndroidInjector()
    abstract PaymentSuccessFragment PaymentSuccessFragmentInjector();

    @ContributesAndroidInjector()
    abstract MyIntegralCenterSignFagment MyIntegralCenterSignFagmentInjector();

    @ContributesAndroidInjector
    abstract CouponViewPagerFragment  CouponViewPagerFragmentInjector();

    @ContributesAndroidInjector
    abstract MyCouponMainFragment myCouponMainFragmentInjector();

    @ContributesAndroidInjector
    abstract CouponReceiveFragment couponReceiveFragmentInjector();


    @ContributesAndroidInjector
    abstract Fragment_InvitesCourtesy fragment_invitesCourtesyInjector();


    /**
     * 预约订单确认页--》优惠券选择
     */

    @ContributesAndroidInjector
    abstract OrderFragmentSelectCouponFragment fragmentSelectCouponFragmentInjector();

    @ContributesAndroidInjector(modules = Fragment_Exchange_Mall_Module.class)
    abstract Fragment_Exchange_mall Fragment_Exchange_mallInjector();

    @ContributesAndroidInjector(modules = Fragment_Exchange_shopping_detail_Module.class)
    abstract Fragment_exchange_shopping_detail Fragment_exchange_shopping_detailInjector();

    @ContributesAndroidInjector(modules = FragmentPayPass_Module.class)
    abstract FragmentPayPass FragmentPayPassInjector();
}

package com.yinglan.scg.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.yinglan.scg.R;
import com.yinglan.scg.message.InteractiveMessageFragment;
import com.yinglan.scg.message.SystemMessageFragment;
import com.yinglan.scg.message.interactivemessage.imuitl.RongIMUtil;

/**
 * 消息
 * Created by Admin on 2017/8/10.
 */

public class MessageFragment extends BaseFragment {

    private MainActivity aty;

    @BindView(id = R.id.tv_interactiveMessage, click = true)
    private TextView tv_interactiveMessage;

    @BindView(id = R.id.tv_systemMessage, click = true)
    private TextView tv_systemMessage;

    /**
     * 用来表示移动的Fragment
     */
    private int chageIcon;

    private BaseFragment contentFragment;
    private BaseFragment contentFragment1;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_message, null);
    }

    @Override
    protected void initData() {
        super.initData();
        contentFragment = new InteractiveMessageFragment();
        contentFragment1 = new SystemMessageFragment();
        chageIcon = aty.getIntent().getIntExtra("chageMessageIcon", 20);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        if (chageIcon == 20) {
            chageIcon = 20;
            cleanColors(20);
            changeFragment(contentFragment);
            RongIMUtil.connectRongIM(getActivity());
        } else if (chageIcon == 21) {
            chageIcon = 21;
            cleanColors(21);
            changeFragment(contentFragment1);
        } else {
            chageIcon = 20;
            cleanColors(20);
            changeFragment(contentFragment);
            RongIMUtil.connectRongIM(getActivity());
        }
    }


    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.main_content, targetFragment);
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_interactiveMessage:
                chageIcon = 20;
                cleanColors(20);
                changeFragment(contentFragment);
                break;
            case R.id.tv_systemMessage:
                chageIcon = 21;
                cleanColors(21);
                changeFragment(contentFragment1);
                break;
            default:
                break;
        }
    }

    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
    @Override
    public void onChange() {
        super.onChange();

    }


//    private void setTabSelection(int index) {
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        hideFragment(fragmentTransaction);
//        switch (index) {
//            case 0:
//                chageIcon = 20;
//                cleanColors(20);
//                Fragment conversationListFragment = initConversationList(fragmentTransaction);
//                fragmentTransaction.show(conversationListFragment);
//                break;
//            case 1:
//                chageIcon = 21;
//                cleanColors(21);
//                SystemMessageFragment systemMsgFragment = (SystemMessageFragment) fragmentManager.findFragmentByTag(TAB_SYSTEM_MSG);
//                if (systemMsgFragment == null) {
//                    fragmentTransaction.add(ID, new SystemMessageFragment(), TAB_SYSTEM_MSG);
//                } else {
//                    fragmentTransaction.show(systemMsgFragment);
//                }
//                break;
//
//        }
//        fragmentTransaction.commit();
//    }


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_interactiveMessage.setTextColor(getResources().getColor(R.color.titletextcolors));
        tv_systemMessage.setTextColor(getResources().getColor(R.color.titletextcolors));
        if (chageIcon == 20) {
            tv_interactiveMessage.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 21) {
            tv_systemMessage.setTextColor(getResources().getColor(R.color.greenColors));
        } else {
            tv_interactiveMessage.setTextColor(getResources().getColor(R.color.greenColors));
        }
    }

//    private void hideFragment(FragmentTransaction fragmentTransaction) {
//        SystemMessageFragment systemMsgFragment = (SystemMessageFragment) fragmentManager.findFragmentByTag(TAB_SYSTEM_MSG);
//        if (systemMsgFragment != null) {
//            fragmentTransaction.hide(systemMsgFragment);
//        }
//
//        ConversationListFragment conversationListFragment = (ConversationListFragment) fragmentManager.findFragmentByTag(TAB_CUSTOM_MSG);
//        if (conversationListFragment != null) {
//            fragmentTransaction.hide(conversationListFragment);
//        }
//    }

    /**
     * 刷新会话消息
     */
    public void refreshRedDot(int count) {
//        if (count > 0) {
//            tvCustomNotice.setVisibility(View.VISIBLE);
//        } else {
//            tvCustomNotice.setVisibility(View.INVISIBLE);
//        }
    }

    /**
     * 登陆成功后 刷新系统消息
     */
    public void refreshSystemMsg() {
        //   SystemMessageFragment systemMsgFragment = (SystemMessageFragment) fragmentManager.findFragmentByTag(TAB_SYSTEM_MSG);
//        if (systemMsgFragment != null) {
//            //  systemMsgFragment.refreshAutoData();
//        }
    }

//    private Fragment initConversationList(FragmentTransaction fragmentTransaction) {
//        if (mConversationListFragment == null) {
//            ConversationListFragment listFragment = new ConversationListFragment();
//            Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
//                    .appendPath("conversationlist")
//                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
//                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
//                    .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
//                    .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
//                    .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
//                    .build();
////            mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
////                    Conversation.ConversationType.GROUP,
////                    Conversation.ConversationType.PUBLIC_SERVICE,
////                    Conversation.ConversationType.APP_PUBLIC_SERVICE,
////                    Conversation.ConversationType.SYSTEM
////            };
//            listFragment.setUri(uri);
//            mConversationListFragment = listFragment;
//            fragmentTransaction.add(ID, mConversationListFragment, TAB_CUSTOM_MSG);
//            return listFragment;
//        } else {
//            return mConversationListFragment;
//        }
//    }
}
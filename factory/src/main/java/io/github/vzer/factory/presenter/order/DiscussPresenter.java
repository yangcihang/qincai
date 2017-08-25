package io.github.vzer.factory.presenter.order;

import io.github.vzer.common.factory.presenter.BasePresenter;
import io.github.vzer.factory.model.order.DiscussModel;

/**
 * @author YangCihang
 * @since 17/8/23.
 * email yangcihang@hrsoft.net
 */

public class DiscussPresenter extends BasePresenter<DiscussContract.View>
        implements DiscussContract.Presenter{
    /**
     * P层构造方法;
     * 创建P层时就进行双向绑定
     *
     * @param mView V层的引用
     */
    public DiscussPresenter(DiscussContract.View mView) {
        super(mView);
    }

    /**
     * 发表评论
     */
    @Override
    public void requestDiscussModel(DiscussModel model) {

    }
}

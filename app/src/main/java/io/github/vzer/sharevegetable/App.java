package io.github.vzer.sharevegetable;

import com.squareup.leakcanary.LeakCanary;

import io.github.vzer.common.app.Application;
import io.github.vzer.factory.Factory;

/**
 * @author: Vzer.
 * @date: 2017/7/25. 21:37
 * @email: vzer@qq.com
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        //对Factory进行初始化
        Factory.setup();
    }
}

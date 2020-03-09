package com.example.xposedtest;

import android.util.Log;
import android.webkit.WebViewClient;
import android.widget.Toast;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class HookMain implements IXposedHookLoadPackage {
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

//        findAndHookMethod(
//                "android.telephony.TelephonyManager",
//                loadPackageParam.classLoader,
//                "getDeviceId",
//                new XC_MethodHook(){
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam methodHookParam) throws Throwable {
//                    }
//
//                    protected void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable {
//                        methodHookParam.setResult("123456789123456");
//                    }
//                }
//        );
        if(!loadPackageParam.packageName.equals("com.ss.android.ugc.aweme")) //这里过滤一下包名
        {
            Log.e("error", "is not PackageName");
            return;
        }
        Log.i("Tiger_test","hook进入解锁程序");
        //Hook a方法
        findAndHookMethod(
                "com.hfdcxy.android.by.test.a",  //要hook的包名+类名
                loadPackageParam.classLoader,                   //classLoader固定
                "a",                                   //要hook的方法名
                String.class,                          //方法的参数类型 这里为String类
                new XC_MethodHook() {
                    @Override
                    //方法执行前执行
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Log.i("roherotest", "before");
                    }

                    //方法执行后执行,改方法的返回值一定要在方法执行完毕后更改
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {
                        Log.i("Tiger_test","a方法的第一个参数为:"+param.args[0].toString());//param.args[0]为方法的第一个参数,同理param.args[1]为第二个参数
                        Log.i("Tiger_test","a方法的返回值为:"+param.getResult());//方法的返回值只能放在afterHookedMethod中获取
                        //以下为修改
                        //param.args[0] = "235wtwerteq"  //如果要修改第一个参数可以直接这样写
                        //param.setResult("7f769c0f91efd402a23d63627f48f03e");   //param.setResult修改方法的返回值
                    }
                }
        );

        XposedBridge.log("web   hoooooooooook");
        findAndHookMethod(
                "android.webkit.WebViewClient",  //要hook的包名+类名
                loadPackageParam.classLoader,                   //classLoader固定
                "setWebViewClient",                                   //要hook的方法名
                WebViewClient.class,                          //方法的参数类型 这里为String类
                new XC_MethodHook() {
                    @Override
                    //方法执行前执行
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        param.args[0] = "shabi";
                        param.setResult("shabi");
                    }

                    //方法执行后执行,改方法的返回值一定要在方法执行完毕后更改
                    protected void afterHookedMethod(MethodHookParam param)
                            throws Throwable {
                        Log.i("Tiger_test","a方法的第一个参数为:"+param.args[0].toString());//param.args[0]为方法的第一个参数,同理param.args[1]为第二个参数
                        Log.i("Tiger_test","a方法的返回值为:"+param.getResult());//方法的返回值只能放在afterHookedMethod中获取
                        //以下为修改
                        //param.args[0] = "235wtwerteq"  //如果要修改第一个参数可以直接这样写
                        //param.setResult("7f769c0f91efd402a23d63627f48f03e");   //param.setResult修改方法的返回值
                        param.setResult("shabi");
                    }
                }
        );
    }
}

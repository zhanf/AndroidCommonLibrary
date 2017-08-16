package spider.commonlibrary.permission;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */

public interface PermissionListener {

    //授权成功
    void onGranted();

    // 授权失败的回调，返回未授权的一些权限
    void onDenied(List<String> deniedPermission);
}

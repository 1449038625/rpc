package com.zbz.rpc.model;

import cn.hutool.core.util.StrUtil;
import com.zbz.rpc.constant.RpcConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classname: ServiceMetaInfo
 * Package: com.zbz.rpc.model
 * Decription:服务的注册信息封装类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/5 - 14:59
 * @Version: v1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceMetaInfo {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 服务的版本号
     */
    private String serviceVersion= RpcConstant.DEFAULT_SERVICE_VERSION;
    /**
     * 服务的域名
     */
    private String serviceHost="localhost";
    /**
     * 服务的端口号
     */
    private Integer servicePort;
    /**
     * 服务分组
     */
    private String serviceGroup="default";

    /**
     * 获取服务键名，类似于接口地址
      * @return
     */
    public String getServiceKey(){
        return String.format("%s:%s",serviceName,serviceVersion);
    }
    /**
     * 获取服务节点键名，类似于实现类地址
     * @return
     */
    public String getServiceNodeKey(){
        return String.format("%s/%s:%s",getServiceKey(),serviceHost,servicePort);
    }

    /**
     * 获取完整服务地址
     *
     * @return
     */
    public String getServiceAddress() {
        if (!StrUtil.contains(serviceHost, "http")) {
            return String.format("http://%s:%s", serviceHost, servicePort);
        }
        return String.format("%s:%s", serviceHost, servicePort);
    }
}

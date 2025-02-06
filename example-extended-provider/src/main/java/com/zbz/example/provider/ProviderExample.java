package com.zbz.example.provider;

import com.zbz.example.common.service.UserService;
import com.zbz.rpc.bootstrap.ProviderBootstrap;
import com.zbz.rpc.model.ServiceRegisterInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classname: EasyProviderExample
 * Package: com.zbz.example.provider
 * Decription:服务启动类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 10:30
 * @Version: v1.0
 */
public class ProviderExample {
    public static void main(String[] args) {
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo<UserService> serviceRegisterInfo = new ServiceRegisterInfo<>();
        serviceRegisterInfo.setServiceName(UserService.class.getName());
        serviceRegisterInfo.setImplClass(UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}

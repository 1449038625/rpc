package com.zbz.rpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classname: ServiceRegisterInfo
 * Package: com.zbz.rpc.model
 * Decription:注册类
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/6 - 21:08
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ServiceRegisterInfo<T> {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 实现类
     */
    private Class<? extends T> implClass;
}

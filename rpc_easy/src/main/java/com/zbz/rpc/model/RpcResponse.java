package com.zbz.rpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Classname: RpcResponse
 * Package: com.zbz.rpc.model
 * Decription:封装响应的信息
 *
 * @Author: 爱可尼科
 * @Create: 2025/2/4 - 14:50
 * @Version: v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RpcResponse implements Serializable {
    /**
     * 响应数据
     */
    private Object data;
    /**
     * 响应数据类型
     */
    private Class<?> dataType;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 异常信息
     */
    private Exception exception;
}

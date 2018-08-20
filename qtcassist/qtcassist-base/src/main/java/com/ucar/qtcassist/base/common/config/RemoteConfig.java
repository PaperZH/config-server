package com.ucar.qtcassist.base.common.config;

import com.zuche.framework.extend.hessian.SerializerFactoryExtendClient;
import com.zuche.framework.remote.StartLoadRemoteService;
import com.zuche.framework.remote.hessian.ExtendHessianProxyFactory;
import com.zuche.framework.remote.hessian.HessianRemoteClientInternal;
import com.zuche.framework.remote.http.HttpRemoteClientInternal;
import com.zuche.framework.remote.nio.client.TcpRemoteClientInternal;
import com.zuche.framework.remote.nio.client.UdpRemoteClientInternal;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Description: 公司远程调用配置
 *
 * @author wangyang
 * @since 2018/8/14
 */
@Configuration
public class RemoteConfig {

    @Bean(name="remote.serializerFactory.client")
    public SerializerFactoryExtendClient serializerFactoryClient () {
        return new SerializerFactoryExtendClient();
    }

    @Bean(name="remote.hessianProxyFactory")
    public ExtendHessianProxyFactory hessianProxyFactory () {
        ExtendHessianProxyFactory extendHessianProxyFactory = new ExtendHessianProxyFactory();
        extendHessianProxyFactory.setSerializerFactory(serializerFactoryClient());
        extendHessianProxyFactory.setConnectTimeout(15000);
        extendHessianProxyFactory.setReadTimeout(30000);
        return extendHessianProxyFactory;
    }

    @Bean(name="remote.hessianRemoteClientInternal")
    public HessianRemoteClientInternal hessianClient () {
        HessianRemoteClientInternal hessianRemoteClientInternal = new HessianRemoteClientInternal();
        hessianRemoteClientInternal.setHessianFactory(hessianProxyFactory());
        return hessianRemoteClientInternal;
    }

    @Bean(name="remote.tcpRemoteClientInternal")
    public TcpRemoteClientInternal tcpRemoteClient () {
        TcpRemoteClientInternal tcpRemoteClientInternal = new TcpRemoteClientInternal();
        return tcpRemoteClientInternal;
    }

    @Bean(name="remote.udpRemoteClientInternal")
    public UdpRemoteClientInternal udpRemoteClient () {
        UdpRemoteClientInternal udpRemoteClientInternal = new UdpRemoteClientInternal();
        return udpRemoteClientInternal;
    }

    @Bean(name="remote.httpRemoteClientInternal")
    public HttpRemoteClientInternal httpRemoteClient () {
        HttpRemoteClientInternal httpRemoteClientInternal = new HttpRemoteClientInternal();
        httpRemoteClientInternal.setConnectTimeout(15000);
        httpRemoteClientInternal.setReadTimeout(30000);
        return httpRemoteClientInternal;
    }

    @Bean(name="startInit.StartLoadRemoteService",initMethod="loadServices")
    @Lazy(value = false)
    public StartLoadRemoteService startLoadRemoteService () {
        StartLoadRemoteService startLoadRemoteService = new StartLoadRemoteService();
        return startLoadRemoteService;
    }
}

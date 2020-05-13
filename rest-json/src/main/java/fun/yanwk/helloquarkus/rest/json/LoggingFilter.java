package fun.yanwk.helloquarkus.rest.json;

import io.vertx.core.http.HttpServerRequest;
import org.jboss.logging.Logger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * HTTP 过滤器／拦截器
 * 可以用来处理： HTTP 头、请求参数、媒体类型、metadata，以及用户鉴权等
 * 请求： ContainerRequestFilter
 * 响应： ContainerResponseFilter
 * <p>
 * 本例为日志功能
 */
@Provider // 试该类可被扫描到（作为扩展接口的实现）
public class LoggingFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class);

    @Context // 上下文注入
        UriInfo info;

    @Context
    HttpServerRequest request;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        final String method = requestContext.getMethod();
        final String path = info.getPath();
        final String address = request.remoteAddress().toString();

        LOGGER.infof("来自 IP %s 请求 %s %s", address, method, path);
    }

}

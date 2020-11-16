package cn.tedu.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作为网关filter 条件
 * 1 继承ZuulFilter 实现方法
 * 2 让过滤器对象在容器保存管理
 */
@Component
public class MyFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //验证请求地址uri是否是以/zuul-a开始的
        //网关中封装了请求上下文.拿到请求,响应
        RequestContext context=RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        //解析uri
        String uri=request.getRequestURI();
        return uri.startsWith("/zuul-a");
    }

    @Override
    public Object run() throws ZuulException {
        //验证一下 请求对象中是否存在一个参数 name 不存在
        //返回失败,不让继续通行,调用后端微服务.
        System.out.println("拦住了,要执行过滤逻辑");
        RequestContext context=RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        response.setContentType("text/html;charset=utf-8");
        //获取参数 servlet的api
        String name = request.getParameter("name");
        //如果为空 null ""都不允许向后调用
        if(StringUtils.isEmpty(name)){
            //拦住 响应直接返回
            context.setResponseStatusCode(401);//没有身份认证
            context.setResponseBody(
                    "parameter name is null,请求参数非法"
            );
            context.setSendZuulResponse(false);//后续过滤调用逻辑 false
        }
        return null;
    }
}

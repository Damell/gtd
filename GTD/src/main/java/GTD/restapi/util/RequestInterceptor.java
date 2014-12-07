package GTD.restapi.util;

import org.springframework.web.servlet.HandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.DispatcherType;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Drugnanov on 7.12.2014.
 */
public class RequestInterceptor implements HandlerInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler) throws Exception {
    //show only true requests
    if (!request.getDispatcherType().equals(DispatcherType.REQUEST))
      return true;
    logger.info("Received HTTP request with URL:" + request.getRequestURL());
    try {
      ServletInputStream in = request.getInputStream();
      byte[] buf = new byte[1000];
      StringBuilder sb = new StringBuilder();
      for (int nChunk = in.read(buf); nChunk != -1; nChunk = in.read(buf)) {
        sb.append(new String(buf, 0, nChunk));
      }
      logger.info("Request JSON Content" + sb.toString());
    }
    catch (Throwable e) {
      logger.warn("Cannot cast request to string.");
    }
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    return;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                         ModelAndView modelAndView) throws Exception {
    return;
  }
}
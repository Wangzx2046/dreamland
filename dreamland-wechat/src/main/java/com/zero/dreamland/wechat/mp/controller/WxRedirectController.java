package com.zero.dreamland.wechat.mp.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Edward
 */
@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping("/wx/redirect/{appid}")
public class WxRedirectController {
    private final WxMpService wxService;


    /**
     * 获取用户信息
     * 第一步：用户同意授权，获取code
     *
     * @param session
     * @param request
     * @param response
     * @throws IOException
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    @SuppressWarnings("deprecation")
    @GetMapping("/holderLogin")
    public void login1(HttpSession session, HttpServletRequest request, HttpServletResponse response
            , @PathVariable("appid") String appid
    ) throws IOException, NoSuchFieldException, SecurityException, WxErrorException {

        String url = "http://faker.51vip.biz/devil/wx/redirect/" + appid + "/callBack?targetUrl=toIndex";
        String s = wxService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, "STATE#wechat_redirect");

     /*   String url = "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + WeiXinUtil.WECHAT_BASE_MAP.get("APPID_" + sooType.toUpperCase())
                + "&redirect_uri=" + URLEncoder.encode(redirect_uri + "/" + sooType + "/" + id)
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=STATE#wechat_redirect";*/

        response.sendRedirect(s);
    }


    @RequestMapping("/callBack")
    public void greetUser(HttpServletRequest request, HttpServletResponse response, @PathVariable String appid, @RequestParam String code,
                          @RequestParam String targetUrl) throws ServletException, IOException {

        if (!this.wxService.switchover(appid)) {
            throw new IllegalArgumentException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }

        try {
            WxMpOAuth2AccessToken accessToken = wxService.oauth2getAccessToken(code);
            WxMpUser user = wxService.oauth2getUserInfo(accessToken, null);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        log.info("当前请求跳转==》" + targetUrl);
        request.getRequestDispatcher(targetUrl).forward(request, response);


    }
}

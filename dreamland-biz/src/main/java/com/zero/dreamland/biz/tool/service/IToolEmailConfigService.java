package com.zero.dreamland.biz.tool.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zero.dreamland.biz.tool.entity.ToolEmailConfig;
import com.zero.dreamland.common.email.domain.EmailVo;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 邮箱配置 服务类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-07-03
 */
public interface IToolEmailConfigService extends IService<ToolEmailConfig> {

    List<ToolEmailConfig> list(ToolEmailConfig toolEmailConfig);

    int config(ToolEmailConfig emailConfig, ToolEmailConfig old) throws Exception;

    void send(EmailVo emailVo, ToolEmailConfig emailConfig);

    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendSimpleMail(String to, String subject, String content);


    /**
     * 发送HTML邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    void sendHtmlMail(String to, String subject, String content);


    /**
     * 发送带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void sendTemplateMail(String to, String subject, Map<String, Object> paramMap, String template) throws MessagingException;
}

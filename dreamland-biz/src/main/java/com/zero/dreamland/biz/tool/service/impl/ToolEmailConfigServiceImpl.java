package com.zero.dreamland.biz.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zero.dreamland.biz.tool.dao.ToolEmailConfigDao;
import com.zero.dreamland.biz.tool.entity.ToolEmailConfig;
import com.zero.dreamland.biz.tool.service.IToolEmailConfigService;
import com.zero.dreamland.common.email.domain.EmailVo;
import com.zero.dreamland.common.exception.BadRequestException;
import com.zero.dreamland.common.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 邮箱配置 服务实现类
 * </p>
 *
 * @author Wang.zx
 * @since 2020-07-03
 */
@Slf4j
@Service
public class ToolEmailConfigServiceImpl extends ServiceImpl<ToolEmailConfigDao, ToolEmailConfig> implements IToolEmailConfigService {

    @Resource
    private ToolEmailConfigDao toolEmailConfigDao;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;
    
    @Value("apring.mail.username")
    private String from;

    @Override
    public List<ToolEmailConfig> list(ToolEmailConfig toolEmailConfig) {
        QueryWrapper<ToolEmailConfig> queryWrapper = new QueryWrapper<>(toolEmailConfig);
        if (StringUtils.isNotBlank(toolEmailConfig.getBlurry())) {
            queryWrapper.eq("name", toolEmailConfig.getBlurry())
                    .or().eq("description", toolEmailConfig.getBlurry());
        }
        return toolEmailConfigDao.selectList(queryWrapper);
    }

    @Override
    public int config(ToolEmailConfig emailConfig, ToolEmailConfig old) throws Exception {
        emailConfig.setId(old.getId());
        if (!emailConfig.getPass().equals(old.getPass())) {
            // 对称加密
            emailConfig.setPass(EncryptUtils.desEncrypt(emailConfig.getPass()));
        }
        return toolEmailConfigDao.updateById(emailConfig);
    }

    @Override
    public void send(EmailVo emailVo, ToolEmailConfig emailConfig) {
        try {
            int size = emailVo.getTos().size();
            for (int i = 0; i < size; i++) {
                sendSimpleMail(emailVo.getTos().get(i), emailVo.getSubject(), emailVo.getContent());
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }


    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        //创建SimpleMailMessage对象
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送人
        message.setFrom(from);
        //邮件接收人
        message.setTo(to);
        //邮件主题
        message.setSubject(subject);
        //邮件内容
        message.setText(content);
        //发送邮件
        mailSender.send(message);
    }

    /**
     * html邮件
     *
     * @param to      收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        //获取MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(from);
            //邮件接收人
            messageHelper.setTo(subject);
            //邮件主题
            message.setSubject(subject);
            //邮件内容，html格式
            messageHelper.setText(content, true);
            //发送
            mailSender.send(message);
            //日志信息
            log.info("邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常！", e);
        }
    }

    /**
     * 带附件的邮件
     *
     * @param to       收件人
     * @param subject  主题
     * @param content  内容
     * @param filePath 附件
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            mailSender.send(message);
            //日志信息
            log.info("邮件已经发送。");
        } catch (MessagingException e) {
            log.error("发送邮件时发生异常！", e);
        }


    }


    /**
     * 发送模版邮件
     *
     * @param to
     * @param subject
     * @param paramMap
     * @param template
     * @throws MessagingException
     */
    @Override
    public void sendTemplateMail(String to, String subject, Map<String, Object> paramMap, String template)
            throws MessagingException {


        try {
            //创建一个mime类型的消息体
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //设置邮件的四要素：发送人、接收者、主题、内容
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);

            Context context = new Context();
            // 设置变量的值
            context.setVariables(paramMap);
            String emailContent = templateEngine.process(template, context);

            helper.setText(emailContent, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }


}

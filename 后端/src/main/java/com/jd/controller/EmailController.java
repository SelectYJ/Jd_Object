package com.jd.controller;

import com.jd.entity.Result;
import com.jd.entity.ToEmail;
import com.jd.util.CreateCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("getEmail")
public class EmailController {
    //	引入邮件接口
    @Autowired
    private JavaMailSender mailSender;
    // 获得发件人信息
    @Value("${spring.mail.username}")
    private String from;

    // 存放发送给邮箱的验证码
    private String emailCode = "";

    // 设置验证码过期时间
    private Long expTime = 2 * 60 * 1000L;

    // 发送验证码的时间
    private Long sendTime = 0L;

    /**
     * 发送验证码
     * @param toEmail
     * @return
     */
    @GetMapping("sendEmail")
    public Result commonEmail(ToEmail toEmail) {
        Long nowTime = System.currentTimeMillis();
        log.info("当前时间与发送验证码时间间隔为：{}", (nowTime - sendTime));
        if(sendTime == 0||(nowTime - sendTime)>=expTime){
            // 创建邮件消息
            SimpleMailMessage message = new SimpleMailMessage();
            // 从哪个地址
            message.setFrom(from);
            // 获取要发送到哪个邮箱地址
            message.setTo(toEmail.getTos());
            message.setSubject("您本次的验证码是");
            emailCode = CreateCodeUtil.getCode(6);
            message.setText("尊敬的" + toEmail.getTos().toString() + ",您好:\n"
                    + "\n本次请求的邮件验证码为:" + emailCode + ",本验证码 5 分钟内效，请及时输入。（请勿泄露此验证码）\n"
                    + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）");
            mailSender.send(message);
            sendTime = System.currentTimeMillis();
            log.info("向{}发送验证码成功，验证码为：{}，发送时间为：{}", toEmail.getTos().toString(), emailCode, sendTime);
            return Result.success("发送成功");
        }
        return Result.success("已经发送过了，快去邮箱查看吧！！");
    }

    /**
     * 判断验证码是否正确
     * @param code
     * @return
     */
    @GetMapping("codeIsOk")
    public Result codeIsOk(String code) {
        Long nowTime = System.currentTimeMillis();
        log.info("用户输入的验证码为：{}，发送的验证码为：{}，已过{}ms", code, emailCode,(nowTime - sendTime));
        if((nowTime - sendTime) < expTime){
            if (code.equals(emailCode)) {
                return Result.success();
            }
            return Result.error("验证码不正确！！");
        }
        emailCode = "";
        return Result.error("验证码已过期！！请重新发送！！");
    }
}

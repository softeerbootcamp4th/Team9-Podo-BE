package com.softeer.podo.verification.service;

import com.softeer.podo.verification.exception.MessageSendFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.FailedMessage;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

    @Value("${message.sender}")
    private String MESSAGE_SENDER;

    @Value("${message.api-key}")
    private String API_KEY;

    @Value("${message.api-secret-key}")
    private String API_SECRET_KEY;

    @Value("${message.domain}")
    private String MESSAGE_DOMAIN;

    /**
     * 외부 API와 연결되어 인증번호를 발송한다.
     */
    public void sendVerificationMessage(String phoneNum, String code) {
        DefaultMessageService messageService =  NurigoApp.INSTANCE.initialize(API_KEY, API_SECRET_KEY, MESSAGE_DOMAIN);
        Message message = new Message();
        message.setFrom(MESSAGE_SENDER);
        message.setTo(phoneNum);
        message.setText("\n[현대자동차] 인증번호: ["+code+"]입니다 - 타인 노출 금지");

        try {
            messageService.send(message);
        } catch (NurigoMessageNotReceivedException exception) {
            List<FailedMessage> failedMessageList = exception.getFailedMessageList();
            for (FailedMessage failedMessage : failedMessageList) {
                log.info("Message Send Failed! :: {}", failedMessage);
            }
            throw new MessageSendFailException("메시지 전송에 실패했습니다.");
        } catch (Exception exception) {
            throw new MessageSendFailException("기타 이유로 메시지 전송에 실패했습니다.");
        }
    }

}

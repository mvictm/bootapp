package com.max.education.quartz.jobs;

import com.max.education.baseapplication.entity.PersonH2;
import com.max.education.baseapplication.repository.PersonRepoH2;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * Simple Job, which send email to users from database (H2).
 *
 * @author Max
 */

@Slf4j
@Component
@DisallowConcurrentExecution
public class MailSenderJob implements Job {
    @Autowired
    private PersonRepoH2 personRepoH2;
    @Autowired
    private JavaMailSender sender;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        log.info("JOB START. Detail: {}, Current time: {}", jobExecutionContext.getJobDetail().getKey().getName(), jobExecutionContext.getFireTime());
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        if (!personRepoH2.findAll().isEmpty()) {
            for (PersonH2 personH2 : personRepoH2.findAll()) {
                helper.setTo(personH2.getEmail());
                helper.setText("Пора пить чай!");
                helper.setSubject("Hi");

                sender.send(message);
                log.info("Send message to " + personH2.getEmail());
            }
        } else {
            log.info("Database is empty!");
        }
        log.info("JOB FINISH. Detail: {}, Next start: {}", jobExecutionContext.getJobDetail().getKey().getName(), jobExecutionContext.getNextFireTime());
    }
}
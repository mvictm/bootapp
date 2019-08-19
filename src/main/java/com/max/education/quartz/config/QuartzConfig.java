package com.max.education.quartz.config;

import com.max.education.quartz.jobs.MailSenderJob;
import com.max.education.quartz.ustils.MyHolidayCreator;
import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * Beans for Quartz Scheduler.
 *
 * @author Max
 */

@Configuration
@RequiredArgsConstructor
public class QuartzConfig {

    @Value("${cronExpression}")
    private String cronExpression;

    private final ApplicationContext applicationContext;
    private final MyHolidayCreator myHolidayCreator;

    @Bean
    @Qualifier("Factory")
    public SpringBeanJobFactory getAutowiringSpringBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    @Qualifier("JobDetail")
    public JobDetail buildJobDetail() {
        return JobBuilder.newJob().ofType(MailSenderJob.class)
                .withIdentity("MailJob")
                .storeDurably(true)
                .build();
    }

    @Bean
    @Qualifier("Trigger")
    @Primary
    public CronTrigger buildCronSchedulerTrigger(@Qualifier("JobDetail") JobDetail jobDetail) {
        return TriggerBuilder
                .newTrigger()
                .withIdentity("Trigger")
                .withSchedule(
                        CronScheduleBuilder
                                .cronSchedule(cronExpression)
                                .withMisfireHandlingInstructionDoNothing())
                .modifiedByCalendar("MyCalendar")
                .forJob(jobDetail)
                .build();
    }

    @Bean
    @Qualifier("Calendar")
    public HolidayCalendar holidayCalendar() {
        HolidayCalendar holidayCalendar = new HolidayCalendar();
        myHolidayCreator.getAllDates().forEach(holidayCalendar::addExcludedDate);
        return holidayCalendar;
    }

    @Bean
    @Primary
    public Scheduler scheduler(@Qualifier("JobDetail") JobDetail buildJobDetail,
                               @Qualifier("Trigger") Trigger buildSchedulerTrigger,
                               @Qualifier("Factory") SpringBeanJobFactory springBeanJobFactory,
                               @Qualifier("Calendar") HolidayCalendar holidayCalendar) throws SchedulerException {

        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.setJobFactory(springBeanJobFactory);
        scheduler.scheduleJob(buildJobDetail, buildSchedulerTrigger);
        scheduler.addCalendar("MyCalendar", holidayCalendar, false, true);

        scheduler.start();
        return scheduler;
    }
}
package CRUD3.CRUD3.controller;

import CRUD3.CRUD3.model.ReturnString;
import CRUD3.CRUD3.services.impl.Productimpl.parse.ScheduledJob;
import org.joda.time.DateTime;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@RestController
@RequestMapping("/scheduled")
public class ScheduledController {

    @Autowired
    private ParseController parseController;

    private Scheduler scheduler;
    private CronExpression cronExpression;
    private boolean disableStart = false;
    private boolean disableStop = true;
//    private  String citilink;
//    private  String dns;
//    private  String pc;
//    private  String monitor;
//    private  String printer;
//    private  String userName;
//    private  String cron;
//
//    public ScheduledController() {
//    }
//
//    public ScheduledController(String citilink, String dns, String pc, String monitor, String printer, String userName, String cron) {
//        this.citilink = citilink;
//        this.dns = dns;
//        this.pc = pc;
//        this.monitor = monitor;
//        this.printer = printer;
//        this.userName = userName;
//        this.cron = cron;
//    }

//    public void reportCurrentTime() throws NoSuchMethodException, SchedulerException {
////        ScheduledJob scheduledJob = new ScheduledJob(citilink, dns, pc, monitor, printer, userName);
////        JobDetail jobDetail = JobBuilder.newJob(scheduledJob.getClass()).withIdentity("job").build();
////        String cron = "0 0/1 * 1/1 * ? *";
////        Trigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
////
////        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
////
////        scheduler.scheduleJob(jobDetail, cronTrigger);
////
////        scheduler.start();
////        parseController.ParseStart("", "", "", "", "", "");
////        System.out.println("The time is now " + new Date());
//    }

    @GetMapping("/scheduled_start")
    public ReturnString SchedulerStart(@RequestParam(name = "citilink", required = false) String citilink,
                                       @RequestParam(name = "dns", required = false) String dns,
                                       @RequestParam(name = "pc", required = false) String pc,
                                       @RequestParam(name = "monitor", required = false) String monitor,
                                       @RequestParam(name = "printer", required = false) String printer,
                                       @RequestParam(name = "userName", required = false) String userName,
                                       @RequestParam(name = "cron", required = false) String cron) throws NoSuchMethodException, SchedulerException, ParseException {


        if (!CronExpression.isValidExpression(cron)) {
            disableStart = false;
            disableStop = true;
            return new ReturnString("Не корректно, попробуйте снова;Не известна", false, true);
        }

        cronExpression = new CronExpression(cron);
//        cronExpression.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        DateTime dateTime = new DateTime(cronExpression.getNextValidTimeAfter(new Date())).plusHours(3);
        Date nextValidTimeAfter = dateTime.toDate();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String nextDate = df.format(nextValidTimeAfter);


        scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class).withIdentity("job").build();

        jobDetail.getJobDataMap().put("citilink", citilink);
        jobDetail.getJobDataMap().put("dns", dns);
        jobDetail.getJobDataMap().put("pc", pc);
        jobDetail.getJobDataMap().put("monitor", monitor);
        jobDetail.getJobDataMap().put("printer", printer);
        jobDetail.getJobDataMap().put("userName", userName);
        jobDetail.getJobDataMap().put("parseController", parseController);

        Trigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("CronTrigger").withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();
        disableStart = true;
        disableStop = false;
        return new ReturnString("Корректно;" + nextDate, true, false);
    }

    @GetMapping("/scheduled_stop")
    public ReturnString SchedulerStop() throws NoSuchMethodException, SchedulerException {
        scheduler.shutdown();
        parseController.ParseStop();
        scheduler = null;
        cronExpression = null;
        disableStart = false;
        disableStop = true;
        return new ReturnString("Не известно;Не известна", false, true);
    }

    @GetMapping("/get_next_date")
    public ReturnString getNextDate() throws NoSuchMethodException, SchedulerException {

        try {

//            Date nextValidTimeAfter = cronExpression.getNextValidTimeAfter(new Date());
            DateTime dateTime = new DateTime(cronExpression.getNextValidTimeAfter(new Date())).plusHours(3);
            Date nextValidTimeAfter = dateTime.toDate();

//            Calendar instance = Calendar.getInstance();
//            instance.setTime(nextValidTimeAfter); //устанавливаем дату, с которой будет производить операции
//            instance.add(Calendar.HOUR_OF_DAY , 3);// прибавляем 3 дня к установленной дате
//            Date newDate = instance.getTime();


            DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            String nextDate = df.format(nextValidTimeAfter);


            return new ReturnString(nextDate, disableStart, disableStop);
        } catch (Exception e) {
            return new ReturnString("Не известна", disableStart, disableStop);

        }
    }
}

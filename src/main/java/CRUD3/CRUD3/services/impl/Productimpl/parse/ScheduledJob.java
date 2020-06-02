package CRUD3.CRUD3.services.impl.Productimpl.parse;

import CRUD3.CRUD3.controller.ParseController;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;


@Service
public class ScheduledJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String citilink = context.getJobDetail().getJobDataMap().getString("citilink");
        String dns = context.getJobDetail().getJobDataMap().getString("dns");
        String pc = context.getJobDetail().getJobDataMap().getString("pc");
        String monitor = context.getJobDetail().getJobDataMap().getString("monitor");
        String printer = context.getJobDetail().getJobDataMap().getString("printer");
        String userName = context.getJobDetail().getJobDataMap().getString("userName");
        ParseController parseController = (ParseController) context.getJobDetail().getJobDataMap().get("parseController");

        try {
            parseController.ParseStart(citilink, dns, pc, monitor, printer, userName);
            parseController.ParseJoin();
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }
    }
}

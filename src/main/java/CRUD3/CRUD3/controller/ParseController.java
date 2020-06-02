package CRUD3.CRUD3.controller;

import CRUD3.CRUD3.model.ReturnString;
import CRUD3.CRUD3.model.parse.ParsedTable;
import CRUD3.CRUD3.repository.ParsedTableRepo;
import CRUD3.CRUD3.services.impl.Productimpl.parse.ParseThread;
import CRUD3.CRUD3.services.impl.Productimpl.parse.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/parcer")
public class ParseController {
    @Autowired
    private ParserService parserService;

    private ParseThread parseThread = new ParseThread();
    private String statusValue = "stop";

    @Autowired
    private ParsedTableRepo parsedTableRepo;

    //    @Scheduled(cron = "* */1 * * * ?")
//    @Scheduled(fixedRate =  50000)
//    @Scheduled(cron = "0 */1 * * * ?")
//    public void reportCurrentTime() {
//        System.out.println("The time is now " + new Date());
//    }


    @GetMapping("/parse_start")
    public ReturnString ParseStart(@RequestParam(name = "citilink", required = false) String citilink,
                                   @RequestParam(name = "dns", required = false) String dns,
                                   @RequestParam(name = "pc", required = false) String pc,
                                   @RequestParam(name = "monitor", required = false) String monitor,
                                   @RequestParam(name = "printer", required = false) String printer,
                                   @RequestParam(name = "userName", required = false) String userName) throws NoSuchMethodException {
        List<Method> methods = parserService.methodList(citilink, dns, pc, monitor, printer);

        if (methods.size() < 1) {
            System.out.println("Укажите параметры!");
            return new ReturnString("Укажите параметры!", false, true);
        }
        try {
            if (!parseThread.isAlive()) {
                ParsedTable parsedTable = new ParsedTable();
                parsedTable.setDatePars(new Date());
                parsedTable.setUserName(userName);
                parsedTable.setParamParsed(parserService.getParamParsed(citilink, dns, pc, monitor, printer));

                parseThread = new ParseThread(methods, parserService);
//                parseThread.setMethods(methods);
//                parseThread.setObject(parserService);
                parseThread.setParsedTable(parsedTable);
                parseThread.start();
                statusValue = "start";
                return new ReturnString("В процессе...", true, false);
            } else
                return new ReturnString("Уже запущена...", true, false);
        } catch (Exception e) {
            ParsedTable parsedTable1 = parseThread.getParsedTable();
            String status = parsedTable1.getCountParsedData() > 0 ? "Частично успешна" : "Ошибка";

            parsedTable1.setParseStatus(status);

            parsedTableRepo.save(parsedTable1);
            System.out.println(status);
            return new ReturnString(status, true, false);
        }
    }

    @GetMapping("/parse_join")
    public ReturnString ParseJoin() {
        if (parseThread.isAlive())
            try {
                parseThread.join();
                if (statusValue.equals("start")) {
                    if (parseThread.getParsedTable().getParseStatus().equals("error_pars"))
                        throw new Exception();
                    parsedTableRepo.save(parseThread.getParsedTable());
                    statusValue = "stop";
                    return new ReturnString("Успешна", false, true);
                } else {
                    ParsedTable parsedTable1 = parseThread.getParsedTable();
                    parsedTableRepo.save(parsedTable1);

                    return new ReturnString("Остановлена", false, true);
                }

            } catch (Exception e) {
                ParsedTable parsedTable1 = parseThread.getParsedTable();
                String status = parsedTable1.getCountParsedData() > 0 ? "Частично успешна" : "Ошибка";
                parsedTable1.setParseStatus(status);
                parsedTableRepo.save(parsedTable1);
                System.out.println(status);
                return new ReturnString(status, false, true);
            }
        else return new ReturnString("Укажите параметры!", false, true);
    }


    @GetMapping("/stop")
    ReturnString ParseStop() {
        if (parseThread.isAlive()) {
            parseThread.stop();
            statusValue = "stop";
            return new ReturnString("Остановлен", false, true);
        } else
            return new ReturnString("Нельзя остановить, т.к миграция не активна", true, false);

    }

    @GetMapping("/get_status")
    ReturnString statusParse() {
        return new ReturnString(statusValue.equals("start") ? "В процессе..." : "Не запущена", statusValue.equals("start"), statusValue.equals("stop"));
    }
}

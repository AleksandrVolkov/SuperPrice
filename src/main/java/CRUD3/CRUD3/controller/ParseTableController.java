package CRUD3.CRUD3.controller;

import CRUD3.CRUD3.model.MyParseTable;
import CRUD3.CRUD3.model.parse.ParsedTable;
import CRUD3.CRUD3.repository.ParsedTableRepo;
import CRUD3.CRUD3.services.impl.Productimpl.parse.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parseTable")
public class ParseTableController {
    @Autowired
    private ParsedTableRepo parsedTableRepo;
    @Autowired
    private ParserService parserService;

    @GetMapping("/table")
    @ResponseBody
    public MyParseTable[] getParseTable() {  //HttpServletRequest httpRequest, Principal principal) {
        List<ParsedTable> all = parsedTableRepo.findAll();
//        List<ParsedTable> all = parserService.getPageableTable();
        Collections.reverse(all);
//        List<MyParseTable> myParseTableList = new ArrayList<>();
//        for (ParsedTable parsedTable : all) {
//            MyParseTable myParseTable = new MyParseTable();
//            myParseTable.setId(String.valueOf(parsedTable.getId()));
//            if (parsedTable.getDatePars() != null)
//                myParseTable.setParseDate(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(parsedTable.getDatePars()));//+" в "+new SimpleDateFormat("HH:mm:ss").format(parsedTable.getParseDate());
//            else
//                myParseTable.setParseDate("null");
////            myParseTable.setParseDate(date);
//            myParseTable.setUserName(parsedTable.getUserName());
//            myParseTable.setParseStatus(parsedTable.getParseStatus());
//            myParseTable.setCountParsedData(String.valueOf(parsedTable.getCountParsedData()));
//            myParseTable.setCountUpdatedData(String.valueOf(parsedTable.getCountUpdatedData()));
//            myParseTable.setParamParsed(parsedTable.getParamParsed());
//            myParseTableList.add(myParseTable);
//        }
//        all.forEach(parsedTable -> {
//            MyParseTable myParseTable = new MyParseTable();
//            myParseTable.setId(String.valueOf(parsedTable.getId()));
//            String date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(parsedTable.getDatePars());//+" в "+new SimpleDateFormat("HH:mm:ss").format(parsedTable.getParseDate());
//            myParseTable.setParseDate(date);
//            myParseTable.setUserName(parsedTable.getUserName());
//            myParseTable.setParseStatus(parsedTable.getParseStatus());
//            myParseTable.setCountParsedData(String.valueOf(parsedTable.getCountParsedData()));
//            myParseTable.setCountUpdatedData(String.valueOf(parsedTable.getCountUpdatedData()));
//            myParseTable.setParamParsed(parsedTable.getParamParsed());
//            myParseTableList.add(myParseTable);
//        });

        MyParseTable[] myParseTables = all.stream().map(parsedTable -> {
            MyParseTable myParseTable = new MyParseTable();
            myParseTable.setId(String.valueOf(parsedTable.getId()));
            if (parsedTable.getDatePars() != null)
                myParseTable.setParseDate(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(parsedTable.getDatePars()));//+" в "+new SimpleDateFormat("HH:mm:ss").format(parsedTable.getParseDate());
            else
                myParseTable.setParseDate("null");
            myParseTable.setUserName(parsedTable.getUserName());
            myParseTable.setParseStatus(parsedTable.getParseStatus());
            myParseTable.setCountParsedData(String.valueOf(parsedTable.getCountParsedData()));
            myParseTable.setCountUpdatedData(String.valueOf(parsedTable.getCountUpdatedData()));
            myParseTable.setParamParsed(parsedTable.getParamParsed());
            return myParseTable;
        }).collect(Collectors.toList()).toArray(MyParseTable[]::new);

//        MyParseTable[] myParseTables1 = myParseTables;
        return myParseTables;
    }

    @GetMapping("/pageable_table/{page}")
    @ResponseBody
    public Page<ParsedTable> getPageableTable(@PathVariable(value = "page") int page) {
        return parserService.getPageableTable(page);
    }
}

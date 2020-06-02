package CRUD3.CRUD3.services.impl.Productimpl.parse;

import CRUD3.CRUD3.model.parse.ParsedTable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ParseThread extends Thread {
    private List<Method> methods;
    private ParserService object;
    private int countData = 0;
    private ParsedTable parsedTable = new ParsedTable();

    public ParseThread() {
    }

    public ParseThread(List<Method> methods, ParserService object) {
        this.methods = methods;
        this.object = object;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public ParserService getObject() {
        return object;
    }

    public void setObject(ParserService object) {
        this.object = object;
    }

    public int getCountData() {
        return countData;
    }

    public void setCountData(int countData) {
        this.countData = countData;
    }

    public ParsedTable getParsedTable() {
        return parsedTable;
    }

    public void setParsedTable(ParsedTable parsedTable) {
        this.parsedTable = parsedTable;
    }

    @Override
    public void run() {
        try {//java.lang.NullPointerException
            for (Method m : methods)
                countData += (int) m.invoke(object);
            parsedTable.setCountParsedData(countData);
            parsedTable.setCountUpdatedData(countData);
            parsedTable.setParseStatus("Успешно");
        } catch (IllegalAccessException | InvocationTargetException e) {
//            String string = e.getCause().toString();
            countData += object.getCountProduct();
            parsedTable.setCountParsedData(countData);
            parsedTable.setCountUpdatedData(countData);
            if (e.getCause().toString().equals("java.lang.ThreadDeath"))
                parsedTable.setParseStatus("Остановлен");
            else
                parsedTable.setParseStatus(countData > 19 ? "Частично успешно" : "Ошибка");

            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Парсинг остановлен!");
        }
    }
}

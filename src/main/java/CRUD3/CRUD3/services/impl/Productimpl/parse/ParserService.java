package CRUD3.CRUD3.services.impl.Productimpl.parse;

import CRUD3.CRUD3.model.parse.ParsedTable;
import CRUD3.CRUD3.model.tovarmodel.Monitor;
import CRUD3.CRUD3.model.tovarmodel.PC;
import CRUD3.CRUD3.model.tovarmodel.Printer;
import CRUD3.CRUD3.repository.ParsedTableRepo;
import CRUD3.CRUD3.repository.repos.MonitorRepo;
import CRUD3.CRUD3.repository.repos.PCRepo;
import CRUD3.CRUD3.repository.repos.PrinterRepo;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParserService {
    @Autowired
    private PCRepo pcRepo;
    @Autowired
    private MonitorRepo monitorRepo;
    @Autowired
    private PrinterRepo printerRepo;
    @Autowired
    private ParsedTableRepo parsedTableRepo;

    private int countProduct = 0;

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }

    public int citilink_monitor() {
        countProduct = 0;
        System.out.println("Начинаем мониторы компы в ситилинке");
        String url1 = "https://www.citilink.ru/catalog/computers_and_notebooks/monitors/?available=1&status=55395790&p=1";
        Parser p = new Parser();
        Document page = p.getPage(url1);
        int lastPage = Integer.parseInt(page.selectFirst("div[class=page_listing]").selectFirst("li[class=last]").selectFirst("a").attr("data-page"));
        System.out.println("Мониторы ситилинка, последняя страница - " + lastPage + "\n\n");
        for (int i = 1; i < lastPage + 1; i++) {//7
            String url = "https://www.citilink.ru/catalog/computers_and_notebooks/monitors/?available=1&status=55395790&p=" + String.valueOf(i);
            List citilink = p.citilink(url, Monitor.class);
            countProduct += citilink.size();
            monitorRepo.saveAll(citilink);
            System.out.println("закончили парсить страницу " + i);
        }
        System.out.println("final");
        return countProduct;
    }

    public int citilink_pc() {
        countProduct = 0;
        System.out.println("Начинаем парсить компы в ситилинке");
        String url1 = "https://www.citilink.ru/catalog/computers_and_notebooks/computers/?available=1&status=55395790&p=1";
        Parser p = new Parser();
        Document page = p.getPage(url1);
        int lastPage = Integer.parseInt(page.selectFirst("div[class=page_listing]").selectFirst("li[class=last]").selectFirst("a").attr("data-page"));
        System.out.println("ПК ситилинка, последняя страница - " + lastPage + "\n\n");
        for (int i = 1; i < lastPage + 1; i++) {
            String url = "https://www.citilink.ru/catalog/computers_and_notebooks/computers/?available=1&status=55395790&p=" + String.valueOf(i);
            List citilink = p.citilink(url, PC.class);
            countProduct += citilink.size();
            pcRepo.saveAll(citilink);
            System.out.println("закончили парсить страницу " + i);
        }
        System.out.println("final");
        return countProduct;
    }

    public int citilink_printer() {
        countProduct = 0;

        System.out.println("Начинаем парсить принтеры в ситилинке");
        String url1 = "https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/printers/?available=1&status=55395790&p=1";
        Parser p = new Parser();
        Document page = p.getPage(url1);
        Elements elem = page.selectFirst("div[class=page_listing]").select("li[class=next]");//.selectFirst("a").attr("data-page"));
        List<Integer> lisNumPages = new ArrayList<>();
        for (Element e : elem) {
            lisNumPages.add(Integer.valueOf(e.selectFirst("a").attr("data-page")));
        }
        lisNumPages.sort(Integer::compareTo);
        int lastPage = lisNumPages.get(lisNumPages.size() - 1);
        System.out.println("Принтеры ситилинка, последняя страница - " + lastPage + "\n\n");
        for (int i = 1; i < lastPage + 1; i++) {
//        for (int i = 1; i < 2; i++) {
            String url = "https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/printers/?available=1&status=55395790&p=" + String.valueOf(i);
            List citilink = p.citilink(url, Printer.class);
            countProduct += citilink.size();
            printerRepo.saveAll(citilink);
            System.out.println("закончили парсить страницу " + i);
        }
        System.out.println("final");

        String url2 = "https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/ink_printers/?available=1&status=55395790&p=1";
        Document page1 = p.getPage(url2);
        Elements elem1 = page1.selectFirst("div[class=page_listing]").select("li[class=next]");//.selectFirst("a").attr("data-page"));
        List<Integer> lisNumPages1 = new ArrayList<>();
        for (Element e : elem1) {
            lisNumPages1.add(Integer.valueOf(e.selectFirst("a").attr("data-page")));
        }
        lisNumPages1.sort(Integer::compareTo);
        lastPage = lisNumPages1.get(lisNumPages1.size() - 1);
        System.out.println("Принтеры ситилинка, последняя страница - " + lastPage + "\n\n");

        System.out.println("Начинаем парсить принтеры в ситилинке");

        for (int i = 1; i < lastPage + 1; i++) {
            String url = "https://www.citilink.ru/catalog/computers_and_notebooks/monitors_and_office/ink_printers/?available=1&status=55395790&p=" + String.valueOf(i);
            List citilink = p.citilink(url, Printer.class);
            countProduct += citilink.size();
            printerRepo.saveAll(citilink);
            System.out.println("закончили парсить страницу " + i);
        }
        System.out.println("final");
        return countProduct;
    }

    public int dns_pc() {
        countProduct = 0;

        System.out.println("Начинаем парсить компы в dns");
        int lastPage = 0;
        String url1 = "https://www.dns-shop.ru/catalog/17a8932c16404e77/sistemnye-bloki/?p=15&order=1&groupBy=none&stock=2";
        Parser p = new Parser();
        Document page = p.getPage(url1);
        Elements elements = page.select("div[id=products-list-pagination]").first().select("li[class=pagination-widget__page]");
        for (Element el : elements)
            if (el.selectFirst("a").attr("class").contains("pagination-widget__page-link pagination-widget__page-link_last "))
                lastPage = Integer.parseInt(el.attr("data-page-number"));
        System.out.println("ПК dns, последняя страница - " + lastPage + "\n\n");
        for (int i = 1; i < lastPage + 1; i++) {
            String url = "https://www.dns-shop.ru/catalog/17a8932c16404e77/sistemnye-bloki/?p=" + String.valueOf(i) + "&order=1&groupBy=none&stock=2";
            List dns = p.DNS(url, PC.class);
            countProduct += dns.size();
            pcRepo.saveAll(dns);
            System.out.println("закончили парсить страницу " + i);
        }
        System.out.println("final");
        return countProduct;
    }

    public int dns_monitor() {
        countProduct = 0;
        System.out.println("Начинаем парсить моники в dns");
        int lastPage = 0;
        String url1 = "https://www.dns-shop.ru/catalog/17a8943716404e77/monitory/?p=1&order=1&groupBy=none&stock=2&q=%D0%BC%D0%BE%D0%BD%D0%B8%D1%82%D0%BE%D1%80";
        Parser p = new Parser();
        Document page = p.getPage(url1);
        Elements elements = page.select("div[id=products-list-pagination]").first().select("li[class=pagination-widget__page]");
        for (Element el : elements)
            if (el.selectFirst("a").attr("class").contains("pagination-widget__page-link pagination-widget__page-link_last"))
                lastPage = Integer.parseInt(el.attr("data-page-number"));
        System.out.println("Мониторы dns, последняя страница - " + lastPage + "\n\n");

        for (int i = 1; i < lastPage + 1; i++) {
            String url = "https://www.dns-shop.ru/catalog/17a8943716404e77/monitory/?p=" + String.valueOf(i) + "&order=1&groupBy=none&stock=2&q=%D0%BC%D0%BE%D0%BD%D0%B8%D1%82%D0%BE%D1%80";
            List dns = p.DNS(url, Monitor.class);
            countProduct += dns.size();
            monitorRepo.saveAll(dns);
            System.out.println("закончили парсить страницу " + i);
        }
        System.out.println("final");
        return countProduct;
    }

    public int dns_printer() {
        countProduct = 0;
        System.out.println("Начинаем парсить принтеры в dns");
        int lastPage = 0;
        Parser p = new Parser();

        String url1 = "https://www.dns-shop.ru/catalog/17a8e00716404e77/lazernye-printery/?p=1&order=1&groupBy=none&stock=2&q=%D0%BF%D1%80%D0%B8%D0%BD%D1%82%D0%B5%D1%80";
        Document page = p.getPage(url1);
        Elements elements = page.select("div[id=products-list-pagination]").first().select("li[class=pagination-widget__page]");
        for (Element el : elements)
            if (el.selectFirst("a").attr("class").contains("pagination-widget__page-link pagination-widget__page-link_last"))
                lastPage = Integer.parseInt(el.attr("data-page-number"));
        System.out.println("Принтеры dns, последняя страница - " + lastPage + "\n\n");

        for (int i = 1; i < lastPage + 1; i++) {
            String url = "https://www.dns-shop.ru/catalog/17a8e00716404e77/lazernye-printery/?p=" + String.valueOf(i) + "&order=1&groupBy=none&stock=2&q=%D0%BF%D1%80%D0%B8%D0%BD%D1%82%D0%B5%D1%80";
            List dns = p.DNS(url, Printer.class);
            countProduct += dns.size();
            printerRepo.saveAll(dns);
            System.out.println("закончили парсить страницу " + i);
        }
        System.out.println("final");

        lastPage = 0;
        String url2 = "https://www.dns-shop.ru/catalog/17a8e07216404e77/strujnye-printery/?p=1&order=1&groupBy=none&stock=2&f[nz8]=9hd9&q=%D0%BF%D1%80%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D1%8B";
        Document page1 = p.getPage(url2);
        try {
            Elements elements1 = page1.select("div[id=products-list-pagination]").first().select("li[class=pagination-widget__page]");
            for (Element el : elements1)
                if (el.selectFirst("a").attr("class").contains("pagination-widget__page-link pagination-widget__page-link_last"))
                    lastPage = Integer.parseInt(el.attr("data-page-number"));

        } catch (Exception e) {
            lastPage = 1;
            System.out.println("Ошибка в чтении div[id=products-list-pagination], по дефолту стр. = 1");
        }
        System.out.println("Принтеры dns, последняя страница - " + lastPage + "\n\n");


        for (int i = 1; i < lastPage + 1; i++) {
            String url = "https://www.dns-shop.ru/catalog/17a8e07216404e77/strujnye-printery/?p=" + String.valueOf(i) + "&order=1&groupBy=none&stock=2&f[nz8]=9hd9&q=%D0%BF%D1%80%D0%B8%D0%BD%D1%82%D0%B5%D1%80%D1%8B";
            List dns = p.DNS(url, Printer.class);
            countProduct += dns.size();
            printerRepo.saveAll(dns);
            System.out.println("закончили парсить страницу " + i);
        }
        System.out.println("final");
        return countProduct;
    }

    public List<Method> methodList(String citilink, String dns, String pc, String monitor, String printer) throws NoSuchMethodException {
        List<Method> methods = new ArrayList<>();

        if (dns != "") {
            if (pc != "") {
                methods.add(ParserService.class.getMethod("dns_pc"));
            }
            if (monitor != "") {
                methods.add(ParserService.class.getMethod("dns_monitor"));
            }
            if (printer != "") {
                methods.add(ParserService.class.getMethod("dns_printer"));
            }
        }
        if (citilink != "") {
            if (pc != "") {
                methods.add(ParserService.class.getMethod("citilink_pc"));
            }
            if (monitor != "") {
                methods.add(ParserService.class.getMethod("citilink_monitor"));
            }
            if (printer != "") {
                methods.add(ParserService.class.getMethod("citilink_printer"));
            }
        }
        return methods;
    }

    public String getParamParsed(String citilink, String dns, String pc, String monitor, String printer) {
        String[] arr = {citilink, dns, pc, monitor, printer};
        String paramParsed = "";
        for (String s : arr) {
            if (s != "")
                paramParsed += s + " ";
        }
        return paramParsed.trim();
    }

    public Page<ParsedTable> getPageableTable(int page) {
//        parsedTableRepo.findAll(PageRequest.of(page, 10)).get().collect(Collectors.toList()).toArray(ParsedTable[]::new);
//        return parsedTableRepo.findAll(PageRequest.of(page, 10)).get().collect(Collectors.toList());
        return parsedTableRepo.findPageable(PageRequest.of(page, 10));
    }

}


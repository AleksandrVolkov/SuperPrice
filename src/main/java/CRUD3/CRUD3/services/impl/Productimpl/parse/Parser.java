package CRUD3.CRUD3.services.impl.Productimpl.parse;

import CRUD3.CRUD3.model.tovarmodel.Monitor;
import CRUD3.CRUD3.model.tovarmodel.PC;
import CRUD3.CRUD3.model.tovarmodel.Printer;
import CRUD3.CRUD3.model.tovarmodel.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class Parser {
    public Document getPage(String url) {

        try {
            return Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 YaBrowser/20.2.0.1043 Yowser/2.5 Safari/537.36") //
                    .referrer("http://www.google.com")
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println(e.getMessage());
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver80.exe");
            ChromeDriver driver = new ChromeDriver();
            driver.get(url);
            try {
                WebElement textBox = driver.findElementById("captcha_limit");
                textBox.sendKeys("В");
                WebElement button = driver.findElementByClassName("request-limit-page__submit");
                button.click();
                driver.close();
            } catch (Exception e1) {
                e1.printStackTrace();
                System.out.println(e1.getMessage());
                driver.close();
            }
            return getPage(url);
        }
    }

    public List DNS(String url, Class clazz) {
        Document page = getPage(url);
        Element element = page.select("div[class=products-list__content]").first();
        Elements els = element.select("div[class=n-catalog-product__main]");
        List<Product> productList = new ArrayList<>();
        int count = 1;
        for (Element el : els) {
            String shortImg = "";
            try {
                String shortImg1 = el.select("a[class=show-popover ec-price-item-link]").first().selectFirst("img").attr("src");
                String shortImg2 = el.select("a[class=show-popover ec-price-item-link]").first().selectFirst("img").attr("data-src");
                shortImg = shortImg1.equals("") ? shortImg2 : shortImg1;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            String name = el.select("div[class=product-info__title-link]").select("a").first().text();
            if (name.contains("&nbsp;"))
                name = name.replace("&nbsp;", " ");
            String linkOnFullDescription = "https://www.dns-shop.ru" + el.select("div[class=product-info__title-link]").select("a").first().attr("href") + "characteristics/";

            String shortDescription = el.select("span[class=product-info__title-description]").first().text();

            Document page1 = getPage(linkOnFullDescription);
            Element content = page1.selectFirst("div[class=price-item ec-price-item]");
            String price = content.selectFirst("div[class=col-header col-order]").selectFirst("div[class=price_g]").selectFirst("span").text();

            if (clazz == PC.class) {
                PC product = new PC(0L, "pc", "dns", shortImg, name, shortDescription, linkOnFullDescription, new BigDecimal(Integer.parseInt(price.replace(" ", ""))));
                setPC_dns(content, product);
                String short_descr = "процессор: " + product.getCpu_model() + "; частота процессора: " + product.getCpuFrequency() + "; оперативная память: " + product.getRamModel() +
                        " " + product.getRamSize() + " " + product.getRam_frequency() + "; видеокарта: " + product.getGpu_discrete_model() + " — " + product.getGpu_discrete_size() +
                        "; интегрированная: " + product.getGpu_integrated_model() + "; HDD: " + product.getHdd_data() + "; SSD: " + product.getSsd_data();
                product.setShort_description(short_descr.replace("null", "нет"));
                product.setProduct_id(Long.parseLong(page1.selectFirst("div[class=price-item-code]").selectFirst("span").text().trim() + "1234"));

                productList.add(product);
            }
            if (clazz == Monitor.class) {
                Monitor product = new Monitor(0L, "monitor", "dns", shortImg, name, shortDescription, linkOnFullDescription, new BigDecimal(Integer.parseInt(price.replace(" ", ""))));
                setMonitor_dns(content, product);
                String short_descr = "экран: " + product.getScreen() + ", частота: " + product.getScreenFrequency() + ", матрица " + product.getMatrixType() +
                        " с разрешением " + product.getScreenResolution() + ", отношением сторон " + product.getAspectRatio() + ", яркостью " + product.getBrightness() +
                        ", временем отклика " + product.getResponseTime() + ", разъем " + product.getConnector();
                product.setShort_description(short_descr.replace("null", "нет"));
                product.setProduct_id(Long.parseLong(page1.selectFirst("div[class=price-item-code]").selectFirst("span").text().trim() + "1234"));

                productList.add(product);
            }
            if (clazz == Printer.class) {
                Printer product = new Printer(0L, "printer", "dns", shortImg, name, shortDescription, linkOnFullDescription, new BigDecimal(Integer.parseInt(price.replace(" ", ""))));
                setPrinter_dns(content, product);
                String short_descr = "технология печати: " + product.getType() + ", " + product.getColor() + ", формат: " + product.getFormat() +
                        ", максимальная скорость печати " + product.getMaxPrintSpeed() + ", " + product.getConnector() + ", встроенная СНПЧ - " + product.getCISS() +
                        ", двусторонняя печать - " + product.getTwoSidedPrinting();
                product.setShort_description(short_descr.replace("null", "нет"));
                product.setProduct_id(Long.parseLong(page1.selectFirst("div[class=price-item-code]").selectFirst("span").text().trim() + "1234"));

                productList.add(product);
            }
            System.out.print(count);
            if (productList.size() == count++) System.out.printf(" true\n");
        }
        return productList;
    }

    public List citilink(String url, Class clazz) {
        try {

        Document page = getPage(url);
        Element element = page.select("div[class=block_data__gtm-js block_data__pageevents-js listing_block_data__pageevents-js]").first();//tut
        Elements els = element.select("div[class=js--subcategory-product-item subcategory-product-item product_data__gtm-js  product_data__pageevents-js ddl_product]");
        List<Product> productList = new ArrayList<>();

        int count = 1;
        for (Element el : els) {
            String shortImg = "";
            try {
                String shortImg1 = el.select("div[class=wrap-img]").first().selectFirst("img").attr("src");
                String shortImg2 = el.select("div[class=wrap-img]").first().selectFirst("img").attr("data-src");
                shortImg = shortImg1.equals("") ? shortImg2 : shortImg1;
            } catch (NullPointerException e) {
                System.out.println("Картинка не валидна");
            }

            String name = el.select("a[class=link_gtm-js link_pageevents-js ddl_product_link]").first().attr("title");
            if (name.contains("&nbsp;"))
                name = name.replace("&nbsp;", " ");

            String linkOnFullDescription = el.select("a[class=link_gtm-js link_pageevents-js ddl_product_link]").attr("href");
            String price = "нет в наличии";
            try {
                price = el.select("span[class=subcategory-product-item__price subcategory-product-item__price_standart]").first()
                        .selectFirst("ins[class=subcategory-product-item__price-num]").text();
            } catch (NullPointerException e) {
                System.out.println(e);
            }
            Document page1 = getPage(linkOnFullDescription);
            String shortDescription = page1.select("p[class=short_description]").first().text();
            Element content = page1.selectFirst("div[class=specification_view product_view]");

            String[] split;
            BigDecimal price1 = null;
            try {
                price1 = new BigDecimal(Integer.parseInt(price.replace(" ", "")));
            } catch (Exception exep) {
                exep.printStackTrace();
            }
            if (clazz == PC.class) {
                PC product = new PC(0L, "pc", "citilink", shortImg, name, shortDescription, linkOnFullDescription, price1);
                split = shortDescription.split("; ");
                setPC_citilink(product, split);

                Element productFeatures = content.selectFirst("table[class=product_features]");
                Elements elTable = productFeatures.select("tr");
                boolean flag = false;
                for (int i = 0; i < elTable.size(); i++) {
                    Element tr = elTable.get(i);
                    if (tr.attr("class").equals("header_row")) {
                        String mainTh = elTable.get(i).selectFirst("th").text();

                        i++;
                        if (mainTh.contains("Процессор"))
                            for (int j = i; j < elTable.size(); i++) {
                                String th = elTable.get(i).selectFirst("th").text();
                                String td = elTable.get(i).selectFirst("td").text();
                                if (th.contains("Количество ядер процессора")) {
                                    switch (td) {
                                        case ("двухъядерный"):
                                        case ("2"):
                                            product.setCpuCoresCount(2);
                                            break;
                                        case ("четырехъядерный"):
                                        case ("4"):
                                            product.setCpuCoresCount(4);
                                            break;
                                        case ("шестиядерный"):
                                        case ("6"):
                                            product.setCpuCoresCount(6);
                                            break;
                                        case ("восьмиядерный"):
                                        case ("8"):
                                            product.setCpuCoresCount(8);
                                            break;
                                    }
                                    flag = true;
                                    break;
                                }
                                if (i == elTable.size() - 1) break;
                                if (elTable.get(i + 1).attr("class").equals("header_row")) break;
                            }
                        if (flag) break;
                    }
                }

                String ss = page1.selectFirst("span[class=product_id]").text().trim() + "4321";
                if (ss.matches("^\\d+$")) {
                    product.setProduct_id(Long.parseLong(ss));
                    productList.add(product);
                }

            }
            if (clazz == Monitor.class) {
                Monitor product = new Monitor(0L, "monitor", "citilink", shortImg, name, shortDescription, linkOnFullDescription, price1);
                split = shortDescription.split(", ");
                setMonitor_citilink(product, split);
                String ss = page1.selectFirst("span[class=product_id]").text().trim() + "4321";

                if (ss.matches("^\\d+$")) {
                    product.setProduct_id(Long.parseLong(ss));
                    productList.add(product);
                }
            }
            if (clazz == Printer.class) {
                Printer product = new Printer(0L, "printer", "citilink", shortImg, name, shortDescription, linkOnFullDescription, price1);
                split = shortDescription.split(", ");
                setPrinter_citilink(product, split);
                String ss = page1.selectFirst("span[class=product_id]").text().trim() + "4321";
                if (ss.matches("^\\d+$")) {
                    product.setProduct_id(Long.parseLong(ss));
                    productList.add(product);
                }
            }

            System.out.print(count);
            if (productList.size() == count++) System.out.printf(" true\n");
        }
            return productList;
        }catch (Exception ec){
            ec.printStackTrace();
            System.out.println(ec.getMessage());
        }


        return null;
    }

    public static PC setPC_citilink(PC product, String[] split) {
        try {
            for (String str : split) {
                if (str.contains("процессор: "))
                    product.setCpu_model(str.replace("процессор: ", ""));
                if (str.contains("частота процессора: ")) {
                    String str_freq = str.replace("частота процессора: ", "").split(" ")[0];
                    product.setCpuFrequency(Double.valueOf(str_freq));
                }
                if (str.contains("оперативная память: ")) {
                    String text1 = str.replace("оперативная память: ", "");
                    Pattern pattern = Pattern.compile("\\d+\\s+Мб");
                    Matcher matcher = pattern.matcher(text1);
                    if (matcher.find()) {
                        product.setRamModel(text1.substring(0, matcher.start()).trim());
                        String ramSize = text1.substring(matcher.start(), matcher.end() - 6) + " ГБ";
                        if (ramSize.equals(" ГБ")) {
                            product.setRamSize(("0." + text1.substring(matcher.start(), matcher.end() - 3) + " ГБ").trim());
                        } else
                            product.setRamSize(ramSize);
                        product.setRam_frequency(text1.substring(matcher.end()));
                    } else
                        product.setRamModel(text1);
                }
                if (str.contains("видеокарта: ")) {
                    String text1 = str.replace("видеокарта: ", "");
                    Pattern pattern = Pattern.compile("\\d+\\s+Мб");
                    Matcher matcher = pattern.matcher(text1);
                    if (matcher.find()) {
                        product.setGpu_discrete_model(text1.substring(0, matcher.start() - 3));
                        product.setGpu_discrete_size(text1.substring(matcher.start(), matcher.end() - 6) + " ГБ");
                    } else
                        product.setGpu_discrete_model(text1);
                }
                if (str.contains("интегрированная графика: "))
                    product.setGpu_integrated_model(str.replace("интегрированная графика: ", ""));
                if (str.contains("HDD: "))
                    product.setHdd_data(str.replace("HDD: ", "").split(",")[0]);
                if (str.contains("SSD: "))
                    product.setSsd_data(str.replace("SSD: ", ""));
            }
            return product;
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        return product;
    }

    public static PC setPC_dns(Element content, PC product) {
        Elements tableContent = content.selectFirst("div[id=characteristics]").select("tr");
        for (int i = 0; i < tableContent.size(); i++) {
            Element tr = tableContent.get(i);
            if (tr.attr("class").equals("extended-characteristic hidden")) continue;
            if (tr.selectFirst("td").attr("class").equals("table-part")) {
                i++;
                String gpu_model = null;
                String gpu_chip_type = null;
                for (int j = i; j < tableContent.size(); i++) {
                    if (tableContent.get(i).selectFirst("tr").attr("class").equals("extended-characteristic hidden"))
                        continue;
                    String th = tableContent.get(i).selectFirst("tr").selectFirst("span").text();
                    String td = tableContent.get(i).selectFirst("tr").select("td").get(1).text();

                    if (th.contains("Модель процессора"))
                        product.setCpu_model(td);
                    if (th.contains("Частота процессора"))
                        product.setCpuFrequency(Double.valueOf(td.split(" ")[0]) / 1000);
                    if (th.contains("Количество ядер процессора"))
                        product.setCpuCoresCount(Integer.valueOf(td));
                    if (th.contains("Тип оперативной памяти"))
                        product.setRamModel(td.trim());
                    if (th.contains("Размер оперативной памяти"))
                        product.setRamSize(td);
                    if (th.contains("Производитель видеочипа"))
                        gpu_chip_type = td;
                    if (th.contains("Модель дискретной видеокарты"))
                        gpu_model = td;
                    if (th.contains("Объем видеопамяти"))
                        product.setGpu_discrete_size(td.contains("МБ") ? td.substring(0, td.length() - 6) : null);
                    if (th.contains("Модель интегрированной видеокарты"))
                        product.setGpu_integrated_model(td);
                    if (th.contains("Суммарный объем жестких дисков (HDD)"))
                        product.setHdd_data(td.contains("нет") ? null : td);
                    if (th.contains("Объем твердотельного накопителя (SSD)"))
                        product.setSsd_data(td.contains("нет") ? null : td);
                    if (i == tableContent.size() - 1) break;
                    if (tableContent.get(i + 1).selectFirst("td").attr("class").equals("table-part")
                            || tableContent.get(i + 1).attr("class").equals("extended-characteristic hidden"))
                        break;
                }
                if (product.getGpu_discrete_model() == null)
                    product.setGpu_discrete_model(gpu_chip_type == null || gpu_model == null || gpu_chip_type.equals("нет") || gpu_model.equals("нет") ? null : gpu_chip_type + " " + gpu_model);
            }
        }
        return product;
    }

    public static Monitor setMonitor_citilink(Monitor product, String[] split) {
        String response_time = " ";
        String connector = " ";
        if (split.length > 1) {
            for (String str : split) {
                if (str.contains("экран: "))
                    product.setScreen(Double.valueOf(str.replace("экран: ", "").replace("\"", "")));
                if (str.contains("частота: "))
                    product.setScreenFrequency(Integer.parseInt(str.replace("частота: ", "").replace("Гц", "").trim()));
                if (str.contains("матрица ")) {
                    String[] arr = str.replace("матрица ", "").split(" ");
                    product.setScreenResolution(arr[arr.length - 1].replace("×", "x"));
                    product.setMatrixType(arr[0]);
                }
                if (str.contains("отношением сторон "))
                    product.setAspectRatio(str.replace("отношением сторон ", ""));
                if (str.contains("яркостью "))
                    product.setBrightness(str.replace("яркостью ", ""));
                if (str.contains("временем отклика "))
                    response_time += str.replace("временем отклика ", "") + ", ";
                if (str.contains("разъем ") || str.contains("D-SUB") || str.contains("VGA") || str.contains("DVI") || str.contains("HDMI") || str.contains("Display Port"))
                    connector += str.replace("разъем ", "") + ", ";
            }
            product.setResponseTime(response_time.substring(0, response_time.length() - 1).equals("") ? null : response_time.substring(0, response_time.length() - 1));
            product.setConnector(connector.substring(0, connector.length() - 1).equals("") ? null : connector.substring(0, connector.length() - 1));
        }
        return product;
    }

    public static Monitor setMonitor_dns(Element content, Monitor product) {
        Elements tableContent = content.selectFirst("div[id=characteristics]").select("tr");
        for (int i = 0; i < tableContent.size(); i++) {
            Element tr = tableContent.get(i);
            if (tr.attr("class").equals("extended-characteristic hidden")) continue;
            if (tr.selectFirst("td").attr("class").equals("table-part")) {
                i++;
                for (int j = i; j < tableContent.size(); i++) {
                    if (tableContent.get(i).selectFirst("tr").attr("class").equals("extended-characteristic hidden"))
                        continue;
                    String th = tableContent.get(i).selectFirst("tr").selectFirst("span").text();
                    String td = tableContent.get(i).selectFirst("tr").select("td").get(1).text();
                    if (th.contains("Диагональ экрана"))
                        product.setScreen(Double.valueOf(td.replace("\"", "")));
                    if (th.contains("Максимальная частота обновления экрана"))
                        product.setScreenFrequency(Integer.parseInt(td.replace("Гц", "").trim()));
                    if (th.contains("Максимальное разрешение"))
                        product.setScreenResolution(td);
                    if (th.contains("Соотношение сторон"))
                        product.setAspectRatio(td);
                    if (th.contains("Яркость"))
                        product.setBrightness(td);
                    if (th.contains("Время отклика пикселя"))
                        product.setResponseTime(td.substring(0, td.length() - 1));
                    if (th.contains("Тип ЖК-матрицы (подробно)"))
                        product.setMatrixType(td);
                    if (th.contains("Видеоразъемы"))
                        product.setConnector(td);

                    if (i == tableContent.size() - 1) break;
                    if (tableContent.get(i + 1).selectFirst("td").attr("class").equals("table-part")
                            || tableContent.get(i + 1).attr("class").equals("extended-characteristic hidden"))
                        break;
                }
            }
        }
        return product;
    }

    public static Printer setPrinter_citilink(Printer product, String[] split) {
        String connector = "";
        if (split.length > 1) {
            for (String str : split) {
                if (str.contains("технология печати: "))
                    product.setType(str.replace("технология печати: ", ""));
                if (str.contains("цветной") || str.contains("черно-белый"))
                    product.setColor(str.trim());
                if (str.contains("формат: "))
                    product.setFormat(str.replace("формат: ", ""));
                if (str.contains("максимальная скорость печати ")) {
                    //максимальная скорость печати (ЧБ) до 37 стр/мин
                    String text1 = str.replace("максимальная скорость печати ", "");
                    Pattern pattern = Pattern.compile("\\d+.?\\d*\\s*стр/мин");
                    Matcher matcher = pattern.matcher(text1);
                    if (matcher.find()) {
                        product.setMaxPrintSpeed(Double.valueOf(text1.substring(matcher.start(), matcher.end() - 7).trim()));
                    } else
                        product.setMaxPrintSpeed(null);
                }
                if (str.contains("USB") || str.contains("RJ-45") || str.contains("Wi-Fi") || str.contains("LPT"))
                    connector += str.trim() + ", ";
                if (str.contains("встроенная СНПЧ"))
                    product.setCISS("есть");
                if (str.contains("двусторонняя печать"))
                    product.setTwoSidedPrinting("есть");
            }
            try {
                product.setConnector(connector.substring(0, connector.length() - 2));
            } catch (Exception e) {
                System.out.println("Ошибка в сторке " + connector + " выходит за границы массива \"connector.length() - 2\"");
                product.setConnector(null);
            }
        }
        return product;
    }

    public static Printer setPrinter_dns(Element content, Printer product) {
        Elements tableContent = content.selectFirst("div[id=characteristics]").select("tr");
        for (int i = 0; i < tableContent.size(); i++) {
            Element tr = tableContent.get(i);
            if (tr.attr("class").equals("extended-characteristic hidden")) continue;
            if (tr.selectFirst("td").attr("class").equals("table-part")) {
                i++;
                for (int j = i; j < tableContent.size(); i++) {
                    if (tableContent.get(i).selectFirst("tr").attr("class").equals("extended-characteristic hidden"))
                        continue;
                    String th = tableContent.get(i).selectFirst("tr").selectFirst("span").text();
                    String td = tableContent.get(i).selectFirst("tr").select("td").get(1).text();
                    if (th.contains("Технология печати")) {
                        if (td.contains("лазерная"))
                            product.setType("лазерный");
                        if (td.contains("светодиодная"))
                            product.setType("светодиодный");
                        if (td.contains("струйная"))
                            product.setType("струйный");
                        if (td.contains("матричная"))
                            product.setType("матричный");
                    }
                    if (th.contains("Цветность печати")) {
                        if (td.contains("черно-белая"))
                            product.setColor("черно-белый");
                        if (td.contains("цветная"))
                            product.setColor("цветной");
                    }
                    if (th.contains("Максимальный формат"))
                        product.setFormat(td);
                    if (th.contains("Скорость чёрно-белой печати (стр/мин)")) {
                        String text1 = td.replace("максимальная скорость печати ", "");
                        Pattern pattern = Pattern.compile("\\d+.?\\d*\\s*стр/мин");
                        Matcher matcher = pattern.matcher(text1);
                        if (matcher.find()) {
                            product.setMaxPrintSpeed(Double.valueOf(text1.substring(matcher.start(), matcher.end() - 7).trim()));
                        } else
                            product.setMaxPrintSpeed(null);
                    }
                    if (th.contains("Система непрерывной подачи чернил (СНПЧ)"))
                        product.setCISS(td);
                    if (th.contains("Автоматическая двусторонняя печать"))
                        product.setTwoSidedPrinting(td);
                    if (th.contains("Интерфейсы"))
                        product.setConnector(td);

                    if (i == tableContent.size() - 1) break;
                    if (tableContent.get(i + 1).selectFirst("td").attr("class").equals("table-part")
                            || tableContent.get(i + 1).attr("class").equals("extended-characteristic hidden"))
                        break;
                }
            }
        }
        return product;
    }
}

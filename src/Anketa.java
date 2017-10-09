import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Anketa extends HttpServlet {

    AtomicInteger raio1_java = new AtomicInteger(0);
    AtomicInteger raio1_cplus = new AtomicInteger(0);
    AtomicInteger raio1_csharp = new AtomicInteger(0);

    AtomicInteger raio2_easy = new AtomicInteger(0);
    AtomicInteger raio2_midle = new AtomicInteger(0);
    AtomicInteger raio2_hight = new AtomicInteger(0);

    Map<String, Integer> countMap = new HashMap<>();

    static final String SHABLON = "<html>\n" +
            "<head>\n" +
            "    <title>Statistic</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <h3>Какой язык программирования вы изучаете?</h3>\n" +
            "<table border=\"1\">\n" +
            "    <tr>\n" +
            "        <td>Java</td><td>%s</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>C++</td><td>%s</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>C#</td><td>%s</td>\n" +
            "    </tr>\n" +
            "</table>\n" +
            "\n" +
            "<h3>Какой у вас уровнеь английского?</h3>\n" +
            "<table border=\"1\">\n" +
            "    <tr>\n" +
            "        <td>Easy</td><td>%s</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>Midle</td><td>%s</td>\n" +
            "    </tr>\n" +
            "    <tr>\n" +
            "        <td>Hight</td><td>%s</td>\n" +
            "    </tr>\n" +
            "</table>\n" +
            "\n" +
            "</body>\n" +
            "</html>";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String radio1 = req.getParameter("radio1");
        String radio2 = req.getParameter("radio2");

        switch (radio1) {
            case "ValJava":
                raio1_java.incrementAndGet();
                break;
            case "ValCplusplus":
                raio1_cplus.incrementAndGet();
                break;
            case "ValCsharp":
                raio1_csharp.incrementAndGet();
                break;
            default: break;
        }

        switch (radio2) {
            case "ValEasy":
                raio2_easy.incrementAndGet();
                break;
            case "ValMidle":
                raio2_midle.incrementAndGet();
                break;
            case "ValHight":
                raio2_hight.incrementAndGet();
                break;
            default: break;
        }


        countMap.put("ValJava", raio1_java.intValue());
        countMap.put("ValCplusplus", raio1_cplus.intValue());
        countMap.put("ValCsharp", raio1_csharp.intValue());

        countMap.put("ValEasy", raio2_easy.intValue());
        countMap.put("ValMidle", raio2_midle.intValue());
        countMap.put("ValHight", raio2_hight.intValue());

        String[] msg = new String[6];
        msg[0] = String.valueOf(countMap.get("ValJava"));
        msg[1] = String.valueOf(countMap.get("ValCplusplus"));
        msg[2] = String.valueOf(countMap.get("ValCsharp"));

        msg[3] = String.valueOf(countMap.get("ValEasy"));
        msg[4] = String.valueOf(countMap.get("ValMidle"));
        msg[5] = String.valueOf(countMap.get("ValHight"));

        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(String.format(SHABLON, msg[0], msg[1], msg[2], msg[3], msg[4], msg[5]));
    }
}
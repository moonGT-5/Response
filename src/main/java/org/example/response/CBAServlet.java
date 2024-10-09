package org.example.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "CBAServlet", value = "/CBAServlet")
public class CBAServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null || "".equals(action)) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().append("本页面是程序首页，为浏览器用户提供了若干功能<br/>").append("在URL后添加 <font color='red'>查询字符串</font>, 测试response各功能<br/>").append("?action=statusCode 测试响应状态码,在 Tomcat控制台点看本程序的响应状态码<br/>").append("?action=header 注意使用F12, 打开浏览器使用开发者工具<br/>").append("?action=refresh 查看Tomcat控制台, refresh后的代码是否执行<br/>").append("?action=redirect 查看Tomcat控制台, redirect后的代码是否执行<br/>").append("?action=forward 查看Tomcat控制台, 请求转发后的代码是否执行<br/>").append("?action=include 查看Tomcat控制台, 请求包含后的代码是否执行<br/>").append("?action=contentType 不指定浏览器解码方式, 乱码了吗?<br/>").append("?action=chars 文本型数据里不能掺杂字节数据, 否则异常<br/>").append("?action=bytes字节数据里不能掺杂文本型数据, 否则异常<br/>").append("?action= picture&fileName=学习强国.jpg 通过response预览图片<br/>").append("?action=picture&subAction=download&fileName=学习强国.jpg 通过response加载图片文件<br/>").append("?action=img&fileName=学习强国.jpg 使用img标签预览图片<br/>").append("?action=imgServlet&fileName=学习强国.jpg 使用ing标签和response的结合预览图片<br/>");
            return;
        }
        if (action.equals("statusCode")) {
            System.out.println("设置前的状态码是：" + response.getStatus());
            response.sendError(404, "您设置的页面跑到火星上去了！");
            System.out.println("设置后的状态码是：" + response.getStatus());
            return;
        }
        if (action.equals("header")) {
            response.setHeader("Content-Type", "text/html; charset=UTF-8");
            response.setIntHeader("Content-Length", 0);
            response.setDateHeader("Date", System.currentTimeMillis());
            System.out.println("包含Set-Cookie响应头吗?" + response.containsHeader("Set-Cookie"));
            response.addHeader("Set-Cookie", " username=zhangsan; Path=/; HttpOnly");
            response.addHeader("Set-Cookie", " password=123456; Path=/; Http0nly");
            System.out.println("包含Set-Cookie响应头吗?" + response.containsHeader(" Set-Cookie"));
            System.out.println(" Set-Cookie响应头是:" + response.getHeader("Set-Cookie"));
            java.util.Collection<java.lang.String> headerNames = response.getHeaderNames();
            for (String headerName : headerNames) {
                System.out.print(headerName + "是: ");
                java.util.Collection<java.lang.String> headerValues = response.getHeaders(headerName);
                for (String headerValue : headerValues) {
                    System.out.println(headerValue);
                }
            }
            return;
        }
        if (action.equals("refresh")) {
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            String url = request.getContextPath() + "/" + "form0.jsp";
            response.getWriter().append("观察refresh前的字符串是否输出到网页上!");
            response.setHeader("refresh", "5;url=" + url);
            response.getWriter().append("观察refresh后的字符串是否输出到网页上!");
            System.out.println("refresh后的语句");
            return;
        }
        if (action.equals("redirect")) {
            String url = request.getContextPath() + "/" + "form0.jsp";
            response.getWriter().append("观察redirect前的字符串是否输出到网页上!");
            response.sendRedirect(url);
            response.getWriter().append("观察redirect后的字符串是否输出到网页上!");
            System.out.println("redirect后的语句");
            return;
        }
        if (action.equals("forward")) {
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.getWriter().append("观察forward前的字符串是否输出到网页上!");
            request.getRequestDispatcher("/form0.jsp").forward(request, response);
            response.getWriter().append("观察forward后的字符串是否输出到网页上!");
            System.out.println("forward后的语句");
            return;
        }
        if (action.equals("include")) {
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            response.getWriter().append("观察include前的字符串是否输出到网页上!");
            request.getRequestDispatcher("/form0.jsp").include(request, response);
            response.getWriter().append("观察include后的字符串是否输出到网页上!");
            System.out.println("include后的语句");
            return;
        }
        if (action.equals("contentType")) {
            System.out.println("response.getCharacterEncoding()默认值是：" + response.getCharacterEncoding());
            System.out.println("response.getContentType默认值是：" + response.getContentType());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("<font color='red'>测试数据</font>");//可以打印对象
            return;
        }
        if (action.equals("chars")) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("write数据");
            response.getWriter().print(2020);//整数
            response.getWriter().print("print数据");//可以打印对象
            java.util.List<String> l = new java.util.ArrayList<String>();
            l.add("test");
            response.getWriter().print(l);//对象
            response.getWriter().println("println数据");
            response.getWriter().append("append数据1").append("append数据2").append("append数据3");
            String data = "使用OutputStream将字节数据封装到响应体<br/>";
            byte[] dataByteArray = data.getBytes(StandardCharsets.UTF_8);
            response.getOutputStream().write(dataByteArray);//文本型数据封装到响应体后，不能再封装字节数据
            return;
        }
        if (action.equals("bytes")) {
            response.setContentType("text/html;charset=UTF-8");
            String data = "使用OutputStream将字节数据封装到响应体<br/>";
            byte[] dataByteArray = data.getBytes(StandardCharsets.UTF_8);
            response.getOutputStream().write(dataByteArray);
            response.getOutputStream().write(dataByteArray, 0, dataByteArray.length);
            response.getWriter().write("write数据");//文本型数据封装到响应体后，不能再封装字节数据
        }
        if (action.equals("picture")) {
            String fileName = request.getParameter("fileName");
            String path = this.getServletContext().getRealPath("download/" + fileName);
            java.io.File file = new java.io.File(path);
            java.io.FileInputStream fis = new java.io.FileInputStream(file);

            String mime = request.getServletContext().getMimeType(fileName);
            if (mime == null) {
                mime = "application/octet-stream";
            }
            response.setContentType(mime);
            response.setContentLength((int) file.length());
            String subAction = request.getParameter("subAction");
            if (subAction != null && subAction.equals("download")) {
                System.out.println("开始测试" + subAction + "功能");//解决文件下载时文件名乱码问题
                String newFileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
                response.setHeader("Content-Disposition", "attachment;fileName=" + newFileName);
            }
            jakarta.servlet.ServletOutputStream sos = response.getOutputStream();
            byte[] bytes = new byte[1024 * 4];
            int len = 0;
            while ((len = fis.read(bytes)) != -1) {
                sos.write(bytes, 0, len);
            }
            fis.close();
            sos.close();
        }
        if (action.equals("img")) {
            String fileName = request.getParameter("fileName");
            String contextPath = request.getContextPath();
            String src = contextPath + "/download/" + fileName;
            String img = "<img src='" + src + "' alt='图片' />";
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().append("图片可以和文字并存:<br/>").append(img);
        }
        if (action.equals("imgServlet")) {
            String fileName = request.getParameter("fileName");
            String contextPath = request.getContextPath();
            String servletURLPattern = request.getHttpServletMapping().getPattern();
            String src = contextPath + servletURLPattern + "?action=picture&fileName=" + fileName;
            String img = "<img src=' " + src + " ' />";
            response.setContentType("text/html;charset-UTF-8");
            response.getWriter().append("图片可以和文字并存，并且图片的物理路径可以是任意位置:<br/>").append(img);
        } else {
            //所有新增的功能代码活频到此处
            //通知浏览器用户， 更多功能尚待开发
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print("该功能尚未提供, 期待您的开发升级:!<br/>");
        }
        System.out.println("开始测试" + action + "功能");
    }


    public void destroy() {
    }
}
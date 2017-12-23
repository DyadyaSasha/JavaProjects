<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="java.util.Iterator" %>

<%
    File file;
    int maxFileSize = 5000*1024;
    int maxMemSize = 5000*1024;
//  получаем объект ServletContext из pageContext, чтобы иметь доступ к файлу web.xml
    ServletContext context = pageContext.getServletContext();
    String filePath = context.getInitParameter("file-upload");

    String contentType = request.getContentType();
    if(contentType.contains("multipart/form-data")){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(maxMemSize);

        factory.setRepository(new File("D:\\Projects\\Webjsp\\data"));

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(maxFileSize);

        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            Iterator<FileItem> iterator = fileItems.iterator();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>JSP File upload</title>");
            out.println("</head>");
            out.println("<body>");

            while (iterator.hasNext()){
                FileItem fi = iterator.next();
                if(!fi.isFormField()){
//                  параметры файла
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

//                  сохраняем файл
                    if(fileName.contains("\\")){
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
//                    непосредственно скачивает файл
                    fi.write(file);
                    out.println("Uploaded filename: " + filePath + fileName + "<br>");
                }
            }

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e){
            e.printStackTrace();
        }
    } else {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet upload</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<p>No file uploaded</p>");
        out.println("</body>");
        out.println("</html>");
    }
%>
package ru.geekbrains;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {
    private static final Pattern PARAM_PATTERN = Pattern.compile("\\/(\\d+)");
    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = new ProductRepository();
        productRepository.insert(new Product("Product 1"));
        productRepository.insert(new Product("Product 2"));
        productRepository.insert(new Product("Product 3"));
        productRepository.insert(new Product("Product 4"));
        productRepository.insert(new Product("Product 5"));
        productRepository.insert(new Product("Product 6"));
        productRepository.insert(new Product("Product 7"));
        productRepository.insert(new Product("Product 8"));
        productRepository.insert(new Product("Product 9"));
        productRepository.insert(new Product("Product 10"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("products", productRepository.findAll());
            getServletContext().getRequestDispatcher("/product.jsp").forward(req, resp);
        } else {
            Matcher matcher = PARAM_PATTERN.matcher(req.getPathInfo());
            if (matcher.matches()) {
                long id = Long.parseLong(matcher.group(1));
                Product product = this.productRepository.findById(id);
                if (product == null) {
                    resp.getWriter().println("Product not found");
                    resp.setStatus(404);
                    return;
                }
                req.setAttribute("Product", product);
                getServletContext().getRequestDispatcher("/product_form.jsp").forward(req, resp);
            } else {
                resp.getWriter().println("Bad parameter value");
                resp.setStatus(400);
            }
        }
       /* if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            PrintWriter wr = resp.getWriter();
            wr.println("<table>");
            wr.println("<tr>");
            wr.println("<th>   Id   </th>");
            wr.println("<th>product</th>");
            wr.println("</tr>");
            for (Product product : productRepository.findAll()) {
                wr.println("<tr>");
                wr.println("<td><a href='"+ req.getContextPath() + "/product/" + product.getId() + "'>" + product.getId() + "</a></td>");
                wr.println("<td>" + product.getProduct() + "</td>");
                wr.println("<tr>");
            }

            wr.println("</table>");
        } else {
            Matcher matcher = PARAM_PATTERN.matcher(req.getPathInfo());
            if(matcher.matches()){
                long id = Long.parseLong(matcher.group(1));
                Product product = this.productRepository.findById(id);
                if (product == null) {
                   resp.getWriter().println("Product not found");
                   resp.setStatus(404);
                   return;
                }
                resp.getWriter().println("<p>Id = " + product.getId()+ "</p>");
                resp.getWriter().println("<p>Product = " + product.getProduct() + "</p>");
            }else {
                resp.getWriter().println("BAd parameter value");
                resp.setStatus(400);

            }
        }
    }
    }*/
    }
}
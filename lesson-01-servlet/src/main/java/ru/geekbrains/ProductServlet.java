package ru.geekbrains;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.geekbrains.product.Product;
import ru.geekbrains.product.ProductRepository;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

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
        PrintWriter wr = resp.getWriter();
        wr.println("<table>");
        wr.println("<tr>");
        wr.println("<th>   Id   </th>");
        wr.println("<th>product</th>");
        wr.println("</tr>");
        for (Product product : productRepository.findAll()) {
            wr.println("<tr>");
            wr.println("<td>"+ product.getId() +"</td>");
            wr.println("<td>"+ product.getProduct() +"</td>");
            wr.println("<tr>");
        }

        wr.println("</table>");
    }

    }
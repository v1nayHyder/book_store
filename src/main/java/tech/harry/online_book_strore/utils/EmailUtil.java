package tech.harry.online_book_strore.utils;

import tech.harry.online_book_strore.entities.Order;
import tech.harry.online_book_strore.entities.OrderItem;

public class EmailUtil {

    public static String createOrderConfirmationEmail(Order order) {
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("<html>")
                .append("<head>")
                .append("<style>")
                // ... (Include your existing styles with brand colors) ..."
                .append("</style>")
                .append("</head>")
                .append("<body>")
                .append("<div class='container'>")
                .append("<img src='cid:company_logo' alt='Vinay's Online Book Store' style='width: 100px; height: auto; display: block; margin-bottom: 20px;'/>") // Add company logo
                // ... (Consider adding a CTA banner here) ..."
                .append("<h1> Order Confirmation</h1>")
                .append("<p>Dear ")
                .append(order.getId()!= null ? order.getUserId() : "Valued Customer")
                .append(",</p>")
                .append("<p>Thank you for choosing Vinay's Online Book Store! We are thrilled to confirm your order.</p>")
                .append("<h2>Your Order ID: <strong>")
                .append(order.getId())
                .append("</strong></h2>")
                .append("<p>Here’s a summary of your order:</p>")
                .append("<ul>");

        for (OrderItem item : order.getOrderItems()) {
            emailBody.append("<li>")
                    .append("<img src='cid:") // Add image for each book using item.getBook().getImageUrl()
                    .append("' alt='")
                    .append(item.getBook().getTitle())
                    .append("' style='width: 50px; height: auto; float: left; margin: 5px 10px 5px 0;'/>")  // Display book image
                    .append(item.getQuantity())
                    .append(" x ")
                    .append(item.getBook().getTitle())
                    .append(" - Rs. ")
                    .append(item.getPrice())
                    .append("</li>");
        }

        emailBody.append("</ul>")
                .append("<p class='total'>Total Amount: <strong>Rs. ")
                .append(order.getTotalPrice())
                .append("</strong></p>")
                .append("<p>We hope you enjoy your new books! ✨</p>")
                .append("<a href='https://vinaystore.com/orders/")
                .append(order.getId())
                .append("' class='button'>View Your Order</a>")
                .append("<p>If you have any questions, feel free to reach out to us at <a href='mailto:support@vinaystore.com'>support@vinaystore.com</a>.</p>")
                // ... (Consider adding a recommended book section here) ..."
                .append("<p class='footer'>Happy reading!<br>Best regards,<br>Vinay<br>Online Book Store Team</p>")
                .append("</div>")
                .append("</body>")
                .append("</html>");

        return emailBody.toString();
    }

}




//package tech.harry.online_book_strore.utils;
//
//import tech.harry.online_book_strore.entities.Order;
//import tech.harry.online_book_strore.entities.OrderItem;
//
//public class EmailUtil {
//
//    public static String createOrderConfirmationEmail(Order order) {
//        StringBuilder emailBody = new StringBuilder();
//        emailBody.append("<html>")
//                .append("<head>")
//                .append("<style>")
//                .append("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f9f9f9; }")
//                .append("h1 { color: #5d5d5d; }")
//                .append("p { font-size: 16px; color: #555; }")
//                .append("ul { list-style-type: none; padding: 0; }")
//                .append("li { margin: 10px 0; padding: 10px; background-color: #fff; border-radius: 5px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }")
//                .append("strong { color: #000; }")
//                .append(".total { font-size: 18px; color: #d9534f; }")
//                .append(".button { display: inline-block; margin-top: 20px; padding: 10px 20px; font-size: 16px; color: white; background-color: #28a745; text-decoration: none; border-radius: 5px; }")
//                .append("</style>")
//                .append("</head>")
//                .append("<body>")
//                .append("<h1>🎉 Order Confirmation</h1>")
//                .append("<p>Dear ").append(order.getUserId()+"Unknown").append(",</p>")
//                .append("<p>Thank you for choosing Vinay's Online Book Store! We’re thrilled to confirm your order.</p>")
//                .append("<p>Your order ID: <strong>").append(order.getId()).append("</strong></p>")
//                .append("<p>Here’s a summary of your order:</p>")
//                .append("<ul>");
//
//        for (OrderItem item : order.getOrderItems()) {
//            emailBody.append("<li>")
//                    .append(item.getQuantity()).append(" x ")
//                    .append(item.getBook().getTitle()).append(" - Rs. ")
//                    .append(item.getPrice()).append("</li>");
//        }
//
//        emailBody.append("</ul>")
//                .append("<p class='total'>Total Amount: <strong>Rs. ").append(order.getTotalPrice()).append("</strong></p>")
//                .append("<p>We hope you enjoy your new books! 📚✨</p>")
//                .append("<a href='https://vinaystore.com/orders/").append(order.getId()).append("' class='button'>View Your Order</a>")
//                .append("<p>If you have any questions, feel free to reach out to us at <a href='mailto:support@vinaystore.com'>support@vinaystore.com</a>.</p>")
//                .append("<p>Happy reading!<br>Best regards,<br>Vinay<br>Online Book Store Team</p>")
//                .append("</body>")
//                .append("</html>");
//
//        return emailBody.toString();
//    }
//
//}

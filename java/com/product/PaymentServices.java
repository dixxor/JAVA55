package com.product;

import java.util.*;

import com.product.OrderDetail;
import com.paypal.api.payments.*;
import com.paypal.base.rest.*;

public class PaymentServices {
	private static final String CLIENT_ID = "AZb-6aReF-rRFBUQXYTCjEzhCKxLLD2wViWovWICvtXP6VP3hITyzHfERh3iRMUVxIfl1X2vL7EhEq7S";
    private static final String CLIENT_SECRET = "EI620zc-Gaje3EjBXjzO6DA0FZ7qJJ3SaYr43X0laS-xG94HpqQPm_wdyk3WPlXQf7z5LWNpquyd_q4D";
    private static final String MODE = "sandbox";
    
    public String authorizePayment(OrderDetail orderDetail, String name) throws PayPalRESTException {           	 
        Payer payer = getPayerInformation(name);
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(orderDetail);
         
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");
 
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
 
        Payment approvedPayment = requestPayment.create(apiContext);
 
        return getApprovalLink(approvedPayment); 
    }
    
    private Payer getPayerInformation(String name) {
    	Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
         
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(name) 
                 //.setLastName("Peterson")
                 .setEmail("sb-j43ysc28953520@personal.example.com"); 
         
        payer.setPayerInfo(payerInfo);         
        return payer;
    }
    
    private RedirectUrls getRedirectURLs() {
    	RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8086/Supermarket/fail.jsp"); 
        redirectUrls.setReturnUrl("http://localhost:8086/Supermarket/ReviewPaymentServlet"); 
         
        return redirectUrls;
    }
    
    private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {
    	Details details = new Details();
        details.setShipping(orderDetail.getShipping());
        details.setSubtotal(orderDetail.getSubTotal());
        details.setTax(orderDetail.getTax());
     
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(orderDetail.getTotal());
        amount.setDetails(details);
     
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        
         
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
         
        Item item = new Item();
        item.setCurrency("USD");
        
        item.setPrice(orderDetail.getSubTotal());
        item.setTax(orderDetail.getTax());
        item.setQuantity("1");
         
        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);
     
        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);  
         
        return listTransaction;
    }
    
    private String getApprovalLink(Payment approvedPayment) {
    	List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
         
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }      
         
        return approvalLink;
    }
    
    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }
    
    public Payment executePayment(String paymentId, String payerId)
            throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
     
        Payment payment = new Payment().setId(paymentId);
     
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
     
        return payment.execute(apiContext, paymentExecution);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Farmer.Farmer_ConnectSP.Services;

import com.Farmer.Farmer_ConnectSP.DTOS.BiddingDTO;
import com.Farmer.Farmer_ConnectSP.DTOS.ProductDTO;
import com.Farmer.Farmer_ConnectSP.Entities.Bidding;
import com.Farmer.Farmer_ConnectSP.Entities.CustomerRegister;
import com.Farmer.Farmer_ConnectSP.Entities.FarmerRegister;
import com.Farmer.Farmer_ConnectSP.Entities.Product;
import com.Farmer.Farmer_ConnectSP.Repository.BiddingRepository;
import com.Farmer.Farmer_ConnectSP.Repository.CustomerRepository;
import com.Farmer.Farmer_ConnectSP.Repository.FarmerRepository;
import com.Farmer.Farmer_ConnectSP.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author preml
 */
@Service
public class BiddingService {

    @Autowired
    private BiddingRepository biddingrepo;

    @Autowired
    private CustomerRepository customerrepo;

    @Autowired
    private ProductRepository productrepo;

    @Autowired
    private FarmerRepository farmerrepo;

    @Autowired
    private JavaMailSender javamailsender;

    private final String serverurl = "http://localhost:8080";

    public BiddingDTO Biddingvalue(BiddingDTO biddto) {
        try {
            System.out.println(biddto.getCustomerId());
            Optional<CustomerRegister> customerop = customerrepo.findById(biddto.getCustomerId());
            Optional<Product> productop = productrepo.findById(biddto.getProductId());
            System.out.println(biddto.getProductId());

            if (customerop.isEmpty()) {
                new RuntimeException("customer id is null");
            }

            if (productop.isEmpty()) {
                new RuntimeException("product id is null");
            }

            CustomerRegister customerobj = customerop.get();
            Product productobj = productop.get();

            Bidding bidobj = new Bidding();
            bidobj.setBiddingPrice(biddto.getPrice());
            bidobj.setCustomerId(customerobj);
            bidobj.setProductId(productobj);
            bidobj.setStatus(1);

            biddingrepo.save(bidobj);

            biddto.setBid(bidobj.getBid());
            biddto.setStatus(bidobj.getStatus());
            biddto.setDatetime(bidobj.getDatetime());
            return biddto;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bidding rejectBidprice(Integer id) {
        Optional<Bidding> bidop = biddingrepo.findById(id);

        if (bidop.isEmpty()) {
            new RuntimeException("not values");
        }

        Bidding bidobj = new Bidding();
        bidobj.setCustomerId(bidop.get().getCustomerId());

        biddingrepo.deleteById(id);
        return bidobj;

    }

    public List<BiddingDTO> GetBiddingproducts(Integer cid) {
        List<Bidding> bidobj = biddingrepo.findByCustomerId_Cid(cid);

        List<BiddingDTO> dtolist = new ArrayList<>();

        for (Bidding bid : bidobj) {
            BiddingDTO bidto = new BiddingDTO();
            bidto.setBid(bid.getBid());
            bidto.setBiddingPrice(bid.getBiddingPrice());
            bidto.setCustomerId(bid.getCustomerId().getCid());
            bidto.setProductId(bid.getProductId().getPid());
            bidto.setStatus(bid.getStatus());
            bidto.setDatetime(bid.getDatetime());
            Optional<Product> productop = productrepo.findById(bid.getProductId().getPid());
            if (productop.isEmpty()) {
                new RuntimeException("Empty products");
            }
            Product pobj = productop.get();
            bidto.setDescription(pobj.getDescription());
            bidto.setProductName(pobj.getProductName());
            bidto.setPrice(pobj.getPrice());
            bidto.setProductType(pobj.getProductType());
            bidto.setImg1(serverurl + "/Product-images/" + pobj.getImg1());
//            bidto.setImg2(serverurl + "/Product-images/" + pobj.getImg2());

            dtolist.add(bidto);
        }
        return dtolist;
    }

    public List<BiddingDTO> Getallbiddingdata() {
        List<Bidding> objlist = biddingrepo.findAll();

        List<BiddingDTO> bidinglist = new ArrayList<>();

        for (Bidding bdto : objlist) {
            BiddingDTO bid = new BiddingDTO();

            bid.setBid(bdto.getBid());
            bid.setBiddingPrice(bdto.getBiddingPrice());
            bid.setDatetime(bdto.getDatetime());
            bid.setStatus(bdto.getStatus());
            bid.setCustomerId(bdto.getCustomerId().getCid());
            bid.setProductId(bdto.getProductId().getPid());
            

            Optional<Product> productop = productrepo.findById(bid.getProductId());
            if (productop.isEmpty()) {
                new RuntimeException("Empty products");
            }
            Product pobj = productop.get();
            bid.setImg1(serverurl + "/Product-images/" + pobj.getImg1());
            bid.setImg2(serverurl + "/Product-images/" + pobj.getImg2());

            bidinglist.add(bid);
        }

        return bidinglist;

    }

    public BiddingDTO getBiddedvalue(Integer id) {
        Optional<Bidding> bidop = biddingrepo.findById(id);

        if (bidop.isEmpty()) {
            new RuntimeException("Empty Biiding values");
        }

        Bidding bidobj = bidop.get();

        Optional<Product> productop = productrepo.findById(bidobj.getProductId().getPid());

        Product productobj = productop.get();
        BiddingDTO biddto = new BiddingDTO();

        Optional<FarmerRegister> farmerop = farmerrepo.findById(productobj.getFid().getFid());
        FarmerRegister farmerobj = farmerop.get();

        biddto.setBid(id);
        biddto.setBiddingPrice(bidobj.getBiddingPrice());
        biddto.setDatetime(bidobj.getDatetime());
        biddto.setProductId(productobj.getPid());
        biddto.setCustomerId(bidobj.getCustomerId().getCid());
        biddto.setStatus(bidobj.getStatus());
        biddto.setProductName(productobj.getProductName());
        biddto.setQuantity(productobj.getQuantity());
        biddto.setStatus(productobj.getStatus());
        biddto.setState(farmerobj.getState());
        biddto.setCity(farmerobj.getCity());
        biddto.setFarmername(farmerobj.getUsername());
        biddto.setPhoneno(farmerobj.getPhoneno());

        biddto.setImg1(serverurl + "/Product-images/" + productobj.getImg1());

        return biddto;

    }

    public BiddingDTO replacaebidvalue(BiddingDTO biddto, Integer id) {
        Optional<Bidding> bidop = biddingrepo.findById(id);

        Bidding bidobj = bidop.get();

        bidobj.setBiddingPrice(biddto.getBiddingPrice());

        biddingrepo.save(bidobj);
        return biddto;

    }

    public List<BiddingDTO> biddingEnd(Integer pid) {
        List<Bidding> biddinglist = biddingrepo.findByProductId_Pid(pid);

        List<BiddingDTO> biddtolist = new ArrayList<>();

        for (Bidding product : biddinglist) {
            BiddingDTO biddto = new BiddingDTO();

            biddto.setBid(product.getBid());
            biddto.setBiddingPrice(product.getBiddingPrice());
            biddto.setDatetime(product.getDatetime());
            biddto.setStatus(product.getStatus());

            Optional<CustomerRegister> customerop = customerrepo.findById(product.getCustomerId().getCid());
            CustomerRegister customerobj = customerop.get();
            biddto.setCustomername(customerobj.getUsername());
            biddto.setCustomerimage(serverurl + "/Product-images/" + customerobj.getCustomerImg());
            biddto.setPhoneno(customerobj.getPhoneno());
            biddto.setState(customerobj.getState());
            biddto.setCity(customerobj.getCity());
            biddto.setCustomerId(customerobj.getCid());

            Optional<Product> productop = productrepo.findById(product.getProductId().getPid());
            Product productobj = productop.get();
            biddto.setImg1(serverurl + "/Product-images/" + productobj.getImg1());
            biddto.setProductId(pid);
            biddto.setProductType(productobj.getProductType());
            biddto.setQuality(productobj.getQuality());
            biddto.setQuantity(productobj.getQuantity());
            biddto.setDescription(productobj.getDescription());
            biddto.setPrice(productobj.getPrice());

            biddtolist.add(biddto);

        }
        return biddtolist;

    }

    @Transactional
    public SimpleMailMessage bidEndEmail(Integer id) {
        Optional<Bidding> bidop = biddingrepo.findById(id);
        Bidding bidobj = bidop.get();

        Optional<Product> productop = productrepo.findById(bidobj.getProductId().getPid());
        Product productobj = productop.get();

        Optional<CustomerRegister> cutomerop = customerrepo.findById(bidobj.getCustomerId().getCid());
        CustomerRegister cutomerobj = cutomerop.get();

        Optional<FarmerRegister> farmerop = farmerrepo.findById(productobj.getFid().getFid());
        FarmerRegister farmerobj = farmerop.get();

        bidobj.setStatus(0);
        productobj.setStatus(0);
        
        
        biddingrepo.deleteByProductId_PidAndStatus(productobj.getPid(),1);
        biddingrepo.save(bidobj);
        productrepo.save(productobj);

        SimpleMailMessage message = new SimpleMailMessage();

        //email for customer 
        message.setTo(cutomerobj.getEmail());
        message.setSubject("🎉 Your Bid Has Been Accepted – Farmer-Connect");
        message.setText("Dear " + cutomerobj.getUsername() + ",\n"
                + "\n"
                + "Greetings from Farmer-Connect 🌾\n"
                + "\n"
                + "We are happy to inform you that the farmer has accepted your bid for the product listed below.\n"
                + "\n"
                + "Product Details:\n"
                + "Product Name:" + productobj.getProductName() + "\n"
                + "\n"
                + "Farmer Details:\n"
                + "Farmer Name: " + farmerobj.getUsername() + "\n"
                + "Farmer Phone Number:" + farmerobj.getPhoneno() + "\n"
                + "Farm Address:" + farmerobj.getAddress() + "\n"
                + "\n"
                + "Accepted Bid Details:\n"
                + "Your Bid Amount: ₹ " + bidobj.getBiddingPrice() + "\n"
                + "Acceptance Date & Time:" + bidobj.getDatetime() + "\n"
                + "Status: Accepted ✅\n"
                + "\n"
                + "🎉 Congratulations! Your bid has been successfully approved.\n"
                + "\n"
                + "You can now contact the farmer directly using the above details to proceed with payment and delivery arrangements.\n"
                + "\n"
                + "If you need any assistance, feel free to contact our support team.\n"
                + "\n"
                + "Thank you for choosing Farmer-Connect — Connecting Farmers & Customers Directly.\n"
                + "\n"
                + "Best Regards,\n"
                + "Team Farmer-Connect\n"
                + "🌐 Farmer-Connect Platform\n"
                + "📞 Customer Support: +91-XXXXXXXXXX\n"
                + "✉ support@farmerconnect.com");

        javamailsender.send(message);

        // email for farmer 
        message.setTo(farmerobj.getEmail());
        message.setSubject(serverurl);
        message.setText("Dear " + farmerobj.getUsername() + ",\n"
                + "\n"
                + "Greetings from Farmer-Connect 🌾\n"
                + "\n"
                + "This is to confirm that you have successfully accepted the customer’s bid for your product listed below.\n"
                + "\n"
                + "🛒 Product Details:\n"
                + "Product Name:" + productobj.getProductName() + "\n"
                + "\n"
                + "👤 Customer Details:\n"
                + "Customer Name:" + cutomerobj.getUsername() + "\n"
                + "Customer Phone Number:" + cutomerobj.getPhoneno() + "\n"
                + "Customer Address:" + cutomerobj.getAddress() + "\n"
                + "\n"
                + "💰 Accepted Bid Details:\n"
                + "Accepted Bid Amount: ₹ " + bidobj.getBiddingPrice() + "\n"
                + "Acceptance Date & Time:" + bidobj.getDatetime() + "\n"
                + "Status: Accepted ✅\n"
                + "\n"
                + "🎉 The customer has been notified about your acceptance.\n"
                + "\n"
                + "You may now contact the customer directly to proceed with payment and delivery arrangements.\n"
                + "\n"
                + "If you need any assistance, feel free to contact our support team.\n"
                + "\n"
                + "Thank you for using Farmer-Connect — Connecting Farmers & Customers Directly.\n"
                + "\n"
                + "Best Regards,\n"
                + "Team Farmer-Connect\n"
                + "🌐 Farmer-Connect Platform\n"
                + "📞 Customer Support: +91-XXXXXXXXXX\n"
                + "✉ support@farmerconnect.com");

        javamailsender.send(message);

        message.setSubject("Bid Confirmation");
        message.setFrom(farmerobj.getUsername());
        message.setTo(cutomerobj.getUsername());

        return message;
    }

    //history
    public List<ProductDTO> getfarmerhistory(Integer fid) {
        List<Product> productlist = productrepo.findByFid_Fid(fid);

        List<ProductDTO> prodcutstolist = new ArrayList<>();

        for (Product productobj : productlist) {
            ProductDTO productdto = new ProductDTO();

            productdto.setDatetime(productobj.getDatetime());
            productdto.setPid(productobj.getPid());
            productdto.setStatus(productobj.getStatus());
            productdto.setProductName(productobj.getProductName());
            productdto.setPrice(productobj.getPrice());
            productdto.setDescription(productobj.getDescription());

            productdto.setImgname1(serverurl + "/Product-images/" + productobj.getImg1());

            prodcutstolist.add(productdto);
        }
        return prodcutstolist;

    }

    public BiddingDTO getFarmerproducthistory(Integer pid) {
        Optional<Product> productop = productrepo.findById(pid);
        Product productobj = productop.get();

        Bidding biddinglist = biddingrepo.findByProductId_PidAndStatus(pid, 0);

        Optional<CustomerRegister> customerop = customerrepo.findById(biddinglist.getCustomerId().getCid());
        CustomerRegister customerobj = customerop.get();

        BiddingDTO biddto = new BiddingDTO();

        biddto.setBiddingPrice(biddinglist.getBiddingPrice().max(BigDecimal.ONE));
        biddto.setDatetime(biddinglist.getDatetime());
        biddto.setStatus(biddinglist.getStatus());

        //customer infomation 
        biddto.setCustomername(customerobj.getUsername());
        biddto.setCity(customerobj.getCity());
        biddto.setPhoneno(customerobj.getPhoneno());
        biddto.setState(customerobj.getState());
        biddto.setCustomerimage(serverurl + "/Customer-images/" + customerobj.getCustomerImg());

        //product info
        biddto.setProductName(productobj.getProductName());
        biddto.setProductType(productobj.getProductType());
        biddto.setQuantity(productobj.getQuantity());
        biddto.setQuality(productobj.getQuality());
        biddto.setImg1(serverurl + "/Product-images/" + productobj.getImg1());

        return biddto;
    }

    public List<BiddingDTO> getcustomerhistory(Integer cid) {
        List<Bidding> bidlist = biddingrepo.findByCustomerId_CidAndStatus(cid, 0);

        List<BiddingDTO> biddtolist = new ArrayList<>();
        for (Bidding bidobj : bidlist) {
            BiddingDTO biddto = new BiddingDTO();

            Optional<Product> productop = productrepo.findById(bidobj.getProductId().getPid());
            Product productobj = productop.get();
            biddto.setProductName(productobj.getProductName());
            biddto.setProductId(productobj.getPid());
            biddto.setDescription(productobj.getDescription());
            biddto.setDatetime(bidobj.getDatetime());
            biddto.setPrice(bidobj.getBiddingPrice());
            biddto.setImg1(serverurl + "/Product-images/" + productobj.getImg1());

            biddtolist.add(biddto);
        }

        return biddtolist;
    }

    public BiddingDTO getcustomerproducthistory(Integer pid) {

        Optional<Product> productop = productrepo.findById(pid);
        Product productobj = productop.get();

        System.out.println(pid);
        Bidding biddinglist = biddingrepo.findByProductId_PidAndStatus(pid, 0);
        System.out.print(biddinglist.getBiddingPrice());
        BiddingDTO biddto = new BiddingDTO();

        //product info
        biddto.setProductName(productobj.getProductName());
        biddto.setProductType(productobj.getProductType());
        biddto.setDescription(productobj.getDescription());
        biddto.setQuantity(productobj.getQuantity());
        biddto.setQuality(productobj.getQuality());

        biddto.setDatetime(biddinglist.getDatetime());
        biddto.setBiddingPrice(biddinglist.getBiddingPrice());

        biddto.setImg1(serverurl + "/Product-images/" + productobj.getImg1());

        Optional<FarmerRegister> farmerop = farmerrepo.findById(productobj.getFid().getFid());
        FarmerRegister farmerobj = farmerop.get();

        biddto.setFarmername(farmerobj.getUsername());
        biddto.setPhoneno(farmerobj.getPhoneno());
        biddto.setState(farmerobj.getState());
        biddto.setCity(farmerobj.getCity());
        biddto.setImg2(serverurl + "/Farmer-photo/" + farmerobj.getFarmerImg());

        return biddto;

    }
}

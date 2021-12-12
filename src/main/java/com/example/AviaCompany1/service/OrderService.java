package com.example.AviaCompany1.service;


import com.example.AviaCompany1.model.*;
import com.example.AviaCompany1.repo.OrderRepository;
import com.example.AviaCompany1.repo.OrderedProductRepository;
import com.example.AviaCompany1.repo.ProductRepository;
import com.example.AviaCompany1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
//    private final String MAIL_MESSAGE = "Ваш заказ оформлен";
//    private final String MAIL_SUBJECT = "Заказ";


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }


    public void cancelorder(Long id)
    {
        //вернуть деньги
        //вернуть место
        //удалить из orderepproducts


        OrderedProduct orderedProduct=orderedProductRepository.getById(id);
        Set<OrderedProduct> list=orderedProduct.getOrder().getOrderedProducts();
        Set<OrderedProduct> list1=new HashSet<>();
        for (OrderedProduct orderedProduct1 : list)
        {
            if(orderedProduct.getId()!=orderedProduct1.getId())
            {
                list1.add(orderedProduct1);
            }
        }

        orderRepository.deleteById(orderedProduct.getOrder().getId());
        orderedProduct.getOrder().setStatus(Status.Accepted);
        orderedProduct.getOrder().setUser(orderedProduct.getOrder().getUser());
        orderedProduct.getOrder().setOrderedProducts(list1);
        orderedProduct.getOrder().getUser().setBalance(orderedProduct.getOrder().getUser().getBalance()+orderedProduct.getProduct().getPrice());
        orderedProduct.getProduct().setPlaces(orderedProduct.getProduct().getPlaces()+1);
        orderRepository.save(orderedProduct.getOrder());
    }

    public void makeOrder(String username, Cart cart) {

        User user=userRepository.findByUsername(username);

//       if (user.getOrders() == null) {
//           user.setOrders(new ArrayList<Order>());
//        }

        Order order = new Order();
        Set<OrderedProduct> orderedProducts = new HashSet<>();
        for (CartItem cartItem : cart.getCartItems()) {
            orderedProducts.add(new OrderedProduct(order, cartItem.getProduct(), cartItem.getAmount()));

        }


        order.setStatus(Status.Accepted);
        order.setUser(user);
        order.setOrderedProducts(orderedProducts);
        for (CartItem cartItem : cart.getCartItems()) {
            cartItem.getProduct().setPlaces(cartItem.getProduct().getPlaces()-cartItem.getAmount());
            productRepository.save(cartItem.getProduct());
        }
        order.setProductsPrise(cart.getTotalPrise());

        order.getUser().setBalance(order.getUser().getBalance()-cart.getTotalPrise());



        orderRepository.save(order);


//        List<String> productNames = order.getOrderedProducts().stream().map(OrderedProduct::getProduct).map(Product::getName).collect(Collectors.toList());
//        String names = String.join(", ", productNames);

    }
}
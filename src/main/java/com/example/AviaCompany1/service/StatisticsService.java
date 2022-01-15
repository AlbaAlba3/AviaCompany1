package com.example.AviaCompany1.service;

import com.example.AviaCompany1.model.Order;
import com.example.AviaCompany1.model.OrderedProduct;
import com.example.AviaCompany1.model.User;
import com.example.AviaCompany1.repo.OrderRepository;
import com.example.AviaCompany1.repo.OrderedProductRepository;
import com.example.AviaCompany1.repo.ProductRepository;
import com.example.AviaCompany1.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class StatisticsService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    int count=0;
    int count1=0;
    int price=0;
    String name;
    String namein;
    ArrayList<String> direction=new ArrayList<>();
    ArrayList<String> popular=new ArrayList<>();
    int max;

    public Map<String,Integer> numberstat() {

        Map<String, Integer> statictics = new HashMap<>();
        for (OrderedProduct orderedProduct : orderedProductRepository.findAll()) {
            count = count + orderedProduct.getAmount();
            price = price + orderedProduct.getProduct().getPrice() * orderedProduct.getAmount();
            statictics.put("count", count);
            statictics.put("price", price);

        }

        count = 0;
        price = 0;
        return statictics;
    }

        public ArrayList<String>  populartickets(){

            Map<Long, Integer> counts=new HashMap<>();
            counts.clear();
            direction.clear();
            for (OrderedProduct orderedProduct : orderedProductRepository.findAll()) {
                for (OrderedProduct orderedProduct1 : orderedProductRepository.findAll()) {
                    if(orderedProduct.getProduct().getId()==orderedProduct1.getProduct().getId())
                    {
                        count1++;
                    }
                }
                counts.put(orderedProduct.getProduct().getId(),count1);
                count1=0;
            }

            if (counts.isEmpty())
            {
                return direction;
            }
            else
            { max=counts.values().stream().max(Comparator.naturalOrder()).get();}


            Collection<Long> collection= counts.keySet();
            ArrayList<Long> popularsid=new ArrayList<>();


            for (Long key : collection) {
                Integer obj = counts.get(key);
                if (key != null) {
                    if (max==obj) {
                        popularsid.add(key);
//                        popularid=key;
                    }
                }
            }

            for (Long id : popularsid) {
                name= productRepository.getById(id).getName();
                namein=productRepository.getById(id).getNamein();;
                direction.add(name+'-'+namein);
            }
//            name=orderedProductRepository.getById(popularid).getProduct().getName();
//            namein=orderedProductRepository.getById(popularid).getProduct().getNamein();
//            direction=name+'-'+namein;
            return direction;

        }


}

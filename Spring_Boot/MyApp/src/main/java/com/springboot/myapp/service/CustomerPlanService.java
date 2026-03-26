package com.springboot.myapp.service;

import com.springboot.myapp.dto.CustomerPlanReqDto;
import com.springboot.myapp.dto.CustomerPlanResDto;
import com.springboot.myapp.mapper.CustomerPlanMapper;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.model.CustomerPlan;
import com.springboot.myapp.model.Plan;
import com.springboot.myapp.repository.CustomerPlanRepository;
import com.springboot.myapp.utility.PlanUtility;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class CustomerPlanService {
    private final CustomerService customerService;
    private final PlanService planService;
    private final PlanUtility planUtility;
    private final CustomerPlanRepository customerPlanRepository;

    public CustomerPlanService(CustomerService customerService, PlanService planService, PlanUtility planUtility, CustomerPlanRepository customerPlanRepository) {
        this.customerService = customerService;
        this.planService = planService;
        this.planUtility = planUtility;
        this.customerPlanRepository = customerPlanRepository;
    }

    public void addCustomerPlan(CustomerPlanReqDto customerPlanReqDto, long customerId, long planId) {
        //1. get customer by id
        Customer customer = customerService.getById(customerId);

        //2. get plan by id
        Plan plan = planService.getById(planId);

        //3. attach customer and plan to CustomerPlan object and compute missing fields
        LocalDate time = planUtility.computeEndDate(customerPlanReqDto.start_date(), plan.getDays());
        CustomerPlan customerPlan = new CustomerPlan();

        customerPlan.setStart_date(customerPlanReqDto.start_date());
        customerPlan.setEnd_date(time);
        customerPlan.setDiscount(customerPlanReqDto.discount());
        customerPlan.setCoupon(customerPlan.getCoupon());
        customerPlan.setCustomer(customer);
        customerPlan.setPlan(plan);

        //4. save
        customerPlanRepository.save(customerPlan);
    }

    public List<CustomerPlanResDto> getCustomersByPlanId(long planId) {
        //validation
        planService.getById(planId);

        List<CustomerPlan> list = customerPlanRepository.getCustomerByPlanId(planId);

        return list
                .stream()
                .map(CustomerPlanMapper::mapToCustomerPlanDto)
                .toList();
    }
}

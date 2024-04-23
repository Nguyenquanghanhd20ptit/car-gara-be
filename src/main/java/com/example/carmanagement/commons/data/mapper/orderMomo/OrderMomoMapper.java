package com.example.carmanagement.commons.data.mapper.orderMomo;

import com.example.carmanagement.commons.data.dto.momo.InformationOrderMomo;
import com.example.carmanagement.commons.data.dto.momo.ItemMomo;
import com.example.carmanagement.commons.data.dto.momo.UserInfoMomo;
import com.example.carmanagement.commons.data.entity.AccessoryOrderEntity;
import com.example.carmanagement.commons.data.entity.CustomerEntity;
import com.example.carmanagement.commons.data.entity.OrderEntity;
import com.example.carmanagement.commons.data.entity.ServiceOrderEntity;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public class OrderMomoMapper {

    public InformationOrderMomo toOrderMomo(OrderEntity entity,String redirectUrl) {
        return new InformationOrderMomo()
                .setOrderId(entity.getId().toString())
                .setUserInfo(toUserInfoMomo(entity.getCustomer()))
                .setRedirectUrl(redirectUrl)
                .setItems(toItemMomo(entity.getAccessoryOrder(), entity.getServiceOrder()));
    }

    private UserInfoMomo toUserInfoMomo(CustomerEntity entity) {
        if (entity == null) return null;
        return new UserInfoMomo()
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .setPhoneNumber(entity.getPhoneNumber());
    }

    private List<ItemMomo> toItemMomo(List<AccessoryOrderEntity> accessories,
                                      List<ServiceOrderEntity> services) {
        List<ItemMomo> combinedMomoList = new ArrayList<>();

        Optional.of(accessories).orElse(new ArrayList<>())
                .stream().map(accessory -> {
                    ItemMomo itemMomo = new ItemMomo();
                    itemMomo.setId(accessory.getAccessory().getId().toString())
                            .setName(accessory.getAccessory().getName())
                            .setCategory(accessory.getAccessory().getDescription())
                            .setImageUrl(accessory.getAccessory().getImageUrl())
                            .setPrice(accessory.getAccessory().getPrice().longValue())
                            .setCurrency(accessory.getAccessory().getCurrency())
                            .setUnit("Thiết bị")
                            .setQuantity(accessory.getQuantity())
                            .setTotalPrice(accessory.getTotalPrice().longValue());
                    return itemMomo;
                }).forEach(combinedMomoList::add);

        Optional.of(services).orElse(new ArrayList<>())
                .stream().map(service -> {
                    ItemMomo itemMomo = new ItemMomo();
                    itemMomo.setId(service.getService().getId().toString())
                            .setName(service.getService().getName())
                            .setCategory(service.getService().getType())
                            .setImageUrl(service.getService().getImageUrl())
                            .setPrice(service.getService().getPrice().longValue())
                            .setCurrency(service.getService().getCurrency())
                            .setQuantity(1)
                            .setUnit("Dịch vụ")
                            .setTotalPrice(service.getTotalPrice().longValue());
                    return itemMomo;
                }).forEach(combinedMomoList::add);
        return combinedMomoList;
    }
}

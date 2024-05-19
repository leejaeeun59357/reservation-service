package org.smarthammer.reservation.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.smarthammer.reservation.Exception.CustomException;
import org.smarthammer.reservation.Exception.ErrorCode;
import org.smarthammer.reservation.domain.Form.user.StoreListLookupForm;
import org.smarthammer.reservation.domain.dto.StoreListDto;
import org.smarthammer.reservation.domain.model.Store;
import org.smarthammer.reservation.service.StoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreListApplication {
    private final StoreService storeService;


    /**
     * 상점 이름을 이용하여 상점 검색
     * @param storeName 상점 이름
     * @return StoreListDto
     */
    public StoreListDto storeDetails(String storeName) {

        // 상점 이름으로 조회했을 때 존재하지 않는다면 에러
        Store store = storeService.findStore(storeName)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_STORE));

        return StoreListDto.entityToDto(store);
    }


    /**
     * 현재 위치를 입력받아
     * 현재 위치와 가까운 store 순으로 정렬
     *
     * @param storeListLookupForm
     * @return List<StoreListDto>
     */
    @Transactional
    public List<StoreListDto> findStoreList(StoreListLookupForm storeListLookupForm) {

        List<Store> stores = storeService.allStoreList();
        List<StoreListDto> list = new ArrayList<>();

        for (Store item : stores) {

            double distanceVal = distance(
                    storeListLookupForm.getLat()
                    , storeListLookupForm.getLng()
                    , Double.parseDouble(item.getLat())
                    , Double.parseDouble(item.getLng())
            );

            StoreListDto storeListDto = StoreListDto.entityToListDto(item, distanceVal);

            list.add(storeListDto);
        }

        // 거리 순으로 리스트 정렬
        list = list.stream().sorted(Comparator.comparing(StoreListDto::getDistance))
                .collect(Collectors.toList());


        return list;
    }


    /**
     * 두 지점간의 거리 계산
     *
     * @param lat1 지점 1 위도
     * @param lng1 지점 1 경도
     * @param lat2 지점 2 위도
     * @param lng2 지점 2 경도
     * @return
     */
    private double distance(double lat1, double lng1, double lat2, double lng2) {
        double theta = lng1 - lng2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344; // 킬로미터로 계산

        return dist;
    }

    /**
     * decimal -> radians
     *
     * @param deg
     * @return double
     */
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /**
     * radians -> decimal
     *
     * @param rad
     * @return double
     */
    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}

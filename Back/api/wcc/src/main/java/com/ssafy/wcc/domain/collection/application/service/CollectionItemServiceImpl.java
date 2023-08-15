package com.ssafy.wcc.domain.collection.application.service;

import com.ssafy.wcc.common.exception.Error;
import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.domain.collection.application.dto.response.CollectionResponse;
import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import com.ssafy.wcc.domain.collection.db.repository.CollectionItemRepository;
import com.ssafy.wcc.domain.member.application.dto.request.MemberItemRequest;
import com.ssafy.wcc.domain.member.application.mapper.MemberItemMapper;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import com.ssafy.wcc.domain.member.db.repository.MemberItemRepository;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import com.ssafy.wcc.domain.notice.application.service.NoticeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectionItemServiceImpl implements CollectionItemService {

    Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    final private CollectionItemRepository collectionItemRepository;
    final private MemberItemRepository memberItemRepository;
    final private MemberItemMapper memberItemMapper;
    final private MemberRepository memberRepository;

    @Override
    public List<CollectionResponse> getCollectionList(Long id) throws WCCException {
        List<CollectionItem> collectionItemList = collectionItemRepository.findAll();
        List<CollectionResponse> responses = new ArrayList<>();

        for (int i = 0; i < collectionItemList.size(); ++i) {
            CollectionItem collectionItem = collectionItemList.get(i);
            if (collectionItem.getId() == 6) continue; // 관리자 프로필은 건너띄기

            // 착용, 구매 여부 확인
            boolean wear = false;
            boolean buy = false;
            for (int j = 0; j < collectionItem.getMemberItems().size(); j++) {
                if (collectionItem.getMemberItems().get(j).getMember().getId() == id) {
                    if (collectionItem.getMemberItems().get(j).isWear()) wear = true;
                    if (collectionItem.getMemberItems().get(j).isBuy()) buy = true;
                }
            }

            // 해당 아이템의 이미지 url 가져오기
            StringBuilder url = new StringBuilder();
            url.append(collectionItem.getUrl());

            CollectionResponse response = CollectionResponse.builder()
                    .id(collectionItem.getId())
                    .type(collectionItem.getType())
                    .name(collectionItem.getName())
                    .price(collectionItem.getPrice())
                    .description(collectionItem.getDescription())
                    .wear(wear)
                    .buy(buy)
                    .url(url.toString())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    @Override
    public boolean buy(Long memberId, int collectionId) {
        MemberItemRequest memberItemRequest = MemberItemRequest.builder().buy(true).wear(false).memberId(memberId).collectionId(collectionId).build();

        MemberItem memberItem = memberItemMapper.memberItemRequestToMemberItem(memberItemRequest);
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isPresent()) {
            int myMoney = member.get().getMoney();
            int itemPrice = this.getPrice(collectionId);
            if (myMoney >= itemPrice) {
                member.get().setMoney(myMoney - itemPrice);
                memberRepository.save(member.get());
                memberItemRepository.save(memberItem);
                return true;
            } else {
                throw new WCCException(Error.NOT_ENOUGH_MONEY);
            }
        }
        throw new RuntimeException("구매 실패");
    }

    @Override
    public boolean wear(Long memberId, int collectionId) {
        Optional<MemberItem> memberItem = memberItemRepository.findByMemberIdAndCollectionId(memberId, (long) collectionId);
        if (memberItem.isPresent()) {

            if(!memberItem.get().isBuy()) throw new WCCException(Error.ITEM_NOT_PURCHASED);

            this.takeOffItem(this.getType(collectionId), memberId);

            memberItem.get().setWear(true);
            memberItemRepository.save(memberItem.get());

            return true;
        }

        throw new RuntimeException("멤버아이템 없음");
    }


    @Override
    public int getPrice(int collectionId) {
        Optional<CollectionItem> collectionItem = collectionItemRepository.findById((long) collectionId);
        if (collectionItem.isPresent()) {
            return collectionItem.get().getPrice();
        }
        throw new RuntimeException("가격정보를 불러올 수 없습니다.");
    }

    @Override
    public int getType(int collectionId) {
        Optional<CollectionItem> nowCollectionItem = collectionItemRepository.findById((long) collectionId);
        if (nowCollectionItem.isPresent()) {
            return nowCollectionItem.get().getType();
        }
        throw new WCCException(Error.COLLECTION_NOT_FOUND);
    }

    @Override
    public void takeOffItem(int type, Long memberId){
        List<CollectionItem> itemList = collectionItemRepository.findAllByType(type);
        for (int i = 0; i < itemList.size(); i++) {
            Long itemId = itemList.get(i).getId();
            Optional<MemberItem> m = memberItemRepository.findByMemberIdAndCollectionId(memberId, itemId);
            if (m.isPresent()) {
                m.get().setWear(false);
            }
            memberItemRepository.save(m.get());
        }
    }
}

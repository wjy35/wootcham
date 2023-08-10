package com.ssafy.wcc.domain.collection.application.service;

import com.ssafy.wcc.common.exception.Error;
import com.ssafy.wcc.common.exception.WCCException;
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
    public List<CollectionItem> getCollectionList(Long id) {
        logger.info("getCollectionList service 진입");
        List<CollectionItem> collectionItemList = collectionItemRepository.findAll();
        return collectionItemList;
    }

    @Override
    public boolean buy(Long memberId, int collectionId) {
        logger.info("buy service 진입");
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
        logger.info("wear service 진입");
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
        logger.info("getPrice service 진입");
        Optional<CollectionItem> collectionItem = collectionItemRepository.findById((long) collectionId);
        if (collectionItem.isPresent()) {
            return collectionItem.get().getPrice();
        }
        throw new RuntimeException("가격정보를 불러올 수 없습니다.");
    }

    @Override
    public int getType(int collectionId) {
        logger.info("getType service 진입");
        Optional<CollectionItem> nowCollectionItem = collectionItemRepository.findById((long) collectionId);
        if (nowCollectionItem.isPresent()) {
            return nowCollectionItem.get().getType();
        }
        throw new WCCException(Error.COLLECTION_NOT_FOUND);
    }

    @Override
    public void takeOffItem(int type, Long memberId){
        logger.info("takeOffItem service 진입");
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

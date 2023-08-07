package com.ssafy.wcc.domain.collection.application.service;

import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import com.ssafy.wcc.domain.collection.db.repository.CollectionItemRepository;
import com.ssafy.wcc.domain.member.application.dto.request.MemberItemRequest;
import com.ssafy.wcc.domain.member.application.mapper.MemberItemMapper;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import com.ssafy.wcc.domain.member.db.repository.MemberItemRepository;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.ssafy.wcc.domain.member.db.entity.QMember.member;

@Service
@RequiredArgsConstructor
public class CollectionItemServiceImpl implements CollectionItemService{

    final private CollectionItemRepository collectionItemRepository;
    final private MemberItemRepository memberItemRepository;
    final private MemberItemMapper memberItemMapper;
    final private MemberRepository memberRepository;

    @Override
    public List<CollectionItem> getList(Long id) {
        List<CollectionItem> collectionItemList = collectionItemRepository.findAll();
        return collectionItemList;
    }

    @Override
    public boolean buy(Long memberId, int collectionId) {

        MemberItemRequest memberItemRequest = MemberItemRequest.builder().buy(true).wear(false).memberId(memberId).collectionId(collectionId).build();

        MemberItem memberItem = memberItemMapper.memberItemRequestToMemberItem(memberItemRequest);
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()){
            int myMoney = member.get().getMoney();
            int itemPrice = this.getPrice(collectionId);
            if(myMoney >= itemPrice){
                member.get().setMoney(myMoney-itemPrice);
                memberRepository.save(member.get());
                memberItemRepository.save(memberItem);
                return true;
            }else{
                throw new RuntimeException("돈이 없어요");
            }
        }
        throw new RuntimeException("구매 실패");
    }

    @Override
    public boolean wear(Long memberId, int collectionId) {
        Optional<MemberItem> memberItem = memberItemRepository.findByMemberIdAndCollectionId(memberId, (long)collectionId);
        if(memberItem.isPresent()){
            Optional<CollectionItem> nowCollectionItem = collectionItemRepository.findById((long)collectionId);
            if(nowCollectionItem.isPresent()){
                int type = nowCollectionItem.get().getType();
                List<CollectionItem> itemList = collectionItemRepository.findAllByType(type);
                for(int i=0; i<itemList.size(); i++){
                    Long itemId = itemList.get(i).getId();
                    Optional<MemberItem> m = memberItemRepository.findByMemberIdAndCollectionId(memberId, itemId);
                    if(m.isPresent()){
                        m.get().setWear(false);
                    }
                    memberItemRepository.save(m.get());
                }
            }

            memberItem.get().setWear(true);
            memberItemRepository.save(memberItem.get());

            return true;
        }
        throw new RuntimeException();
    }

    @Override
    public int getPrice(int collectionId) {
        Optional<CollectionItem> collectionItem = collectionItemRepository.findById((long) collectionId);
        if(collectionItem.isPresent()){
            return collectionItem.get().getPrice();
        }
        throw new RuntimeException("가격정보를 불러올 수 없습니다.");
    }
}

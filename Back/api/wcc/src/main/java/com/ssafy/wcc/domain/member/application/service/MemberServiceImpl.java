package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.common.exception.Error;
import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import com.ssafy.wcc.domain.collection.db.repository.CollectionItemRepository;
import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.request.MemberloginRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.member.application.mapper.MemberMapper;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import com.ssafy.wcc.domain.member.db.entity.MemberItemPK;
import com.ssafy.wcc.domain.member.db.repository.MemberItemRepository;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    private final CollectionItemRepository collectionItemRepository;

    private final MemberItemRepository memberItemRepository;

    private final EmailService emailService;

    @Override
    public void memberSignUp(MemberRequest signupInfo) throws WCCException {
        // 비밀번호에 암호 적용
        Member member = memberMapper.memberRequestToMember(signupInfo);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String securePassword = encoder.encode(member.getPassword());

        member.setPassword(securePassword);
        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        List<CollectionItem> collectionItemList = collectionItemRepository.findAll();

        for (int i = 0; i < collectionItemList.size(); i++) {
            MemberItemPK memberItemPK = MemberItemPK.builder().memberId(findMember.get().getId()).collectionId(collectionItemList.get(i).getId()).build();
            Optional<CollectionItem> collectionItem = collectionItemRepository.findById(memberItemPK.getCollectionId());
            MemberItem memberItem;
            if(collectionItem.get().getId() == 1) { // 기본 프로필 설정
                memberItem = MemberItem.builder().buy(true).wear(true).memberItemPK(memberItemPK).member(findMember.get()).collection(collectionItem.get()).build();
            } else if(collectionItem.get().getId() == 6) { // 관리자 프로필은 건너띄기
                continue;
            }else {
                memberItem = MemberItem.builder().buy(false).wear(false).memberItemPK(memberItemPK).member(findMember.get()).collection(collectionItem.get()).build();
            }
            memberItemRepository.save(memberItem);
        }
    }

    @Override
    public Member memberLogin(MemberloginRequest loginInfo) throws WCCException {
        Optional<Member> findMember = memberRepository.findByEmail(loginInfo.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!findMember.isPresent()) {
            throw new WCCException(Error.USER_NOT_FOUND);
        }
        if (!encoder.matches(loginInfo.getPassword(), findMember.get().getPassword())) {
            throw new WCCException(Error.PASSWORD_NOT_MATCH);
        }
        if (findMember.get().getSuspensionDay() != null) {
            this.checkSuspendedMember(findMember);
        }

        this.updateCurrentLogin(findMember);

        return findMember.get();
    }

    @Override
    public LocalDate getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String today = sdf.format(c.getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(today, formatter);
        return date;
    }

    @Override
    public void updateCurrentLogin(Optional<Member> findMember){
        LocalDate currentTime = this.getCurrentTime();
        Member member = findMember.get();
        member.setCurrentLogin(currentTime);
        memberRepository.save(member);
    }

    @Override
    public void checkSuspendedMember(Optional<Member> findMember){
        LocalDate date1 = findMember.get().getSuspensionDay();
        LocalDate date2 = LocalDate.now();
        if (date1.compareTo(date2) >= 0) {
            throw new WCCException(Error.SUSPENDED_USER);
        }
    }

    @Override
    public void setTmpPassword(String email) {
        logger.info("임시 비밀번호를 발급하는 email 주소: {}", email);
        // 이메일 조회
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new WCCException(Error.USER_NOT_FOUND);
        }

        // 임시 비밀번호 생성 및 메일 전송
        String code;
        try {
            code = emailService.sendMessage(email, 2);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new WCCException(Error.EMAIL_SEND_FAILURE);
        }

        // DB에 임시 비밀번호 저장
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String securePassword = encoder.encode(code);

        findMember.get().setPassword(securePassword);
        memberRepository.save(findMember.get());
    }

    @Override
    public void confirmPassword(Long id, String password) {
        Optional<Member> member = memberRepository.findById(id);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (member.isEmpty()) {
            throw new WCCException(Error.USER_NOT_FOUND);
        }
        if (!encoder.matches(password, member.get().getPassword())) {
            throw new WCCException(Error.PASSWORD_NOT_MATCH);
        }
    }

    @Override
    public void memberDelete(Long id) throws WCCException {
        memberRepository.deleteById(id);
    }

    @Override
    public void memberUpdate(MemberRequest memberRequest, Long id) throws WCCException {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (memberRequest.getPassword() != null) { // 비밀번호 변경을 요청하는 경우
                String securePassword = encoder.encode(memberRequest.getPassword());
                member.get().setPassword(securePassword);
            }
            if (memberRequest.getNickname() != null) { // 닉네임 변경을 요청하는 경우
                member.get().setNickname(memberRequest.getNickname());
            }
            memberRepository.save(member.get());
        } else {
            throw new WCCException(Error.USER_NOT_FOUND);
        }
    }

    @Override
    public void checkEmail(String email) {
        if(memberRepository.existsByEmail(email)){
            throw new WCCException(Error.DUPLICATE_EMAIL);
        }
    }


    @Override
    public MemberInfoResponse memberInfoResponse(Long id) {
        Optional<Member> findMember = memberRepository.findById(id);
        if (findMember.isPresent()) {
            // 사용 중인 아이템 가져오기
            String url = collectionItemRepository.getCurrentItemImage(findMember.get().getId(), 1).toString();

            MemberInfoResponse memberInfoResponse = MemberInfoResponse.builder()
                    .email(findMember.get().getEmail())
                    .nickname(findMember.get().getNickname())
                    .point(findMember.get().getPoint())
                    .money(findMember.get().getMoney())
                    .profileImg(url)
                    .build();

            return memberInfoResponse;
        }
        throw new WCCException(Error.USER_NOT_FOUND);
    }

    @Override
    public boolean checkNickname(String nickname) {
        long count = memberRepository.countByNickname(nickname);
        if (count == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String getMemberNickname(Long id) {
        Optional<String> nickname = memberRepository.findNicknameById(id);
        if (nickname.isPresent()) {
            return nickname.get();
        }
        return null;
    }

}
